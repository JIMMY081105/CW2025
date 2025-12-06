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

public class BoardVibrationEffect {

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
                new KeyFrame(Duration.ZERO, keyValuesForTargets(0)),
                new KeyFrame(Duration.millis(30), keyValuesForTargets(8)),
                new KeyFrame(Duration.millis(60), keyValuesForTargets(-6)),
                new KeyFrame(Duration.millis(90), keyValuesForTargets(5)),
                new KeyFrame(Duration.millis(120), keyValuesForTargets(-3)),
                new KeyFrame(Duration.millis(150), keyValuesForTargets(0))
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
