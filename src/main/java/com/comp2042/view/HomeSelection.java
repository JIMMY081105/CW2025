package com.comp2042.view;

public record HomeSelection(Mode mode, String option) {
    public enum Mode {
        COUNTRY_EXPLORE,
        TIME_RACING
    }
}
