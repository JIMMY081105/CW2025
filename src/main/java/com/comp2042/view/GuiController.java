package com.comp2042.view;

import com.comp2042.data.DownData;
import com.comp2042.data.ViewData;
import com.comp2042.event.EventSource;
import com.comp2042.event.EventType;
import com.comp2042.event.InputEventListener;
import com.comp2042.event.MoveEvent;
import com.comp2042.model.Board;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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

    private Label modeLabel;

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
                nextBricksContainer,
                nextBricksList,
                groupNotification,
                boardRenderer,
                bombToolbar
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
        
        setupBombDragAndDrop();
        updateBombCountLabel();

        addModeLabel();
    }

    private void addModeLabel() {
        if (rootPane == null) {
            return;
        }
        modeLabel = new Label();
        modeLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        modeLabel.setVisible(false);
        rootPane.getChildren().add(modeLabel);
        modeLabel.setLayoutX(16);
        modeLabel.setLayoutY(10);
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
    
    private void handleBombMousePressed(MouseEvent event) {
        if (isGameOver.get() || bombCount.get() <= 0) {
            return;
        }
        
        isDraggingBomb = true;
        
        if (gameLoop != null && !isPause.get()) {
            gameLoop.pause();
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
                gameOver();
            } else if (gameOverPanel != null) {
                gameOverPanel.setVisible(false);
                isGameOver.set(false);
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

    public void bindScore(IntegerProperty integerProperty) {
        if (scoreValue != null) {
            scoreValue.textProperty().bind(integerProperty.asString());
        }
        
        integerProperty.addListener((obs, oldVal, newVal) -> {
            checkBombMilestone(newVal.intValue());
        });
    }

    public void initGameView(int[][] boardMatrix, ViewData viewData) {
        boardRenderer.initialiseBoard(boardMatrix, viewData);
        nextBricksRenderer.renderNextBricks(viewData.getNextBricksData());
        ensureGameLoopInitialised();
        gameLoop.start();
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

        if (keyEvent.getCode() == KeyCode.N) {
            newGame(null);
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
        if (gameLoop != null) {
            gameLoop.stop();
        }
        if (gameOverPanel != null) {
            gameOverPanel.setVisible(true);
        }
        isGameOver.set(true);
    }

    public void newGame(ActionEvent actionEvent) {
        if (gameLoop != null) {
            gameLoop.stop();
        }

        if (gameOverPanel != null) {
            gameOverPanel.setVisible(false);
        }
        isPause.set(false);
        isGameOver.set(false);
        if (pauseButton != null) {
            pauseButton.setText("Pause");
        }
        
        bombCount.set(0);
        lastBombMilestone = 0;
        updateBombCountLabel();

        ViewData viewData = eventListener.createNewGame();
        boardRenderer.initialiseBoard(board.getBoardMatrix(), viewData);
        nextBricksRenderer.renderNextBricks(viewData.getNextBricksData());
        gamePanel.requestFocus();

        ensureGameLoopInitialised();
        gameLoop.start();
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
            pauseButton.setText("Resume");
        } else {
            if (gameLoop != null) {
                gameLoop.start();
            }
            pauseButton.setText("Pause");
        }

        gamePanel.requestFocus();
    }

    private void ensureGameLoopInitialised() {
        if (gameLoop == null) {
            gameLoop = new GameLoop(
                    GameConstants.GAME_TICK_MS,
                    () -> moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))
            );
        }
    }

    public void applyBackgroundImage(String resourcePath) {
        if (rootPane == null || resourcePath == null || resourcePath.isBlank()) {
            return;
        }

        String style = String.format(
                "-fx-background-image: linear-gradient(rgba(0,0,0,0.2), rgba(0,0,0,0.2)), url('%s');"
                        + "-fx-background-size: cover, cover;"
                        + "-fx-background-position: center center, center center;"
                        + "-fx-background-repeat: no-repeat, no-repeat;",
                resourcePath
        );
        rootPane.setStyle(style);
    }

    public void showModeLabel(String text) {
        if (modeLabel != null) {
            modeLabel.setText(text);
            modeLabel.setVisible(true);
        }
    }
}
