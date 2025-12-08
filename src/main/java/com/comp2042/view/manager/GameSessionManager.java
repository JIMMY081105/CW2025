package com.comp2042.view.manager;

import com.comp2042.data.DownData;
import com.comp2042.data.ViewData;
import com.comp2042.event.EventSource;
import com.comp2042.event.EventType;
import com.comp2042.event.InputEventListener;
import com.comp2042.event.MoveEvent;
import com.comp2042.model.Board;
import com.comp2042.util.GameConfig;
import com.comp2042.view.GameLoop;
import com.comp2042.view.effect.BoardVibrationEffect;
import com.comp2042.view.render.BoardRenderer;
import com.comp2042.view.render.GameOverPanel;
import com.comp2042.view.render.NextBricksRenderer;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

import java.util.Objects;

/**
 * The {@code GameSessionManager} class coordinates gameplay flow by wiring the board model, renderers,
 * time-attack timer, bomb handling, notifications, and layout transitions. It forwards input events to
 * the controller, maintains game loop timing, and triggers end overlays when sessions conclude.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/manager/GameSessionManager.java">
 * GameSessionManager.java</a>
 */
public final class GameSessionManager {

    private static final String TITLE_GAME_OVER = "Game Over";
    private static final String MESSAGE_GAME_OVER = "The bricks reached the ceiling.";
    private static final String TITLE_EXIT = "Exit Game";
    private static final String MESSAGE_EXIT = "Choose what to do next.";

    private final BooleanProperty pauseProperty;
    private final BooleanProperty gameOverProperty;

    private final GridPane gamePanel;
    private final BoardRenderer boardRenderer;
    private final NextBricksRenderer nextBricksRenderer;
    private final BoardVibrationEffect vibrationEffect;
    private final TimeAttackManager timeAttackManager;
    private final GameLayoutManager layoutManager;
    private final GameNotificationManager notificationManager;
    private final GameOverPanel gameOverPanel;

    private GameLoop gameLoop;
    private InputEventListener eventListener;

    private int currentTickMillis = GameConfig.GAME_TICK_MS;
    private boolean endScreenShown = false;

    /**
     * Constructs a session manager coordinating renderers, timers, and notifications.
     *
     * @param pauseProperty       pause flag.
     * @param gameOverProperty    game-over flag.
     * @param gamePanel           grid pane receiving focus and input.
     * @param boardRenderer       renderer for the board.
     * @param nextBricksRenderer  renderer for preview bricks.
     * @param vibrationEffect     effect for hard drops or bombs.
     * @param timeAttackManager   time-attack controller (nullable).
     * @param layoutManager       layout coordinator.
     * @param notificationManager notification handler.
     * @param gameOverPanel       panel shown when the game ends.
     */
    public GameSessionManager(BooleanProperty pauseProperty,
                              BooleanProperty gameOverProperty,
                              GridPane gamePanel,
                              BoardRenderer boardRenderer,
                              NextBricksRenderer nextBricksRenderer,
                              BoardVibrationEffect vibrationEffect,
                              TimeAttackManager timeAttackManager,
                              GameLayoutManager layoutManager,
                              GameNotificationManager notificationManager,
                              GameOverPanel gameOverPanel) {

        this.pauseProperty = Objects.requireNonNull(pauseProperty, "pauseProperty must not be null");
        this.gameOverProperty = Objects.requireNonNull(gameOverProperty, "gameOverProperty must not be null");
        this.gamePanel = Objects.requireNonNull(gamePanel, "gamePanel must not be null");
        this.boardRenderer = Objects.requireNonNull(boardRenderer, "boardRenderer must not be null");
        this.nextBricksRenderer = Objects.requireNonNull(nextBricksRenderer, "nextBricksRenderer must not be null");
        this.vibrationEffect = Objects.requireNonNull(vibrationEffect, "vibrationEffect must not be null");
        this.timeAttackManager = timeAttackManager;
        this.layoutManager = layoutManager;
        this.notificationManager = notificationManager;
        this.gameOverPanel = gameOverPanel;
    }

    /**
     * Registers the controller that will process movement events.
     *
     * @param listener input listener.
     */
    public void setEventListener(InputEventListener listener) {
        this.eventListener = listener;
    }

    /**
     * Binds renderers and listeners to the supplied board, starting the game if state is available.
     *
     * @param board active board instance.
     */
    public void bindBoard(Board board) {

        board.boardMatrixProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                boardRenderer.refreshBackground(newVal);
            }
        });

        board.isGameOverProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal) {
                handleGameEnd(TITLE_GAME_OVER, MESSAGE_GAME_OVER);
            } else {
                if (gameOverPanel != null) {
                    gameOverPanel.setVisible(false);
                }
                gameOverProperty.set(false);
                endScreenShown = false;
                if (layoutManager != null) {
                    layoutManager.hideEndOverlay();
                }
            }
        });

        if (board.getBoardMatrix() != null && board.getViewData() != null) {
            startGame(board.getBoardMatrix(), board.getViewData());
        }
    }

    /**
     * Initialises rendering from the provided state and starts the game loop (and time attack, if enabled).
     *
     * @param boardMatrix board state including background.
     * @param viewData    active piece view data.
     */
    public void startGame(int[][] boardMatrix, ViewData viewData) {
        boardRenderer.initialiseBoard(boardMatrix, viewData);
        nextBricksRenderer.renderNextBricks(viewData.getNextBricksData());
        ensureGameLoopInitialised();
        gameLoop.start();

        if (timeAttackManager != null && timeAttackManager.isEnabled()) {
            timeAttackManager.start();
        }
    }

    /**
     * Handles a left movement input and refreshes active brick rendering.
     *
     * @param event move event metadata.
     */
    public void onMoveLeft(MoveEvent event) {
        if (pauseProperty.get() || eventListener == null) {
            return;
        }
        ViewData viewData = eventListener.onLeftEvent(event);
        updateActiveBrick(viewData);
        gamePanel.requestFocus();
    }

    /**
     * Handles a right movement input and refreshes active brick rendering.
     *
     * @param event move event metadata.
     */
    public void onMoveRight(MoveEvent event) {
        if (pauseProperty.get() || eventListener == null) {
            return;
        }
        ViewData viewData = eventListener.onRightEvent(event);
        updateActiveBrick(viewData);
        gamePanel.requestFocus();
    }

    /**
     * Handles a rotation request and refreshes active brick rendering.
     *
     * @param event move event metadata.
     */
    public void onRotate(MoveEvent event) {
        if (pauseProperty.get() || eventListener == null) {
            return;
        }
        ViewData viewData = eventListener.onRotateEvent(event);
        updateActiveBrick(viewData);
        gamePanel.requestFocus();
    }

    /**
     * Handles a soft drop from the user or game loop and updates visuals/notifications.
     *
     * @param event move event metadata.
     */
    public void onMoveDown(MoveEvent event) {
        if (pauseProperty.get() || eventListener == null) {
            return;
        }
        DownData downData = eventListener.onDownEvent(event);
        handleDownMovement(downData, false);
        gamePanel.requestFocus();
    }

    /**
     * Handles a hard drop from the user, applying vibration and updating visuals.
     *
     * @param event move event metadata.
     */
    public void onHardDrop(MoveEvent event) {
        if (pauseProperty.get() || eventListener == null) {
            return;
        }
        DownData downData = eventListener.onHardDropEvent(event);
        handleDownMovement(downData, true);
        gamePanel.requestFocus();
    }

    private void handleDownMovement(DownData downData, boolean vibrate) {
        if (downData == null) {
            return;
        }

        if (notificationManager != null) {
            notificationManager.handleDownMovement(downData);
        }

        ViewData viewData = downData.getViewData();
        updateActiveBrick(viewData);

        if (vibrate) {
            vibrationEffect.vibrate();
        }
    }

    private void updateActiveBrick(ViewData viewData) {
        if (viewData == null) {
            return;
        }

        if (!pauseProperty.get()) {
            boardRenderer.refreshBrick(viewData);
        }
        nextBricksRenderer.renderNextBricks(viewData.getNextBricksData());
    }

    /**
     * Toggles pause state and updates the pause button label accordingly.
     *
     * @param pauseButton button to update.
     */
    public void togglePause(ToggleButton pauseButton) {
        if (gameOverProperty.get()) {
            return;
        }

        boolean paused = !pauseProperty.get();
        pauseProperty.set(paused);

        if (paused) {
            pauseLoopAndTimeAttack();
            if (pauseButton != null) {
                pauseButton.setText("Resume");
            }
        } else {
            resumeLoopAndTimeAttack();
            if (pauseButton != null) {
                pauseButton.setText("Pause");
            }
        }

        gamePanel.requestFocus();
    }

    /**
     * Pauses the session and shows the exit end-screen overlay.
     */
    public void exitGame() {
        pauseLoopAndTimeAttack();
        pauseProperty.set(true);
        if (layoutManager != null) {
            layoutManager.showEndScreen(TITLE_EXIT, MESSAGE_EXIT);
        }
    }

    /**
     * Handles session end triggered by loss or completion by stopping timers and showing the overlay.
     *
     * @param title    title text to display.
     * @param subtitle subtitle text to display.
     */
    public void handleGameEnd(String title, String subtitle) {
        if (gameOverProperty.get() || endScreenShown) {
            return;
        }
        gameOverProperty.set(true);
        endScreenShown = true;

        if (gameLoop != null) {
            gameLoop.stop();
        }
        if (timeAttackManager != null) {
            timeAttackManager.handleGameStopped();
        }
        if (gameOverPanel != null) {
            gameOverPanel.setVisible(false);
        }

        if (layoutManager != null) {
            layoutManager.showEndScreen(title, subtitle);
        }
    }

    /**
     * Temporarily pauses timers while a bomb drag interaction is active.
     */
    public void pauseForBombDrag() {
        if (gameLoop != null && !pauseProperty.get()) {
            gameLoop.pause();
        }
        if (timeAttackManager != null && timeAttackManager.isEnabled() && !pauseProperty.get()) {
            timeAttackManager.pause();
        }
    }

    /**
     * Resumes timers after a bomb drag interaction finishes.
     */
    public void resumeAfterBombDrag() {
        if (gameLoop != null && !pauseProperty.get()) {
            gameLoop.start();
        }
        if (timeAttackManager != null && timeAttackManager.isEnabled() && !pauseProperty.get()) {
            timeAttackManager.resume();
        }
        gamePanel.requestFocus();
    }

    /**
     * Updates the tick duration for the game loop, preserving running state when appropriate.
     *
     * @param newTickMillis new tick duration in milliseconds.
     */
    public void updateGameLoopSpeed(int newTickMillis) {
        int clamped = Math.max(GameConfig.MIN_GAME_TICK_MS, newTickMillis);
        if (clamped == currentTickMillis) {
            return;
        }
        currentTickMillis = clamped;

        if (gameLoop == null) {
            return;
        }

        boolean wasRunning = gameLoop.isRunning()
                && !pauseProperty.get()
                && !gameOverProperty.get();

        gameLoop.stop();
        gameLoop = new GameLoop(
                currentTickMillis,
                () -> onMoveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))
        );
        if (wasRunning) {
            gameLoop.start();
        }
    }

    /**
     * Indicates whether the game loop is currently running.
     *
     * @return {@code true} if running.
     */
    public boolean isRunning() {
        return gameLoop != null && gameLoop.isRunning();
    }

    private void ensureGameLoopInitialised() {
        if (gameLoop == null) {
            gameLoop = new GameLoop(
                    currentTickMillis,
                    () -> onMoveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))
            );
        }
    }

    private void pauseLoopAndTimeAttack() {
        if (gameLoop != null) {
            gameLoop.pause();
        }
        if (timeAttackManager != null) {
            timeAttackManager.pause();
        }
    }

    private void resumeLoopAndTimeAttack() {
        if (gameLoop != null) {
            gameLoop.start();
        }
        if (timeAttackManager != null) {
            timeAttackManager.resume();
        }
    }
}
