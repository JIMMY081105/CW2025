package com.comp2042.model.scoring;

import com.comp2042.util.GameConfig;

public final class ClassicScoringStrategy implements ScoringStrategy {

    @Override
    public int scoreForManualDrop(int steps) {
        if (steps <= 0) {
            return 0;
        }
        return steps * GameConfig.MANUAL_DOWN_SCORE;
    }

    @Override
    public int scoreForLineClear(int linesRemoved) {
        if (linesRemoved <= 0) {
            return 0;
        }
        return GameConfig.SCORE_PER_LINE * linesRemoved * linesRemoved;
    }
}
