package com.comp2042.model.brick;

import com.comp2042.util.MatrixOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code AbstractBrick} class provides shared storage and deep-copying support for concrete brick shapes,
 * requiring subclasses to initialise their rotation matrices.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/brick/AbstractBrick.java">
 * AbstractBrick.java</a>
 */
public abstract class AbstractBrick implements Brick {

    protected final List<int[][]> brickMatrix = new ArrayList<>();

    /**
     * Constructs a brick and triggers shape initialisation.
     */
    protected AbstractBrick() {
        initializeShapes();
    }

    protected abstract void initializeShapes();

    @Override
    /**
     * Returns deep copies of the rotation matrices for this brick.
     *
     * @return list of shape matrices.
     */
    public List<int[][]> getShapeMatrix() {
        return MatrixOperations.deepCopyList(brickMatrix);
    }
}

