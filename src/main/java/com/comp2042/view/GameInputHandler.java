package com.comp2042.view;

import com.comp2042.event.EventSource;
import com.comp2042.event.EventType;
import com.comp2042.event.MoveEvent;
import javafx.beans.property.BooleanProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.function.Consumer;

public final class GameInputHandler {

    private final BooleanProperty isPause;
    private final BooleanProperty isGameOver;

    private final Consumer<MoveEvent> leftHandler;
    private final Consumer<MoveEvent> rightHandler;
    private final Consumer<MoveEvent> rotateHandler;
    private final Consumer<MoveEvent> downHandler;
    private final Consumer<MoveEvent> hardDropHandler;

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
