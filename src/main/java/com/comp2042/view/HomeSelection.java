package com.comp2042.view;

/**
 * The {@code HomeSelection} record captures a player's menu choice, pairing the selected mode with a specific
 * option label such as a time-attack duration.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/HomeSelection.java">
 * HomeSelection.java</a>
 */
public record HomeSelection(Mode mode, String option) {
    public enum Mode {
        COUNTRY_EXPLORE,
        TIME_RACING
    }
}
