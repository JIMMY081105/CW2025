package com.comp2042.model.brick;

import com.comp2042.util.MatrixOperations;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBrick implements Brick {

    protected final List<int[][]> brickMatrix = new ArrayList<>();

    protected AbstractBrick() {
        initializeShapes();
    }

    protected abstract void initializeShapes();

    @Override
    public List<int[][]> getShapeMatrix() {
        return MatrixOperations.deepCopyList(brickMatrix);
    }
}

