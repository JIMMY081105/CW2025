package com.comp2042.util;

import com.comp2042.data.ClearRow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixOperationsTest {

    @Test
    void checkRemoving_NoFullRows_ReturnsZeroAndSameMatrix() {
        int[][] matrix = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };

        ClearRow result = MatrixOperations.checkRemoving(matrix);

        assertEquals(0, result.getLinesRemoved());
        int[][] newMatrix = result.getNewMatrix();

        assertArrayEquals(matrix[0], newMatrix[0]);
        assertArrayEquals(matrix[1], newMatrix[1]);
        assertArrayEquals(matrix[2], newMatrix[2]);
    }

    @Test
    void checkRemoving_SingleFullRow_RemovesRowAndShiftsDown() {
        int[][] matrix = {
                {0, 0, 0},
                {1, 1, 1},
                {2, 0, 2}
        };

        ClearRow result = MatrixOperations.checkRemoving(matrix);

        assertEquals(1, result.getLinesRemoved());

        int[][] newMatrix = result.getNewMatrix();
        assertArrayEquals(new int[]{0, 0, 0}, newMatrix[0]);
        assertArrayEquals(new int[]{0, 0, 0}, newMatrix[1]);
        assertArrayEquals(new int[]{2, 0, 2}, newMatrix[2]);
    }

    @Test
    void checkRemoving_MultipleFullRows_CascadesUntilStable() {
        int[][] matrix = {
                {1, 1, 1},
                {1, 1, 1},
                {0, 1, 0},
                {1, 1, 1}
        };

        ClearRow result = MatrixOperations.checkRemoving(matrix);

        assertEquals(3, result.getLinesRemoved());

        int[][] newMatrix = result.getNewMatrix();
        assertArrayEquals(new int[]{0, 0, 0}, newMatrix[0]);
        assertArrayEquals(new int[]{0, 0, 0}, newMatrix[1]);
        assertArrayEquals(new int[]{0, 0, 0}, newMatrix[2]);
        assertArrayEquals(new int[]{0, 1, 0}, newMatrix[3]);
    }

    @Test
    void explodeBomb_Clears3x3AreaAroundCenter() {
        int[][] matrix = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 9, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };

        int[][] result = MatrixOperations.explodeBomb(matrix, 2, 2);

        for (int y = 1; y <= 3; y++) {
            for (int x = 1; x <= 3; x++) {
                assertEquals(0, result[y][x], "3x3 area around bomb center should be cleared");
            }
        }

        assertEquals(0, result[0][0]);
        assertEquals(0, result[0][4]);
        assertEquals(0, result[4][0]);
        assertEquals(0, result[4][4]);
    }

    @Test
    void explodeBomb_AtEdge_OnlyClearsValidCells() {
        int[][] matrix = {
                {7, 7, 0},
                {7, 3, 0},
                {0, 0, 0}
        };

        int[][] result = MatrixOperations.explodeBomb(matrix, 0, 0);

        assertEquals(0, result[0][0]);
        assertEquals(0, result[0][1]);
        assertEquals(0, result[1][0]);
        assertEquals(0, result[1][1]);

        assertEquals(0, result[0][2]);
        assertEquals(0, result[2][2]);
    }
}
