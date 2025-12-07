package com.comp2042.view;

import com.comp2042.event.MoveEvent;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class GameInputHandlerTest {

    private KeyEvent key(KeyCode code) {
        return new KeyEvent(
                KeyEvent.KEY_PRESSED,
                "",
                "",
                code,
                false,
                false,
                false,
                false
        );
    }

    @Test
    void handleKeyPressed_GameRunning_DispatchesMovementEvents() {
        BooleanProperty paused = new SimpleBooleanProperty(false);
        BooleanProperty gameOver = new SimpleBooleanProperty(false);

        AtomicInteger movementCalls = new AtomicInteger(0);

        // All movement callbacks just increment the same counter.
        Consumer<MoveEvent> movementCounter = move -> movementCalls.incrementAndGet();

        GameInputHandler handler = new GameInputHandler(
                paused,
                gameOver,
                movementCounter, // down
                movementCounter, // left
                movementCounter, // right
                movementCounter, // rotate
                movementCounter  // hard drop
        );

        handler.handleKeyPressed(key(KeyCode.DOWN));
        handler.handleKeyPressed(key(KeyCode.LEFT));
        handler.handleKeyPressed(key(KeyCode.RIGHT));

        assertEquals(3, movementCalls.get(),
                "Each movement key press should dispatch to one movement callback");
    }

    @Test
    void handleKeyPressed_PausedOrGameOver_IgnoresMovement() {
        BooleanProperty paused = new SimpleBooleanProperty(true);
        BooleanProperty gameOver = new SimpleBooleanProperty(false);

        AtomicInteger movementCalls = new AtomicInteger(0);

        Consumer<MoveEvent> movementCounter = move -> movementCalls.incrementAndGet();

        GameInputHandler handler = new GameInputHandler(
                paused,
                gameOver,
                movementCounter,
                movementCounter,
                movementCounter,
                movementCounter,
                movementCounter
        );

        // While paused → ignore keys
        handler.handleKeyPressed(key(KeyCode.DOWN));
        handler.handleKeyPressed(key(KeyCode.LEFT));
        assertEquals(0, movementCalls.get(),
                "When paused, movement keys should be ignored");

        // Unpause but set game over → still ignore
        paused.set(false);
        gameOver.set(true);

        handler.handleKeyPressed(key(KeyCode.DOWN));
        handler.handleKeyPressed(key(KeyCode.RIGHT));
        assertEquals(0, movementCalls.get(),
                "When game is over, movement keys should also be ignored");
    }
}
