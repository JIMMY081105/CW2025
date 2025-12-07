package com.comp2042.data;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DownDataTest {

    @Test
    void constructor_StoresFieldsCorrectly() {
        int[][] boardMatrix = {
                {1, 0},
                {0, 1}
        };
        int[][] currentBrick = {
                {2}
        };
        int[][] nextBrick = {
                {3}
        };

        ViewData viewData = new ViewData(
                currentBrick,
                1,
                2,
                10,
                Collections.singletonList(nextBrick)
        );
        ClearRow clearRow = new ClearRow(2, boardMatrix);

        int scoreBonus = 400;

        DownData downData = new DownData(clearRow, viewData, scoreBonus);

        assertSame(clearRow, downData.getClearRow(),
                "ClearRow reference should be stored as given");
        assertSame(viewData, downData.getViewData(),
                "ViewData reference should be stored as given");
        assertEquals(scoreBonus, downData.getScoreBonus(),
                "scoreBonus should be stored as given");
    }

    @Test
    void allowsNullClearRow_WhenNoLinesCleared() {
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

        DownData downData = new DownData(null, viewData, 0);

        assertNull(downData.getClearRow(),
                "clearRow may be null when no lines were cleared");
        assertSame(viewData, downData.getViewData());
        assertEquals(0, downData.getScoreBonus());
    }
}
