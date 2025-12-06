package com.comp2042.data;

public final class DownData {

    private final ClearRow clearRow;
    private final ViewData viewData;
    private final int scoreBonus;
    public DownData(ClearRow clearRow, ViewData viewData) {
        this(clearRow, viewData, 0);
    }

    public DownData(ClearRow clearRow, ViewData viewData, int scoreBonus) {
        this.clearRow = clearRow;
        this.viewData = viewData;
        this.scoreBonus = scoreBonus;
    }

    public ClearRow getClearRow() {
        return clearRow;
    }

    public ViewData getViewData() {
        return viewData;
    }

    public int getScoreBonus() {
        return scoreBonus;
    }
}
