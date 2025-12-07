package com.comp2042.view.effect;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class BoardVibrationEffect {

    private static final double STEP_1_TIME_MS = 30;
    private static final double STEP_2_TIME_MS = 60;
    private static final double STEP_3_TIME_MS = 90;
    private static final double STEP_4_TIME_MS = 120;
    private static final double STEP_5_TIME_MS = 150;

    private static final double OFFSET_INITIAL = 0;
    private static final double OFFSET_STEP_1 = 8;
    private static final double OFFSET_STEP_2 = -6;
    private static final double OFFSET_STEP_3 = 5;
    private static final double OFFSET_STEP_4 = -3;
    private static final double OFFSET_FINAL = 0;

    private final List<Node> targets = new ArrayList<>();
    private Timeline vibrationTimeline;

    public BoardVibrationEffect(Node... nodes) {
        if (nodes != null) {
            Arrays.stream(nodes)
                    .filter(node -> node != null)
                    .forEach(targets::add);
        }
    }

    public void vibrate() {
        if (targets.isEmpty()) {
            return;
        }

        if (vibrationTimeline != null && vibrationTimeline.getStatus() == Animation.Status.RUNNING) {
            vibrationTimeline.stop();
            resetTargets();
        }

        vibrationTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, keyValuesForTargets(OFFSET_INITIAL)),
                new KeyFrame(Duration.millis(STEP_1_TIME_MS), keyValuesForTargets(OFFSET_STEP_1)),
                new KeyFrame(Duration.millis(STEP_2_TIME_MS), keyValuesForTargets(OFFSET_STEP_2)),
                new KeyFrame(Duration.millis(STEP_3_TIME_MS), keyValuesForTargets(OFFSET_STEP_3)),
                new KeyFrame(Duration.millis(STEP_4_TIME_MS), keyValuesForTargets(OFFSET_STEP_4)),
                new KeyFrame(Duration.millis(STEP_5_TIME_MS), keyValuesForTargets(OFFSET_FINAL))
        );
        vibrationTimeline.setOnFinished(event -> resetTargets());
        vibrationTimeline.play();
    }

    private void resetTargets() {
        targets.forEach(target -> target.setTranslateY(0));
    }

    private KeyValue[] keyValuesForTargets(double value) {
        return targets.stream()
                .map(target -> new KeyValue(target.translateYProperty(), value))
                .toArray(KeyValue[]::new);
    }
}
