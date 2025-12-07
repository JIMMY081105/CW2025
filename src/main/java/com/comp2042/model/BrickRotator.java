package com.comp2042.model;

import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.NextShapeInfo;

import java.util.List;

public class BrickRotator {

    private Brick brick;
    private int currentShape = 0;

    public NextShapeInfo getNextShape() {
        ensureBrickSet();
        List<int[][]> shapes = brick.getShapeMatrix();
        if (shapes.isEmpty()) {
            throw new IllegalStateException("Brick has no shapes");
        }

        int nextShape = (currentShape + 1) % shapes.size();
        return new NextShapeInfo(shapes.get(nextShape), nextShape);
    }

    public int[][] getCurrentShape() {
        ensureBrickSet();
        List<int[][]> shapes = brick.getShapeMatrix();
        if (shapes.isEmpty()) {
            throw new IllegalStateException("Brick has no shapes");
        }
        return shapes.get(currentShape);
    }

    public void setCurrentShape(int currentShape) {
        ensureBrickSet();
        List<int[][]> shapes = brick.getShapeMatrix();
        int size = shapes.size();
        if (currentShape < 0 || currentShape >= size) {
            throw new IllegalArgumentException("Invalid shape index: " + currentShape);
        }
        this.currentShape = currentShape;
    }

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
