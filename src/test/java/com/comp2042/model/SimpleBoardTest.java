package com.comp2042.model;

import com.comp2042.data.ClearRow;
import com.comp2042.data.ViewData;
import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.BrickFactory;
import com.comp2042.model.brick.BrickGenerator;
import com.comp2042.util.GameConstants;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleBoardTest {

    @Test
    void createNewBrick_WhenSpawnBlocked_SetsGameOver() {
        SimpleBoard board = new SimpleBoard(GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT);

        int[][] matrix = board.getBoardMatrix();
        for (int y = GameConstants.SPAWN_Y; y < GameConstants.SPAWN_Y + 4 && y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                matrix[y][x] = 1;
            }
        }

        boolean gameOver = board.createNewBrick();

        assertTrue(gameOver);
        assertTrue(board.isGameOverProperty().get());
    }


    @Test
    void moveBrickLeftRightDown_UpdatesViewDataPosition() {
        SimpleBoard boardLeft = new SimpleBoard(GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT);
        boardLeft.createNewBrick();
        ViewData initialLeft = boardLeft.getViewData();

        boolean movedLeft = boardLeft.moveBrickLeft();
        ViewData afterLeft = boardLeft.getViewData();

        assertTrue(movedLeft);
        assertEquals(initialLeft.getXPosition() - 1, afterLeft.getXPosition());
        assertEquals(initialLeft.getYPosition(), afterLeft.getYPosition());

        SimpleBoard boardRight = new SimpleBoard(GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT);
        boardRight.createNewBrick();
        ViewData initialRight = boardRight.getViewData();

        boolean movedRight = boardRight.moveBrickRight();
        ViewData afterRight = boardRight.getViewData();

        assertTrue(movedRight);
        assertEquals(initialRight.getXPosition() + 1, afterRight.getXPosition());
        assertEquals(initialRight.getYPosition(), afterRight.getYPosition());

        SimpleBoard boardDown = new SimpleBoard(GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT);
        boardDown.createNewBrick();
        ViewData initialDown = boardDown.getViewData();

        boolean movedDown = boardDown.moveBrickDown();
        ViewData afterDown = boardDown.getViewData();

        assertTrue(movedDown);
        assertEquals(initialDown.getXPosition(), afterDown.getXPosition());
        assertEquals(initialDown.getYPosition() + 1, afterDown.getYPosition());
    }

    @Test
    void clearRows_RemovesFullRowsAndUpdatesMatrixProperty() {
        int width = 4;
        int height = 4;
        SimpleBoard board = new SimpleBoard(width, height);

        int[][] matrix = board.getBoardMatrix();
        for (int x = 0; x < width; x++) {
            matrix[height - 1][x] = 1;
        }

        ClearRow clearRow = board.clearRows();

        assertEquals(1, clearRow.getLinesRemoved());

        int[][] newMatrix = board.getBoardMatrix();
        for (int x = 0; x < width; x++) {
            assertEquals(0, newMatrix[height - 1][x]);
        }

        assertSame(newMatrix, board.boardMatrixProperty().get());
    }

    @Test
    void clearRows_SeparatedRows_RemovesBothAndUpdatesMatrixProperty() {
        SimpleBoard board = new SimpleBoard(4, 4);
        int[][] matrix = board.getBoardMatrix();

        for (int x = 0; x < 4; x++) {
            matrix[1][x] = 1;
        }

        matrix[2][1] = 1;

        for (int x = 0; x < 4; x++) {
            matrix[3][x] = 1;
        }

        ClearRow result = board.clearRows();

        assertEquals(2, result.getLinesRemoved());

        int[][] newMatrix = board.getBoardMatrix();

        assertArrayEquals(new int[]{0, 0, 0, 0}, newMatrix[0]);
        assertArrayEquals(new int[]{0, 0, 0, 0}, newMatrix[1]);
        assertArrayEquals(new int[]{0, 0, 0, 0}, newMatrix[2]);
        assertArrayEquals(new int[]{0, 1, 0, 0}, newMatrix[3]);
    }

    @Test
    void nextPreviews_UpdateWhenNewBricksSpawned() {
        List<Brick> bricks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            bricks.add(BrickFactory.createBrick(i % BrickFactory.getBrickCount()));
        }
        BrickGenerator generator = new FixedBrickGenerator(bricks);
        SimpleBoard board = new SimpleBoard(GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT, generator);

        board.createNewBrick();
        List<int[][]> firstPreview = board.getViewData().getNextBricksData();

        assertEquals(GameConstants.NEXT_PREVIEW_COUNT, firstPreview.size());
        assertMatrixEquals(bricks.get(1).getShapeMatrix().get(0), firstPreview.get(0));
        assertMatrixEquals(bricks.get(2).getShapeMatrix().get(0), firstPreview.get(1));

        board.mergeBrickToBackground();
        clearBoard(board.getBoardMatrix());
        board.createNewBrick();
        List<int[][]> secondPreview = board.getViewData().getNextBricksData();

        assertMatrixEquals(bricks.get(2).getShapeMatrix().get(0), secondPreview.get(0));
        assertMatrixEquals(bricks.get(3).getShapeMatrix().get(0), secondPreview.get(1));
    }

    private void assertMatrixEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int y = 0; y < expected.length; y++) {
            assertArrayEquals(expected[y], actual[y]);
        }
    }

    private void clearBoard(int[][] matrix) {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                matrix[y][x] = 0;
            }
        }
    }

    private static class FixedBrickGenerator implements BrickGenerator {
        private final Deque<Brick> bricks;

        FixedBrickGenerator(List<Brick> bricks) {
            this.bricks = new ArrayDeque<>(bricks);
        }

        @Override
        public Brick getBrick() {
            return bricks.poll();
        }

        @Override
        public Brick getNextBrick() {
            return bricks.peek();
        }

        @Override
        public List<Brick> preview(int count) {
            List<Brick> list = new ArrayList<>(bricks);
            return list.subList(0, Math.min(count, list.size()));
        }
    }

}
