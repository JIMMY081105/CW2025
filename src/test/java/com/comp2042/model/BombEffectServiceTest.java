package com.comp2042.model;

import com.comp2042.data.ClearRow;
import com.comp2042.util.MatrixOperations;
import com.comp2042.data.ViewData;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BombEffectServiceTest {

    private static class FakeBoard implements Board {

        private final int[][] matrix;
        private final BooleanProperty gameOver = new SimpleBooleanProperty(false);
        private final ObjectProperty<int[][]> matrixProperty = new SimpleObjectProperty<>();
        private final IntegerProperty scoreProperty = new SimpleIntegerProperty(0);

        FakeBoard(int[][] initialMatrix) {
            this.matrix = initialMatrix;
            this.matrixProperty.set(initialMatrix);
        }

        @Override
        public int[][] getBoardMatrix() {
            return matrix;
        }


        @Override
        public boolean moveBrickDown() {
            return false;
        }

        @Override
        public boolean moveBrickLeft() {
            return false;
        }

        @Override
        public boolean moveBrickRight() {
            return false;
        }

        @Override
        public boolean rotateLeftBrick() {
            return false;
        }

        @Override
        public boolean createNewBrick() {
            return false;
        }

        @Override
        public ViewData getViewData() {
            return null;
        }

        @Override
        public void mergeBrickToBackground() {
        }

        @Override
        public ClearRow clearRows() {
            return null;
        }

        @Override
        public Score getScore() {
            return null;
        }

        @Override
        public BooleanProperty isGameOverProperty() {
            return gameOver;
        }

        @Override
        public ObjectProperty<int[][]> boardMatrixProperty() {
            return matrixProperty;
        }

        @Override
        public IntegerProperty scoreProperty() {
            return scoreProperty;
        }
    }

    @Test
    void applyBomb_ReturnsClearRowWithExplodedArea() {
        int[][] matrix = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 9, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };

        FakeBoard board = new FakeBoard(matrix);

        ClearRow result = BombEffectService.applyBomb(board, 2, 2);

        assertNotNull(result, "applyBomb should return a ClearRow");

        int[][] exploded = MatrixOperations.explodeBomb(matrix, 2, 2);
        ClearRow expected = MatrixOperations.checkRemoving(exploded);

        assertEquals(expected.getLinesRemoved(), result.getLinesRemoved());
        int[][] newMatrix = result.getNewMatrix();
        int[][] expectedMatrix = expected.getNewMatrix();

        assertEquals(expectedMatrix.length, newMatrix.length);
        for (int y = 0; y < newMatrix.length; y++) {
            assertArrayEquals(expectedMatrix[y], newMatrix[y]);
        }
    }

    @Test
    void applyBomb_AtCorner_DoesNotThrow() {
        int[][] matrix = {
                {7, 7, 0},
                {7, 7, 0},
                {0, 0, 0}
        };

        FakeBoard board = new FakeBoard(matrix);

        assertDoesNotThrow(() -> BombEffectService.applyBomb(board, 0, 0));
    }
}
