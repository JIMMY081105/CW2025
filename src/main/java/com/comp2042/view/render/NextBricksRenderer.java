package com.comp2042.view.render;

import com.comp2042.util.BlockTextureProvider;
import com.comp2042.util.GameConfig;
import com.comp2042.util.LayoutMetrics;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The {@code NextBricksRenderer} class renders the list of upcoming bricks into separate preview grids for display
 * in the side panel.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/render/NextBricksRenderer.java">
 * NextBricksRenderer.java</a>
 */
public final class NextBricksRenderer {

    private final VBox nextBricksList;
    private final List<GridPane> nextPreviewGrids = new ArrayList<>();

    /**
     * Creates a renderer bound to the container that will host preview grids.
     *
     * @param nextBricksList container for preview grids.
     */
    public NextBricksRenderer(VBox nextBricksList) {
        this.nextBricksList = Objects.requireNonNull(nextBricksList, "nextBricksList must not be null");
    }

    /**
     * Prepares empty preview panels according to the configured preview count.
     */
    public void initialisePanels() {
        nextBricksList.getChildren().clear();
        nextPreviewGrids.clear();

        for (int i = 0; i < GameConfig.NEXT_PREVIEW_COUNT; i++) {
            GridPane previewGrid = new GridPane();
            previewGrid.setHgap(LayoutMetrics.NEXT_BRICK_GAP);
            previewGrid.setVgap(LayoutMetrics.NEXT_BRICK_GAP);
            previewGrid.setAlignment(Pos.CENTER);
            nextPreviewGrids.add(previewGrid);
            nextBricksList.getChildren().add(previewGrid);
        }
    }

    /**
     * Renders the given preview matrices into the prepared grids.
     *
     * @param nextBricksData list of brick matrices in spawn order.
     */
    public void renderNextBricks(List<int[][]> nextBricksData) {
        if (nextPreviewGrids.isEmpty()) {
            initialisePanels();
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
        Rectangle rectangle = new Rectangle(LayoutMetrics.NEXT_BRICK_SIZE, LayoutMetrics.NEXT_BRICK_SIZE);
        rectangle.setFill(BlockTextureProvider.getPattern(colorIndex));
        rectangle.setArcHeight(LayoutMetrics.BRICK_ARC_SIZE);
        rectangle.setArcWidth(LayoutMetrics.BRICK_ARC_SIZE);
        return rectangle;
    }
}
