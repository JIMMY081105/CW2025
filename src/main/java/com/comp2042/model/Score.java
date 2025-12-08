package com.comp2042.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * The {@code Score} class tracks the player's score as an observable property for UI binding and updates,
 * encapsulating mutation helpers without exposing internal state.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/Score.java">
 * Score.java</a>
 */
public final class Score {

    private final IntegerProperty score = new SimpleIntegerProperty(0);

    /**
     * Returns the observable score property for UI binding.
     *
     * @return score property.
     */
    public IntegerProperty scoreProperty() {
        return score;
    }

    /**
     * Increments the current score by the specified amount.
     *
     * @param i amount to add; may be negative to subtract points.
     */
    public void add(int i){
        score.setValue(score.getValue() + i);
    }

    /**
     * Resets the score to zero.
     */
    public void reset() {
        score.setValue(0);
    }
}
