package com.comp2042.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameConfigTest {

    @Test
    void visibleRows_EqualsBoardHeightMinusHiddenBuffer() {
        int expected = GameConfig.BOARD_HEIGHT - GameConfig.HIDDEN_BUFFER_ROWS;
        assertEquals(expected, GameConfig.visibleRows());
    }

    @Test
    void minGameTick_IsNotGreaterThanDefaultTick() {
        assertTrue(
                GameConfig.MIN_GAME_TICK_MS <= GameConfig.GAME_TICK_MS,
                "MIN_GAME_TICK_MS should not be greater than GAME_TICK_MS"
        );
    }

    @Test
    void chinaStageSpeedStep_IsPositive() {
        assertTrue(GameConfig.CHINA_STAGE_SPEED_STEP > 0);
    }

    @Test
    void pointsPerStageAndBomb_ArePositive() {
        assertTrue(GameConfig.POINTS_PER_CHINA_STAGE > 0);
        assertTrue(GameConfig.POINTS_PER_BOMB > 0);
    }
}
