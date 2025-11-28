package com.comp2042.model.scoring;

import com.comp2042.data.ClearRow;

public interface ScoringStrategy {

    int scoreForManualDrop(int steps);

    int scoreForLineClear(ClearRow clearRow);
}
