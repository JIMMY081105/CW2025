package com.comp2042.view.manager;

import com.comp2042.data.ChinaStageDescriptionProvider;
import com.comp2042.util.GameConfig;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class ChinaStageManagerTest {

    @Test
    void enableExploreMode_AppliesFirstStageAndShowsDescriptionBox() {
        VBox descriptionBox = new VBox();
        Text title = new Text();
        Text desc = new Text();

        List<String> appliedBackgrounds = new ArrayList<>();
        AtomicInteger lastTick = new AtomicInteger(0);
        AtomicBoolean completed = new AtomicBoolean(false);

        ChinaStageManager manager = new ChinaStageManager(
                descriptionBox,
                title,
                desc,
                appliedBackgrounds::add,
                lastTick::set,
                () -> completed.set(true)
        );

        assertFalse(manager.isEnabled());

        manager.enableExploreMode();

        assertTrue(manager.isEnabled());
        assertTrue(descriptionBox.isVisible());
        assertTrue(descriptionBox.isManaged());
        assertFalse(appliedBackgrounds.isEmpty());
        assertTrue(lastTick.get() > 0);
        assertFalse(completed.get());
    }

    @Test
    void handleScoreChanged_AdvancesStageWhenThresholdReached() {
        VBox descriptionBox = new VBox();
        Text title = new Text();
        Text desc = new Text();

        List<String> appliedBackgrounds = new ArrayList<>();
        AtomicInteger lastTick = new AtomicInteger(0);

        ChinaStageManager manager = new ChinaStageManager(
                descriptionBox,
                title,
                desc,
                appliedBackgrounds::add,
                lastTick::set,
                () -> {}
        );

        manager.enableExploreMode();
        appliedBackgrounds.clear();

        int scoreForNextStage = GameConfig.POINTS_PER_CHINA_STAGE;
        manager.handleScoreChanged(scoreForNextStage);

        assertFalse(appliedBackgrounds.isEmpty(),
                "Background should be re-applied when moving to a new stage");
        assertTrue(lastTick.get() <= GameConfig.GAME_TICK_MS,
                "Tick speed should not be slower than initial tick");
    }

    @Test
    void handleScoreChanged_WhenJourneyComplete_InvokesCallback() {
        VBox descriptionBox = new VBox();
        Text title = new Text();
        Text desc = new Text();

        List<String> appliedBackgrounds = new ArrayList<>();
        AtomicBoolean completed = new AtomicBoolean(false);

        ChinaStageManager manager = new ChinaStageManager(
                descriptionBox,
                title,
                desc,
                appliedBackgrounds::add,
                tick -> {},
                () -> completed.set(true)
        );

        int stageCount = ChinaStageDescriptionProvider.getStages().size();
        int completionScore = GameConfig.POINTS_PER_CHINA_STAGE * stageCount;

        manager.enableExploreMode();
        manager.handleScoreChanged(completionScore);

        assertTrue(completed.get(), "Journey completion callback should be invoked");
    }
}
