package com.comp2042.view.render;

import com.comp2042.data.ViewData;
import com.comp2042.util.BlockTextureProvider;
import com.comp2042.util.GameConstants;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class BoardRenderer {

    private final GridPane gamePanel;
    private final GridPane brickPanel;
    private final Pane ghostPane;
    private final Pane gridLinesPane;

    private Rectangle[][] displayMatrix;
    private Rectangle[][] activeRectangles;

    public BoardRenderer(GridPane gamePanel,
                         GridPane brickPanel,
                         Pane ghostPane,
                         Pane gridLinesPane) {
        this.gamePanel = gamePanel;
        this.brickPanel = brickPanel;
        this.ghostPane = ghostPane;
        this.gridLinesPane = gridLinesPane;

        if (this.gamePanel != null) {
            this.gamePanel.setAlignment(Pos.CENTER);
        }

        if (this.brickPanel != null) {
            this.brickPanel.setHgap(GameConstants.GRID_GAP);
            this.brickPanel.setVgap(GameConstants.GRID_GAP);
        }
    }

public void initialiseBoard(int[][] boardMatrix, ViewData viewData) {
        createBackgroundCells(boardMatrix);
        createActiveBrick(viewData.getBrickData());
        updateBrickPosition(viewData);
        drawGhost(viewData);
        redrawGridLines();
    }

public void refreshBackground(int[][] boardMatrix) {
        if (displayMatrix == null) {
            return;
        }

        for (int i = GameConstants.HIDDEN_BUFFER_ROWS; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                setRectangleData(boardMatrix[i][j], displayMatrix[i][j]);
            }
        }
    }

public void refreshBrick(ViewData viewData) {
        if (activeRectangles == null) {
            return;
        }

        updateBrickPosition(viewData);
        drawGhost(viewData);

        int[][] brickData = viewData.getBrickData();
        for (int row = 0; row < brickData.length; row++) {
            for (int col = 0; col < brickData[row].length; col++) {
                setRectangleData(brickData[row][col], activeRectangles[row][col]);
            }
        }
    }

public void redrawGridLines() {
        if (gridLinesPane == null) {
            return;
        }

        gridLinesPane.getChildren().clear();

        double gridWidth = GameConstants.boardPixelWidth();
        double gridHeight = GameConstants.boardPixelHeight();
        double step = GameConstants.brickStep();
        int visibleRows = GameConstants.visibleRows();
        int cols = GameConstants.BOARD_WIDTH;

        Color lineColor = Color.rgb(60, 60, 80, 0.6);

        for (int col = 0; col <= cols; col++) {
            double x = col * step;
            Line verticalLine = new Line(x, 0, x, gridHeight);
            verticalLine.setStroke(lineColor);
            verticalLine.setStrokeWidth(1);
            gridLinesPane.getChildren().add(verticalLine);
        }

        for (int row = 0; row <= visibleRows; row++) {
            double y = row * step;
            Line horizontalLine = new Line(0, y, gridWidth, y);
            horizontalLine.setStroke(lineColor);
            horizontalLine.setStrokeWidth(1);
            gridLinesPane.getChildren().add(horizontalLine);
        }

        gridLinesPane.setPrefSize(gridWidth, gridHeight);
        gridLinesPane.setMinSize(gridWidth, gridHeight);
        gridLinesPane.setMaxSize(gridWidth, gridHeight);
    }

private void createBackgroundCells(int[][] boardMatrix) {
        if (gamePanel == null) {
            return;
        }

        gamePanel.getChildren().clear();
        int rows = boardMatrix.length;
        int cols = boardMatrix[0].length;

        displayMatrix = new Rectangle[rows][cols];

        for (int i = GameConstants.HIDDEN_BUFFER_ROWS; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Rectangle rectangle = new Rectangle(GameConstants.BRICK_SIZE, GameConstants.BRICK_SIZE);
                rectangle.setFill(Color.TRANSPARENT);
                displayMatrix[i][j] = rectangle;
                gamePanel.add(rectangle, j, i - GameConstants.HIDDEN_BUFFER_ROWS);
            }
        }
    }

    private void createActiveBrick(int[][] brickData) {
        if (brickPanel == null) {
            return;
        }

        brickPanel.getChildren().clear();

        int rows = brickData.length;
        int cols = brickData[0].length;

        activeRectangles = new Rectangle[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Rectangle rectangle = new Rectangle(GameConstants.BRICK_SIZE, GameConstants.BRICK_SIZE);
                setRectangleData(brickData[row][col], rectangle);
                activeRectangles[row][col] = rectangle;
                brickPanel.add(rectangle, col, row);
            }
        }
    }

    private void updateBrickPosition(ViewData brick) {
        if (gamePanel == null || brickPanel == null) {
            return;
        }

        double boardOriginX = gamePanel.getLayoutX();
        double boardOriginY = gamePanel.getLayoutY();

        brickPanel.setLayoutX(
                boardOriginX + brick.getXPosition() * GameConstants.brickStep()
        );
        brickPanel.setLayoutY(
                GameConstants.brickPanelYOffset()
                        + boardOriginY
                        + brick.getYPosition() * GameConstants.brickStep()
        );
    }

    private void drawGhost(ViewData brick) {
        if (ghostPane == null || gamePanel == null) {
            return;
        }

        ghostPane.getChildren().clear();

        int[][] brickData = brick.getBrickData();
        int ghostY = brick.getGhostYPosition();
        int brickX = brick.getXPosition();

        if (ghostY == brick.getYPosition()) {
            return;
        }

        double step = GameConstants.brickStep();
        double brickSize = GameConstants.BRICK_SIZE;
        Color ghostOutline = Color.rgb(180, 220, 255, 0.65);
        Color ghostDetail = Color.rgb(180, 220, 255, 0.4);
        double strokeWidth = 1.5;
        double cornerRadius = Math.max(2, GameConstants.BRICK_ARC_SIZE - 4);
        double inset = strokeWidth * 0.5; // let diamond touch the square stroke from inside

        for (int row = 0; row < brickData.length; row++) {
            for (int col = 0; col < brickData[row].length; col++) {
                if (brickData[row][col] == 0) {
                    continue;
                }

                double cellX = col * step;
                double cellY = row * step;

                Rectangle outline = new Rectangle(cellX, cellY, brickSize, brickSize);
                outline.setFill(Color.TRANSPARENT);
                outline.setStroke(ghostOutline);
                outline.setStrokeWidth(strokeWidth);
                outline.setArcWidth(cornerRadius);
                outline.setArcHeight(cornerRadius);
                ghostPane.getChildren().add(outline);

                Polygon diamond = new Polygon(
                        cellX + brickSize / 2, cellY + inset,
                        cellX + brickSize - inset, cellY + brickSize / 2,
                        cellX + brickSize / 2, cellY + brickSize - inset,
                        cellX + inset, cellY + brickSize / 2
                );
                diamond.setFill(Color.TRANSPARENT);
                diamond.setStroke(ghostDetail);
                diamond.setStrokeWidth(strokeWidth);
                ghostPane.getChildren().add(diamond);
            }
        }

        double boardOriginX = gamePanel.getLayoutX();
        double boardOriginY = gamePanel.getLayoutY();

        ghostPane.setLayoutX(boardOriginX + brickX * step);
        ghostPane.setLayoutY(
                GameConstants.brickPanelYOffset()
                        + boardOriginY
                        + ghostY * step
        );
    }

    private void setRectangleData(int color, Rectangle rectangle) {
        rectangle.setFill(BlockTextureProvider.getPattern(color));
        rectangle.setArcHeight(GameConstants.BRICK_ARC_SIZE);
        rectangle.setArcWidth(GameConstants.BRICK_ARC_SIZE);
    }
}
