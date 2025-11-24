package com.comp2042.view;

import com.comp2042.data.DownData;
import com.comp2042.data.ViewData;
import com.comp2042.event.EventSource;
import com.comp2042.event.EventType;
import com.comp2042.event.InputEventListener;
import com.comp2042.event.MoveEvent;
import com.comp2042.model.Board;
import com.comp2042.util.ColorMapper;
import com.comp2042.util.GameConstants;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GuiController implements Initializable {

    @FXML
    private Pane rootPane;

    @FXML
    private BorderPane gameBoard;

    @FXML
    private GridPane gamePanel;

    @FXML
    private Group groupNotification;

    @FXML
    private GridPane brickPanel;

    @FXML
    private VBox sidePanel;

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

    private Rectangle[][] displayMatrix;
    private Rectangle[][] rectangles;

    private InputEventListener eventListener;
    private Board board;

    private final BooleanProperty isPause = new SimpleBooleanProperty();
    private final BooleanProperty isGameOver = new SimpleBooleanProperty();

    private GameLoop gameLoop;
    private final List<GridPane> nextPreviewGrids = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Font.loadFont(
                getClass().getClassLoader().getResource("digital.ttf").toExternalForm(),
                38
        );

        applyLayoutMetrics();
        initialiseNextPreviewPanels();

        gamePanel.setFocusTraversable(true);
        gamePanel.requestFocus();

        gamePanel.setOnKeyPressed(this::handleKeyPressed);

        gameOverPanel.setVisible(false);
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
        }

        if (keyEvent.getCode() == KeyCode.N) {
            newGame(null);
        }
    }

    public void bind(Board board) {
        this.board = board;

        ChangeListener<int[][]> boardMatrixListener = (obs, oldVal, newVal) -> {
            if (newVal != null) {
                refreshGameBackground(newVal);
            }
        };

        ChangeListener<Boolean> gameOverListener = (obs, oldVal, newVal) -> {
            if (newVal != null && newVal) {
                gameOver();
            } else {
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

    private void applyLayoutMetrics() {
        gamePanel.setHgap(GameConstants.GRID_GAP);
        gamePanel.setVgap(GameConstants.GRID_GAP);
        gamePanel.setPrefWidth(GameConstants.boardPixelWidth());
        gamePanel.setPrefHeight(GameConstants.boardPixelHeight());
        gamePanel.setMinSize(GameConstants.boardPixelWidth(), GameConstants.boardPixelHeight());
        gamePanel.setMaxSize(GameConstants.boardPixelWidth(), GameConstants.boardPixelHeight());

        brickPanel.setHgap(GameConstants.GRID_GAP);
        brickPanel.setVgap(GameConstants.GRID_GAP);

        gameBoard.setPrefWidth(GameConstants.boardAreaWidth());
        gameBoard.setPrefHeight(GameConstants.boardAreaHeight());
        gameBoard.setMinSize(GameConstants.boardAreaWidth(), GameConstants.boardAreaHeight());
        gameBoard.setMaxSize(GameConstants.boardAreaWidth(), GameConstants.boardAreaHeight());

        sidePanel.setSpacing(GameConstants.SIDE_PANEL_SPACING);
        sidePanel.setPrefWidth(GameConstants.SIDE_PANEL_WIDTH);
        sidePanel.setPadding(new Insets(GameConstants.SIDE_PANEL_PADDING));
        if (nextBricksContainer != null) {
            nextBricksContainer.setSpacing(GameConstants.NEXT_PREVIEW_SPACING);
        }
        if (nextBricksList != null) {
            nextBricksList.setSpacing(GameConstants.NEXT_PREVIEW_SPACING);
        }

        groupNotification.setLayoutY(GameConstants.notificationPanelY());

        if (rootPane != null) {
            rootPane.setPrefWidth(GameConstants.initialWindowWidth());
            rootPane.setPrefHeight(GameConstants.initialWindowHeight());
            rootPane.widthProperty().addListener((obs, oldWidth, newWidth) -> positionContent(newWidth.doubleValue()));
        }

        double initialWidth = rootPane != null
                ? Math.max(rootPane.getWidth(), GameConstants.initialWindowWidth())
                : GameConstants.initialWindowWidth();
        positionContent(initialWidth);
    }

    private void positionContent(double availableWidth) {
        double boardLeft = calculateBoardLeft(availableWidth);
        double boardTop = GameConstants.BOARD_TOP_PADDING;

        gamePanel.setLayoutX(boardLeft);
        gamePanel.setLayoutY(boardTop);

        gameBoard.setLayoutX(boardLeft - GameConstants.BOARD_FRAME_THICKNESS);
        gameBoard.setLayoutY(boardTop - GameConstants.BOARD_FRAME_THICKNESS);

        double sidePanelLeft = boardLeft + GameConstants.boardAreaWidth() + GameConstants.PANEL_GAP;
        sidePanel.setLayoutX(sidePanelLeft);
        sidePanel.setLayoutY(boardTop);

        groupNotification.setLayoutX(sidePanelLeft);

        if (board != null && board.getViewData() != null) {
            updateBrickPanelPosition(board.getViewData());
        }
    }

    private double calculateBoardLeft(double availableWidth) {
        double safeWidth = Math.max(availableWidth, GameConstants.initialWindowWidth());
        double boardWidth = GameConstants.boardAreaWidth();

        double centeredBoardLeft = (safeWidth - boardWidth) / 2;
        if (safeWidth < GameConstants.minimumCenteredWindowWidth()) {
            double centeredContent = (safeWidth - GameConstants.contentWidth()) / 2;
            return Math.max(GameConstants.BOARD_LEFT_PADDING, centeredContent);
        }
        return centeredBoardLeft;
    }

    public void initGameView(int[][] boardMatrix, ViewData brick) {
        displayMatrix = new Rectangle[boardMatrix.length][boardMatrix[0].length];
        for (int i = GameConstants.HIDDEN_BUFFER_ROWS; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                Rectangle rectangle = new Rectangle(GameConstants.BRICK_SIZE, GameConstants.BRICK_SIZE);
                rectangle.setFill(Color.TRANSPARENT);
                displayMatrix[i][j] = rectangle;
                gamePanel.add(rectangle, j, i - GameConstants.HIDDEN_BUFFER_ROWS);
            }
        }

        int[][] currentBrickData = brick.getBrickData();
        rectangles = new Rectangle[currentBrickData.length][currentBrickData[0].length];
        for (int i = 0; i < currentBrickData.length; i++) {
            for (int j = 0; j < currentBrickData[i].length; j++) {
                Rectangle rectangle = new Rectangle(GameConstants.BRICK_SIZE, GameConstants.BRICK_SIZE);
                rectangle.setFill(ColorMapper.getColor(currentBrickData[i][j]));
                rectangles[i][j] = rectangle;
                brickPanel.add(rectangle, j, i);
            }
        }

        updateBrickPanelPosition(brick);
        renderNextBricks(brick.getNextBricksData());

        if (gameLoop == null) {
            gameLoop = new GameLoop(
                    GameConstants.GAME_TICK_MS,
                    () -> moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))
            );
        }
        gameLoop.start();
    }

    private void updateBrickPanelPosition(ViewData brick) {
        double boardOriginX = gameBoard.getLayoutX() + GameConstants.BOARD_FRAME_THICKNESS;
        double boardOriginY = gameBoard.getLayoutY() + GameConstants.BOARD_FRAME_THICKNESS;
        brickPanel.setLayoutX(
                boardOriginX
                        + brick.getXPosition() * GameConstants.brickStep()
        );
        brickPanel.setLayoutY(
                GameConstants.brickPanelYOffset()
                        + boardOriginY
                        + brick.getYPosition() * GameConstants.brickStep()
        );
    }

    private void refreshBrick(ViewData brick) {
        if (!isPause.get()) {
            updateBrickPanelPosition(brick);

            int[][] currentBrickData = brick.getBrickData();
            for (int i = 0; i < currentBrickData.length; i++) {
                for (int j = 0; j < currentBrickData[i].length; j++) {
                    setRectangleData(currentBrickData[i][j], rectangles[i][j]);
                }
            }
        }
        renderNextBricks(brick.getNextBricksData());
    }

    public void refreshGameBackground(int[][] board) {
        for (int i = GameConstants.HIDDEN_BUFFER_ROWS; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                setRectangleData(board[i][j], displayMatrix[i][j]);
            }
        }
    }

    private void initialiseNextPreviewPanels() {
        if (nextBricksList == null) {
            return;
        }
        nextBricksList.getChildren().clear();
        nextPreviewGrids.clear();
        for (int i = 0; i < GameConstants.NEXT_PREVIEW_COUNT; i++) {
            GridPane previewGrid = new GridPane();
            previewGrid.setHgap(GameConstants.NEXT_BRICK_GAP);
            previewGrid.setVgap(GameConstants.NEXT_BRICK_GAP);
            previewGrid.setAlignment(Pos.CENTER);
            nextPreviewGrids.add(previewGrid);
            nextBricksList.getChildren().add(previewGrid);
        }
    }

    private void renderNextBricks(List<int[][]> nextBricksData) {
        if (nextBricksList == null) {
            return;
        }
        if (nextPreviewGrids.isEmpty()) {
            initialiseNextPreviewPanels();
        }
        for (int i = 0; i < nextPreviewGrids.size(); i++) {
            GridPane previewGrid = nextPreviewGrids.get(i);
            previewGrid.getChildren().clear();
            if (nextBricksData == null || nextBricksData.size() <= i) {
                continue;
            }
            int[][] brickMatrix = nextBricksData.get(i);
            for (int row = 0; row < brickMatrix.length; row++) {
                for (int col = 0; col < brickMatrix[row].length; col++) {
                    int colorIndex = brickMatrix[row][col];
                    if (colorIndex != 0) {
                        Rectangle rectangle = createPreviewRectangle(colorIndex);
                        previewGrid.add(rectangle, col, row);
                    }
                }
            }
        }
    }

    private Rectangle createPreviewRectangle(int colorIndex) {
        Rectangle rectangle = new Rectangle(GameConstants.NEXT_BRICK_SIZE, GameConstants.NEXT_BRICK_SIZE);
        rectangle.setFill(ColorMapper.getColor(colorIndex));
        rectangle.setArcHeight(GameConstants.BRICK_ARC_SIZE);
        rectangle.setArcWidth(GameConstants.BRICK_ARC_SIZE);
        return rectangle;
    }

    private void setRectangleData(int color, Rectangle rectangle) {
        rectangle.setFill(ColorMapper.getColor(color));
        rectangle.setArcHeight(GameConstants.BRICK_ARC_SIZE);
        rectangle.setArcWidth(GameConstants.BRICK_ARC_SIZE);
    }

    private void moveDown(MoveEvent event) {
        if (!isPause.get()) {
            DownData downData = eventListener.onDownEvent(event);

            if (downData.getClearRow() != null && downData.getClearRow().getLinesRemoved() > 0) {
                NotificationPanel notificationPanel =
                        new NotificationPanel("+" + downData.getClearRow().getScoreBonus());
                groupNotification.getChildren().add(notificationPanel);
                notificationPanel.showScore(groupNotification.getChildren());
            }

            refreshBrick(downData.getViewData());
        }
        gamePanel.requestFocus();
    }

    public void setEventListener(InputEventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void bindScore(IntegerProperty integerProperty) {
        if (scoreValue != null) {
            scoreValue.textProperty().bind(integerProperty.asString());
        }
    }

    public void gameOver() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
        gameOverPanel.setVisible(true);
        isGameOver.set(true);
    }

    public void newGame(ActionEvent actionEvent) {
        if (gameLoop != null) {
            gameLoop.stop();
        }

        gameOverPanel.setVisible(false);
        isPause.set(false);
        isGameOver.set(false);
        if (pauseButton != null) {
            pauseButton.setText("Pause");
        }

        ViewData viewData = eventListener.createNewGame();
        refreshBrick(viewData);
        gamePanel.requestFocus();

        if (gameLoop == null) {
            gameLoop = new GameLoop(
                    GameConstants.GAME_TICK_MS,
                    () -> moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))
            );
        }
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
}
