package com.comp2042.model.brick;

final class OBrick extends AbstractBrick {

    @Override
    protected void initializeShapes() {
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {0, 4, 4, 0},
                {0, 4, 4, 0},
                {0, 0, 0, 0}
        });
    }
}
