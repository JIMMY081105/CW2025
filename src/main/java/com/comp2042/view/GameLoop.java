package com.comp2042.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.util.Duration;

public class GameLoop {

    private final Timeline timeline;

    public GameLoop(int tickMillis, Runnable onTick) {
        this.timeline = new Timeline(
                new KeyFrame(Duration.millis(tickMillis), e -> onTick.run())
        );
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void start() {
        timeline.play();
    }

    public void pause() {
        timeline.pause();
    }

    public void stop() {
        timeline.stop();
    }

    public boolean isRunning() {
        return timeline.getStatus() == Animation.Status.RUNNING;
    }
}
