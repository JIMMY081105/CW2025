package com.comp2042.view.manager;

import com.comp2042.view.render.NotificationPanel;
import javafx.scene.Group;

public final class NotificationManager {

    private final Group notificationGroup;

    public NotificationManager(Group notificationGroup) {
        this.notificationGroup = notificationGroup;
    }

    public void showScoreNotification(int scoreBonus) {
        if (notificationGroup == null || scoreBonus <= 0) {
            return;
        }
        NotificationPanel panel = new NotificationPanel("+" + scoreBonus);
        notificationGroup.getChildren().add(panel);
        panel.showScore(notificationGroup.getChildren());
    }

    public void showBombNotification(int bombsAwarded) {
        if (notificationGroup == null || bombsAwarded <= 0) {
            return;
        }
        NotificationPanel panel = new NotificationPanel("+" + bombsAwarded + " 💣");
        notificationGroup.getChildren().add(panel);
        panel.showScore(notificationGroup.getChildren());
    }
}
