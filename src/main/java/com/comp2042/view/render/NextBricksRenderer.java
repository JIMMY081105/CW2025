package com.comp2042.view.render;

import com.comp2042.util.BlockTextureProvider;
import com.comp2042.util.GameConstants;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class NextBricksRenderer {

    private final VBox nextBricksList;
    private final List<GridPane> nextPreviewGrids = new ArrayList<>();

    public NextBricksRenderer(VBox nextBricksList) {
        this.nextBricksList = nextBricksList;
    }

    public void initialisePanels() {
        if (nextBricksList == null) {
            return;
        }

        nextBricksList.getChildren().clear();
        nextPreviewGrids.clear();

        for (int i = 0; i < GameConstants.NEXT_PREVIEW_COUNT; i++) {
            GridPane previewGrid = new GridPane();
            previewGrid.setHgap(GameConstants.NEXT_BRICK_GAP);
            previewGrid.setVgap(GameConstants.NEXT_BRICK_GAP);
            previewGrid.setAlignment(Pos.CENTER);
            nextPreviewGrids.add(previewGrid);
            nextBricksList.getChildren().add(previewGrid);
        }
    }

    public void renderNextBricks(List<int[][]> nextBricksData) {
        if (nextBricksList == null) {
            return;
        }
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
        Rectangle rectangle = new Rectangle(GameConstants.NEXT_BRICK_SIZE, GameConstants.NEXT_BRICK_SIZE);
        rectangle.setFill(BlockTextureProvider.getPattern(colorIndex));
        rectangle.setArcHeight(GameConstants.BRICK_ARC_SIZE);
        rectangle.setArcWidth(GameConstants.BRICK_ARC_SIZE);
        return rectangle;
    }
}
