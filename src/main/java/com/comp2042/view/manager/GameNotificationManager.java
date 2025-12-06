package com.comp2042.view.manager;

import com.comp2042.data.ClearRow;
import com.comp2042.data.DownData;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;

public final class GameNotificationManager {

    private final Group notificationGroup;

    public GameNotificationManager(Group notificationGroup) {
        this.notificationGroup = notificationGroup;
    }

    public GameNotificationManager(Group notificationGroup, IntegerProperty scoreProperty) {
        this.notificationGroup = notificationGroup;

        if (scoreProperty != null) {
            scoreProperty.addListener((obs, oldVal, newVal) -> {
                int delta = newVal.intValue() - oldVal.intValue();
                handleScoreChanged(delta);
            });
        }
    }

    public void handleScoreChanged(int deltaScore) {
        if (deltaScore <= 0 || notificationGroup == null) {
            return;
        }
        showScoreNotification(deltaScore);
    }

    public void handleDownMovement(DownData downData) {
        if (downData == null || notificationGroup == null) {
            return;
        }

        ClearRow clearRow = downData.getClearRow();
        int bonus = downData.getScoreBonus();

        if (clearRow == null) {
            return;
        }

        if (clearRow.getLinesRemoved() > 0 && bonus > 0) {
            showScoreNotification(bonus);
        }
    }

    public void showBombNotification(int bombsAwarded) {
        if (bombsAwarded <= 0 || notificationGroup == null) {
            return;
        }
        showTextNotification("+" + bombsAwarded + " \uD83D\uDCA3");
    }

    private void showScoreNotification(int scoreBonus) {
        if (notificationGroup == null) {
            return;
        }
        showTextNotification("+" + scoreBonus);
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
