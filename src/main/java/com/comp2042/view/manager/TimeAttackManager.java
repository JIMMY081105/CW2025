package com.comp2042.view.manager;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Objects;

public final class TimeAttackManager {

    private static final int ONE_MINUTE = 1;
    private static final int THREE_MINUTES = 3;
    private static final int FIVE_MINUTES = 5;

    private static final String CLASSIC_MODE_TITLE = "CLASSIC MODE";
    private static final String TIME_ATTACK_TITLE_SUFFIX = " MIN TIME ATTACK";
    private static final String BEST_SCORE_LABEL = "BEST SCORE";
    private static final String BEST_1_MIN_LABEL = "BEST 1 MIN";
    private static final String BEST_3_MIN_LABEL = "BEST 3 MIN";
    private static final String BEST_5_MIN_LABEL = "BEST 5 MIN";
    private static final String DEFAULT_TIME_DISPLAY = "--:--";

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

    /**
     * Creates a manager responsible for time-attack countdowns and best score tracking.
     *
     * @param timerTitleLabel     label showing the mode title.
     * @param timerValueLabel     text displaying remaining time.
     * @param bestScoreTitleLabel label describing the best score shown.
     * @param bestScoreValueLabel text displaying the best score value.
     * @param pauseProperty       property indicating pause state.
     * @param gameOverProperty    property indicating game-over state.
     */
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
        this.pauseProperty = Objects.requireNonNull(pauseProperty, "pauseProperty must not be null");
        this.gameOverProperty = Objects.requireNonNull(gameOverProperty, "gameOverProperty must not be null");

        disableTimeAttack();
    }

    /**
     * Binds the current score property so best scores can be updated when runs end.
     *
     * @param scoreProperty observable score from the board.
     */
    public void bindScoreProperty(IntegerProperty scoreProperty) {
        this.boundScoreProperty = scoreProperty;
        updateBestScoreLabel();
    }

    /**
     * Registers a callback to invoke when the countdown reaches zero.
     *
     * @param callback action to run when time expires.
     */
    public void setOnTimeOver(Runnable callback) {
        this.onTimeOverCallback = callback;
    }

    /**
     * Indicates whether time-attack mode is configured and active.
     *
     * @return {@code true} if enabled.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Configures the time-attack duration and updates UI labels; disables when minutes are non-positive.
     *
     * @param minutes duration in minutes.
     */
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
            timerTitleLabel.setText(configuredMinutes + TIME_ATTACK_TITLE_SUFFIX);
        }

        updateTimerLabel();
        updateBestScoreLabel();
        recreateTimeline();
    }

    /**
     * Starts the countdown from the configured duration.
     */
    public void start() {
        if (!enabled || timeline == null || gameOverProperty.get()) {
            return;
        }
        timeline.playFromStart();
    }

    /**
     * Pauses the countdown without resetting remaining time.
     */
    public void pause() {
        if (timeline != null) {
            timeline.pause();
        }
    }

    /**
     * Resumes the countdown if enabled and not blocked by pause or game over.
     */
    public void resume() {
        if (!enabled || timeline == null || gameOverProperty.get()) {
            return;
        }
        if (!pauseProperty.get()) {
            timeline.play();
        }
    }

    /**
     * Stops the countdown timer.
     */
    public void stop() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    /**
     * Handles game termination by stopping the timer and updating best scores if applicable.
     */
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
            timerTitleLabel.setText(CLASSIC_MODE_TITLE);
        }
        if (timerValueLabel != null) {
            timerValueLabel.setText(DEFAULT_TIME_DISPLAY);
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
            case ONE_MINUTE:
                if (currentScore > bestScore1Min) {
                    bestScore1Min = currentScore;
                }
                break;
            case THREE_MINUTES:
                if (currentScore > bestScore3Min) {
                    bestScore3Min = currentScore;
                }
                break;
            case FIVE_MINUTES:
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
            case ONE_MINUTE:
                label = BEST_1_MIN_LABEL;
                value = bestScore1Min;
                break;
            case THREE_MINUTES:
                label = BEST_3_MIN_LABEL;
                value = bestScore3Min;
                break;
            case FIVE_MINUTES:
                label = BEST_5_MIN_LABEL;
                value = bestScore5Min;
                break;
            default:
                label = BEST_SCORE_LABEL;
                value = 0;
        }

        bestScoreTitleLabel.setText(label);
        bestScoreValueLabel.setText(String.valueOf(value));
    }
}
