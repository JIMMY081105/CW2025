package com.comp2042.view;

import com.comp2042.util.GameConstants;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameLayoutManager {

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
        if (gamePanel != null) {
            gamePanel.setHgap(GameConstants.GRID_GAP);
            gamePanel.setVgap(GameConstants.GRID_GAP);
            gamePanel.setPrefWidth(GameConstants.boardPixelWidth());
            gamePanel.setPrefHeight(GameConstants.boardPixelHeight());
            gamePanel.setMinSize(GameConstants.boardPixelWidth(), GameConstants.boardPixelHeight());
            gamePanel.setMaxSize(GameConstants.boardPixelWidth(), GameConstants.boardPixelHeight());
        }

        if (sidePanel != null) {
            sidePanel.setSpacing(GameConstants.SIDE_PANEL_SPACING);
            sidePanel.setPrefWidth(GameConstants.SIDE_PANEL_WIDTH);
            sidePanel.setPadding(new Insets(GameConstants.SIDE_PANEL_PADDING));
        }

        if (nextBricksContainer != null) {
            nextBricksContainer.setSpacing(GameConstants.NEXT_PREVIEW_SPACING);
        }
        if (nextBricksList != null) {
            nextBricksList.setSpacing(GameConstants.NEXT_PREVIEW_SPACING);
        }

        if (timerBox != null) {
            timerBox.setSpacing(GameConstants.SIDE_PANEL_SPACING / 2);
            timerBox.setPrefWidth(GameConstants.SIDE_PANEL_WIDTH);
            timerBox.setPadding(new Insets(GameConstants.SIDE_PANEL_PADDING));
        }

        double visualBuffer = 4.0;
        double bufferedWidth = GameConstants.boardAreaWidth() + (visualBuffer * 2);
        double bufferedHeight = GameConstants.boardAreaHeight() + (visualBuffer * 2);

        if (gameBoard != null) {
            gameBoard.setPrefWidth(bufferedWidth);
            gameBoard.setPrefHeight(bufferedHeight);
            gameBoard.setMinSize(bufferedWidth, bufferedHeight);
            gameBoard.setMaxSize(bufferedWidth, bufferedHeight);
        }

        if (notificationGroup != null) {
            notificationGroup.setLayoutY(GameConstants.notificationPanelY());
        }

        if (rootPane != null) {
            rootPane.setPrefWidth(GameConstants.initialWindowWidth());
            rootPane.setPrefHeight(GameConstants.initialWindowHeight());
        }

        if (bombToolbar != null) {
            bombToolbar.setPadding(new Insets(8));
        }

        if (chinaDescriptionBox != null) {
            chinaDescriptionBox.setPrefWidth(bufferedWidth);
            chinaDescriptionBox.setPrefHeight(bufferedHeight);
            chinaDescriptionBox.setMinSize(bufferedWidth, bufferedHeight);
            chinaDescriptionBox.setMaxSize(bufferedWidth, bufferedHeight);
        }
    }

public void positionContent(double availableWidth) {
        double safeWidth = Math.max(availableWidth, GameConstants.initialWindowWidth());
        double boardWidth = GameConstants.boardAreaWidth();

        double centeredBoardLeft = (safeWidth - boardWidth) / 2;
        double boardLeft;

        if (safeWidth < GameConstants.minimumCenteredWindowWidth()) {
            double centeredContent = (safeWidth - GameConstants.contentWidth()) / 2;
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
            boardRenderer.redrawGridLines();
        }

        double visualBuffer = 4.0;
        double bufferedWidth = GameConstants.boardAreaWidth() + (visualBuffer * 2);
        double bufferedHeight = GameConstants.boardAreaHeight() + (visualBuffer * 2);
        if (gameBoard != null) {
            gameBoard.setLayoutX(boardLeft - GameConstants.BOARD_FRAME_THICKNESS - visualBuffer);
            gameBoard.setLayoutY(boardTop - GameConstants.BOARD_FRAME_THICKNESS - visualBuffer);
        }

        double sidePanelLeft = boardLeft + GameConstants.boardAreaWidth() + GameConstants.PANEL_GAP;
        if (sidePanel != null) {
            sidePanel.setLayoutX(sidePanelLeft);
            sidePanel.setLayoutY(boardTop);
        }

        if (timerBox != null) {
            double timerLeft = Math.max(GameConstants.SIDE_PANEL_PADDING, boardLeft - GameConstants.SIDE_PANEL_WIDTH - GameConstants.PANEL_GAP);
            timerBox.setLayoutX(timerLeft);
            timerBox.setLayoutY(boardTop);
        }

        if (notificationGroup != null) {
            notificationGroup.setLayoutX(sidePanelLeft);
        }

        if (bombToolbar != null) {
            double bombToolbarY = boardTop + GameConstants.boardPixelHeight() + 35;
            double bombToolbarX = boardLeft + (GameConstants.boardPixelWidth() - 50) / 2;
            bombToolbar.setLayoutX(bombToolbarX);
            bombToolbar.setLayoutY(bombToolbarY);
        }

        if (chinaDescriptionBox != null) {
            double descLeft = Math.max(GameConstants.SIDE_PANEL_PADDING, boardLeft - bufferedWidth - GameConstants.PANEL_GAP);
            chinaDescriptionBox.setLayoutX(descLeft);
            chinaDescriptionBox.setLayoutY(boardTop - GameConstants.BOARD_FRAME_THICKNESS - visualBuffer);
            chinaDescriptionBox.setPrefWidth(bufferedWidth);
            chinaDescriptionBox.setPrefHeight(bufferedHeight);
        }
    }
}
