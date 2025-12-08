package com.comp2042.model.brick;

import com.comp2042.util.MatrixOperations;

/**
 * The {@code NextShapeInfo} class holds details about an upcoming brick rotation, including its shape matrix and index.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/brick/NextShapeInfo.java">
 * NextShapeInfo.java</a>
 */
public final class NextShapeInfo {

    private final int[][] shape;
    private final int position;

    /**
     * Constructs a descriptor for the next shape orientation.
     *
     * @param shape     matrix representing the next orientation.
     * @param position  index of the orientation within the brick.
     */
    public NextShapeInfo(final int[][] shape, final int position) {
        this.shape = shape;
        this.position = position;
    }

    /**
     * Returns a defensive copy of the shape matrix.
     *
     * @return next shape matrix.
     */
    public int[][] getShape() {
        return MatrixOperations.copy(shape);
    }

    /**
     * Returns the index of the next orientation.
     *
     * @return rotation index.
     */
    public int getPosition() {
        return position;
    }
}

