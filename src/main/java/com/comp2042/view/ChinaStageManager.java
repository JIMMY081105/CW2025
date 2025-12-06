package com.comp2042.view;

import com.comp2042.data.ChinaStageDescriptionProvider;
import com.comp2042.model.brick.BrickFactory;
import com.comp2042.util.GameConstants;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public final class ChinaStageManager {

    private final VBox descriptionBox;
    private final Text stateTitleText;
    private final Text stateDescriptionText;

    private final Consumer<String> backgroundApplier;
    private final IntConsumer gameTickUpdater;
    private final Runnable completionCallback;

    private final List<ChinaStageDescriptionProvider.ChinaStage> stages;

    private boolean enabled;
    private int currentStageIndex;
    private boolean completionTriggered;

    public ChinaStageManager(VBox descriptionBox,
                             Text stateTitleText,
                             Text stateDescriptionText,
                             Consumer<String> backgroundApplier,
                             IntConsumer gameTickUpdater,
                             Runnable completionCallback) {

        this.descriptionBox = descriptionBox;
        this.stateTitleText = stateTitleText;
        this.stateDescriptionText = stateDescriptionText;
        this.backgroundApplier = backgroundApplier;
        this.gameTickUpdater = gameTickUpdater;
        this.completionCallback = completionCallback;
        this.stages = ChinaStageDescriptionProvider.getStages();

        hideDescriptionBox();
    }

    public void enableExploreMode() {
        if (stages.isEmpty()) {
            enabled = false;
            hideDescriptionBox();
            return;
        }
        enabled = true;
        completionTriggered = false;
        currentStageIndex = 0;
        applyStage(0);
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

        if (!completionTriggered) {
            boolean atFinalStage = currentStageIndex >= stages.size() - 1;
            int completionScore = GameConstants.POINTS_PER_CHINA_STAGE * stages.size();
            if (atFinalStage && newScore >= completionScore) {
                completionTriggered = true;
                if (completionCallback != null) {
                    completionCallback.run();
                }
            }
        }
    }

    private void applyStage(int stageIndex) {
        if (!enabled || stages.isEmpty()) {
            return;
        }

        int safeIndex = Math.min(stageIndex, stages.size() - 1);
        currentStageIndex = safeIndex;

        ChinaStageDescriptionProvider.ChinaStage stage = stages.get(safeIndex);

        BrickFactory.setPlusEnabled(safeIndex >= 15);

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

    private void hideDescriptionBox() {
        if (descriptionBox != null) {
            descriptionBox.setVisible(false);
            descriptionBox.setManaged(false);
        }
    }
}
