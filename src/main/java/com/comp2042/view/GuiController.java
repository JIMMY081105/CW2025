package com.comp2042.view;

import com.comp2042.data.DownData;
import com.comp2042.data.ViewData;
import com.comp2042.event.EventSource;
import com.comp2042.event.EventType;
import com.comp2042.event.InputEventListener;
import com.comp2042.event.MoveEvent;
import com.comp2042.model.Board;
import com.comp2042.model.brick.BrickFactory;
import com.comp2042.util.GameConstants;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class GuiController implements Initializable {

    @FXML
    private Pane rootPane;

    @FXML
    private Pane gameLayer;

    @FXML
    private BorderPane gameBoard;

    @FXML
    private Pane gridLinesPane;

    @FXML
    private GridPane gamePanel;

    @FXML
    private Group groupNotification;

    @FXML
    private GridPane brickPanel;

    @FXML
    private Pane ghostPane;

    @FXML
    private VBox sidePanel;

    @FXML
    private VBox scoreBox;

    @FXML
    private VBox nextBricksContainer;

    @FXML
    private VBox nextBricksList;

    @FXML
    private GameOverPanel gameOverPanel;

    @FXML
    private Text scoreValue;

    @FXML
    private ToggleButton pauseButton;

    @FXML
    private StackPane bombToolbar;

    @FXML
    private Label bombEmoji;

    @FXML
    private Label bombCountLabel;

    @FXML
    private VBox timerBox;

    @FXML
    private VBox bestScoreBox;

    @FXML
    private Label timerTitle;

    @FXML
    private Label bestScoreTitle;

    @FXML
    private Text timerValue;

    @FXML
    private Text bestScoreValue;

    @FXML
    private VBox chinaDescriptionBox;

    @FXML
    private Text chinaStateTitle;

    @FXML
    private Text chinaStateDescription;

    @FXML
    private StackPane endOverlay;

    @FXML
    private MediaView endBackgroundVideo;

    @FXML
    private Label endTitle;

    @FXML
    private Label endSubtitle;

    private InputEventListener eventListener;
    private Board board;

    private final BooleanProperty isPause = new SimpleBooleanProperty();
    private final BooleanProperty isGameOver = new SimpleBooleanProperty();

    private final IntegerProperty bombCount = new SimpleIntegerProperty(0);
    private int lastBombMilestone = 0;

    private GameLoop gameLoop;

    private BoardRenderer boardRenderer;
    private NextBricksRenderer nextBricksRenderer;
    private GameLayoutManager layoutManager;
    private BoardVibrationEffect vibrationEffect;

    private BombManager bombManager;
    private TimeAttackManager timeAttackManager;
    private ChinaStageManager chinaStageManager;
    private GameInputHandler inputHandler;

    private int currentTickMillis = GameConstants.GAME_TICK_MS;
    private boolean endScreenShown = false;

    private Runnable backToHomeHandler;
    private Runnable restartHandler;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Font.loadFont(
                getClass().getClassLoader().getResource("digital.ttf").toExternalForm(),
                38
        );

        boardRenderer = new BoardRenderer(gamePanel, brickPanel, ghostPane, gridLinesPane);
        nextBricksRenderer = new NextBricksRenderer(nextBricksList);
        layoutManager = new GameLayoutManager(
                rootPane,
                gameBoard,
                gamePanel,
                gridLinesPane,
                sidePanel,
                timerBox,
                nextBricksContainer,
                nextBricksList,
                groupNotification,
                boardRenderer,
                bombToolbar,
                chinaDescriptionBox
        );
        vibrationEffect = new BoardVibrationEffect(gameBoard, scoreBox, nextBricksContainer);

        layoutManager.applyInitialLayout();
        nextBricksRenderer.initialisePanels();

        if (rootPane != null) {
            rootPane.widthProperty().addListener((obs, oldWidth, newWidth) -> {
                layoutManager.positionContent(newWidth.doubleValue());
                if (board != null && board.getViewData() != null) {
                    boardRenderer.refreshBrick(board.getViewData());
                }
            });

            double initialWidth = Math.max(rootPane.getWidth(), GameConstants.initialWindowWidth());
            layoutManager.positionContent(initialWidth);
        }

        if (timerBox != null) {
            timerBox.setVisible(false);
            timerBox.setManaged(false);
        }
        if (bestScoreBox != null) {
            bestScoreBox.setVisible(false);
            bestScoreBox.setManaged(false);
        }
        if (chinaDescriptionBox != null) {
            chinaDescriptionBox.setVisible(false);
            chinaDescriptionBox.setManaged(false);
        }

        timeAttackManager = new TimeAttackManager(
                timerTitle,
                timerValue,
                bestScoreTitle,
                bestScoreValue,
                isPause,
                isGameOver
        );
        timeAttackManager.setOnTimeOver(() ->
                handleGameEnd("Time's Up", "The clock reached zero. Try another run!")
        );

        chinaStageManager = new ChinaStageManager(
                chinaDescriptionBox,
                chinaStateTitle,
                chinaStateDescription,
                layoutManager::applyBackgroundImage,
                this::updateGameLoopSpeed,
                () -> handleGameEnd("Journey Complete", "You finished every China stage!")
        );

        bombManager = new BombManager(
                bombToolbar,
                bombEmoji,
                bombCountLabel,
                gameLayer,
                gamePanel,
                bombCount,
                isGameOver,
                boardRenderer,
                vibrationEffect,
                this::pauseForBombDrag,
                this::resumeAfterBombDrag
        );
        bombManager.initialise();

        layoutManager.setupEndOverlay(endOverlay, endBackgroundVideo, endTitle, endSubtitle);

        inputHandler = new GameInputHandler(
                isPause,
                isGameOver,
                event -> {
                    if (eventListener != null) {
                        refreshBrick(eventListener.onLeftEvent(event));
                    }
                },
                event -> {
                    if (eventListener != null) {
                        refreshBrick(eventListener.onRightEvent(event));
                    }
                },
                event -> {
                    if (eventListener != null) {
                        refreshBrick(eventListener.onRotateEvent(event));
                    }
                },
                this::moveDown,
                this::performHardDrop
        );

        if (gamePanel != null) {
            gamePanel.setFocusTraversable(true);
            gamePanel.requestFocus();
            gamePanel.setOnKeyPressed(inputHandler::handleKeyPressed);
        }

        if (gameOverPanel != null) {
            gameOverPanel.setVisible(false);
        }
    }

    private void pauseForBombDrag() {
        if (gameLoop != null && !isPause.get()) {
            gameLoop.pause();
        }
        if (timeAttackManager != null && timeAttackManager.isEnabled() && !isPause.get()) {
            timeAttackManager.pause();
        }
    }

    private void resumeAfterBombDrag() {
        if (gameLoop != null && !isPause.get()) {
            gameLoop.start();
        }
        if (timeAttackManager != null && timeAttackManager.isEnabled() && !isPause.get()) {
            timeAttackManager.resume();
        }
        if (gamePanel != null) {
            gamePanel.requestFocus();
        }
    }

    private void checkBombMilestone(int currentScore) {
        int milestonesReached = currentScore / GameConstants.POINTS_PER_BOMB;
        int newBombs = milestonesReached - lastBombMilestone;

        if (newBombs > 0) {
            bombCount.set(bombCount.get() + newBombs);
            lastBombMilestone = milestonesReached;
            showBombNotification(newBombs);
        }
    }

    private void showBombNotification(int bombsAwarded) {
        NotificationPanel notificationPanel = new NotificationPanel("+" + bombsAwarded + " 💣");
        groupNotification.getChildren().add(notificationPanel);
        notificationPanel.showScore(groupNotification.getChildren());
    }

    public void bind(Board board) {
        this.board = board;

        if (bombManager != null) {
            bombManager.setBoard(board);
        }

        ChangeListener<int[][]> boardMatrixListener = (obs, oldVal, newVal) -> {
            if (newVal != null) {
                boardRenderer.refreshBackground(newVal);
            }
        };

        ChangeListener<Boolean> gameOverListener = (obs, oldVal, newVal) -> {
            if (newVal != null && newVal) {
                handleGameEnd("Game Over", "The bricks reached the ceiling.");
            } else {
                if (gameOverPanel != null) {
                    gameOverPanel.setVisible(false);
                }
                isGameOver.set(false);
                endScreenShown = false;
                if (layoutManager != null) {
                    layoutManager.hideEndOverlay();
                }
            }
        };

        this.board.boardMatrixProperty().addListener(boardMatrixListener);
        this.board.isGameOverProperty().addListener(gameOverListener);

        if (this.board.getBoardMatrix() != null && this.board.getViewData() != null) {
            initGameView(this.board.getBoardMatrix(), this.board.getViewData());
        }
    }

    public void setEventListener(InputEventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void setNavigationHandlers(Runnable backToHomeHandler, Runnable restartHandler) {
        this.backToHomeHandler = backToHomeHandler;
        this.restartHandler = restartHandler;
    }

    public void bindScore(IntegerProperty integerProperty) {
        if (scoreValue != null) {
            scoreValue.textProperty().bind(integerProperty.asString());
        }

        if (timeAttackManager != null) {
            timeAttackManager.bindScoreProperty(integerProperty);
        }

        integerProperty.addListener((obs, oldVal, newVal) -> {
            int newScore = newVal.intValue();
            checkBombMilestone(newScore);
            if (chinaStageManager != null && chinaStageManager.isEnabled()) {
                chinaStageManager.handleScoreChanged(newScore);
            }
        });
    }

    public void initGameView(int[][] boardMatrix, ViewData viewData) {
        boardRenderer.initialiseBoard(boardMatrix, viewData);
        nextBricksRenderer.renderNextBricks(viewData.getNextBricksData());
        ensureGameLoopInitialised();
        gameLoop.start();

        if (timeAttackManager != null && timeAttackManager.isEnabled()) {
            timeAttackManager.start();
        }
    }

    private void moveDown(MoveEvent event) {
        if (!isPause.get()) {
            DownData downData = eventListener.onDownEvent(event);
            handleDownMovement(downData, false);
        }
        gamePanel.requestFocus();
    }

    private void performHardDrop(MoveEvent event) {
        if (!isPause.get()) {
            DownData downData = eventListener.onHardDropEvent(event);
            handleDownMovement(downData, true);
        }
        gamePanel.requestFocus();
    }

    private void handleDownMovement(DownData downData, boolean vibrate) {
        if (downData == null) {
            return;
        }

        showScoreNotificationIfNeeded(downData);
        refreshBrick(downData.getViewData());

        if (vibrate) {
            vibrationEffect.vibrate();
        }
    }

    private void refreshBrick(ViewData brick) {
        if (!isPause.get()) {
            boardRenderer.refreshBrick(brick);
        }
        nextBricksRenderer.renderNextBricks(brick.getNextBricksData());
    }

    private void showScoreNotificationIfNeeded(DownData downData) {
        if (downData.getClearRow() != null && downData.getClearRow().getLinesRemoved() > 0) {
            showScoreNotification(downData.getClearRow().getScoreBonus());
        }
    }

    private void showScoreNotification(int scoreBonus) {
        NotificationPanel notificationPanel =
                new NotificationPanel("+" + scoreBonus);
        groupNotification.getChildren().add(notificationPanel);
        notificationPanel.showScore(groupNotification.getChildren());
    }

    public void gameOver() {
        handleGameEnd("Game Over", "The bricks reached the ceiling.");
    }

    private void handleGameEnd(String title, String subtitle) {
        if (isGameOver.get() || endScreenShown) {
            return;
        }
        isGameOver.set(true);
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

        if (endOverlay == null) {
            if (gameOverPanel != null) {
                gameOverPanel.setVisible(true);
            }
            return;
        }

        layoutManager.showEndScreen(title, subtitle);
    }

    public void pauseGame(ActionEvent actionEvent) {
        if (isGameOver.get()) {
            return;
        }

        isPause.set(!isPause.get());

        if (isPause.get()) {
            if (gameLoop != null) {
                gameLoop.pause();
            }
            if (timeAttackManager != null) {
                timeAttackManager.pause();
            }
            pauseButton.setText("Resume");
        } else {
            if (gameLoop != null) {
                gameLoop.start();
            }
            if (timeAttackManager != null) {
                timeAttackManager.resume();
            }
            pauseButton.setText("Pause");
        }

        gamePanel.requestFocus();
    }

    @FXML
    private void handleBackToMain(ActionEvent actionEvent) {
        if (backToHomeHandler != null) {
            backToHomeHandler.run();
        }
    }

    @FXML
    private void handleRestartGame(ActionEvent actionEvent) {
        if (restartHandler != null) {
            restartHandler.run();
        }
    }

    @FXML
    private void handleExitGame(ActionEvent actionEvent) {
        if (gameLoop != null) {
            gameLoop.pause();
        }
        if (timeAttackManager != null) {
            timeAttackManager.pause();
        }
        isPause.set(true);
        if (pauseButton != null) {
            pauseButton.setText("Resume");
        }
        if (endOverlay == null) {
            if (gameOverPanel != null) {
                gameOverPanel.setVisible(true);
            }
            return;
        }
        layoutManager.showEndScreen("Exit Game", "Choose what to do next.");
    }

    private void ensureGameLoopInitialised() {
        if (gameLoop == null) {
            gameLoop = new GameLoop(
                    currentTickMillis,
                    () -> moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))
            );
        }
    }

    public void showModeLabel(String text) {
    }

    public void configureExploreChinaMode() {
        if (chinaStageManager == null) {
            return;
        }

        BrickFactory.setPlusEnabled(false);
        BackgroundMusicManager.playExploreChinaMusic();

        configureTimeAttack(0);

        if (timerBox != null) {
            timerBox.setVisible(false);
            timerBox.setManaged(false);
        }
        if (bestScoreBox != null) {
            bestScoreBox.setVisible(false);
            bestScoreBox.setManaged(false);
        }

        chinaStageManager.enableExploreMode();
    }

    public void configureTimeAttack(int minutes) {
        if (timeAttackManager == null) {
            return;
        }

        timeAttackManager.configure(minutes);

        if (minutes <= 0) {
            if (timerBox != null) {
                timerBox.setVisible(false);
                timerBox.setManaged(false);
            }
            if (bestScoreBox != null) {
                bestScoreBox.setVisible(false);
                bestScoreBox.setManaged(false);
            }
            return;
        }

        if (timerBox != null) {
            timerBox.setVisible(true);
            timerBox.setManaged(true);
        }
        if (bestScoreBox != null) {
            bestScoreBox.setVisible(true);
            bestScoreBox.setManaged(true);
        }

        applyTimeAttackBackground(minutes);

        BackgroundMusicManager.playTimeRacingMusic();
        BrickFactory.setPlusEnabled(minutes == 5);

        if (gameLoop != null && gameLoop.isRunning()) {
            timeAttackManager.start();
        }
    }

    private void applyTimeAttackBackground(int minutes) {
        String resourcePath = null;
        if (minutes == 1) {
            resourcePath = "Time stages/1.jpg";
        } else if (minutes == 3) {
            resourcePath = "Time stages/3.jpg";
        } else if (minutes == 5) {
            resourcePath = "Time stages/5.jpg";
        }

        if (resourcePath != null && layoutManager != null) {
            layoutManager.applyBackgroundImage(resourcePath);
        }
    }

    private void updateGameLoopSpeed(int newTickMillis) {
        int clamped = Math.max(GameConstants.MIN_GAME_TICK_MS, newTickMillis);
        if (clamped == currentTickMillis) {
            return;
        }
        currentTickMillis = clamped;

        if (gameLoop == null) {
            return;
        }

        boolean wasRunning = gameLoop.isRunning() && !isPause.get() && !isGameOver.get();
        gameLoop.stop();
        gameLoop = new GameLoop(
                currentTickMillis,
                () -> moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))
        );
        if (wasRunning) {
            gameLoop.start();
        }
    }
}
