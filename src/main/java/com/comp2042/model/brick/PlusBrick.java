package com.comp2042.model.brick;

final class PlusBrick extends AbstractBrick {

    @Override
    protected void initializeShapes() {
        brickMatrix.add(new int[][]{
                {0, 8, 0, 0},
                {8, 8, 8, 0},
                {0, 8, 0, 0},
                {0, 0, 0, 0}
        });
    }
}

