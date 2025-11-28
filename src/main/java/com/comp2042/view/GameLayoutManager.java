package com.comp2042.view;

import com.comp2042.util.GameConstants;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameLayoutManager {

    private final Pane rootPane;
    private final BorderPane gameBoard;
    private final GridPane gamePanel;
    private final Pane gridLinesPane;
    private final VBox sidePanel;
    private final VBox nextBricksContainer;
    private final VBox nextBricksList;
    private final Group notificationGroup;
    private final BoardRenderer boardRenderer;

    public GameLayoutManager(Pane rootPane,
                             BorderPane gameBoard,
                             GridPane gamePanel,
                             Pane gridLinesPane,
                             VBox sidePanel,
                             VBox nextBricksContainer,
                             VBox nextBricksList,
                             Group notificationGroup,
                             BoardRenderer boardRenderer) {
        this.rootPane = rootPane;
        this.gameBoard = gameBoard;
        this.gamePanel = gamePanel;
        this.gridLinesPane = gridLinesPane;
        this.sidePanel = sidePanel;
        this.nextBricksContainer = nextBricksContainer;
        this.nextBricksList = nextBricksList;
        this.notificationGroup = notificationGroup;
        this.boardRenderer = boardRenderer;
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
        if (gameBoard != null) {
            gameBoard.setLayoutX(boardLeft - GameConstants.BOARD_FRAME_THICKNESS - visualBuffer);
            gameBoard.setLayoutY(boardTop - GameConstants.BOARD_FRAME_THICKNESS - visualBuffer);
        }

        double sidePanelLeft = boardLeft + GameConstants.boardAreaWidth() + GameConstants.PANEL_GAP;
        if (sidePanel != null) {
            sidePanel.setLayoutX(sidePanelLeft);
            sidePanel.setLayoutY(boardTop);
        }

        if (notificationGroup != null) {
            notificationGroup.setLayoutX(sidePanelLeft);
        }
    }
}
