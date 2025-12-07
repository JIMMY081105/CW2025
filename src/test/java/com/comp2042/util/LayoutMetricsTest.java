package com.comp2042.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LayoutMetricsTest {

    @Test
    void brickStep_EqualsBrickSizePlusGridGap() {
        double expected = LayoutMetrics.BRICK_SIZE + LayoutMetrics.GRID_GAP;
        assertEquals(expected, LayoutMetrics.brickStep(), 0.0001);
    }

    @Test
    void boardPixelWidth_EqualsBrickStepTimesBoardWidth() {
        double expected = LayoutMetrics.brickStep() * GameConfig.BOARD_WIDTH;
        assertEquals(expected, LayoutMetrics.boardPixelWidth(), 0.0001);
    }

    @Test
    void boardPixelHeight_EqualsBrickStepTimesVisibleRows() {
        double expected = LayoutMetrics.brickStep() * GameConfig.visibleRows();
        assertEquals(expected, LayoutMetrics.boardPixelHeight(), 0.0001);
    }

    @Test
    void boardAreaDimensions_IncludeFrameThickness() {
        double contentWidth = LayoutMetrics.boardPixelWidth();
        double contentHeight = LayoutMetrics.boardPixelHeight();

        double expectedWidth = contentWidth + 2 * LayoutMetrics.BOARD_FRAME_THICKNESS;
        double expectedHeight =
                contentHeight
                        + LayoutMetrics.BOARD_FRAME_THICKNESS
                        + LayoutMetrics.BOARD_FRAME_THICKNESS;

        assertEquals(expectedWidth, LayoutMetrics.boardAreaWidth(), 0.0001);
        assertEquals(expectedHeight, LayoutMetrics.boardAreaHeight(), 0.0001);
    }

    @Test
    void brickPanelYOffset_IsNegativeHiddenBufferHeights() {
        double expected = -GameConfig.HIDDEN_BUFFER_ROWS * LayoutMetrics.brickStep();
        assertEquals(expected, LayoutMetrics.brickPanelYOffset(), 0.0001);
    }
}
