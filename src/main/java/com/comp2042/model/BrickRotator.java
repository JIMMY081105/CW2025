package com.comp2042.model;

import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.NextShapeInfo;

import java.util.List;

/**
 * The {@code BrickRotator} class coordinates rotation state for a single brick, exposing current and next
 * orientations while validating index bounds. It separates rotation management from placement logic in the board.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/BrickRotator.java">
 * BrickRotator.java</a>
 */
public class BrickRotator {

    private Brick brick;
    private int currentShape = 0;

    /**
     * Returns the next rotation for the current brick without mutating state.
     *
     * @return info about the next shape matrix and its index.
     */
    public NextShapeInfo getNextShape() {
        ensureBrickSet();
        List<int[][]> shapes = brick.getShapeMatrix();
        if (shapes.isEmpty()) {
            throw new IllegalStateException("Brick has no shapes");
        }

        int nextShape = (currentShape + 1) % shapes.size();
        return new NextShapeInfo(shapes.get(nextShape), nextShape);
    }

    /**
     * Retrieves the current orientation matrix.
     *
     * @return the active shape matrix.
     */
    public int[][] getCurrentShape() {
        ensureBrickSet();
        List<int[][]> shapes = brick.getShapeMatrix();
        if (shapes.isEmpty()) {
            throw new IllegalStateException("Brick has no shapes");
        }
        return shapes.get(currentShape);
    }

    /**
     * Sets the active orientation index for the current brick.
     *
     * @param currentShape index into the brick's shape list.
     */
    public void setCurrentShape(int currentShape) {
        ensureBrickSet();
        List<int[][]> shapes = brick.getShapeMatrix();
        int size = shapes.size();
        if (currentShape < 0 || currentShape >= size) {
            throw new IllegalArgumentException("Invalid shape index: " + currentShape);
        }
        this.currentShape = currentShape;
    }

    /**
     * Assigns the brick to manage and resets rotation to the first orientation.
     *
     * @param brick the brick to rotate.
     */
    public void setBrick(Brick brick) {
        if (brick == null) {
            throw new IllegalArgumentException("Brick cannot be null");
        }
        this.brick = brick;
        currentShape = 0;
    }

    private void ensureBrickSet() {
        if (brick == null) {
            throw new IllegalStateException("Brick not set in BrickRotator");
        }
    }
}
