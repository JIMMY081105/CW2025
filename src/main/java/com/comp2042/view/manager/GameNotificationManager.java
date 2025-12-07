package com.comp2042.view.manager;

import com.comp2042.data.DownData;
import com.comp2042.util.GameConfig;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;

public final class GameNotificationManager {

    private final Group notificationGroup;
    private final IntegerProperty bombCountProperty;
    private int lastBombMilestone = 0;

    public GameNotificationManager(Group notificationGroup, IntegerProperty bombCountProperty) {
        this.notificationGroup = notificationGroup;
        this.bombCountProperty = bombCountProperty;
    }

    public void handleDownMovement(DownData downData) {
        if (downData == null || notificationGroup == null) {
            return;
        }

        int bonus = downData.getScoreBonus();
        if (bonus > 0) {
            showScoreNotification(bonus);
        }
    }

    public void handleScoreChanged(int totalScore) {
        if (bombCountProperty == null) {
            return;
        }

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
        if (notificationGroup == null) {
            return;
        }
        showTextNotification("+" + scoreBonus);
    }

    private void showBombNotification(int bombsAwarded) {
        if (bombsAwarded <= 0 || notificationGroup == null) {
            return;
        }
        showTextNotification("+" + bombsAwarded + " \uD83D\uDCA3");
    }

    private void showTextNotification(String text) {
        if (notificationGroup == null) {
            return;
        }

        Label label = new Label(text);
        label.getStyleClass().add("score-notification");

        ObservableList<Node> children = notificationGroup.getChildren();
        children.add(label);
    }
}
