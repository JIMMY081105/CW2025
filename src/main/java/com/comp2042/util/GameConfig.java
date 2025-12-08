package com.comp2042.util;

/**
 * The {@code GameConfig} class centralises static gameplay constants such as board dimensions, scoring values,
 * spawn positions, and preview settings, ensuring consistent configuration across the application.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/util/GameConfig.java">
 * GameConfig.java</a>
 */
public final class GameConfig {

    public static final int BOARD_HEIGHT = 23;
    public static final int BOARD_WIDTH = 10;
    public static final int HIDDEN_BUFFER_ROWS = 2;

    public static final int GAME_TICK_MS = 400;
    public static final int MIN_GAME_TICK_MS = 120;
    public static final int CHINA_STAGE_SPEED_STEP = 7;

    public static final int SCORE_PER_LINE = 50;
    public static final int MANUAL_DOWN_SCORE = 1;
    public static final int POINTS_PER_CHINA_STAGE = 1000;
    public static final int POINTS_PER_BOMB = 1000;

    public static final int SPAWN_X = 4;
    public static final int SPAWN_Y = 0;

    public static final int NEXT_PREVIEW_COUNT = 3;

    public static final int INITIAL_QUEUE_SIZE = 10;

    /**
     * Returns the number of rows visible to the player, excluding the hidden spawn buffer.
     *
     * @return visible row count.
     */
    public static int visibleRows() {
        return BOARD_HEIGHT - HIDDEN_BUFFER_ROWS;
    }

    private GameConfig() {
    }
}
