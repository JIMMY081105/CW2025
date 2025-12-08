package com.comp2042.model.scoring;

import com.comp2042.util.GameConfig;

/**
 * The {@code ClassicScoringStrategy} class applies the default scoring rules, granting bonuses for manual drops
 * and quadratic rewards for multi-line clears.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/scoring/ClassicScoringStrategy.java">
 * ClassicScoringStrategy.java</a>
 */
public final class ClassicScoringStrategy implements ScoringStrategy {

    @Override
    /**
     * Awards linear points for manual drops based on configured per-step value.
     *
     * @param steps number of rows advanced manually.
     * @return score earned for the manual drop.
     */
    public int scoreForManualDrop(int steps) {
        if (steps <= 0) {
            return 0;
        }
        return steps * GameConfig.MANUAL_DOWN_SCORE;
    }

    @Override
    /**
     * Awards quadratic points for clearing multiple lines at once.
     *
     * @param linesRemoved number of rows cleared.
     * @return score earned for the clear.
     */
    public int scoreForLineClear(int linesRemoved) {
        if (linesRemoved <= 0) {
            return 0;
        }
        return GameConfig.SCORE_PER_LINE * linesRemoved * linesRemoved;
    }
}
