package com.comp2042.view.manager;

import com.comp2042.data.DownData;
import com.comp2042.data.ViewData;
import com.comp2042.event.EventSource;
import com.comp2042.event.EventType;
import com.comp2042.event.InputEventListener;
import com.comp2042.event.MoveEvent;
import com.comp2042.model.Board;
import com.comp2042.util.GameConstants;
import com.comp2042.view.GameLoop;
import com.comp2042.view.effect.BoardVibrationEffect;
import com.comp2042.view.render.BoardRenderer;
import com.comp2042.view.render.GameOverPanel;
import com.comp2042.view.render.NextBricksRenderer;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

public final class GameSessionManager {

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

    private int currentTickMillis = GameConstants.GAME_TICK_MS;
    private boolean endScreenShown = false;

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

        this.pauseProperty = pauseProperty;
        this.gameOverProperty = gameOverProperty;
        this.gamePanel = gamePanel;
        this.boardRenderer = boardRenderer;
        this.nextBricksRenderer = nextBricksRenderer;
        this.vibrationEffect = vibrationEffect;
        this.timeAttackManager = timeAttackManager;
        this.layoutManager = layoutManager;
        this.notificationManager = notificationManager;
        this.gameOverPanel = gameOverPanel;
    }

    public void setEventListener(InputEventListener listener) {
        this.eventListener = listener;
    }

    public void bindBoard(Board board) {

        board.boardMatrixProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                boardRenderer.refreshBackground(newVal);
            }
        });

        board.isGameOverProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal) {
                handleGameEnd("Game Over", "The bricks reached the ceiling.");
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

    public void startGame(int[][] boardMatrix, ViewData viewData) {
        boardRenderer.initialiseBoard(boardMatrix, viewData);
        nextBricksRenderer.renderNextBricks(viewData.getNextBricksData());
        ensureGameLoopInitialised();
        gameLoop.start();

        if (timeAttackManager != null && timeAttackManager.isEnabled()) {
            timeAttackManager.start();
        }
    }

    public void onMoveLeft(MoveEvent event) {
        if (pauseProperty.get() || eventListener == null) {
            return;
        }
        ViewData viewData = eventListener.onLeftEvent(event);
        updateActiveBrick(viewData);
        gamePanel.requestFocus();
    }

    public void onMoveRight(MoveEvent event) {
        if (pauseProperty.get() || eventListener == null) {
            return;
        }
        ViewData viewData = eventListener.onRightEvent(event);
        updateActiveBrick(viewData);
        gamePanel.requestFocus();
    }

    public void onRotate(MoveEvent event) {
        if (pauseProperty.get() || eventListener == null) {
            return;
        }
        ViewData viewData = eventListener.onRotateEvent(event);
        updateActiveBrick(viewData);
        gamePanel.requestFocus();
    }

    public void onMoveDown(MoveEvent event) {
        if (pauseProperty.get() || eventListener == null) {
            return;
        }
        DownData downData = eventListener.onDownEvent(event);
        handleDownMovement(downData, false);
        gamePanel.requestFocus();
    }

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

    public void togglePause(ToggleButton pauseButton) {
        if (gameOverProperty.get()) {
            return;
        }

        boolean paused = !pauseProperty.get();
        pauseProperty.set(paused);

        if (paused) {
            if (gameLoop != null) {
                gameLoop.pause();
            }
            if (timeAttackManager != null) {
                timeAttackManager.pause();
            }
            if (pauseButton != null) {
                pauseButton.setText("Resume");
            }
        } else {
            if (gameLoop != null) {
                gameLoop.start();
            }
            if (timeAttackManager != null) {
                timeAttackManager.resume();
            }
            if (pauseButton != null) {
                pauseButton.setText("Pause");
            }
        }

        gamePanel.requestFocus();
    }

    public void exitGame() {
        if (gameLoop != null) {
            gameLoop.pause();
        }
        if (timeAttackManager != null) {
            timeAttackManager.pause();
        }
        pauseProperty.set(true);
        if (layoutManager != null) {
            layoutManager.showEndScreen("Exit Game", "Choose what to do next.");
        }
    }

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

    public void pauseForBombDrag() {
        if (gameLoop != null && !pauseProperty.get()) {
            gameLoop.pause();
        }
        if (timeAttackManager != null && timeAttackManager.isEnabled() && !pauseProperty.get()) {
            timeAttackManager.pause();
        }
    }

    public void resumeAfterBombDrag() {
        if (gameLoop != null && !pauseProperty.get()) {
            gameLoop.start();
        }
        if (timeAttackManager != null && timeAttackManager.isEnabled() && !pauseProperty.get()) {
            timeAttackManager.resume();
        }
        gamePanel.requestFocus();
    }

    public void updateGameLoopSpeed(int newTickMillis) {
        int clamped = Math.max(GameConstants.MIN_GAME_TICK_MS, newTickMillis);
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
}
