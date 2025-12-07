package com.comp2042.util;

public final class GameConfig {

    public static final int BOARD_HEIGHT = 23;
    public static final int BOARD_WIDTH = 10;
    public static final int HIDDEN_BUFFER_ROWS = 2;

    public static final int GAME_TICK_MS = 400;
    public static final int MIN_GAME_TICK_MS = 120;
    public static final int CHINA_STAGE_SPEED_STEP = 10;

    public static final int SCORE_PER_LINE = 50;
    public static final int MANUAL_DOWN_SCORE = 1;
    public static final int POINTS_PER_CHINA_STAGE = 200;
    public static final int POINTS_PER_BOMB = 1000;

    public static final int SPAWN_X = 4;
    public static final int SPAWN_Y = 0;

    public static final int NEXT_PREVIEW_COUNT = 3;

    public static final int INITIAL_QUEUE_SIZE = 10;

    public static int visibleRows() {
        return BOARD_HEIGHT - HIDDEN_BUFFER_ROWS;
    }

    private GameConfig() {
    }
}
