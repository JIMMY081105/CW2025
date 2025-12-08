package com.comp2042.view.manager;

import com.comp2042.data.ChinaStageDescriptionProvider;
import com.comp2042.model.brick.BrickFactory;
import com.comp2042.util.GameConfig;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * The {@code ChinaStageManager} class drives the Explore China mode by advancing stage descriptions, applying
 * backgrounds, and adjusting tick speed as the player's score increases. It hides or reveals related UI elements
 * and signals completion when all stages are finished.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/manager/ChinaStageManager.java">
 * ChinaStageManager.java</a>
 */
public final class ChinaStageManager {

    private static final int PLUS_BRICK_STAGE_INDEX = 14;

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

        System.out.println("[ChinaStageManager] Loaded " + stages.size() + " stages from provider");
        hideDescriptionBox();
    }

    /**
     * Enables Explore China mode and applies the first stage.
     */
    public void enableExploreMode() {
        System.out.println("[ChinaStageManager] enableExploreMode() called");
        if (stages.isEmpty()) {
            System.out.println("[ChinaStageManager] No stages available, disabling mode");
            enabled = false;
            hideDescriptionBox();
            return;
        }
        enabled = true;
        currentStageIndex = 0;
        System.out.println("[ChinaStageManager] Enabling mode, applying initial stage index 0");
        applyStage(currentStageIndex);
    }

    /**
     * Disables Explore China mode and hides related UI.
     */
    public void disableExploreMode() {
        System.out.println("[ChinaStageManager] disableExploreMode() called");
        enabled = false;
        hideDescriptionBox();
    }

    /**
     * Returns whether Explore China mode is active.
     *
     * @return {@code true} if enabled.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Handles score changes, advancing stages or completing the journey as needed.
     *
     * @param newScore latest score value.
     */
    public void handleScoreChanged(int newScore) {
        if (!enabled || stages.isEmpty()) {
            return;
        }

        int targetIndex = Math.min(
                newScore / GameConfig.POINTS_PER_CHINA_STAGE,
                stages.size() - 1
        );

        if (targetIndex > currentStageIndex) {
            System.out.println("[ChinaStageManager] Score=" + newScore +
                    " advancing from stage " + currentStageIndex + " to " + targetIndex);
            applyStage(targetIndex);
        }

        checkCompletion(newScore);
    }

    private void applyStage(int stageIndex) {
        if (!enabled || stages.isEmpty()) {
            System.out.println("[ChinaStageManager] applyStage called but mode disabled or no stages");
            return;
        }

        int safeIndex = Math.min(stageIndex, stages.size() - 1);
        currentStageIndex = safeIndex;

        ChinaStageDescriptionProvider.ChinaStage stage = stages.get(safeIndex);

        System.out.println("[ChinaStageManager] Applying stage index " + safeIndex +
                ", name='" + stage.getName() +
                "', bg='" + stage.getBackgroundResource() + "'");

        boolean enablePlusBrick = safeIndex >= PLUS_BRICK_STAGE_INDEX;
        BrickFactory.setPlusEnabled(enablePlusBrick);
        System.out.println("[ChinaStageManager] Plus brick enabled=" + enablePlusBrick +
                " at stage index " + safeIndex);

        if (backgroundApplier != null) {
            backgroundApplier.accept(stage.getBackgroundResource());
        } else {
            System.out.println("[ChinaStageManager] backgroundApplier is null, cannot apply background");
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
                GameConfig.MIN_GAME_TICK_MS,
                GameConfig.GAME_TICK_MS - (safeIndex * GameConfig.CHINA_STAGE_SPEED_STEP)
        );
        if (gameTickUpdater != null) {
            System.out.println("[ChinaStageManager] Updating game tick to " + newTick + " ms");
            gameTickUpdater.accept(newTick);
        } else {
            System.out.println("[ChinaStageManager] gameTickUpdater is null, not updating tick");
        }
    }

    private void checkCompletion(int score) {
        if (!enabled || stages.isEmpty()) {
            return;
        }

        boolean atFinalStage = currentStageIndex >= stages.size() - 1;
        int completionScore = GameConfig.POINTS_PER_CHINA_STAGE * stages.size();

        if (atFinalStage && score >= completionScore) {
            System.out.println("[ChinaStageManager] Journey complete at score=" + score +
                    ", finalStageIndex=" + currentStageIndex +
                    ", completionScore=" + completionScore);
            if (onJourneyCompleted != null) {
                onJourneyCompleted.run();
            } else {
                System.out.println("[ChinaStageManager] onJourneyCompleted callback is null");
            }
        }
    }

    private void hideDescriptionBox() {
        if (descriptionBox != null) {
            descriptionBox.setVisible(false);
            descriptionBox.setManaged(false);
        }
    }
}
