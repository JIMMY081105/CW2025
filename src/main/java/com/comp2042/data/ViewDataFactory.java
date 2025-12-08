package com.comp2042.data;

import com.comp2042.model.ActivePiece;
import com.comp2042.model.brick.Brick;
import com.comp2042.util.GameConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code ViewDataFactory} class builds {@link ViewData} snapshots from the active piece, board matrix,
 * and upcoming bricks. It centralises preview selection and ghost position calculation for rendering.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/data/ViewDataFactory.java">
 * ViewDataFactory.java</a>
 */
public final class ViewDataFactory {

    private ViewDataFactory() {
    }

    /**
     * Creates a {@link ViewData} instance for the current active piece and preview queue.
     *
     * @param activePiece the active falling piece.
     * @param boardMatrix current board matrix for ghost computation.
     * @param nextBricks  preview bricks to display.
     * @return populated view data snapshot.
     */
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

        int maxCount = GameConfig.NEXT_PREVIEW_COUNT;
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
