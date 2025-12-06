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
                {1, 1, 1}, // full
                {2, 0, 2}
        };

        ClearRow result = MatrixOperations.checkRemoving(matrix);

        assertEquals(1, result.getLinesRemoved());

        int[][] newMatrix = result.getNewMatrix();
        // Bottom row should be {2,0,2}, others zero
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
}
