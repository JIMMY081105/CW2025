package com.comp2042.controller;

import com.comp2042.data.ClearRow;
import com.comp2042.data.DownData;
import com.comp2042.data.ViewData;
import com.comp2042.event.EventSource;
import com.comp2042.event.InputEventListener;
import com.comp2042.event.MoveEvent;
import com.comp2042.model.Board;
import com.comp2042.util.GameConstants;

public class GameController implements InputEventListener {

    private final Board board;

    public GameController(Board board) {
        this.board = board;
        board.createNewBrick();
    }

    @Override
    public DownData onDownEvent(MoveEvent event) {
        boolean canMove = board.moveBrickDown();
        ClearRow clearRow = null;
        if (!canMove) {
            board.mergeBrickToBackground();
            clearRow = board.clearRows();
            if (clearRow.getLinesRemoved() > 0) {
                board.getScore().add(clearRow.getScoreBonus());
            }
            board.createNewBrick();
        } else {
            if (event.getEventSource() == EventSource.USER) {
                board.getScore().add(GameConstants.MANUAL_DOWN_SCORE);
            }
        }
        return new DownData(clearRow, board.getViewData());
    }

    @Override
    public ViewData onLeftEvent(MoveEvent event) {
        board.moveBrickLeft();
        return board.getViewData();
    }

    @Override
    public ViewData onRightEvent(MoveEvent event) {
        board.moveBrickRight();
        return board.getViewData();
    }

    @Override
    public ViewData onRotateEvent(MoveEvent event) {
        board.rotateLeftBrick();
        return board.getViewData();
    }

    @Override
    public ViewData createNewGame() {
        board.newGame();
        return board.getViewData();
    }
}
