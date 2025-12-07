package com.comp2042.data;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ViewDataTest {

    private void assertMatrixEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length, "Matrix row count should match");
        for (int y = 0; y < expected.length; y++) {
            assertArrayEquals(expected[y], actual[y], "Row " + y + " should match");
        }
    }

    @Test
    void constructor_StoresFieldsCorrectly() {
        int[][] currentBrick = {
                {1, 0},
                {0, 1}
        };
        int x = 3;
        int y = 5;
        int score = 120; 

        int[][] next1 = {
                {2, 2},
                {0, 2}
        };
        int[][] next2 = {
                {3, 3},
                {3, 0}
        };

        List<int[][]> nextBricks = Arrays.asList(next1, next2);

        ViewData viewData = new ViewData(
                currentBrick,
                x,
                y,
                score,
                nextBricks
        );

        int[][] brickData = viewData.getBrickData();
        assertNotNull(brickData, "getBrickData() should not return null");
        assertMatrixEquals(currentBrick, brickData);

        assertEquals(x, viewData.getXPosition());
        assertEquals(y, viewData.getYPosition());

        List<int[][]> resultNext = viewData.getNextBricksData();
        assertNotNull(resultNext, "getNextBricksData() should not return null");
        assertEquals(2, resultNext.size());

        assertMatrixEquals(next1, resultNext.get(0));
        assertMatrixEquals(next2, resultNext.get(1));
    }

    @Test
    void nextBricksData_CanBeEmptyList() {
        int[][] currentBrick = {
                {1}
        };

        ViewData viewData = new ViewData(
                currentBrick,
                0,
                0,
                0,
                Collections.emptyList()
        );

        int[][] brickData = viewData.getBrickData();
        assertNotNull(brickData);
        assertMatrixEquals(currentBrick, brickData);

        assertEquals(0, viewData.getXPosition());
        assertEquals(0, viewData.getYPosition());

        List<int[][]> next = viewData.getNextBricksData();
        assertNotNull(next, "getNextBricksData() should not return null even if no previews");
        assertTrue(next.isEmpty(), "When constructed with empty list, previews should be empty");
    }
}
