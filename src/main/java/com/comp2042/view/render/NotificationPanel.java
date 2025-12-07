package com.comp2042.view.render;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Objects;

public class NotificationPanel extends BorderPane {

    private static final double MIN_WIDTH = 220.0;
    private static final double MIN_HEIGHT = 200.0;
    private static final double GLOW_LEVEL = 0.6;
    private static final String BONUS_STYLE_CLASS = "bonusStyle";

    private static final double FADE_DURATION_MS = 2000.0;
    private static final double MOVE_DURATION_MS = 2500.0;
    private static final double MOVE_OFFSET_Y = 40.0;

    public NotificationPanel(String text) {
        setMinHeight(MIN_HEIGHT);
        setMinWidth(MIN_WIDTH);

        Label score = new Label(text);
        score.getStyleClass().add(BONUS_STYLE_CLASS);

        Effect glow = new Glow(GLOW_LEVEL);
        score.setEffect(glow);
        score.setTextFill(Color.WHITE);

        setCenter(score);
    }

    public void showScore(ObservableList<Node> list) {
        Objects.requireNonNull(list, "list must not be null");

        FadeTransition fadeTransition =
                new FadeTransition(Duration.millis(FADE_DURATION_MS), this);
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.millis(MOVE_DURATION_MS), this);

        translateTransition.setToY(getLayoutY() - MOVE_OFFSET_Y);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        ParallelTransition transition = new ParallelTransition(translateTransition, fadeTransition);
        transition.setOnFinished(event -> list.remove(NotificationPanel.this));
        transition.play();
    }
}
