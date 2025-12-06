package com.comp2042.view.screen;

import com.comp2042.event.InputEventListener;
import com.comp2042.model.Board;
import com.comp2042.model.brick.BrickFactory;
import com.comp2042.util.GameConstants;
import com.comp2042.view.GameInputHandler;
import com.comp2042.view.effect.BoardVibrationEffect;
import com.comp2042.view.manager.BackgroundMusicManager;
import com.comp2042.view.manager.BombManager;
import com.comp2042.view.manager.ChinaStageManager;
import com.comp2042.view.manager.GameLayoutManager;
import com.comp2042.view.manager.GameNotificationManager;
import com.comp2042.view.manager.GameSessionManager;
import com.comp2042.view.manager.TimeAttackManager;
import com.comp2042.view.render.BoardRenderer;
import com.comp2042.view.render.GameOverPanel;
import com.comp2042.view.render.NextBricksRenderer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

public class GameScreenController implements Initializable {

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

    private Board board;

    private final BooleanProperty isPause = new SimpleBooleanProperty();
    private final BooleanProperty isGameOver = new SimpleBooleanProperty();
    private final IntegerProperty bombCount = new SimpleIntegerProperty(0);

    private BoardRenderer boardRenderer;
    private NextBricksRenderer nextBricksRenderer;
    private GameLayoutManager layoutManager;
    private BoardVibrationEffect vibrationEffect;
    private TimeAttackManager timeAttackManager;
    private GameNotificationManager notificationManager;
    private GameSessionManager sessionManager;
    private BombManager bombManager;
    private ChinaStageManager chinaStageManager;
    private GameInputHandler inputHandler;

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

        notificationManager = new GameNotificationManager(
                groupNotification,
                bombCount
        );

        sessionManager = new GameSessionManager(
                isPause,
                isGameOver,
                gamePanel,
                boardRenderer,
                nextBricksRenderer,
                vibrationEffect,
                timeAttackManager,
                layoutManager,
                notificationManager,
                gameOverPanel
        );

        timeAttackManager.setOnTimeOver(() ->
                sessionManager.handleGameEnd("Time's Up", "The clock reached zero. Try another run!")
        );

        chinaStageManager = new ChinaStageManager(
                chinaDescriptionBox,
                chinaStateTitle,
                chinaStateDescription,
                layoutManager::applyBackgroundImage,
                sessionManager::updateGameLoopSpeed,
                () -> sessionManager.handleGameEnd("Journey Complete", "You finished every China stage!")
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
                sessionManager::pauseForBombDrag,
                sessionManager::resumeAfterBombDrag
        );
        bombManager.initialise();

        layoutManager.setupEndOverlay(endOverlay, endBackgroundVideo, endTitle, endSubtitle);

        inputHandler = new GameInputHandler(
                isPause,
                isGameOver,
                sessionManager::onMoveLeft,
                sessionManager::onMoveRight,
                sessionManager::onRotate,
                sessionManager::onMoveDown,
                sessionManager::onHardDrop
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

    public void bind(Board board) {
        this.board = board;

        if (bombManager != null) {
            bombManager.setBoard(board);
        }
        if (sessionManager != null) {
            sessionManager.bindBoard(board);
        }
    }

    public void setEventListener(InputEventListener eventListener) {
        if (sessionManager != null) {
            sessionManager.setEventListener(eventListener);
        }
    }

    public void setNavigationHandlers(Runnable backToHomeHandler, Runnable restartHandler) {
        this.backToHomeHandler = backToHomeHandler;
        this.restartHandler = restartHandler;
    }

    public void bindScore(IntegerProperty scoreProperty) {
        if (scoreValue != null) {
            scoreValue.textProperty().bind(scoreProperty.asString());
        }

        if (timeAttackManager != null) {
            timeAttackManager.bindScoreProperty(scoreProperty);
        }

        scoreProperty.addListener((obs, oldVal, newVal) -> {
            int newScore = newVal.intValue();
            if (notificationManager != null) {
                notificationManager.handleScoreChanged(newScore);
            }
            if (chinaStageManager != null && chinaStageManager.isEnabled()) {
                chinaStageManager.handleScoreChanged(newScore);
            }
        });
    }

    public void pauseGame(ActionEvent actionEvent) {
        if (sessionManager != null) {
            sessionManager.togglePause(pauseButton);
        }
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
        if (sessionManager != null) {
            sessionManager.exitGame();
        }
    }

    public void gameOver() {
        if (sessionManager != null) {
            sessionManager.handleGameEnd("Game Over", "The bricks reached the ceiling.");
        }
    }

    public void configureExploreChinaMode() {
        if (chinaStageManager == null || sessionManager == null) {
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

        if (sessionManager != null && sessionManager.isRunning()) {
            timeAttackManager.start();
        }
    }

    public void showModeLabel(String modeLabel) {
        if (bestScoreTitle != null) {
            bestScoreTitle.setText(modeLabel);
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
}
