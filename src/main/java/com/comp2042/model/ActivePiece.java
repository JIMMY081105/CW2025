package com.comp2042.model;

import com.comp2042.data.ViewData;
import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.NextShapeInfo;
import com.comp2042.util.MatrixOperations;
import java.util.ArrayList;
import java.util.List;

public class ActivePiece {

    private final BrickRotator brickRotator = new BrickRotator();
    private int x;
    private int y;

    public void spawn(Brick brick) {
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

    public ViewData toViewData(List<Brick> nextBricks, int[][] boardMatrix) {
        List<int[][]> previews = new ArrayList<>();
        if (nextBricks != null) {
            for (Brick brick : nextBricks) {
                if (brick != null && !brick.getShapeMatrix().isEmpty()) {
                    previews.add(brick.getShapeMatrix().get(0));
                }
            }
        }
        int ghostY = calculateGhostY(boardMatrix);
        return new ViewData(getShape(), x, y, ghostY, previews);
    }

    private int calculateGhostY(int[][] boardMatrix) {
        int ghostY = y;
        int[][] shape = brickRotator.getCurrentShape();
        while (!MatrixOperations.intersect(boardMatrix, shape, x, ghostY + 1)) {
            ghostY++;
        }
        return ghostY;
    }
}
