package com.comp2042.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    void initialScore_IsZero() {
        Score score = new Score();
        assertEquals(0, score.scoreProperty().get());
    }

    @Test
    void add_IncreasesScore() {
        Score score = new Score();

        score.add(10);
        score.add(5);

        assertEquals(15, score.scoreProperty().get());
    }

    @Test
    void reset_SetsScoreBackToZero() {
        Score score = new Score();

        score.add(42);
        score.reset();

        assertEquals(0, score.scoreProperty().get());
    }
}
