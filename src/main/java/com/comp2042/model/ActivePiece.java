package com.comp2042.model;

import com.comp2042.data.ViewData;
import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.NextShapeInfo;
import com.comp2042.util.GameConstants;
import com.comp2042.util.MatrixOperations;

import java.util.ArrayList;
import java.util.List;

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

    public ViewData toViewData(List<Brick> nextBricks, int[][] boardMatrix) {
        List<int[][]> previews = buildPreviews(nextBricks);
        int ghostY = calculateGhostY(boardMatrix);
        return new ViewData(getShape(), x, y, ghostY, previews);
    }

private boolean collides(int[][] boardMatrix, int[][] shape, int targetX, int targetY) {
        return MatrixOperations.intersect(boardMatrix, shape, targetX, targetY);
    }

    private int calculateGhostY(int[][] boardMatrix) {
        int ghostY = y;
        int[][] shape = brickRotator.getCurrentShape();

        while (!collides(boardMatrix, shape, x, ghostY + 1)) {
            ghostY++;
        }

        return ghostY;
    }

    private List<int[][]> buildPreviews(List<Brick> nextBricks) {
        List<int[][]> previews = new ArrayList<>();
        if (nextBricks == null) {
            return previews;
        }

        for (Brick brick : nextBricks) {
            if (brick == null) {
                continue;
            }
            List<int[][]> shapes = brick.getShapeMatrix();
            if (shapes.isEmpty()) {
                continue;
            }

            previews.add(shapes.get(0));
        }

        return previews;
    }
}
