package com.comp2042.view;

/**
 * Simple record describing the user's home screen selection.
 */
public record HomeSelection(Mode mode, String option) {
    public enum Mode {
        COUNTRY_EXPLORE,
        TIME_RACING
    }
}
