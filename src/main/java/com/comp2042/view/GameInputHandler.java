package com.comp2042.view;

import com.comp2042.event.EventSource;
import com.comp2042.event.EventType;
import com.comp2042.event.MoveEvent;
import javafx.beans.property.BooleanProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.function.Consumer;

/**
 * The {@code GameInputHandler} class listens for key events and forwards them as movement commands to the session
 * manager, respecting pause and game-over states. It acts as a thin adapter between JavaFX input and the
 * {@link com.comp2042.event.InputEventListener}.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/GameInputHandler.java">
 * GameInputHandler.java</a>
 */
public final class GameInputHandler {

    private final BooleanProperty isPause;
    private final BooleanProperty isGameOver;

    private final Consumer<MoveEvent> leftHandler;
    private final Consumer<MoveEvent> rightHandler;
    private final Consumer<MoveEvent> rotateHandler;
    private final Consumer<MoveEvent> downHandler;
    private final Consumer<MoveEvent> hardDropHandler;

    /**
     * Creates an input handler bound to pause and game-over flags and downstream consumers.
     *
     * @param isPause         pause flag.
     * @param isGameOver      game-over flag.
     * @param leftHandler     consumer for left movement.
     * @param rightHandler    consumer for right movement.
     * @param rotateHandler   consumer for rotation.
     * @param downHandler     consumer for soft drop.
     * @param hardDropHandler consumer for hard drop.
     */
    public GameInputHandler(BooleanProperty isPause,
                            BooleanProperty isGameOver,
                            Consumer<MoveEvent> leftHandler,
                            Consumer<MoveEvent> rightHandler,
                            Consumer<MoveEvent> rotateHandler,
                            Consumer<MoveEvent> downHandler,
                            Consumer<MoveEvent> hardDropHandler) {

        this.isPause = isPause;
        this.isGameOver = isGameOver;
        this.leftHandler = leftHandler;
        this.rightHandler = rightHandler;
        this.rotateHandler = rotateHandler;
        this.downHandler = downHandler;
        this.hardDropHandler = hardDropHandler;
    }

    /**
     * Handles a key press event, dispatching movement commands when appropriate.
     *
     * @param keyEvent the key event.
     */
    public void handleKeyPressed(KeyEvent keyEvent) {
        if (isPause.get() || isGameOver.get()) {
            return;
        }

        KeyCode code = keyEvent.getCode();

        if (code == KeyCode.LEFT || code == KeyCode.A) {
            MoveEvent moveEvent = new MoveEvent(EventType.LEFT, EventSource.USER);
            leftHandler.accept(moveEvent);
            keyEvent.consume();
            return;
        }

        if (code == KeyCode.RIGHT || code == KeyCode.D) {
            MoveEvent moveEvent = new MoveEvent(EventType.RIGHT, EventSource.USER);
            rightHandler.accept(moveEvent);
            keyEvent.consume();
            return;
        }

        if (code == KeyCode.UP || code == KeyCode.W) {
            MoveEvent moveEvent = new MoveEvent(EventType.ROTATE, EventSource.USER);
            rotateHandler.accept(moveEvent);
            keyEvent.consume();
            return;
        }

        if (code == KeyCode.DOWN || code == KeyCode.S) {
            MoveEvent moveEvent = new MoveEvent(EventType.DOWN, EventSource.USER);
            downHandler.accept(moveEvent);
            keyEvent.consume();
            return;
        }

        if (code == KeyCode.SPACE) {
            MoveEvent moveEvent = new MoveEvent(EventType.HARD_DROP, EventSource.USER);
            hardDropHandler.accept(moveEvent);
            keyEvent.consume();
        }
    }
}
