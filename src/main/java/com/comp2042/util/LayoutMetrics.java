package com.comp2042.util;

public final class LayoutMetrics {

    public static final int BRICK_SIZE = 28;
    public static final double GRID_GAP = 2.5;
    public static final int BRICK_ARC_SIZE = 10;

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

    public static double brickStep() {
        return BRICK_SIZE + GRID_GAP;
    }

    public static double boardPixelWidth() {
        return GameConfig.BOARD_WIDTH * brickStep();
    }

    public static double boardPixelHeight() {
        int visibleRows = GameConfig.visibleRows();
        return visibleRows * brickStep();
    }

    public static double gridContentWidth() {
        return boardPixelWidth();
    }

    public static double gridContentHeight() {
        return boardPixelHeight();
    }

    public static double gridCenterOffsetX() {
        return 0;
    }

    public static double gridCenterOffsetY() {
        return (boardPixelHeight() - gridContentHeight()) / 2;
    }

    public static double brickPanelYOffset() {
        return -GameConfig.HIDDEN_BUFFER_ROWS * brickStep();
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
        return Math.max(
                contentWidth() + 2 * SIDE_PANEL_PADDING,
                minimumCenteredWindowWidth()
        );
    }

    public static double initialWindowHeight() {
        return BOARD_TOP_PADDING + boardAreaHeight() + BOTTOM_PADDING;
    }

    private LayoutMetrics() {
    }
}
