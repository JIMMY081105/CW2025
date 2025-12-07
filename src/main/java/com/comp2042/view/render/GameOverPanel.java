package com.comp2042.view.render;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public final class GameOverPanel extends BorderPane {

    private static final String GAME_OVER_TEXT = "GAME OVER";
    private static final String GAME_OVER_STYLE_CLASS = "gameOverStyle";

    public GameOverPanel() {
        Label gameOverLabel = new Label(GAME_OVER_TEXT);
        gameOverLabel.getStyleClass().add(GAME_OVER_STYLE_CLASS);
        setCenter(gameOverLabel);
    }
}
