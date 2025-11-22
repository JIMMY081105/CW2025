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
                {2, 2},
                {2, 2}
        };

        int[][] result = MatrixOperations.merge(board, shape, 1, 1);

        assertEquals(0, result[0][0]);
        assertEquals(0, result[0][1]);
        assertEquals(0, result[0][2]);
        assertEquals(0, result[0][3]);

        assertEquals(0, result[1][0]);
        assertEquals(2, result[1][1]);
        assertEquals(2, result[1][2]);
        assertEquals(0, result[1][3]);

        assertEquals(0, result[2][0]);
        assertEquals(2, result[2][1]);
        assertEquals(2, result[2][2]);
        assertEquals(0, result[2][3]);
    }

    @Test
    void merge_DoesNotModifyOriginalBoard() {
        int[][] board = {
                {0, 0},
                {0, 0}
        };

        int[][] shape = {
                {1, 1},
                {1, 1}
        };

        int[][] result = MatrixOperations.merge(board, shape, 0, 0);

        assertArrayEquals(new int[]{0, 0}, board[0]);
        assertArrayEquals(new int[]{0, 0}, board[1]);

        assertEquals(1, result[0][0]);
        assertEquals(1, result[0][1]);
        assertEquals(1, result[1][0]);
        assertEquals(1, result[1][1]);
    }

    @Test
    void merge_DoesNotOverrideExistingBlocks() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 3, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        int[][] shape = {
                {2, 2},
                {2, 2}
        };

        int[][] result = MatrixOperations.merge(board, shape, 2, 2);

        assertEquals(3, result[1][1]);
        assertEquals(2, result[2][2]);
        assertEquals(2, result[2][3]);
        assertEquals(2, result[3][2]);
        assertEquals(2, result[3][3]);
    }

    @Test
    void checkRemoving_NoFullRows_NoLinesRemoved() {
        int[][] board = {
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 0},
                {0, 0, 1, 0}
        };

        ClearRow clearRow = MatrixOperations.checkRemoving(board);

        assertEquals(0, clearRow.getLinesRemoved());
        assertEquals(0, clearRow.getScoreBonus());
        assertArrayEquals(board, clearRow.getNewMatrix());
    }

    @Test
    void checkRemoving_OneFullRow_CorrectLinesRemovedAndScore() {
        int[][] board = {
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        ClearRow clearRow = MatrixOperations.checkRemoving(board);

        assertEquals(1, clearRow.getLinesRemoved());
        assertEquals(GameConstants.SCORE_PER_LINE * 1 * 1, clearRow.getScoreBonus());
    }

    @Test
    void checkRemoving_TwoFullRows_CorrectLinesRemovedAndScore() {
        int[][] board = {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        ClearRow clearRow = MatrixOperations.checkRemoving(board);

        assertEquals(2, clearRow.getLinesRemoved());
        assertEquals(GameConstants.SCORE_PER_LINE * 2 * 2, clearRow.getScoreBonus());
    }

    @Test
    void checkRemoving_FullRowMovesRowsDown() {
        int[][] board = {
                {0, 0, 0, 0},
                {2, 2, 2, 2},
                {3, 0, 0, 0},
                {4, 0, 0, 0}
        };

        ClearRow clearRow = MatrixOperations.checkRemoving(board);
        int[][] newMatrix = clearRow.getNewMatrix();

        assertArrayEquals(new int[]{0, 0, 0, 0}, newMatrix[0]);
        assertArrayEquals(new int[]{0, 0, 0, 0}, newMatrix[1]);
        assertArrayEquals(new int[]{3, 0, 0, 0}, newMatrix[2]);
        assertArrayEquals(new int[]{4, 0, 0, 0}, newMatrix[3]);
    }

    @Test
    void deepCopyList_ReturnsListWithEqualValuesButDifferentArrays() {
        int[][] a = {
                {1, 0},
                {0, 1}
        };
        int[][] b = {
                {2, 2},
                {2, 2}
        };

        List<int[][]> original = new ArrayList<>();
        original.add(a);
        original.add(b);

        List<int[][]> copyList = MatrixOperations.deepCopyList(original);

        assertEquals(original.size(), copyList.size());
        assertArrayEquals(original.get(0), copyList.get(0));
        assertArrayEquals(original.get(1), copyList.get(1));

        assertNotSame(original.get(0), copyList.get(0));
        assertNotSame(original.get(1), copyList.get(1));
    }

    @Test
    void deepCopyList_ModifyingOriginalDoesNotAffectCopy() {
        int[][] a = {
                {1, 0},
                {0, 1}
        };

        List<int[][]> original = new ArrayList<>();
        original.add(a);

        List<int[][]> copyList = MatrixOperations.deepCopyList(original);

        a[0][0] = 9;

        assertEquals(1, copyList.get(0)[0][0]);
        assertEquals(9, original.get(0)[0][0]);
    }
}
