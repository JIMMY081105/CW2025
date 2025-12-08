package com.comp2042.model.scoring;

/**
 * The {@code ScoringStrategy} interface defines how score bonuses are calculated for manual drops and line clears.
 * It enables alternate scoring schemes to be plugged into the controller.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/scoring/ScoringStrategy.java">
 * ScoringStrategy.java</a>
 */
public interface ScoringStrategy {

    /**
     * Calculates points awarded for manually dropping a piece by the given number of steps.
     *
     * @param steps rows advanced by player input.
     * @return score bonus for the manual drop.
     */
    int scoreForManualDrop(int steps);

    /**
     * Calculates points awarded for clearing the specified number of lines.
     *
     * @param linesRemoved number of lines cleared in one lock.
     * @return score bonus for the clear.
     */
    int scoreForLineClear(int linesRemoved);
}
