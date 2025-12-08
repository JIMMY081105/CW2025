package com.comp2042.view.render;

import com.comp2042.data.ViewData;
import com.comp2042.util.BlockTextureProvider;
import com.comp2042.util.GameConfig;
import com.comp2042.util.LayoutMetrics;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

/**
 * The {@code BoardRenderer} class handles visual rendering of the board grid, settled bricks, active piece, ghost
 * projection, and grid lines based on {@link com.comp2042.model.Board} view data.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/render/BoardRenderer.java">
 * BoardRenderer.java</a>
 */
public class BoardRenderer {

    private static final Color GRID_LINE_COLOR = Color.rgb(60, 60, 80, 0.6);
    private static final double GRID_LINE_WIDTH = 1.0;

    private static final Color GHOST_OUTLINE_COLOR = Color.rgb(180, 220, 255, 0.65);
    private static final Color GHOST_DETAIL_COLOR = Color.rgb(180, 220, 255, 0.4);
    private static final double GHOST_STROKE_WIDTH = 1.5;
    private static final double GHOST_CORNER_RADIUS_MIN = 2.0;
    private static final double GHOST_CORNER_RADIUS_OFFSET = 4.0;

    private final GridPane gamePanel;
    private final GridPane brickPanel;
    private final Pane ghostPane;
    private final Pane gridLinesPane;

    private Rectangle[][] displayMatrix;
    private Rectangle[][] activeRectangles;

    /**
     * Creates a renderer bound to the provided panes.
     *
     * @param gamePanel     grid pane for settled bricks.
     * @param brickPanel    grid pane for the active piece.
     * @param ghostPane     pane for ghost projections.
     * @param gridLinesPane pane for drawing grid lines.
     */
    public BoardRenderer(GridPane gamePanel,
                         GridPane brickPanel,
                         Pane ghostPane,
                         Pane gridLinesPane) {

        this.gamePanel = Objects.requireNonNull(gamePanel, "gamePanel must not be null");
        this.brickPanel = Objects.requireNonNull(brickPanel, "brickPanel must not be null");
        this.ghostPane = ghostPane;
        this.gridLinesPane = gridLinesPane;

        this.gamePanel.setAlignment(Pos.CENTER);

        this.brickPanel.setHgap(LayoutMetrics.GRID_GAP);
        this.brickPanel.setVgap(LayoutMetrics.GRID_GAP);
    }

    /**
     * Initialises board cells, active brick, ghost projection, and grid lines from the given state.
     *
     * @param boardMatrix initial board matrix.
     * @param viewData    active piece view data.
     */
    public void initialiseBoard(int[][] boardMatrix, ViewData viewData) {
        createBackgroundCells(boardMatrix);
        createActiveBrick(viewData.getBrickData());
        updateBrickPosition(viewData);
        drawGhost(viewData);
        redrawGridLines();
    }

    /**
     * Updates the settled brick background using the supplied matrix.
     *
     * @param boardMatrix latest board state.
     */
    public void refreshBackground(int[][] boardMatrix) {
        if (displayMatrix == null) {
            return;
        }

        for (int i = GameConfig.HIDDEN_BUFFER_ROWS; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                setRectangleData(boardMatrix[i][j], displayMatrix[i][j]);
            }
        }
    }

    /**
     * Updates the active brick rendering and ghost projection.
     *
     * @param viewData latest active piece view data.
     */
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

    /**
     * Redraws the grid lines overlay to match current board dimensions.
     */
    public void redrawGridLines() {
        if (gridLinesPane == null) {
            return;
        }

        gridLinesPane.getChildren().clear();

        double gridWidth = LayoutMetrics.boardPixelWidth();
        double gridHeight = LayoutMetrics.boardPixelHeight();
        double step = LayoutMetrics.brickStep();
        int visibleRows = GameConfig.visibleRows();
        int cols = GameConfig.BOARD_WIDTH;

        for (int col = 0; col <= cols; col++) {
            double x = col * step;
            Line verticalLine = new Line(x, 0, x, gridHeight);
            verticalLine.setStroke(GRID_LINE_COLOR);
            verticalLine.setStrokeWidth(GRID_LINE_WIDTH);
            gridLinesPane.getChildren().add(verticalLine);
        }

        for (int row = 0; row <= visibleRows; row++) {
            double y = row * step;
            Line horizontalLine = new Line(0, y, gridWidth, y);
            horizontalLine.setStroke(GRID_LINE_COLOR);
            horizontalLine.setStrokeWidth(GRID_LINE_WIDTH);
            gridLinesPane.getChildren().add(horizontalLine);
        }

        gridLinesPane.setPrefSize(gridWidth, gridHeight);
        gridLinesPane.setMinSize(gridWidth, gridHeight);
        gridLinesPane.setMaxSize(gridWidth, gridHeight);
    }

    private void createBackgroundCells(int[][] boardMatrix) {
        gamePanel.getChildren().clear();
        int rows = boardMatrix.length;
        int cols = boardMatrix[0].length;

        displayMatrix = new Rectangle[rows][cols];

        for (int i = GameConfig.HIDDEN_BUFFER_ROWS; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Rectangle rectangle = new Rectangle(LayoutMetrics.BRICK_SIZE, LayoutMetrics.BRICK_SIZE);
                rectangle.setFill(Color.TRANSPARENT);
                displayMatrix[i][j] = rectangle;
                gamePanel.add(rectangle, j, i - GameConfig.HIDDEN_BUFFER_ROWS);
            }
        }
    }

    private void createActiveBrick(int[][] brickData) {
        brickPanel.getChildren().clear();

        int rows = brickData.length;
        int cols = brickData[0].length;

        activeRectangles = new Rectangle[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Rectangle rectangle = new Rectangle(LayoutMetrics.BRICK_SIZE, LayoutMetrics.BRICK_SIZE);
                setRectangleData(brickData[row][col], rectangle);
                activeRectangles[row][col] = rectangle;
                brickPanel.add(rectangle, col, row);
            }
        }
    }

    private void updateBrickPosition(ViewData brick) {
        double boardOriginX = gamePanel.getLayoutX();
        double boardOriginY = gamePanel.getLayoutY();

        brickPanel.setLayoutX(
                boardOriginX + brick.getXPosition() * LayoutMetrics.brickStep()
        );
        brickPanel.setLayoutY(
                LayoutMetrics.brickPanelYOffset()
                        + boardOriginY
                        + brick.getYPosition() * LayoutMetrics.brickStep()
        );
    }

    private void drawGhost(ViewData brick) {
        if (ghostPane == null) {
            return;
        }

        ghostPane.getChildren().clear();

        int[][] brickData = brick.getBrickData();
        int ghostY = brick.getGhostYPosition();
        int brickX = brick.getXPosition();

        if (ghostY == brick.getYPosition()) {
            return;
        }

        double step = LayoutMetrics.brickStep();
        double brickSize = LayoutMetrics.BRICK_SIZE;
        double strokeWidth = GHOST_STROKE_WIDTH;
        double cornerRadius = Math.max(GHOST_CORNER_RADIUS_MIN,
                LayoutMetrics.BRICK_ARC_SIZE - GHOST_CORNER_RADIUS_OFFSET);
        double inset = strokeWidth * 0.5;

        for (int row = 0; row < brickData.length; row++) {
            for (int col = 0; col < brickData[row].length; col++) {
                if (brickData[row][col] == 0) {
                    continue;
                }

                double cellX = col * step;
                double cellY = row * step;

                Rectangle outline = new Rectangle(cellX, cellY, brickSize, brickSize);
                outline.setFill(Color.TRANSPARENT);
                outline.setStroke(GHOST_OUTLINE_COLOR);
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
                diamond.setStroke(GHOST_DETAIL_COLOR);
                diamond.setStrokeWidth(strokeWidth);
                ghostPane.getChildren().add(diamond);
            }
        }

        double boardOriginX = gamePanel.getLayoutX();
        double boardOriginY = gamePanel.getLayoutY();

        ghostPane.setLayoutX(boardOriginX + brickX * step);
        ghostPane.setLayoutY(
                LayoutMetrics.brickPanelYOffset()
                        + boardOriginY
                        + ghostY * step
        );
    }

    private void setRectangleData(int color, Rectangle rectangle) {
        rectangle.setFill(BlockTextureProvider.getPattern(color));
        rectangle.setArcHeight(LayoutMetrics.BRICK_ARC_SIZE);
        rectangle.setArcWidth(LayoutMetrics.BRICK_ARC_SIZE);
    }
}
