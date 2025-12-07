package com.comp2042.testutil;

import javafx.application.Platform;

public final class JavaFxTestUtil {

    private static boolean initialized = false;

    private JavaFxTestUtil() {
    }

    public static synchronized void initFx() {
        if (initialized) {
            return;
        }
        try {
            Platform.startup(() -> {

            });
        } catch (IllegalStateException e) {
            
        }
        initialized = true;
    }
}
