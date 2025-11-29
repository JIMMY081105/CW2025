package com.comp2042.util;

import com.comp2042.data.ClearRow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public final class MatrixOperations {

    private MatrixOperations() {

    }

    public static boolean intersect(final int[][] matrix, final int[][] brick, int x, int y) {
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

    public static int[][] merge(final int[][] matrix, final int[][] brick, int x, int y) {
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

    public static int[][] copy(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int row = 0; row < original.length; row++) {
            int[] sourceRow = original[row];
            copy[row] = new int[sourceRow.length];
            System.arraycopy(sourceRow, 0, copy[row], 0, sourceRow.length);
        }
        return copy;
    }

    public static ClearRow checkRemoving(final int[][] matrix) {
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

            currentMatrix = newMatrix;
        }

        int scoreBonus = GameConstants.SCORE_PER_LINE * totalLinesRemoved * totalLinesRemoved;

        return new ClearRow(totalLinesRemoved, currentMatrix, scoreBonus);
    }

    public static List<int[][]> deepCopyList(List<int[][]> list) {
        return list.stream()
                .map(MatrixOperations::copy)
                .collect(Collectors.toList());
    }

    private static boolean isOutOfBounds(int[][] matrix, int x, int y) {
        return x < 0
                || y < 0
                || y >= matrix.length
                || x >= matrix[y].length;
    }

    private static boolean isRowFull(int[] row) {
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

    public static int[][] explodeBomb(final int[][] matrix, int centerX, int centerY) {
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
}
