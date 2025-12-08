package com.comp2042.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.util.Duration;

/**
 * The {@code GameLoop} class wraps a JavaFX {@link Timeline} to drive periodic game ticks for automatic movement.
 * It offers lifecycle controls to start, pause, stop, and query the running state of the loop.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/GameLoop.java">
 * GameLoop.java</a>
 */
public class GameLoop {

    private final Timeline timeline;

    /**
     * Creates a repeating loop that invokes the provided callback every tick.
     *
     * @param tickMillis duration of each tick in milliseconds.
     * @param onTick     callback executed per tick.
     */
    public GameLoop(int tickMillis, Runnable onTick) {
        this.timeline = new Timeline(
                new KeyFrame(Duration.millis(tickMillis), e -> onTick.run())
        );
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Starts or resumes the loop.
     */
    public void start() {
        timeline.play();
    }

    /**
     * Pauses the loop without resetting progress.
     */
    public void pause() {
        timeline.pause();
    }

    /**
     * Stops the loop and resets progress.
     */
    public void stop() {
        timeline.stop();
    }

    /**
     * Indicates whether the loop is currently running.
     *
     * @return {@code true} if running.
     */
    public boolean isRunning() {
        return timeline.getStatus() == Animation.Status.RUNNING;
    }
}
