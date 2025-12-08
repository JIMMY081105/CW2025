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

/**
 * The {@code GameController} class translates movement events into board actions and updates the score using a
 * pluggable {@link ScoringStrategy}. It acts as a façade over the {@link Board} for the UI layer, handling line
 * clears, bonuses, and spawning of subsequent pieces.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/controller/GameController.java">
 * GameController.java</a>
 */
public class GameController implements InputEventListener {

    private final Board board;
    private final ScoringStrategy scoringStrategy;

    /**
     * Creates a controller using the classic scoring strategy.
     *
     * @param board board to manipulate.
     */
    public GameController(Board board) {
        this(board, new ClassicScoringStrategy());
    }

    /**
     * Creates a controller with a supplied scoring strategy.
     *
     * @param board            board to manipulate.
     * @param scoringStrategy  scoring rules to apply.
     */
    public GameController(Board board, ScoringStrategy scoringStrategy) {
        this.board = board;
        this.scoringStrategy = scoringStrategy;
        board.createNewBrick();
    }

    /**
     * Handles a soft drop tick; locks and clears when movement stops, awarding any bonuses.
     *
     * @param event move event metadata.
     * @return result containing clears and updated view data.
     */
    @Override
    public DownData onDownEvent(MoveEvent event) {
        boolean canMove = board.moveBrickDown();
        ClearRow clearRow = null;
        int lineClearBonus = 0;

        if (!canMove) {
            clearRow = lockPieceAndClearLines();
            if (clearRow != null && clearRow.getLinesRemoved() > 0) {
                lineClearBonus = scoringStrategy.scoreForLineClear(clearRow.getLinesRemoved());
                if (lineClearBonus > 0) {
                    board.getScore().add(lineClearBonus);
                }
            }
        } else {
            awardManualDownScore(event.getEventSource(), 1);
        }

        return new DownData(clearRow, board.getViewData(), lineClearBonus);
    }

    /**
     * Performs a hard drop, awarding manual drop points and resolving any line clears.
     *
     * @param event move event metadata.
     * @return result containing clears and updated view data.
     */
    @Override
    public DownData onHardDropEvent(MoveEvent event) {
        int steps = dropPieceToBottom();
        awardManualDownScore(event.getEventSource(), steps);

        ClearRow clearRow = lockPieceAndClearLines();
        int lineClearBonus = 0;
        if (clearRow != null && clearRow.getLinesRemoved() > 0) {
            lineClearBonus = scoringStrategy.scoreForLineClear(clearRow.getLinesRemoved());
            if (lineClearBonus > 0) {
                board.getScore().add(lineClearBonus);
            }
        }

        return new DownData(clearRow, board.getViewData(), lineClearBonus);
    }

    /**
     * Moves the active piece left, if possible.
     *
     * @param event move event metadata.
     * @return updated view data after movement.
     */
    @Override
    public ViewData onLeftEvent(MoveEvent event) {
        board.moveBrickLeft();
        return board.getViewData();
    }

    /**
     * Moves the active piece right, if possible.
     *
     * @param event move event metadata.
     * @return updated view data after movement.
     */
    @Override
    public ViewData onRightEvent(MoveEvent event) {
        board.moveBrickRight();
        return board.getViewData();
    }

    /**
     * Attempts to rotate the active piece.
     *
     * @param event move event metadata.
     * @return updated view data after rotation.
     */
    @Override
    public ViewData onRotateEvent(MoveEvent event) {
        board.rotateLeftBrick();
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
    
    private ClearRow lockPieceAndClearLines() {
        board.mergeBrickToBackground();
        ClearRow clearRow = board.clearRows();
        board.createNewBrick();
        return clearRow;
    }
}
