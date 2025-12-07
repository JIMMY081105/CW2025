package com.comp2042.data;

import com.comp2042.model.ActivePiece;
import com.comp2042.model.brick.Brick;
import com.comp2042.util.GameConstants;

import java.util.ArrayList;
import java.util.List;

public final class ViewDataFactory {

    private ViewDataFactory() {
    }

    public static ViewData createViewData(ActivePiece activePiece,
                                          int[][] boardMatrix,
                                          List<Brick> nextBricks) {
        List<int[][]> previews = buildPreviews(nextBricks);
        int ghostY = activePiece.getGhostY(boardMatrix);
        return new ViewData(activePiece.getShape(), activePiece.getX(), activePiece.getY(), ghostY, previews);
    }

    private static List<int[][]> buildPreviews(List<Brick> nextBricks) {
        List<int[][]> previews = new ArrayList<>();
        if (nextBricks == null) {
            return previews;
        }

        int maxCount = GameConstants.NEXT_PREVIEW_COUNT;
        int size = nextBricks.size();

        for (int i = 0; i < maxCount && i < size; i++) {
            Brick brick = nextBricks.get(i);
            if (brick == null) {
                continue;
            }
            List<int[][]> shapes = brick.getShapeMatrix();
            if (shapes.isEmpty()) {
                continue;
            }
            previews.add(shapes.get(0));
        }

        return previews;
    }
}
