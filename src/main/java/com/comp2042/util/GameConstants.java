package com.comp2042.util;

public final class GameConstants {

    public static final int BOARD_HEIGHT = 23;
    public static final int BOARD_WIDTH = 10;
    public static final int GAME_TICK_MS = 400;
    public static final int BRICK_SIZE = 28;
    public static final int GRID_GAP = 2;
    public static final int NEXT_PREVIEW_COUNT = 3;
    public static final int NEXT_BRICK_SIZE = 18;
    public static final int NEXT_BRICK_GAP = 2;
    public static final int NEXT_PREVIEW_SPACING = 12;
    public static final int BOARD_FRAME_THICKNESS = 12;
    public static final int BOARD_LEFT_PADDING = 40;
    public static final int BOARD_TOP_PADDING = 30;
    public static final int SIDE_PANEL_WIDTH = 240;
    public static final int SIDE_PANEL_SPACING = 24;
    public static final int SIDE_PANEL_PADDING = 12;
    public static final int PANEL_GAP = 28;
    public static final int BOTTOM_PADDING = 60;
    public static final int SCORE_PER_LINE = 50;
    public static final int SPAWN_X = 4;
    public static final int SPAWN_Y = 0;
    public static final int HIDDEN_BUFFER_ROWS = 2;
    public static final int BRICK_ARC_SIZE = 10;
    public static final int MANUAL_DOWN_SCORE = 1;

    public static int visibleRows() {
        return BOARD_HEIGHT - HIDDEN_BUFFER_ROWS;
    }

    public static double brickStep() {
        return BRICK_SIZE + GRID_GAP;
    }

    public static double boardPixelWidth() {
        return BOARD_WIDTH * brickStep();
    }

    public static double boardPixelHeight() {
        int visibleRows = visibleRows();
        return visibleRows * brickStep();
    }

    public static double brickPanelYOffset() {
        return -HIDDEN_BUFFER_ROWS * brickStep();
    }

    public static double boardAreaWidth() {
        return boardPixelWidth() + BOARD_FRAME_THICKNESS * 2;
    }

    public static double boardAreaHeight() {
        return boardPixelHeight() + BOARD_FRAME_THICKNESS * 2;
    }

    public static double contentWidth() {
        return boardAreaWidth() + PANEL_GAP + SIDE_PANEL_WIDTH;
    }

    public static double minimumCenteredWindowWidth() {
        return boardAreaWidth() + 2 * (SIDE_PANEL_WIDTH + PANEL_GAP);
    }

    public static double notificationPanelY() {
        return BOARD_TOP_PADDING + SIDE_PANEL_PADDING;
    }

    public static double initialWindowWidth() {
        double baseWidth = BOARD_LEFT_PADDING + contentWidth() + PANEL_GAP;
        return Math.max(baseWidth, minimumCenteredWindowWidth());
    }

    public static double initialWindowHeight() {
        return BOARD_TOP_PADDING + boardAreaHeight() + BOTTOM_PADDING;
    }

    private GameConstants() {
    }
}
