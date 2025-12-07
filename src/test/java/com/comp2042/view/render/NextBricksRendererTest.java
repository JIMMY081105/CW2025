package com.comp2042.view.render;

import com.comp2042.util.GameConfig;
import com.comp2042.util.LayoutMetrics;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NextBricksRendererTest {

    @Test
    void initialisePanels_CreatesCorrectNumberOfPreviewGrids() {
        VBox container = new VBox();
        NextBricksRenderer renderer = new NextBricksRenderer(container);

        renderer.initialisePanels();

        assertEquals(
                GameConfig.NEXT_PREVIEW_COUNT,
                container.getChildren().size(),
                "initialisePanels() should create one GridPane per preview slot"
        );

        for (Node node : container.getChildren()) {
            assertTrue(node instanceof GridPane, "Each child should be a GridPane");
            GridPane grid = (GridPane) node;

            assertEquals(LayoutMetrics.NEXT_BRICK_GAP, grid.getHgap(), 0.0001);
            assertEquals(LayoutMetrics.NEXT_BRICK_GAP, grid.getVgap(), 0.0001);
            assertEquals(Pos.CENTER, grid.getAlignment(), "Preview grids should be center-aligned");
        }
    }

    @Test
    void renderNextBricks_PopulatesFirstGridWithRectanglesForNonZeroCells() {
        VBox container = new VBox();
        NextBricksRenderer renderer = new NextBricksRenderer(container);
        renderer.initialisePanels();

        int[][] brick = {
                {1, 0},
                {2, 3}
        };

        List<int[][]> nextBricks = new ArrayList<>();
        nextBricks.add(brick);

        renderer.renderNextBricks(nextBricks);

        assertFalse(container.getChildren().isEmpty(), "There should be at least one preview grid");
        GridPane firstGrid = (GridPane) container.getChildren().get(0);

        long rectangleCount = firstGrid.getChildren().stream()
                .filter(n -> n instanceof Rectangle)
                .count();
        assertEquals(3, rectangleCount, "Should render one Rectangle for each non-zero cell");

        for (Node node : firstGrid.getChildren()) {
            assertTrue(node instanceof Rectangle);

            Integer colIdx = GridPane.getColumnIndex(node);
            Integer rowIdx = GridPane.getRowIndex(node);
            int col = colIdx == null ? 0 : colIdx;
            int row = rowIdx == null ? 0 : rowIdx;

            assertNotEquals(
                    0,
                    brick[row][col],
                    "No Rectangle should be created for zero cells in the brick matrix"
            );
        }
    }
}
