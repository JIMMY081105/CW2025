package com.comp2042.data;

import com.comp2042.util.MatrixOperations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The {@code ViewData} class is an immutable snapshot of data required to render the active piece, ghost
 * projection, and upcoming previews. It provides defensive copies to decouple view rendering from model state.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/data/ViewData.java">
 * ViewData.java</a>
 */
public final class ViewData {

    private final int[][] brickData;
    private final int xPosition;
    private final int yPosition;
    private final int ghostYPosition;
    private final List<int[][]> nextBricksData;

    /**
     * Constructs a snapshot of the active piece and previews.
     *
     * @param brickData      shape matrix of the active piece.
     * @param xPosition      x-coordinate of the active piece.
     * @param yPosition      y-coordinate of the active piece.
     * @param ghostYPosition y-coordinate of the ghost drop position.
     * @param nextBricksData preview shapes for upcoming bricks.
     */
    public ViewData(int[][] brickData, int xPosition, int yPosition, int ghostYPosition, List<int[][]> nextBricksData) {
        this.brickData = MatrixOperations.copy(brickData);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.ghostYPosition = ghostYPosition;
        this.nextBricksData = copyNextBricks(nextBricksData);
    }

    /**
     * Returns a defensive copy of the active brick matrix.
     *
     * @return active brick data.
     */
    public int[][] getBrickData() {
        return MatrixOperations.copy(brickData);
    }

    /**
     * Returns the x-coordinate of the active piece.
     *
     * @return x position.
     */
    public int getXPosition() {
        return xPosition;
    }

    /**
     * Returns the y-coordinate of the active piece.
     *
     * @return y position.
     */
    public int getYPosition() {
        return yPosition;
    }

    /**
     * Returns the ghost drop y-coordinate.
     *
     * @return ghost y position.
     */
    public int getGhostYPosition() {
        return ghostYPosition;
    }

    /**
     * Returns deep copies of preview brick matrices.
     *
     * @return list of preview shapes.
     */
    public List<int[][]> getNextBricksData() {
        return copyNextBricks(nextBricksData);
    }

    private static List<int[][]> copyNextBricks(List<int[][]> source) {
        if (source == null || source.isEmpty()) {
            return Collections.emptyList();
        }
        List<int[][]> copies = new ArrayList<>(source.size());
        for (int[][] shape : source) {
            copies.add(MatrixOperations.copy(shape));
        }
        return copies;
    }
}
