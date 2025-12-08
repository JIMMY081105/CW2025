package com.comp2042.model;

import com.comp2042.data.ClearRow;
import com.comp2042.util.MatrixOperations;

public final class BombEffectService {

    private BombEffectService() {
    }

    /**
     * Applies a bomb explosion at the specified coordinates, clears resulting lines, and updates the board matrix.
     *
     * @param board   board whose matrix will be mutated.
     * @param centerX x-coordinate of the explosion centre.
     * @param centerY y-coordinate of the explosion centre.
     * @return {@link ClearRow} describing removed lines and the new matrix.
     */
    public static ClearRow applyBomb(Board board, int centerX, int centerY) {
        int[][] exploded = MatrixOperations.explodeBomb(
                board.getBoardMatrix(),
                centerX,
                centerY
        );

        ClearRow clearRow = MatrixOperations.checkRemoving(exploded);

        if (board instanceof SimpleBoard) {
            SimpleBoard simpleBoard = (SimpleBoard) board;
            simpleBoard.applyBombMatrix(clearRow.getNewMatrix());
        }

        return clearRow;
    }
}
