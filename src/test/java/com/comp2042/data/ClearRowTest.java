package com.comp2042.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClearRowTest {

    @Test
    void constructor_SetsLinesRemovedAndMatrix() {
        int[][] matrix = {
                {1, 0},
                {0, 1}
        };

        ClearRow clearRow = new ClearRow(2, matrix);

        assertEquals(2, clearRow.getLinesRemoved(),
                "linesRemoved should be stored correctly");
        int[][] resultMatrix = clearRow.getNewMatrix();

        // Same dimensions
        assertEquals(matrix.length, resultMatrix.length);
        for (int i = 0; i < matrix.length; i++) {
            assertArrayEquals(matrix[i], resultMatrix[i],
                    "Row " + i + " should match");
        }
    }

    @Test
    void zeroLinesRemoved_AllowsNullMatrix() {
        ClearRow clearRow = new ClearRow(0, null);

        assertEquals(0, clearRow.getLinesRemoved(),
                "linesRemoved should be 0");
        assertNull(clearRow.getNewMatrix(),
                "newMatrix may be null when no lines are removed");
    }
}
