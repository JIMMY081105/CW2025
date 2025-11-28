package com.comp2042.model.scoring;

import com.comp2042.data.ClearRow;
import com.comp2042.util.GameConstants;

public class ClassicScoringStrategy implements ScoringStrategy {

    @Override
    public int scoreForManualDrop(int steps) {
        if (steps <= 0) {
            return 0;
        }
        return steps * GameConstants.MANUAL_DOWN_SCORE;
    }

    @Override
    public int scoreForLineClear(ClearRow clearRow) {
        if (clearRow == null || clearRow.getLinesRemoved() <= 0) {
            return 0;
        }

        return clearRow.getScoreBonus();
    }
}
