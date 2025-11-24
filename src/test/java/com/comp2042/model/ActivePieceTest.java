package com.comp2042.model;

import com.comp2042.model.brick.Brick;
import com.comp2042.util.GameConstants;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActivePieceTest {

    private static class TestBrick implements Brick {
        private final List<int[][]> shapes;

        TestBrick(List<int[][]> shapes) {
            this.shapes = shapes;
        }

        @Override
        public List<int[][]> getShapeMatrix() {
            return shapes;
        }
    }

    @Test
    void spawn_SetsInitialPositionAndShape() {
        ActivePiece activePiece = new ActivePiece();

        List<int[][]> shapes = new ArrayList<>();
        shapes.add(new int[][]{
                {1, 0},
                {1, 1}
        });
        Brick brick = new TestBrick(shapes);

        activePiece.spawn(brick);

        assertEquals(GameConstants.SPAWN_X, activePiece.getX());
        assertEquals(GameConstants.SPAWN_Y, activePiece.getY());

        int[][] shape = activePiece.getShape();
        assertEquals(2, shape.length);
        assertEquals(2, shape[0].length);
        assertEquals(1, shape[0][0]);
    }

    @Test
    void move_OnEmptyBoard_UpdatesPosition() {
        int[][] board = new int[GameConstants.BOARD_HEIGHT][GameConstants.BOARD_WIDTH];

        ActivePiece activePiece = new ActivePiece();
        List<int[][]> shapes = new ArrayList<>();
        shapes.add(new int[][]{
                {1, 1},
                {1, 1}
        });
        Brick brick = new TestBrick(shapes);
        activePiece.spawn(brick);

        int startX = activePiece.getX();
        int startY = activePiece.getY();

        boolean movedDown = activePiece.move(board, 0, 1);
        assertTrue(movedDown);
        assertEquals(startY + 1, activePiece.getY());
        assertEquals(startX, activePiece.getX());

        boolean movedLeft = activePiece.move(board, -1, 0);
        assertTrue(movedLeft);
        assertEquals(startX - 1, activePiece.getX());
        assertEquals(startY + 1, activePiece.getY());
    }

    @Test
    void move_BlockedByBottom_ReturnsFalseAndKeepsPosition() {
        int[][] board = new int[GameConstants.BOARD_HEIGHT][GameConstants.BOARD_WIDTH];

        ActivePiece activePiece = new ActivePiece();
        List<int[][]> shapes = new ArrayList<>();
        shapes.add(new int[][]{
                {1, 1},
                {1, 1}
        });
        Brick brick = new TestBrick(shapes);
        activePiece.spawn(brick);

        while (activePiece.move(board, 0, 1)) {
        }

        int yBefore = activePiece.getY();

        boolean moved = activePiece.move(board, 0, 1);

        assertFalse(moved);
        assertEquals(yBefore, activePiece.getY());
    }

    @Test
    void rotateLeft_FreeSpace_ChangesShape() {
        int[][] board = new int[GameConstants.BOARD_HEIGHT][GameConstants.BOARD_WIDTH];

        int[][] shape0 = {
                {1, 0},
                {1, 1}
        };
        int[][] shape1 = {
                {0, 1},
                {1, 1}
        };

        List<int[][]> shapes = new ArrayList<>();
        shapes.add(shape0);
        shapes.add(shape1);

        ActivePiece activePiece = new ActivePiece();
        Brick brick = new TestBrick(shapes);
        activePiece.spawn(brick);

        int[][] before = activePiece.getShape();

        boolean rotated = activePiece.rotateLeft(board);
        int[][] after = activePiece.getShape();

        assertTrue(rotated);
        assertFalse(Arrays.deepEquals(before, after));
    }

    @Test
    void rotateLeft_WithCollision_ReturnsFalseAndKeepsShape() {
        int[][] board = new int[GameConstants.BOARD_HEIGHT][GameConstants.BOARD_WIDTH];

        board[GameConstants.SPAWN_Y][GameConstants.SPAWN_X + 1] = 9;

        int[][] shape0 = {
                {1, 0},
                {0, 0}
        };
        int[][] shape1 = {
                {0, 1},
                {0, 0}
        };

        List<int[][]> shapes = new ArrayList<>();
        shapes.add(shape0);
        shapes.add(shape1);

        ActivePiece activePiece = new ActivePiece();
        Brick brick = new TestBrick(shapes);
        activePiece.spawn(brick);

        int[][] before = activePiece.getShape();

        boolean rotated = activePiece.rotateLeft(board);
        int[][] after = activePiece.getShape();

        assertFalse(rotated);
        assertTrue(Arrays.deepEquals(before, after));
    }
}
