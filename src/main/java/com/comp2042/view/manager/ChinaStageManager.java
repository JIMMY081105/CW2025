package com.comp2042.view.manager;

import com.comp2042.data.ChinaStageDescriptionProvider;
import com.comp2042.util.GameConstants;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public final class ChinaStageManager {

    private final List<ChinaStageDescriptionProvider.ChinaStage> stages;
    private final VBox descriptionBox;
    private final Text stateTitleText;
    private final Text stateDescriptionText;

    private final Consumer<String> backgroundApplier;
    private final IntConsumer gameTickUpdater;
    private final Runnable onJourneyCompleted;

    private boolean enabled;
    private int currentStageIndex;

    public ChinaStageManager(VBox descriptionBox,
                             Text stateTitleText,
                             Text stateDescriptionText,
                             Consumer<String> backgroundApplier,
                             IntConsumer gameTickUpdater,
                             Runnable onJourneyCompleted) {

        this.stages = ChinaStageDescriptionProvider.getStages();
        this.descriptionBox = descriptionBox;
        this.stateTitleText = stateTitleText;
        this.stateDescriptionText = stateDescriptionText;
        this.backgroundApplier = backgroundApplier;
        this.gameTickUpdater = gameTickUpdater;
        this.onJourneyCompleted = onJourneyCompleted;

        hideDescriptionBox();
    }

    public void enableExploreMode() {
        if (stages.isEmpty()) {
            enabled = false;
            hideDescriptionBox();
            return;
        }
        enabled = true;
        currentStageIndex = 0;
        applyStage(currentStageIndex);
    }

    public void disableExploreMode() {
        enabled = false;
        hideDescriptionBox();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void handleScoreChanged(int newScore) {
        if (!enabled || stages.isEmpty()) {
            return;
        }

        int targetIndex = Math.min(
                newScore / GameConstants.POINTS_PER_CHINA_STAGE,
                stages.size() - 1
        );

        if (targetIndex > currentStageIndex) {
            applyStage(targetIndex);
        }

        checkCompletion(newScore);
    }

    private void applyStage(int stageIndex) {
        if (!enabled || stages.isEmpty()) {
            return;
        }

        int safeIndex = Math.min(stageIndex, stages.size() - 1);
        currentStageIndex = safeIndex;

        ChinaStageDescriptionProvider.ChinaStage stage = stages.get(safeIndex);

        if (backgroundApplier != null) {
            backgroundApplier.accept(stage.getBackgroundResource());
        }

        if (descriptionBox != null) {
            descriptionBox.setVisible(true);
            descriptionBox.setManaged(true);
        }

        if (stateTitleText != null) {
            stateTitleText.setText(stage.getName());
        }

        if (stateDescriptionText != null) {
            stateDescriptionText.setText(stage.getDescription());
        }

        int newTick = Math.max(
                GameConstants.MIN_GAME_TICK_MS,
                GameConstants.GAME_TICK_MS - (safeIndex * GameConstants.CHINA_STAGE_SPEED_STEP)
        );
        if (gameTickUpdater != null) {
            gameTickUpdater.accept(newTick);
        }
    }

    private void checkCompletion(int score) {
        if (!enabled || stages.isEmpty()) {
            return;
        }

        boolean atFinalStage = currentStageIndex >= stages.size() - 1;
        int completionScore = GameConstants.POINTS_PER_CHINA_STAGE * stages.size();

        if (atFinalStage && score >= completionScore && onJourneyCompleted != null) {
            onJourneyCompleted.run();
        }
    }

    private void hideDescriptionBox() {
        if (descriptionBox != null) {
            descriptionBox.setVisible(false);
            descriptionBox.setManaged(false);
        }
    }
}
