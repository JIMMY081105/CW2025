package com.comp2042.model;

import com.comp2042.data.ClearRow;
import com.comp2042.util.MatrixOperations;

public final class BombEffectService {

    private BombEffectService() {

    }

    public static ClearRow applyBomb(Board board, int centerX, int centerY) {
        int[][] exploded = MatrixOperations.explodeBomb(
                board.getBoardMatrix(),
                centerX,
                centerY
        );

        ClearRow clearRow = MatrixOperations.checkRemoving(exploded);
        board.updateBoardMatrix(clearRow.getNewMatrix());
        return clearRow;
    }
}
