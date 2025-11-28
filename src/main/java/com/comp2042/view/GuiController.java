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
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

    private InputEventListener eventListener;
    private Board board;

    private final BooleanProperty isPause = new SimpleBooleanProperty();
    private final BooleanProperty isGameOver = new SimpleBooleanProperty();

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
                boardRenderer
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
}
