package com.comp2042.util;

import com.comp2042.data.ClearRow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code MatrixOperations} class encapsulates matrix utilities for copying, collision checks, merging,
 * clearing rows, deep copying lists of shapes, and simulating bomb explosions. It isolates low-level grid
 * manipulation from higher-level board and controller logic.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/util/MatrixOperations.java">
 * MatrixOperations.java</a>
 */
public final class MatrixOperations {

    private MatrixOperations() {
    }

    /**
     * Determines whether placing a brick at the given coordinates intersects boundaries or occupied cells.
     *
     * @param matrix board matrix.
     * @param brick  brick matrix to test.
     * @param x      left coordinate for placement.
     * @param y      top coordinate for placement.
     * @return {@code true} if a collision occurs.
     */
    public static boolean intersect(final int[][] matrix, final int[][] brick, int x, int y) {
        requireValidMatrix(matrix, "matrix");
        requireValidMatrix(brick, "brick");

        for (int row = 0; row < brick.length; row++) {
            for (int col = 0; col < brick[row].length; col++) {
                if (brick[row][col] == 0) {
                    continue;
                }
                int targetX = x + col;
                int targetY = y + row;

                if (isOutOfBounds(matrix, targetX, targetY)) {
                    return true;
                }
                if (matrix[targetY][targetX] != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Merges a brick matrix into a copy of the board at the specified location.
     *
     * @param matrix board matrix.
     * @param brick  brick to overlay.
     * @param x      target x-coordinate.
     * @param y      target y-coordinate.
     * @return new matrix containing both board and brick.
     */
    public static int[][] merge(final int[][] matrix, final int[][] brick, int x, int y) {
        requireValidMatrix(matrix, "matrix");
        requireValidMatrix(brick, "brick");

        int[][] result = copy(matrix);

        for (int row = 0; row < brick.length; row++) {
            for (int col = 0; col < brick[row].length; col++) {
                if (brick[row][col] == 0) {
                    continue;
                }

                int targetX = x + col;
                int targetY = y + row;

                if (isOutOfBounds(result, targetX, targetY)) {
                    continue;
                }

                result[targetY][targetX] = brick[row][col];
            }
        }
        return result;
    }

    /**
     * Produces a deep copy of the supplied matrix.
     *
     * @param original matrix to copy.
     * @return copied matrix.
     */
    public static int[][] copy(int[][] original) {
        requireValidMatrix(original, "original");

        int[][] copy = new int[original.length][];
        for (int row = 0; row < original.length; row++) {
            int[] sourceRow = original[row];
            copy[row] = new int[sourceRow.length];
            System.arraycopy(sourceRow, 0, copy[row], 0, sourceRow.length);
        }
        return copy;
    }

    /**
     * Iteratively removes any full rows, compacts remaining rows downward, and reports how many were cleared.
     *
     * @param matrix matrix to process.
     * @return {@link ClearRow} describing removed lines and the resulting matrix.
     */
    public static ClearRow checkRemoving(final int[][] matrix) {
        requireValidMatrix(matrix, "matrix");

        int height = matrix.length;
        int width = matrix[0].length;
        int[][] currentMatrix = copy(matrix);
        int totalLinesRemoved = 0;

        while (true) {
            Deque<int[]> remainingRows = new ArrayDeque<>();
            List<Integer> clearedRows = new ArrayList<>();

            for (int row = height - 1; row >= 0; row--) {
                int[] currentRow = currentMatrix[row];

                if (isRowFull(currentRow)) {
                    clearedRows.add(row);
                } else {
                    remainingRows.addLast(cloneRow(currentRow));
                }
            }

            if (clearedRows.isEmpty()) {
                break;
            }

            totalLinesRemoved += clearedRows.size();

            int[][] newMatrix = new int[height][width];

            int writeRow = height - 1;
            while (!remainingRows.isEmpty() && writeRow >= 0) {
                newMatrix[writeRow] = remainingRows.removeFirst();
                writeRow--;
            }

            while (writeRow >= 0) {
                newMatrix[writeRow] = new int[width];
                writeRow--;
            }

            currentMatrix = newMatrix;
        }

        return new ClearRow(totalLinesRemoved, currentMatrix);
    }

    /**
     * Deep-copies each matrix in the provided list.
     *
     * @param list list of matrices to copy.
     * @return list containing copied matrices.
     */
    public static List<int[][]> deepCopyList(List<int[][]> list) {
        if (list == null) {
            throw new IllegalArgumentException("list must not be null");
        }
        return list.stream()
                .map(MatrixOperations::copy)
                .collect(Collectors.toList());
    }

    /**
     * Simulates a bomb explosion by clearing a 3x3 area and letting blocks above fall down.
     *
     * @param matrix  board matrix to copy and mutate.
     * @param centerX x-coordinate of the blast centre.
     * @param centerY y-coordinate of the blast centre.
     * @return resulting matrix after the explosion.
     */
    public static int[][] explodeBomb(final int[][] matrix, int centerX, int centerY) {
        requireValidMatrix(matrix, "matrix");

        int height = matrix.length;
        int width = matrix[0].length;

        int[][] result = copy(matrix);

        int top = Math.max(0, centerY - 1);
        int bottom = Math.min(height - 1, centerY + 1);
        int left = Math.max(0, centerX - 1);
        int right = Math.min(width - 1, centerX + 1);

        for (int row = top; row <= bottom; row++) {
            for (int col = left; col <= right; col++) {
                result[row][col] = 0;
            }
        }

        for (int col = left; col <= right; col++) {
            int writeRow = bottom;
            for (int row = bottom; row >= 0; row--) {
                if (result[row][col] != 0) {
                    if (writeRow != row) {
                        result[writeRow][col] = result[row][col];
                        result[row][col] = 0;
                    }
                    writeRow--;
                }
            }
        }

        return result;
    }

    private static boolean isOutOfBounds(int[][] matrix, int x, int y) {
        return x < 0
                || y < 0
                || y >= matrix.length
                || x >= matrix[y].length;
    }

    private static boolean isRowFull(int[] row) {
        if (row == null || row.length == 0) {
            return false;
        }
        for (int cell : row) {
            if (cell == 0) {
                return false;
            }
        }
        return true;
    }

    private static int[] cloneRow(int[] source) {
        int[] copy = new int[source.length];
        System.arraycopy(source, 0, copy, 0, source.length);
        return copy;
    }

    private static void requireValidMatrix(int[][] matrix, String name) {
        if (matrix == null) {
            throw new IllegalArgumentException(name + " must not be null");
        }
        if (matrix.length == 0) {
            throw new IllegalArgumentException(name + " must have at least one row");
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == null) {
                throw new IllegalArgumentException(name + " row " + i + " must not be null");
            }
        }
    }
}
