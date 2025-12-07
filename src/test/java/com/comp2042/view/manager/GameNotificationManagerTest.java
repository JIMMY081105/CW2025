package com.comp2042.view.manager;

import com.comp2042.util.GameConfig;
import com.comp2042.testutil.JavaFxTestUtil;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

class GameNotificationManagerTest {

    @BeforeAll
    static void initToolkit() {
        JavaFxTestUtil.initFx();
    }

    @Test
    void handleScoreChanged_AwardsBombsOnMilestones() {
        Group group = new Group();
        IntegerProperty bombCount = new SimpleIntegerProperty(0);

        GameNotificationManager manager = new GameNotificationManager(group, bombCount);

        int milestoneScore = GameConfig.POINTS_PER_BOMB;

        manager.handleScoreChanged(milestoneScore);

        assertEquals(1, bombCount.get(),
                "Reaching first milestone should award one bomb");
        assertFalse(group.getChildren().isEmpty());

        Node n = group.getChildren().get(group.getChildren().size() - 1);
        assertTrue(n instanceof Label);
        assertTrue(((Label) n).getText().contains("+1"),
                "Bomb notification label should include +1");
    }

    @Test
    void handleScoreChanged_DoesNotReawardSameMilestone() {
        Group group = new Group();
        IntegerProperty bombCount = new SimpleIntegerProperty(0);

        GameNotificationManager manager = new GameNotificationManager(group, bombCount);

        int milestoneScore = GameConfig.POINTS_PER_BOMB;

        manager.handleScoreChanged(milestoneScore);
        manager.handleScoreChanged(milestoneScore);

        assertEquals(1, bombCount.get(),
                "Same milestone reached twice should not award extra bombs");
    }

    @Test
    void handleScoreChanged_MultipleMilestonesInOneJump_AwardsAll() {
        Group group = new Group();
        IntegerProperty bombCount = new SimpleIntegerProperty(0);

        GameNotificationManager manager = new GameNotificationManager(group, bombCount);

        int score = GameConfig.POINTS_PER_BOMB * 3;

        manager.handleScoreChanged(score);

        assertEquals(3, bombCount.get(),
                "Jumping over several milestones should award multiple bombs once");
    }
}
