package com.comp2042.data;

/**
 * The {@code DownData} class aggregates the result of a downward movement, combining cleared-row data,
 * updated view data, and any score bonus awarded. It is used by controllers to update the UI after movement.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/data/DownData.java">
 * DownData.java</a>
 */
public final class DownData {

    private final ClearRow clearRow;
    private final ViewData viewData;
    private final int scoreBonus;
    /**
     * Creates a result without an explicit score bonus.
     *
     * @param clearRow details about cleared lines.
     * @param viewData view snapshot after the move.
     */
    public DownData(ClearRow clearRow, ViewData viewData) {
        this(clearRow, viewData, 0);
    }

    /**
     * Creates a result including a score bonus.
     *
     * @param clearRow   details about cleared lines.
     * @param viewData   view snapshot after the move.
     * @param scoreBonus bonus points awarded for the move.
     */
    public DownData(ClearRow clearRow, ViewData viewData, int scoreBonus) {
        this.clearRow = clearRow;
        this.viewData = viewData;
        this.scoreBonus = scoreBonus;
    }

    /**
     * Returns the line-clear details.
     *
     * @return {@link ClearRow} result.
     */
    public ClearRow getClearRow() {
        return clearRow;
    }

    /**
     * Returns the view data to render.
     *
     * @return {@link ViewData} after movement.
     */
    public ViewData getViewData() {
        return viewData;
    }

    /**
     * Returns any score bonus from the move.
     *
     * @return bonus value (may be zero).
     */
    public int getScoreBonus() {
        return scoreBonus;
    }
}
