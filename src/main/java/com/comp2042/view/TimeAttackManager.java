package com.comp2042.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.util.Duration;

public final class TimeAttackManager {

    private final Label timerTitleLabel;
    private final Text timerValueLabel;
    private final Label bestScoreTitleLabel;
    private final Text bestScoreValueLabel;

    private final BooleanProperty pauseProperty;
    private final BooleanProperty gameOverProperty;

    private IntegerProperty boundScoreProperty;

    private Timeline timeline;
    private boolean enabled;
    private int configuredMinutes;
    private int totalSeconds;
    private int remainingSeconds;

    private int bestScore1Min;
    private int bestScore3Min;
    private int bestScore5Min;

    private Runnable onTimeOverCallback;

    public TimeAttackManager(Label timerTitleLabel,
                             Text timerValueLabel,
                             Label bestScoreTitleLabel,
                             Text bestScoreValueLabel,
                             BooleanProperty pauseProperty,
                             BooleanProperty gameOverProperty) {

        this.timerTitleLabel = timerTitleLabel;
        this.timerValueLabel = timerValueLabel;
        this.bestScoreTitleLabel = bestScoreTitleLabel;
        this.bestScoreValueLabel = bestScoreValueLabel;
        this.pauseProperty = pauseProperty;
        this.gameOverProperty = gameOverProperty;

        disableTimeAttack();
    }

    public void bindScoreProperty(IntegerProperty scoreProperty) {
        this.boundScoreProperty = scoreProperty;
        updateBestScoreLabel();
    }

    public void setOnTimeOver(Runnable callback) {
        this.onTimeOverCallback = callback;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void configure(int minutes) {
        if (minutes <= 0) {
            disableTimeAttack();
            return;
        }

        this.enabled = true;
        this.configuredMinutes = minutes;
        this.totalSeconds = minutes * 60;
        this.remainingSeconds = totalSeconds;

        if (timerTitleLabel != null) {
            timerTitleLabel.setText(minutes + " MIN TIME ATTACK");
        }

        updateTimerLabel();
        updateBestScoreLabel();
        recreateTimeline();
    }

    public void start() {
        if (!enabled || timeline == null || gameOverProperty.get()) {
            return;
        }
        timeline.playFromStart();
    }

    public void pause() {
        if (timeline != null) {
            timeline.pause();
        }
    }

    public void resume() {
        if (!enabled || timeline == null || gameOverProperty.get()) {
            return;
        }
        if (!pauseProperty.get()) {
            timeline.play();
        }
    }

    public void stop() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    public void handleGameStopped() {
        stop();
        updateBestScoreIfNeeded();
        updateBestScoreLabel();
    }

    private void disableTimeAttack() {
        this.enabled = false;
        this.configuredMinutes = 0;
        this.totalSeconds = 0;
        this.remainingSeconds = 0;

        if (timerTitleLabel != null) {
            timerTitleLabel.setText("CLASSIC MODE");
        }
        if (timerValueLabel != null) {
            timerValueLabel.setText("--:--");
        }
        updateBestScoreLabel();
        if (timeline != null) {
            timeline.stop();
            timeline = null;
        }
    }

    private void recreateTimeline() {
        if (timeline != null) {
            timeline.stop();
        }

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> onTick())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void onTick() {
        if (!enabled || pauseProperty.get() || gameOverProperty.get()) {
            return;
        }

        remainingSeconds--;
        if (remainingSeconds <= 0) {
            remainingSeconds = 0;
            updateTimerLabel();
            stop();
            updateBestScoreIfNeeded();
            updateBestScoreLabel();
            if (onTimeOverCallback != null) {
                onTimeOverCallback.run();
            }
        } else {
            updateTimerLabel();
        }
    }

    private void updateTimerLabel() {
        if (timerValueLabel == null) {
            return;
        }
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        timerValueLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void updateBestScoreIfNeeded() {
        if (!enabled || boundScoreProperty == null) {
            return;
        }

        int currentScore = boundScoreProperty.get();
        switch (configuredMinutes) {
            case 1:
                if (currentScore > bestScore1Min) {
                    bestScore1Min = currentScore;
                }
                break;
            case 3:
                if (currentScore > bestScore3Min) {
                    bestScore3Min = currentScore;
                }
                break;
            case 5:
                if (currentScore > bestScore5Min) {
                    bestScore5Min = currentScore;
                }
                break;
            default:
        }
    }

    private void updateBestScoreLabel() {
        if (bestScoreTitleLabel == null || bestScoreValueLabel == null) {
            return;
        }

        String label;
        int value;

        switch (configuredMinutes) {
            case 1:
                label = "BEST 1 MIN";
                value = bestScore1Min;
                break;
            case 3:
                label = "BEST 3 MIN";
                value = bestScore3Min;
                break;
            case 5:
                label = "BEST 5 MIN";
                value = bestScore5Min;
                break;
            default:
                label = "BEST SCORE";
                value = 0;
        }

        bestScoreTitleLabel.setText(label);
        bestScoreValueLabel.setText(String.valueOf(value));
    }
}
