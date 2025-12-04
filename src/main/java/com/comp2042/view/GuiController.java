package com.comp2042.view;

import com.comp2042.data.DownData;
import com.comp2042.data.ChinaStageDescriptionProvider;
import com.comp2042.data.ViewData;
import com.comp2042.event.EventSource;
import com.comp2042.event.EventType;
import com.comp2042.event.InputEventListener;
import com.comp2042.event.MoveEvent;
import com.comp2042.model.Board;
import com.comp2042.util.GameConstants;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;


import java.net.URL;
import java.util.ResourceBundle;

public class GuiController implements Initializable {
    @FXML
    private Pane rootPane;

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
    private static final int POINTS_PER_BOMB = 1000;

    private Pane bombTargetOverlay;
    private boolean isDraggingBomb = false;

    private GameLoop gameLoop;

    private BoardRenderer boardRenderer;
    private NextBricksRenderer nextBricksRenderer;
    private GameLayoutManager layoutManager;
    private BoardVibrationEffect vibrationEffect;

    private boolean timeAttackEnabled = false;
    private int timeAttackMinutes = 0;
    private int timeAttackDurationSeconds = 0;
    private int remainingSeconds = 0;
    private Timeline timeAttackTimeline;
    private IntegerProperty boundScoreProperty;

    private static int bestScore1Min = 0;
    private static int bestScore3Min = 0;
    private static int bestScore5Min = 0;

    private final java.util.List<ChinaStageDescriptionProvider.ChinaStage> chinaStages =
            ChinaStageDescriptionProvider.getStages();
    private boolean chinaExploreMode = false;
    private int currentChinaStageIndex = 0;
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

        if (gamePanel != null) {
            gamePanel.setFocusTraversable(true);
            gamePanel.requestFocus();
            gamePanel.setOnKeyPressed(this::handleKeyPressed);
        }

        if (gameOverPanel != null) {
            gameOverPanel.setVisible(false);
        }

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

        setupBombDragAndDrop();
        updateBombCountLabel();

        setupEndOverlay();

    }

    private void setupBombDragAndDrop() {
        if (bombToolbar == null) {
            return;
        }

        bombToolbar.setOnMousePressed(this::handleBombMousePressed);
        bombToolbar.setOnMouseDragged(this::handleBombMouseDragged);
        bombToolbar.setOnMouseReleased(this::handleBombMouseReleased);

        bombCount.addListener((obs, oldVal, newVal) -> updateBombCountLabel());
    }

    private void setupEndOverlay() {
        if (endOverlay == null) {
            return;
        }

        endOverlay.setVisible(false);
        endOverlay.setManaged(false);

        if (rootPane != null) {
            endOverlay.prefWidthProperty().bind(rootPane.widthProperty());
            endOverlay.prefHeightProperty().bind(rootPane.heightProperty());
        }

        if (endBackgroundVideo != null) {
            BackgroundVideoManager.attach(endBackgroundVideo, endOverlay);
        }
    }

    private void handleBombMousePressed(MouseEvent event) {
        if (isGameOver.get() || bombCount.get() <= 0) {
            return;
        }

        isDraggingBomb = true;

        if (gameLoop != null && !isPause.get()) {
            gameLoop.pause();
        }
        if (timeAttackEnabled && timeAttackTimeline != null && !isPause.get()) {
            timeAttackTimeline.pause();
        }

        createBombTargetOverlay();

        if (bombToolbar != null) {
            bombToolbar.setOpacity(0.5);
        }
    }

    private void handleBombMouseDragged(MouseEvent event) {
        if (!isDraggingBomb || bombTargetOverlay == null) {
            return;
        }

        double sceneX = event.getSceneX();
        double sceneY = event.getSceneY();

        int[] gridPos = screenToGrid(sceneX, sceneY);
        if (gridPos != null) {
            updateTargetHighlight(gridPos[0], gridPos[1]);
        } else {
            clearTargetHighlight();
        }
    }

    private void handleBombMouseReleased(MouseEvent event) {
        if (!isDraggingBomb) {
            return;
        }

        isDraggingBomb = false;

        if (bombToolbar != null) {
            bombToolbar.setOpacity(bombCount.get() > 0 ? 1.0 : 0.4);
        }

        double sceneX = event.getSceneX();
        double sceneY = event.getSceneY();

        int[] gridPos = screenToGrid(sceneX, sceneY);
        if (gridPos != null && bombCount.get() > 0) {
            placeBombAt(gridPos[0], gridPos[1]);
        }

        removeBombTargetOverlay();

        if (gameLoop != null && !isPause.get()) {
            gameLoop.start();
        }
        if (timeAttackEnabled && timeAttackTimeline != null && !isPause.get()) {
            timeAttackTimeline.play();
        }

        gamePanel.requestFocus();
    }

    private void createBombTargetOverlay() {
        if (bombTargetOverlay != null) {
            removeBombTargetOverlay();
        }

        bombTargetOverlay = new Pane();
        bombTargetOverlay.setMouseTransparent(true);
        bombTargetOverlay.setPrefSize(GameConstants.gridContentWidth(), GameConstants.gridContentHeight());

        if (gamePanel != null) {
            double offsetX = GameConstants.gridCenterOffsetX();
            double offsetY = GameConstants.gridCenterOffsetY();
            bombTargetOverlay.setLayoutX(gamePanel.getLayoutX() + offsetX);
            bombTargetOverlay.setLayoutY(gamePanel.getLayoutY() + offsetY);
        }

        if (rootPane != null) {
            rootPane.getChildren().add(bombTargetOverlay);
        }
    }

    private void removeBombTargetOverlay() {
        if (bombTargetOverlay != null && rootPane != null) {
            rootPane.getChildren().remove(bombTargetOverlay);
            bombTargetOverlay = null;
        }
    }

    private void updateTargetHighlight(int gridX, int gridY) {
        if (bombTargetOverlay == null) {
            return;
        }

        bombTargetOverlay.getChildren().clear();

        double step = GameConstants.brickStep();

        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                int cellX = gridX + dx;
                int cellY = gridY + dy;

                if (cellX >= 0 && cellX < GameConstants.BOARD_WIDTH &&
                        cellY >= 0 && cellY < GameConstants.visibleRows()) {

                    Rectangle highlight = new Rectangle(
                            cellX * step,
                            cellY * step,
                            GameConstants.BRICK_SIZE,
                            GameConstants.BRICK_SIZE
                    );
                    highlight.setFill(Color.rgb(255, 100, 50, 0.4));
                    highlight.setStroke(Color.rgb(255, 140, 0, 0.8));
                    highlight.setStrokeWidth(2);
                    highlight.setArcWidth(GameConstants.BRICK_ARC_SIZE);
                    highlight.setArcHeight(GameConstants.BRICK_ARC_SIZE);
                    bombTargetOverlay.getChildren().add(highlight);
                }
            }
        }
    }

    private void clearTargetHighlight() {
        if (bombTargetOverlay != null) {
            bombTargetOverlay.getChildren().clear();
        }
    }

    private int[] screenToGrid(double sceneX, double sceneY) {
        if (gamePanel == null) {
            return null;
        }

        javafx.geometry.Point2D localPoint = gamePanel.sceneToLocal(sceneX, sceneY);

        double offsetX = GameConstants.gridCenterOffsetX();
        double offsetY = GameConstants.gridCenterOffsetY();
        double adjustedX = localPoint.getX() - offsetX;
        double adjustedY = localPoint.getY() - offsetY;

        double step = GameConstants.brickStep();
        int gridX = (int) (adjustedX / step);
        int gridY = (int) (adjustedY / step);

        if (gridX >= 0 && gridX < GameConstants.BOARD_WIDTH &&
                gridY >= 0 && gridY < GameConstants.visibleRows()) {
            return new int[]{gridX, gridY};
        }

        return null;
    }

    private void placeBombAt(int gridX, int gridY) {
        if (board == null || bombCount.get() <= 0) {
            return;
        }

        int actualY = gridY + GameConstants.HIDDEN_BUFFER_ROWS;

        board.explodeBomb(gridX, actualY);
        boardRenderer.refreshBackground(board.getBoardMatrix());
        vibrationEffect.vibrate();

        bombCount.set(bombCount.get() - 1);
        updateBombCountLabel();
    }

    private void updateBombCountLabel() {
        if (bombCountLabel != null) {
            bombCountLabel.setText(String.valueOf(bombCount.get()));
            bombCountLabel.setVisible(bombCount.get() > 0);
        }

        if (bombToolbar != null) {
            bombToolbar.setOpacity(bombCount.get() > 0 ? 1.0 : 0.4);
        }
    }

    private void checkBombMilestone(int currentScore) {
        int milestonesReached = currentScore / POINTS_PER_BOMB;
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

        ChangeListener<int[][]> boardMatrixListener = (obs, oldVal, newVal) -> {
            if (newVal != null) {
                boardRenderer.refreshBackground(newVal);
            }
        };

        ChangeListener<Boolean> gameOverListener = (obs, oldVal, newVal) -> {
            if (newVal != null && newVal) {
                handleGameEnd("Game Over", "The bricks reached the ceiling.");
            } else if (gameOverPanel != null) {
                gameOverPanel.setVisible(false);
                isGameOver.set(false);
                endScreenShown = false;
                if (endOverlay != null) {
                    endOverlay.setVisible(false);
                    endOverlay.setManaged(false);
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
        this.boundScoreProperty = integerProperty;

        if (scoreValue != null) {
            scoreValue.textProperty().bind(integerProperty.asString());
        }

        integerProperty.addListener((obs, oldVal, newVal) -> {
            checkBombMilestone(newVal.intValue());
            updateChinaStageForScore(newVal.intValue());
        });
    }

    public void initGameView(int[][] boardMatrix, ViewData viewData) {
        boardRenderer.initialiseBoard(boardMatrix, viewData);
        nextBricksRenderer.renderNextBricks(viewData.getNextBricksData());
        ensureGameLoopInitialised();
        gameLoop.start();

        if (timeAttackEnabled) {
            resetTimeAttackTimer();
            startTimeAttackTimer();
        }

        if (chinaExploreMode) {
            applyChinaStage(currentChinaStageIndex);
        }
    }

    private void handleKeyPressed(KeyEvent keyEvent) {
        if (!isPause.get() && !isGameOver.get()) {
            if (keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.A) {
                refreshBrick(eventListener.onLeftEvent(new MoveEvent(EventType.LEFT, EventSource.USER)));
                keyEvent.consume();
            }
            if (keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() == KeyCode.D) {
                refreshBrick(eventListener.onRightEvent(new MoveEvent(EventType.RIGHT, EventSource.USER)));
                keyEvent.consume();
            }
            if (keyEvent.getCode() == KeyCode.UP || keyEvent.getCode() == KeyCode.W) {
                refreshBrick(eventListener.onRotateEvent(new MoveEvent(EventType.ROTATE, EventSource.USER)));
                keyEvent.consume();
            }
            if (keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.S) {
                moveDown(new MoveEvent(EventType.DOWN, EventSource.USER));
                keyEvent.consume();
            }
            if (keyEvent.getCode() == KeyCode.SPACE) {
                performHardDrop(new MoveEvent(EventType.HARD_DROP, EventSource.USER));
                keyEvent.consume();
            }
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
        if (timeAttackTimeline != null) {
            timeAttackTimeline.stop();
        }
        if (timeAttackEnabled) {
            updateBestScoreIfNeeded();
        }
        if (gameOverPanel != null) {
            gameOverPanel.setVisible(false);
        }

        showEndScreen(title, subtitle);
    }

    private void showEndScreen(String title, String subtitle) {
        if (endOverlay == null) {
            if (gameOverPanel != null) {
                gameOverPanel.setVisible(true);
            }
            return;
        }

        if (endTitle != null && title != null) {
            endTitle.setText(title);
        }
        if (endSubtitle != null && subtitle != null) {
            endSubtitle.setText(subtitle);
        }

        endOverlay.setVisible(true);
        endOverlay.setManaged(true);
        endOverlay.toFront();

        if (endBackgroundVideo != null) {
            BackgroundVideoManager.attach(endBackgroundVideo, endOverlay);
            MediaPlayer player = endBackgroundVideo.getMediaPlayer();
            if (player != null && player.getStatus() != MediaPlayer.Status.PLAYING) {
                player.seek(player.getStartTime());
                player.play();
            }
        }

        double width = GameConstants.initialWindowWidth();
        if (rootPane != null && rootPane.getWidth() > 0) {
            width = rootPane.getWidth();
        }

        endOverlay.setTranslateX(-width);
        TranslateTransition slideIn = new TranslateTransition(Duration.millis(520), endOverlay);
        slideIn.setFromX(-width);
        slideIn.setToX(0);
        slideIn.setInterpolator(Interpolator.EASE_OUT);
        slideIn.play();
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
            if (timeAttackEnabled && timeAttackTimeline != null) {
                timeAttackTimeline.pause();
            }
            pauseButton.setText("Resume");
        } else {
            if (gameLoop != null) {
                gameLoop.start();
            }
            if (timeAttackEnabled && timeAttackTimeline != null) {
                timeAttackTimeline.play();
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

    private void ensureGameLoopInitialised() {
        if (gameLoop == null) {
            gameLoop = new GameLoop(
                    currentTickMillis,
                    () -> moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))
            );
        }
    }

    public void applyBackgroundImage(String resourcePath) {
        if (rootPane == null || resourcePath == null || resourcePath.isBlank()) {
            return;
        }

        var resourceUrl = getClass().getClassLoader().getResource(resourcePath);
        if (resourceUrl == null) {
            System.err.println("Missing background resource: " + resourcePath);
            return;
        }

        String url = resourceUrl.toExternalForm();

        // Apply via JavaFX Background (respects sizing) and inline CSS (overrides .root background)
        Image image = new Image(url, true);
        BackgroundSize size = new BackgroundSize(
                1.0, 1.0, true, true, false, true
        );
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                size
        );
        rootPane.setBackground(new Background(backgroundImage));
        rootPane.setStyle(
                "-fx-background-image: url('" + url + "');"
                        + "-fx-background-size: cover;"
                        + "-fx-background-repeat: no-repeat;"
                        + "-fx-background-position: center center;"
                        + "-fx-background-color: transparent;"
        );
    }

    public void showModeLabel(String text) {
    }

    public void configureExploreChinaMode() {
        chinaExploreMode = true;
        currentChinaStageIndex = 0;

        // disable time-attack UI
        configureTimeAttack(0);
        if (timerBox != null) {
            timerBox.setVisible(false);
            timerBox.setManaged(false);
        }
        if (bestScoreBox != null) {
            bestScoreBox.setVisible(false);
            bestScoreBox.setManaged(false);
        }

        applyChinaStage(currentChinaStageIndex);
    }

    public void configureTimeAttack(int minutes) {
        timeAttackEnabled = minutes > 0;
        timeAttackMinutes = minutes;
        timeAttackDurationSeconds = minutes * 60;
        remainingSeconds = timeAttackDurationSeconds;

        if (!timeAttackEnabled) {
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

        updateTimerLabel();
        refreshBestScoreDisplay();

        if (gameLoop != null && gameLoop.isRunning()) {
            startTimeAttackTimer();
        }
    }

    private void applyChinaStage(int stageIndex) {
        if (!chinaExploreMode || chinaStages.isEmpty()) {
            return;
        }

        int safeIndex = Math.min(stageIndex, chinaStages.size() - 1);
        currentChinaStageIndex = safeIndex;
        ChinaStageDescriptionProvider.ChinaStage stage = chinaStages.get(safeIndex);

        applyBackgroundImage(stage.getBackgroundResource());

        if (chinaDescriptionBox != null) {
            chinaDescriptionBox.setVisible(true);
            chinaDescriptionBox.setManaged(true);
        }
        if (chinaStateTitle != null) {
            chinaStateTitle.setText(stage.getName());
        }
        if (chinaStateDescription != null) {
            chinaStateDescription.setText(stage.getDescription());
        }

        // speed up slightly per stage
        int newTick = Math.max(
                GameConstants.MIN_GAME_TICK_MS,
                GameConstants.GAME_TICK_MS - (safeIndex * 10)
        );
        updateGameLoopSpeed(newTick);
    }

    private void updateChinaStageForScore(int score) {
        if (!chinaExploreMode || chinaStages.isEmpty()) {
            return;
        }

        int targetIndex = Math.min(
                score / GameConstants.POINTS_PER_CHINA_STAGE,
                chinaStages.size() - 1
        );

        if (targetIndex > currentChinaStageIndex) {
            applyChinaStage(targetIndex);
        }

        checkChinaCompletion(score);
    }

    private void checkChinaCompletion(int score) {
        if (!chinaExploreMode || chinaStages.isEmpty() || endScreenShown) {
            return;
        }

        boolean atFinalStage = currentChinaStageIndex >= chinaStages.size() - 1;
        int completionScore = GameConstants.POINTS_PER_CHINA_STAGE * chinaStages.size();
        if (atFinalStage && score >= completionScore) {
            handleGameEnd("Journey Complete", "You finished every China stage!");
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

    private void resetTimeAttackTimer() {
        remainingSeconds = timeAttackDurationSeconds;
        updateTimerLabel();
    }

    private void startTimeAttackTimer() {
        if (!timeAttackEnabled) {
            return;
        }
        if (timeAttackTimeline != null) {
            timeAttackTimeline.stop();
        }
        timeAttackTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> onTimeAttackTick())
        );
        timeAttackTimeline.setCycleCount(Timeline.INDEFINITE);
        timeAttackTimeline.play();
    }

    private void onTimeAttackTick() {
        if (!timeAttackEnabled || isPause.get() || isGameOver.get()) {
            return;
        }
        remainingSeconds--;
        if (remainingSeconds < 0) {
            remainingSeconds = 0;
        }
        updateTimerLabel();
        if (remainingSeconds == 0) {
            if (!isGameOver.get()) {
                handleGameEnd("Time's Up", "The clock reached zero. Try another run!");
            }
        }
    }

    private void updateTimerLabel() {
        if (timerValue == null) {
            return;
        }
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        timerValue.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void updateBestScoreIfNeeded() {
        if (!timeAttackEnabled || boundScoreProperty == null) {
            return;
        }
        int currentScore = boundScoreProperty.get();
        if (timeAttackMinutes == 1) {
            if (currentScore > bestScore1Min) {
                bestScore1Min = currentScore;
            }
        } else if (timeAttackMinutes == 3) {
            if (currentScore > bestScore3Min) {
                bestScore3Min = currentScore;
            }
        } else if (timeAttackMinutes == 5) {
            if (currentScore > bestScore5Min) {
                bestScore5Min = currentScore;
            }
        }
        refreshBestScoreDisplay();
    }

    private void refreshBestScoreDisplay() {
        if (bestScoreBox == null || bestScoreValue == null || bestScoreTitle == null) {
            return;
        }
        if (!timeAttackEnabled) {
            bestScoreBox.setVisible(false);
            bestScoreBox.setManaged(false);
            return;
        }
        bestScoreBox.setVisible(true);
        bestScoreBox.setManaged(true);

        int bestScore;
        String label;
        if (timeAttackMinutes == 1) {
            bestScore = bestScore1Min;
            label = "BEST 1 MIN";
        } else if (timeAttackMinutes == 3) {
            bestScore = bestScore3Min;
            label = "BEST 3 MIN";
        } else if (timeAttackMinutes == 5) {
            bestScore = bestScore5Min;
            label = "BEST 5 MIN";
        } else {
            bestScore = 0;
            label = "BEST SCORE";
        }
        bestScoreTitle.setText(label);
        bestScoreValue.setText(String.valueOf(bestScore));
    }
}
