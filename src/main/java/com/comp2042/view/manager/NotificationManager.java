package com.comp2042.view.manager;

import com.comp2042.view.render.NotificationPanel;
import javafx.scene.Group;

/**
 * The {@code NotificationManager} class is a façade for displaying transient score and bomb notifications on
 * legacy screens, creating {@link NotificationPanel} instances and attaching them to a supplied {@link Group}.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/manager/NotificationManager.java">
 * NotificationManager.java</a>
 */
public final class NotificationManager {

    private final Group notificationGroup;

    /**
     * Creates a notification manager bound to the specified group.
     *
     * @param notificationGroup group used to display notifications.
     */
    public NotificationManager(Group notificationGroup) {
        this.notificationGroup = notificationGroup;
    }

    /**
     * Displays a temporary score notification if the bonus is positive.
     *
     * @param scoreBonus amount to display.
     */
    public void showScoreNotification(int scoreBonus) {
        if (notificationGroup == null || scoreBonus <= 0) {
            return;
        }
        NotificationPanel panel = new NotificationPanel("+" + scoreBonus);
        notificationGroup.getChildren().add(panel);
        panel.showScore(notificationGroup.getChildren());
    }

    /**
     * Displays a temporary bomb award notification if at least one bomb is granted.
     *
     * @param bombsAwarded number of bombs awarded.
     */
    public void showBombNotification(int bombsAwarded) {
        if (notificationGroup == null || bombsAwarded <= 0) {
            return;
        }
        NotificationPanel panel = new NotificationPanel("+" + bombsAwarded + " 💣");
        notificationGroup.getChildren().add(panel);
        panel.showScore(notificationGroup.getChildren());
    }
}
