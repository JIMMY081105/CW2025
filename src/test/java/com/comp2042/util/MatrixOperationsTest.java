package com.comp2042.util;

import com.comp2042.data.ClearRow;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatrixOperationsTest {

    @Test
    void intersect_NoCollision_ReturnsFalse() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        int[][] shape = {
                {1, 1},
                {1, 1}
        };

        boolean result = MatrixOperations.intersect(board, shape, 1, 1);

        assertFalse(result);
    }

    @Test
    void intersect_WithCollision_ReturnsTrue() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        int[][] shape = {
                {1, 1},
                {1, 1}
        };

        boolean result = MatrixOperations.intersect(board, shape, 1, 1);

        assertTrue(result);
    }

    @Test
    void intersect_OutsideBoard_ReturnsTrue() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        int[][] shape = {
                {1, 1},
                {1, 1}
        };

        boolean result = MatrixOperations.intersect(board, shape, -1, 0);

        assertTrue(result);
    }

    @Test
    void copy_ReturnsDeepCopyWithSameValues() {
        int[][] original = {
                {1, 2},
                {3, 4}
        };

        int[][] copy = MatrixOperations.copy(original);

        assertArrayEquals(original, copy);
        assertNotSame(original, copy);

        for (int i = 0; i < original.length; i++) {
            assertNotSame(original[i], copy[i]);
        }
    }

    @Test
    void copy_ModifyingCopyDoesNotAffectOriginal() {
        int[][] original = {
                {1, 2},
                {3, 4}
        };

        int[][] copy = MatrixOperations.copy(original);
        copy[0][0] = 99;

        assertEquals(1, original[0][0]);
        assertEquals(99, copy[0][0]);
    }

    @Test
    void merge_PlacesShapeOnEmptyBoard() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        int[][] shape = {
                {1, 1},
                {1, 1}
        };

        int[][] result = MatrixOperations.merge(board, shape, 1, 1);

        int[][] expected = {
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };

        assertMatrixEquals(expected, result);
    }

    @Test
    void merge_OnlyNonZeroCellsAreWritten() {
        int[][] board = {
                {0, 2, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        int[][] shape = {
                {0, 3},
                {3, 3}
        };

        int[][] result = MatrixOperations.merge(board, shape, 1, 1);

        int[][] expected = {
                {0, 2, 0, 0},
                {0, 2, 3, 0},
                {0, 3, 3, 0},
                {0, 0, 0, 0}
        };

        assertMatrixEquals(expected, result);
    }

    @Test
    void checkRemoving_NoFullRows_ReturnsOriginalAndZeroScore() {
        int[][] board = {
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1}
        };

        ClearRow clearRow = MatrixOperations.checkRemoving(board);

        assertMatrixEquals(board, clearRow.getNewMatrix());
        assertEquals(0, clearRow.getLinesRemoved());
        assertEquals(0, clearRow.getScoreBonus());
    }

    @Test
    void checkRemoving_SingleFullRow_RemovedAndShifted() {
        int[][] board = {
                {0, 0, 0, 0},
                {1, 0, 0, 1},
                {1, 1, 1, 1}, // full
                {2, 0, 2, 0}
        };

        ClearRow clearRow = MatrixOperations.checkRemoving(board);

        int[][] expected = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1},
                {2, 0, 2, 0}
        };

        assertMatrixEquals(expected, clearRow.getNewMatrix());
        assertEquals(1, clearRow.getLinesRemoved());
        assertEquals(GameConstants.SCORE_PER_LINE, clearRow.getScoreBonus());
    }

    @Test
    void checkRemoving_TwoFullRows_CascadeAndScoreSquared() {
        int[][] board = {
                {0, 0, 0, 0},
                {1, 1, 1, 1}, 
                {2, 2, 2, 2}, 
                {3, 0, 0, 3}
        };

        ClearRow clearRow = MatrixOperations.checkRemoving(board);

        int[][] expected = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {3, 0, 0, 3}
        };

        assertMatrixEquals(expected, clearRow.getNewMatrix());
        assertEquals(2, clearRow.getLinesRemoved());
        assertEquals(GameConstants.SCORE_PER_LINE * 2 * 2, clearRow.getScoreBonus());
    }

    @Test
    void deepCopyList_CreatesIndependentCopies() {
        int[][] a = {
                {1, 2},
                {3, 4}
        };
        int[][] b = {
                {5, 6},
                {7, 8}
        };

        List<int[][]> original = new ArrayList<>();
        original.add(a);
        original.add(b);

        List<int[][]> copy = MatrixOperations.deepCopyList(original);

        assertEquals(2, copy.size());
        assertMatrixEquals(a, copy.get(0));
        assertMatrixEquals(b, copy.get(1));

        assertNotSame(original.get(0), copy.get(0));
        assertNotSame(original.get(1), copy.get(1));
    }


    @Test
    void explodeBomb_Clears3x3AndDropsOnlyAboveInSameColumns() {
        int[][] board = {
                {0, 0, 1, 0, 0}, 
                {0, 0, 2, 0, 0}, 
                {0, 0, 3, 0, 0},
                {0, 0, 4, 0, 0},
                {0, 0, 5, 0, 0}, 
                {0, 0, 6, 0, 0}  
        };

        int[][] exploded = MatrixOperations.explodeBomb(board, 2, 3);

        int[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 2, 0, 0},
                {0, 0, 6, 0, 0}
        };

        assertMatrixEquals(expected, exploded);
    }

    @Test
    void explodeBomb_DoesNotMoveColumnsOutsideBombArea() {
        int[][] board = {
                {9, 0, 1, 0, 8},
                {9, 0, 2, 0, 8},
                {9, 0, 3, 0, 8},
                {9, 0, 4, 0, 8},
                {9, 0, 5, 0, 8},
                {9, 0, 6, 0, 8}
        };

        int[][] exploded = MatrixOperations.explodeBomb(board, 2, 3);

        // Column 0 and 4 must be identical to original
        for (int row = 0; row < board.length; row++) {
            assertEquals(board[row][0], exploded[row][0], "Column 0 changed unexpectedly");
            assertEquals(board[row][4], exploded[row][4], "Column 4 changed unexpectedly");
        }
    }

    @Test
    void explodeBomb_HandlesBordersSafely() {
        int[][] board = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] exploded = MatrixOperations.explodeBomb(board, 0, 0);

        assertEquals(0, exploded[0][0]);
        assertEquals(0, exploded[0][1]);
        assertEquals(0, exploded[1][0]);
        assertEquals(0, exploded[1][1]);

        assertEquals(3, exploded.length);
        assertEquals(3, exploded[0].length);
    }


    private void assertMatrixEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length, "Height mismatch");
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row], "Row " + row + " differs");
        }
    }
}
