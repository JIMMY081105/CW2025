package com.comp2042.model;

import com.comp2042.data.ViewData;
import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.NextShapeInfo;
import com.comp2042.util.MatrixOperations;

public class ActivePiece {

    private final BrickRotator brickRotator = new BrickRotator();
    private Brick brick;
    private int x;
    private int y;

    public void spawn(Brick brick) {
        this.brick = brick;
        brickRotator.setBrick(brick);
        x = com.comp2042.util.GameConstants.SPAWN_X;
        y = com.comp2042.util.GameConstants.SPAWN_Y;
    }

    public boolean move(int[][] boardMatrix, int dx, int dy) {
        int nextX = x + dx;
        int nextY = y + dy;
        boolean conflict = MatrixOperations.intersect(boardMatrix, brickRotator.getCurrentShape(), nextX, nextY);
        if (conflict) {
            return false;
        }
        x = nextX;
        y = nextY;
        return true;
    }

    public boolean rotateLeft(int[][] boardMatrix) {
        NextShapeInfo nextShape = brickRotator.getNextShape();
        boolean conflict = MatrixOperations.intersect(boardMatrix, nextShape.getShape(), x, y);
        if (conflict) {
            return false;
        }
        brickRotator.setCurrentShape(nextShape.getPosition());
        return true;
    }

    public int[][] getShape() {
        return brickRotator.getCurrentShape();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ViewData toViewData(Brick nextBrick) {
        int[][] nextData = new int[0][0];
        if (nextBrick != null && !nextBrick.getShapeMatrix().isEmpty()) {
            nextData = nextBrick.getShapeMatrix().get(0);
        }
        return new ViewData(getShape(), x, y, nextData);
    }
}
