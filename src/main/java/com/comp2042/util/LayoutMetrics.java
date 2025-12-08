package com.comp2042.util;

/**
 * The {@code LayoutMetrics} class centralises pixel measurements and spacing values used across the game UI,
 * providing derived sizes for the board, side panels, overlays, and window defaults to keep layout consistent.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/util/LayoutMetrics.java">
 * LayoutMetrics.java</a>
 */
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

    /**
     * Returns the pixel distance between adjacent bricks, including the gap.
     *
     * @return brick step size in pixels.
     */
    public static double brickStep() {
        return BRICK_SIZE + GRID_GAP;
    }

    /**
     * Calculates the visible board width in pixels.
     *
     * @return board width.
     */
    public static double boardPixelWidth() {
        return GameConfig.BOARD_WIDTH * brickStep();
    }

    /**
     * Calculates the visible board height in pixels (excluding hidden buffer rows).
     *
     * @return board height.
     */
    public static double boardPixelHeight() {
        int visibleRows = GameConfig.visibleRows();
        return visibleRows * brickStep();
    }

    /**
     * Returns the width of the grid content area without frames.
     *
     * @return grid width.
     */
    public static double gridContentWidth() {
        return boardPixelWidth();
    }

    /**
     * Returns the height of the grid content area without frames.
     *
     * @return grid height.
     */
    public static double gridContentHeight() {
        return boardPixelHeight();
    }

    /**
     * Returns the x-offset used when centering the grid.
     *
     * @return grid x-offset.
     */
    public static double gridCenterOffsetX() {
        return 0;
    }

    /**
     * Returns the y-offset used when centering the grid.
     *
     * @return grid y-offset.
     */
    public static double gridCenterOffsetY() {
        return (boardPixelHeight() - gridContentHeight()) / 2;
    }

    /**
     * Returns the y-offset applied to the brick panel to hide buffer rows.
     *
     * @return y-offset in pixels.
     */
    public static double brickPanelYOffset() {
        return -GameConfig.HIDDEN_BUFFER_ROWS * brickStep();
    }

    /**
     * Calculates the board width including frame.
     *
     * @return total board width.
     */
    public static double boardAreaWidth() {
        return boardPixelWidth() + BOARD_FRAME_THICKNESS * 2;
    }

    /**
     * Calculates the board height including frame.
     *
     * @return total board height.
     */
    public static double boardAreaHeight() {
        return boardPixelHeight() + BOARD_FRAME_THICKNESS * 2;
    }

    /**
     * Calculates the width of the main content area (board plus side panel).
     *
     * @return content width.
     */
    public static double contentWidth() {
        return boardAreaWidth() + PANEL_GAP + SIDE_PANEL_WIDTH;
    }

    /**
     * Returns the minimum width that allows centering side panels around the board.
     *
     * @return minimum centered width.
     */
    public static double minimumCenteredWindowWidth() {
        return boardAreaWidth() + 2 * (SIDE_PANEL_WIDTH + PANEL_GAP);
    }

    /**
     * Returns the y-coordinate for positioning notification panels.
     *
     * @return notification y-offset.
     */
    public static double notificationPanelY() {
        return BOARD_TOP_PADDING + SIDE_PANEL_PADDING;
    }

    /**
     * Calculates the recommended initial window width based on content and padding.
     *
     * @return initial window width.
     */
    public static double initialWindowWidth() {
        return Math.max(
                contentWidth() + 2 * SIDE_PANEL_PADDING,
                minimumCenteredWindowWidth()
        );
    }

    /**
     * Calculates the recommended initial window height based on board and padding.
     *
     * @return initial window height.
     */
    public static double initialWindowHeight() {
        return BOARD_TOP_PADDING + boardAreaHeight() + BOTTOM_PADDING;
    }

    private LayoutMetrics() {
    }
}
