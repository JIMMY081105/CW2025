package com.comp2042.view.manager;

import com.comp2042.data.DownData;
import com.comp2042.util.GameConstants;
import com.comp2042.view.render.NotificationPanel;
import javafx.beans.property.IntegerProperty;
import javafx.scene.Group;

public final class GameNotificationManager {

    private final Group notificationGroup;
    private final IntegerProperty bombCount;

    private int lastBombMilestone = 0;

    public GameNotificationManager(Group notificationGroup,
                                   IntegerProperty bombCount) {
        this.notificationGroup = notificationGroup;
        this.bombCount = bombCount;
    }

    public void handleScoreChanged(int newScore) {
        int milestonesReached = newScore / GameConstants.POINTS_PER_BOMB;
        int newBombs = milestonesReached - lastBombMilestone;

        if (newBombs > 0) {
            bombCount.set(bombCount.get() + newBombs);
            lastBombMilestone = milestonesReached;
            showBombNotification(newBombs);
        }
    }

    public void handleDownMovement(DownData downData) {
        if (downData == null || downData.getClearRow() == null) {
            return;
        }
        if (downData.getClearRow().getLinesRemoved() <= 0) {
            return;
        }
        int bonus = downData.getClearRow().getScoreBonus();
        if (bonus > 0) {
            showScoreNotification(bonus);
        }
    }

    private void showBombNotification(int bombsAwarded) {
        NotificationPanel panel = new NotificationPanel("+" + bombsAwarded + " 💣");
        notificationGroup.getChildren().add(panel);
        panel.showScore(notificationGroup.getChildren());
    }

    private void showScoreNotification(int scoreBonus) {
        NotificationPanel panel = new NotificationPanel("+" + scoreBonus);
        notificationGroup.getChildren().add(panel);
        panel.showScore(notificationGroup.getChildren());
    }
}
