package com.comp2042.view.manager;

import com.comp2042.testutil.JavaFxTestUtil;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

class TimeAttackManagerTest {

    @BeforeAll
    static void initToolkit() {
        JavaFxTestUtil.initFx();
    }

    @Test
    void configure_SetsEnabledAndTitlesAndTimer() {
        Label timerTitle = new Label();
        Text timerValue = new Text();
        Label bestTitle = new Label();
        Text bestValue = new Text();

        BooleanProperty pause = new SimpleBooleanProperty(false);
        BooleanProperty gameOver = new SimpleBooleanProperty(false);

        TimeAttackManager manager = new TimeAttackManager(
                timerTitle,
                timerValue,
                bestTitle,
                bestValue,
                pause,
                gameOver
        );

        IntegerProperty score = new SimpleIntegerProperty(0);
        manager.bindScoreProperty(score);

        manager.configure(3);

        assertTrue(manager.isEnabled());
        assertEquals("3 MIN TIME ATTACK", timerTitle.getText());
        assertEquals("03:00", timerValue.getText());
        assertEquals("BEST 3 MIN", bestTitle.getText());
        assertEquals("0", bestValue.getText());
    }

    @Test
    void disableTimeAttack_ResetsTitleAndTimerAndBestScoreLabel() {
        Label timerTitle = new Label();
        Text timerValue = new Text();
        Label bestTitle = new Label();
        Text bestValue = new Text();

        BooleanProperty pause = new SimpleBooleanProperty(false);
        BooleanProperty gameOver = new SimpleBooleanProperty(false);

        TimeAttackManager manager = new TimeAttackManager(
                timerTitle,
                timerValue,
                bestTitle,
                bestValue,
                pause,
                gameOver
        );

        IntegerProperty score = new SimpleIntegerProperty(123);
        manager.bindScoreProperty(score);

        manager.configure(1);
        manager.configure(0);

        assertFalse(manager.isEnabled());
        assertEquals("CLASSIC MODE", timerTitle.getText());
        assertEquals("--:--", timerValue.getText());
        assertEquals("BEST SCORE", bestTitle.getText());
        assertEquals("0", bestValue.getText());
    }

    @Test
    void handleGameStopped_UpdatesBestScoreAndKeepsMax() {
        Label timerTitle = new Label();
        Text timerValue = new Text();
        Label bestTitle = new Label();
        Text bestValue = new Text();

        BooleanProperty pause = new SimpleBooleanProperty(false);
        BooleanProperty gameOver = new SimpleBooleanProperty(false);

        TimeAttackManager manager = new TimeAttackManager(
                timerTitle,
                timerValue,
                bestTitle,
                bestValue,
                pause,
                gameOver
        );

        IntegerProperty score = new SimpleIntegerProperty(0);
        manager.bindScoreProperty(score);

        manager.configure(1);

        score.set(100);
        manager.handleGameStopped();
        assertEquals("100", bestValue.getText());

        score.set(80);
        manager.handleGameStopped();
        assertEquals("100", bestValue.getText());
    }
}
