package com.comp2042.model.scoring;

public interface ScoringStrategy {

    int scoreForManualDrop(int steps);

    int scoreForLineClear(int linesRemoved);
}
