package com.comp2042.data;

/**
 * The {@code ClearRow} class describes the outcome of clearing completed rows, capturing the number removed
 * and the resulting board matrix.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/data/ClearRow.java">
 * ClearRow.java</a>
 */
public final class ClearRow {

    private final int linesRemoved;
    private final int[][] newMatrix;

    /**
     * Creates a clear-row result.
     *
     * @param linesRemoved number of rows removed.
     * @param newMatrix    resulting board matrix.
     */
    public ClearRow(int linesRemoved, int[][] newMatrix) {
        this.linesRemoved = linesRemoved;
        this.newMatrix = newMatrix;
    }

    /**
     * Returns how many lines were removed.
     *
     * @return count of cleared rows.
     */
    public int getLinesRemoved() {
        return linesRemoved;
    }

    /**
     * Returns the updated matrix after clearing.
     *
     * @return new board matrix.
     */
    public int[][] getNewMatrix() {
        return newMatrix;
    }
}
