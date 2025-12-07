package com.comp2042.view.manager;

import com.comp2042.util.LayoutMetrics;
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

import java.util.Objects;

public final class GameLayoutManager {

    private static final double VISUAL_BUFFER = 4.0;
    private static final double BOMB_TOOLBAR_PADDING = 8.0;
    private static final double BOMB_TOOLBAR_Y_OFFSET = 35.0;
    private static final double BOMB_TOOLBAR_WIDTH_OFFSET = 50.0;
    private static final double END_OVERLAY_ANIMATION_MS = 520.0;

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

        this.rootPane = Objects.requireNonNull(rootPane, "rootPane must not be null");
        this.gameBoard = Objects.requireNonNull(gameBoard, "gameBoard must not be null");
        this.gamePanel = Objects.requireNonNull(gamePanel, "gamePanel must not be null");
        this.gridLinesPane = Objects.requireNonNull(gridLinesPane, "gridLinesPane must not be null");
        this.sidePanel = Objects.requireNonNull(sidePanel, "sidePanel must not be null");
        this.timerBox = Objects.requireNonNull(timerBox, "timerBox must not be null");
        this.nextBricksContainer = Objects.requireNonNull(nextBricksContainer, "nextBricksContainer must not be null");
        this.nextBricksList = Objects.requireNonNull(nextBricksList, "nextBricksList must not be null");
        this.notificationGroup = Objects.requireNonNull(notificationGroup, "notificationGroup must not be null");
        this.boardRenderer = Objects.requireNonNull(boardRenderer, "boardRenderer must not be null");
        this.bombToolbar = Objects.requireNonNull(bombToolbar, "bombToolbar must not be null");
        this.chinaDescriptionBox = Objects.requireNonNull(chinaDescriptionBox, "chinaDescriptionBox must not be null");
    }

    public void applyInitialLayout() {
        double boardWidth = LayoutMetrics.boardPixelWidth();
        double boardHeight = LayoutMetrics.boardPixelHeight();

        gamePanel.setHgap(LayoutMetrics.GRID_GAP);
        gamePanel.setVgap(LayoutMetrics.GRID_GAP);
        gamePanel.setPrefSize(boardWidth, boardHeight);
        gamePanel.setMinSize(boardWidth, boardHeight);
        gamePanel.setMaxSize(boardWidth, boardHeight);

        gridLinesPane.setPrefSize(boardWidth, boardHeight);
        gridLinesPane.setMinSize(boardWidth, boardHeight);
        gridLinesPane.setMaxSize(boardWidth, boardHeight);

        double bufferedWidth = LayoutMetrics.boardAreaWidth() + (VISUAL_BUFFER * 2);
        double bufferedHeight = LayoutMetrics.boardAreaHeight() + (VISUAL_BUFFER * 2);
        gameBoard.setPrefSize(bufferedWidth, bufferedHeight);
        gameBoard.setMinSize(bufferedWidth, bufferedHeight);
        gameBoard.setMaxSize(bufferedWidth, bufferedHeight);
        gameBoard.setPadding(new Insets(
                LayoutMetrics.BOARD_FRAME_THICKNESS,
                LayoutMetrics.BOARD_FRAME_THICKNESS,
                LayoutMetrics.BOARD_FRAME_THICKNESS + LayoutMetrics.BOTTOM_PADDING,
                LayoutMetrics.BOARD_FRAME_THICKNESS
        ));

        sidePanel.setSpacing(LayoutMetrics.SIDE_PANEL_SPACING);
        sidePanel.setPrefWidth(LayoutMetrics.SIDE_PANEL_WIDTH);
        sidePanel.setPadding(new Insets(LayoutMetrics.SIDE_PANEL_PADDING));

        timerBox.setSpacing(LayoutMetrics.SIDE_PANEL_SPACING / 2.0);
        timerBox.setPrefWidth(LayoutMetrics.SIDE_PANEL_WIDTH);
        timerBox.setPadding(new Insets(LayoutMetrics.SIDE_PANEL_PADDING));

        nextBricksContainer.setSpacing(LayoutMetrics.NEXT_PREVIEW_SPACING);
        nextBricksList.setSpacing(LayoutMetrics.NEXT_PREVIEW_SPACING);

        notificationGroup.setLayoutY(LayoutMetrics.notificationPanelY());

        bombToolbar.setPadding(new Insets(BOMB_TOOLBAR_PADDING));

        double chinaBufferedWidth = LayoutMetrics.boardAreaWidth() + (VISUAL_BUFFER * 2);
        double chinaBufferedHeight = LayoutMetrics.boardAreaHeight() + (VISUAL_BUFFER * 2);
        chinaDescriptionBox.setPrefSize(chinaBufferedWidth, chinaBufferedHeight);
        chinaDescriptionBox.setMinSize(chinaBufferedWidth, chinaBufferedHeight);
        chinaDescriptionBox.setMaxSize(chinaBufferedWidth, chinaBufferedHeight);

        rootPane.setPrefWidth(LayoutMetrics.initialWindowWidth());
        rootPane.setPrefHeight(LayoutMetrics.initialWindowHeight());

        boardRenderer.redrawGridLines();
    }

    public void positionContent(double availableWidth) {
        double safeWidth = Math.max(availableWidth, LayoutMetrics.initialWindowWidth());
        double boardAreaWidth = LayoutMetrics.boardAreaWidth();

        double centeredBoardLeft = (safeWidth - boardAreaWidth) / 2.0;
        double boardLeft;

        if (safeWidth < LayoutMetrics.minimumCenteredWindowWidth()) {
            double centeredContent = (safeWidth - LayoutMetrics.contentWidth()) / 2.0;
            boardLeft = Math.max(LayoutMetrics.BOARD_LEFT_PADDING, centeredContent);
        } else {
            boardLeft = centeredBoardLeft;
        }

        double boardTop = LayoutMetrics.BOARD_TOP_PADDING;

        gamePanel.setLayoutX(boardLeft);
        gamePanel.setLayoutY(boardTop);

        gridLinesPane.setLayoutX(boardLeft);
        gridLinesPane.setLayoutY(boardTop);

        gameBoard.setLayoutX(boardLeft - LayoutMetrics.BOARD_FRAME_THICKNESS - VISUAL_BUFFER);
        gameBoard.setLayoutY(boardTop - LayoutMetrics.BOARD_FRAME_THICKNESS - VISUAL_BUFFER);

        double sidePanelLeft = boardLeft + boardAreaWidth + LayoutMetrics.PANEL_GAP;
        sidePanel.setLayoutX(sidePanelLeft);
        sidePanel.setLayoutY(boardTop);

        double timerLeft = Math.max(
                LayoutMetrics.SIDE_PANEL_PADDING,
                boardLeft - LayoutMetrics.SIDE_PANEL_WIDTH - LayoutMetrics.PANEL_GAP
        );
        timerBox.setLayoutX(timerLeft);
        timerBox.setLayoutY(boardTop);

        notificationGroup.setLayoutX(sidePanelLeft);

        double bombToolbarY = boardTop + LayoutMetrics.boardPixelHeight() + BOMB_TOOLBAR_Y_OFFSET;
        double bombToolbarX = boardLeft + (LayoutMetrics.boardPixelWidth() - BOMB_TOOLBAR_WIDTH_OFFSET) / 2.0;
        bombToolbar.setLayoutX(bombToolbarX);
        bombToolbar.setLayoutY(bombToolbarY);

        double descBufferedWidth = chinaDescriptionBox.getPrefWidth();
        double descLeft = Math.max(
                LayoutMetrics.SIDE_PANEL_PADDING,
                boardLeft - descBufferedWidth - LayoutMetrics.PANEL_GAP
        );
        double descTop = boardTop - LayoutMetrics.BOARD_FRAME_THICKNESS - VISUAL_BUFFER;
        chinaDescriptionBox.setLayoutX(descLeft);
        chinaDescriptionBox.setLayoutY(descTop);
    }

    public void applyBackgroundImage(String resourcePath) {
        if (resourcePath == null || resourcePath.isBlank()) {
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

        this.endOverlay.prefWidthProperty().bind(rootPane.widthProperty());
        this.endOverlay.prefHeightProperty().bind(rootPane.heightProperty());

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

        double width = LayoutMetrics.initialWindowWidth();
        if (rootPane.getWidth() > 0) {
            width = rootPane.getWidth();
        }

        endOverlay.setTranslateX(-width);
        TranslateTransition slideIn = new TranslateTransition(
                Duration.millis(END_OVERLAY_ANIMATION_MS),
                endOverlay
        );
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
