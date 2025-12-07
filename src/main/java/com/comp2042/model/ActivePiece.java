package com.comp2042.model;

import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.NextShapeInfo;
import com.comp2042.util.GameConstants;
import com.comp2042.util.MatrixOperations;

public class ActivePiece {

    private final BrickRotator brickRotator = new BrickRotator();
    private int x;
    private int y;

    public void spawn(Brick brick) {
        brickRotator.setBrick(brick);
        this.x = GameConstants.SPAWN_X;
        this.y = GameConstants.SPAWN_Y;
    }

    public boolean move(int[][] boardMatrix, int dx, int dy) {
        int nextX = x + dx;
        int nextY = y + dy;

        if (collides(boardMatrix, brickRotator.getCurrentShape(), nextX, nextY)) {
            return false;
        }

        x = nextX;
        y = nextY;
        return true;
    }

    public boolean rotateLeft(int[][] boardMatrix) {
        NextShapeInfo nextShape = brickRotator.getNextShape();

        if (collides(boardMatrix, nextShape.getShape(), x, y)) {
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

    public int getGhostY(int[][] boardMatrix) {
        int ghostY = y;
        int[][] shape = brickRotator.getCurrentShape();

        while (!collides(boardMatrix, shape, x, ghostY + 1)) {
            ghostY++;
        }

        return ghostY;
    }

    private boolean collides(int[][] boardMatrix, int[][] shape, int targetX, int targetY) {
        return MatrixOperations.intersect(boardMatrix, shape, targetX, targetY);
    }
}
