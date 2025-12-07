package com.comp2042.view.manager;

import com.comp2042.data.DownData;
import com.comp2042.util.GameConfig;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.util.Objects;

public final class GameNotificationManager {

    private static final String SCORE_NOTIFICATION_STYLE_CLASS = "score-notification";
    private static final int MAX_NOTIFICATIONS = 5;

    private final Group notificationGroup;
    private final IntegerProperty bombCountProperty;
    private int lastBombMilestone = 0;

    public GameNotificationManager(Group notificationGroup, IntegerProperty bombCountProperty) {
        this.notificationGroup = Objects.requireNonNull(notificationGroup, "notificationGroup must not be null");
        this.bombCountProperty = Objects.requireNonNull(bombCountProperty, "bombCountProperty must not be null");
    }

    public void handleDownMovement(DownData downData) {
        if (downData == null) {
            return;
        }

        int bonus = downData.getScoreBonus();
        if (bonus > 0) {
            showScoreNotification(bonus);
        }
    }

    public void handleScoreChanged(int totalScore) {
        int milestonesReached = totalScore / GameConfig.POINTS_PER_BOMB;
        int newBombs = milestonesReached - lastBombMilestone;

        if (newBombs <= 0) {
            return;
        }

        bombCountProperty.set(bombCountProperty.get() + newBombs);
        lastBombMilestone = milestonesReached;

        showBombNotification(newBombs);
    }

    private void showScoreNotification(int scoreBonus) {
        showTextNotification("+" + scoreBonus);
    }

    private void showBombNotification(int bombsAwarded) {
        if (bombsAwarded <= 0) {
            return;
        }
        showTextNotification("+" + bombsAwarded + " \uD83D\uDCA3");
    }

    private void showTextNotification(String text) {
        Label label = new Label(text);
        label.getStyleClass().add(SCORE_NOTIFICATION_STYLE_CLASS);

        ObservableList<Node> children = notificationGroup.getChildren();
        if (children.size() >= MAX_NOTIFICATIONS) {
            children.remove(0);
        }
        children.add(label);
    }
}
