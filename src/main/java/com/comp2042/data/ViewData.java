package com.comp2042.data;

import com.comp2042.util.MatrixOperations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ViewData {

    private final int[][] brickData;
    private final int xPosition;
    private final int yPosition;
    private final int ghostYPosition;
    private final List<int[][]> nextBricksData;

    public ViewData(int[][] brickData, int xPosition, int yPosition, int ghostYPosition, List<int[][]> nextBricksData) {
        this.brickData = brickData;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.ghostYPosition = ghostYPosition;
        this.nextBricksData = copyNextBricks(nextBricksData);
    }

    public int[][] getBrickData() {
        return MatrixOperations.copy(brickData);
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public int getGhostYPosition() {
        return ghostYPosition;
    }

    public List<int[][]> getNextBricksData() {
        return copyNextBricks(nextBricksData);
    }

    private List<int[][]> copyNextBricks(List<int[][]> source) {
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
