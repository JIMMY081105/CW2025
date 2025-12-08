package com.comp2042.view.render;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * The {@code GameOverPanel} class is a simple UI component displaying a styled "Game Over" label.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/render/GameOverPanel.java">
 * GameOverPanel.java</a>
 */
public final class GameOverPanel extends BorderPane {

    private static final String GAME_OVER_TEXT = "GAME OVER";
    private static final String GAME_OVER_STYLE_CLASS = "gameOverStyle";

    /**
     * Constructs the panel and initialises its label content.
     */
    public GameOverPanel() {
        Label gameOverLabel = new Label(GAME_OVER_TEXT);
        gameOverLabel.getStyleClass().add(GAME_OVER_STYLE_CLASS);
        setCenter(gameOverLabel);
    }
}
