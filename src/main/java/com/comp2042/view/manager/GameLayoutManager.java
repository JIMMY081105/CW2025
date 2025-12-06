package com.comp2042.view.manager;

import com.comp2042.util.GameConstants;
import com.comp2042.view.render.BoardRenderer;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public final class GameLayoutManager {

    private final Pane rootPane;
    private final BorderPane gameBoard;
    private final GridPane gamePanel;
    private final Pane gridLinesPane;
    private final VBox sidePanel;
    private final VBox timerBox;
    private final VBox nextBricksContainer;
    private final VBox nextBricksList;
    private final Group notificationGroup;
    private final BoardRenderer boardRenderer;
    private final StackPane bombToolbar;
    private final VBox chinaDescriptionBox;

    private StackPane endOverlay;
    private MediaView endBackgroundVideo;
    private Label endTitle;
    private Label endSubtitle;

    public GameLayoutManager(Pane rootPane,
                             BorderPane gameBoard,
                             GridPane gamePanel,
                             Pane gridLinesPane,
                             VBox sidePanel,
                             VBox timerBox,
                             VBox nextBricksContainer,
                             VBox nextBricksList,
                             Group notificationGroup,
                             BoardRenderer boardRenderer,
                             StackPane bombToolbar,
                             VBox chinaDescriptionBox) {

        this.rootPane = rootPane;
        this.gameBoard = gameBoard;
        this.gamePanel = gamePanel;
        this.gridLinesPane = gridLinesPane;
        this.sidePanel = sidePanel;
        this.timerBox = timerBox;
        this.nextBricksContainer = nextBricksContainer;
        this.nextBricksList = nextBricksList;
        this.notificationGroup = notificationGroup;
        this.boardRenderer = boardRenderer;
        this.bombToolbar = bombToolbar;
        this.chinaDescriptionBox = chinaDescriptionBox;
    }

    public void applyInitialLayout() {
        double boardWidth = GameConstants.boardPixelWidth();
        double boardHeight = GameConstants.boardPixelHeight();

        if (gamePanel != null) {
            gamePanel.setHgap(GameConstants.GRID_GAP);
            gamePanel.setVgap(GameConstants.GRID_GAP);
            gamePanel.setPrefSize(boardWidth, boardHeight);
            gamePanel.setMinSize(boardWidth, boardHeight);
            gamePanel.setMaxSize(boardWidth, boardHeight);
        }

        if (gridLinesPane != null) {
            gridLinesPane.setPrefSize(boardWidth, boardHeight);
            gridLinesPane.setMinSize(boardWidth, boardHeight);
            gridLinesPane.setMaxSize(boardWidth, boardHeight);
        }

        if (gameBoard != null) {
            double visualBuffer = 4.0;
            double bufferedWidth = GameConstants.boardAreaWidth() + (visualBuffer * 2);
            double bufferedHeight = GameConstants.boardAreaHeight() + (visualBuffer * 2);
            gameBoard.setPrefSize(bufferedWidth, bufferedHeight);
            gameBoard.setMinSize(bufferedWidth, bufferedHeight);
            gameBoard.setMaxSize(bufferedWidth, bufferedHeight);
            gameBoard.setPadding(new Insets(
                    GameConstants.BOARD_FRAME_THICKNESS,
                    GameConstants.BOARD_FRAME_THICKNESS,
                    GameConstants.BOARD_FRAME_THICKNESS + GameConstants.BOTTOM_PADDING,
                    GameConstants.BOARD_FRAME_THICKNESS
            ));
        }

        if (sidePanel != null) {
            sidePanel.setSpacing(GameConstants.SIDE_PANEL_SPACING);
            sidePanel.setPrefWidth(GameConstants.SIDE_PANEL_WIDTH);
            sidePanel.setPadding(new Insets(GameConstants.SIDE_PANEL_PADDING));
        }

        if (timerBox != null) {
            timerBox.setSpacing(GameConstants.SIDE_PANEL_SPACING / 2.0);
            timerBox.setPrefWidth(GameConstants.SIDE_PANEL_WIDTH);
            timerBox.setPadding(new Insets(GameConstants.SIDE_PANEL_PADDING));
        }

        if (nextBricksContainer != null) {
            nextBricksContainer.setSpacing(GameConstants.NEXT_PREVIEW_SPACING);
        }

        if (nextBricksList != null) {
            nextBricksList.setSpacing(GameConstants.NEXT_PREVIEW_SPACING);
        }

        if (notificationGroup != null) {
            notificationGroup.setLayoutY(GameConstants.notificationPanelY());
        }

        if (bombToolbar != null) {
            bombToolbar.setPadding(new Insets(8));
        }

        if (chinaDescriptionBox != null) {
            double visualBuffer = 4.0;
            double bufferedWidth = GameConstants.boardAreaWidth() + (visualBuffer * 2);
            double bufferedHeight = GameConstants.boardAreaHeight() + (visualBuffer * 2);
            chinaDescriptionBox.setPrefSize(bufferedWidth, bufferedHeight);
            chinaDescriptionBox.setMinSize(bufferedWidth, bufferedHeight);
            chinaDescriptionBox.setMaxSize(bufferedWidth, bufferedHeight);
        }

        if (rootPane != null) {
            rootPane.setPrefWidth(GameConstants.initialWindowWidth());
            rootPane.setPrefHeight(GameConstants.initialWindowHeight());
        }

        if (boardRenderer != null) {
            boardRenderer.redrawGridLines();
        }
    }

    public void positionContent(double availableWidth) {
        double safeWidth = Math.max(availableWidth, GameConstants.initialWindowWidth());
        double boardAreaWidth = GameConstants.boardAreaWidth();

        double centeredBoardLeft = (safeWidth - boardAreaWidth) / 2.0;
        double boardLeft;

        if (safeWidth < GameConstants.minimumCenteredWindowWidth()) {
            double centeredContent = (safeWidth - GameConstants.contentWidth()) / 2.0;
            boardLeft = Math.max(GameConstants.BOARD_LEFT_PADDING, centeredContent);
        } else {
            boardLeft = centeredBoardLeft;
        }

        double boardTop = GameConstants.BOARD_TOP_PADDING;

        if (gamePanel != null) {
            gamePanel.setLayoutX(boardLeft);
            gamePanel.setLayoutY(boardTop);
        }

        if (gridLinesPane != null) {
            gridLinesPane.setLayoutX(boardLeft);
            gridLinesPane.setLayoutY(boardTop);
        }

        double visualBuffer = 4.0;
        if (gameBoard != null) {
            gameBoard.setLayoutX(boardLeft - GameConstants.BOARD_FRAME_THICKNESS - visualBuffer);
            gameBoard.setLayoutY(boardTop - GameConstants.BOARD_FRAME_THICKNESS - visualBuffer);
        }

        double sidePanelLeft = boardLeft + boardAreaWidth + GameConstants.PANEL_GAP;
        if (sidePanel != null) {
            sidePanel.setLayoutX(sidePanelLeft);
            sidePanel.setLayoutY(boardTop);
        }

        if (timerBox != null) {
            double timerLeft = Math.max(
                    GameConstants.SIDE_PANEL_PADDING,
                    boardLeft - GameConstants.SIDE_PANEL_WIDTH - GameConstants.PANEL_GAP
            );
            timerBox.setLayoutX(timerLeft);
            timerBox.setLayoutY(boardTop);
        }

        if (notificationGroup != null) {
            notificationGroup.setLayoutX(sidePanelLeft);
        }

        if (bombToolbar != null) {
            double bombToolbarY = boardTop + GameConstants.boardPixelHeight() + 35;
            double bombToolbarX = boardLeft + (GameConstants.boardPixelWidth() - 50) / 2.0;
            bombToolbar.setLayoutX(bombToolbarX);
            bombToolbar.setLayoutY(bombToolbarY);
        }

        if (chinaDescriptionBox != null) {
            double bufferedWidth = chinaDescriptionBox.getPrefWidth();
            double descLeft = Math.max(
                    GameConstants.SIDE_PANEL_PADDING,
                    boardLeft - bufferedWidth - GameConstants.PANEL_GAP
            );
            double descTop = boardTop - GameConstants.BOARD_FRAME_THICKNESS - visualBuffer;
            chinaDescriptionBox.setLayoutX(descLeft);
            chinaDescriptionBox.setLayoutY(descTop);
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

    public void setupEndOverlay(StackPane endOverlay,
                                MediaView endBackgroundVideo,
                                Label endTitle,
                                Label endSubtitle) {
        this.endOverlay = endOverlay;
        this.endBackgroundVideo = endBackgroundVideo;
        this.endTitle = endTitle;
        this.endSubtitle = endSubtitle;

        if (this.endOverlay == null) {
            return;
        }

        this.endOverlay.setVisible(false);
        this.endOverlay.setManaged(false);

        if (rootPane != null) {
            this.endOverlay.prefWidthProperty().bind(rootPane.widthProperty());
            this.endOverlay.prefHeightProperty().bind(rootPane.heightProperty());
        }

        if (this.endBackgroundVideo != null) {
            BackgroundVideoManager.attach(this.endBackgroundVideo, this.endOverlay);
        }
    }

    public void showEndScreen(String title, String subtitle) {
        if (endOverlay == null) {
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

    public void hideEndOverlay() {
        if (endOverlay != null) {
            endOverlay.setVisible(false);
            endOverlay.setManaged(false);
        }
    }
}
