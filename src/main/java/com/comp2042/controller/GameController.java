package com.comp2042.controller;

import com.comp2042.data.ClearRow;
import com.comp2042.data.DownData;
import com.comp2042.data.ViewData;
import com.comp2042.event.EventSource;
import com.comp2042.event.InputEventListener;
import com.comp2042.event.MoveEvent;
import com.comp2042.model.Board;
import com.comp2042.model.scoring.ClassicScoringStrategy;
import com.comp2042.model.scoring.ScoringStrategy;

public class GameController implements InputEventListener {

    private final Board board;
    private final ScoringStrategy scoringStrategy;

    public GameController(Board board) {
        this(board, new ClassicScoringStrategy());
    }

    public GameController(Board board, ScoringStrategy scoringStrategy) {
        this.board = board;
        this.scoringStrategy = scoringStrategy;
        board.createNewBrick();
    }

    @Override
    public DownData onDownEvent(MoveEvent event) {
        boolean canMove = board.moveBrickDown();
        ClearRow clearRow = null;

        if (!canMove) {
            clearRow = lockPieceAndHandleLineClear();
        } else {
            awardManualDownScore(event.getEventSource(), 1);
        }

        return new DownData(clearRow, board.getViewData());
    }

    @Override
    public DownData onHardDropEvent(MoveEvent event) {
        int steps = dropPieceToBottom();
        awardManualDownScore(event.getEventSource(), steps);

        ClearRow clearRow = lockPieceAndHandleLineClear();

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

    private int dropPieceToBottom() {
        int steps = 0;
        while (board.moveBrickDown()) {
            steps++;
        }
        return steps;
    }

    private void awardManualDownScore(EventSource source, int steps) {
        if (source != EventSource.USER || steps <= 0) {
            return;
        }
        int score = scoringStrategy.scoreForManualDrop(steps);
        if (score > 0) {
            board.getScore().add(score);
        }
    }

    private ClearRow lockPieceAndHandleLineClear() {
        board.mergeBrickToBackground();

        ClearRow clearRow = board.clearRows();
        int bonus = scoringStrategy.scoreForLineClear(clearRow);
        if (bonus > 0) {
            board.getScore().add(bonus);
        }

        board.createNewBrick();
        return clearRow;
    }
}
