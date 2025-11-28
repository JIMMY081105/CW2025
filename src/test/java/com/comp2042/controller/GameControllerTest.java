package com.comp2042.controller;

import com.comp2042.data.ClearRow;
import com.comp2042.data.DownData;
import com.comp2042.data.ViewData;
import com.comp2042.event.EventSource;
import com.comp2042.event.EventType;
import com.comp2042.event.MoveEvent;
import com.comp2042.model.Board;
import com.comp2042.model.Score;
import com.comp2042.util.GameConstants;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.junit.jupiter.api.Test;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    private static class FakeBoard implements Board {

        int moveDownCalls;
        int moveLeftCalls;
        int moveRightCalls;
        int rotateLeftCalls;
        int createNewBrickCalls;
        int mergeCalls;
        int clearRowsCalls;
        int newGameCalls;

        boolean nextMoveDownResult = true;

        ClearRow clearRowToReturn;
        ViewData viewDataToReturn;

        private final Score score = new Score();
        private final BooleanProperty gameOver = new SimpleBooleanProperty(false);
        private final ObjectProperty<int[][]> boardMatrix =
                new SimpleObjectProperty<>(new int[0][0]);

        FakeBoard() {
            int[][] currentBrick = new int[][]{{1}};
            int[][] nextBrick = new int[][]{{2}};
            viewDataToReturn = new ViewData(currentBrick, 0, 0, 10, Collections.singletonList(nextBrick));
            clearRowToReturn = new ClearRow(0, new int[][]{{0}}, 0);
        }

        @Override
        public boolean moveBrickDown() {
            moveDownCalls++;
            return nextMoveDownResult;
        }

        @Override
        public boolean moveBrickLeft() {
            moveLeftCalls++;
            return true;
        }

        @Override
        public boolean moveBrickRight() {
            moveRightCalls++;
            return true;
        }

        @Override
        public boolean rotateLeftBrick() {
            rotateLeftCalls++;
            return true;
        }

        @Override
        public boolean createNewBrick() {
            createNewBrickCalls++;
            return false;
        }

        @Override
        public int[][] getBoardMatrix() {
            return boardMatrix.get();
        }

        @Override
        public ViewData getViewData() {
            return viewDataToReturn;
        }

        @Override
        public void mergeBrickToBackground() {
            mergeCalls++;
        }

        @Override
        public ClearRow clearRows() {
            clearRowsCalls++;
            return clearRowToReturn;
        }

        @Override
        public Score getScore() {
            return score;
        }

        @Override
        public void newGame() {
            newGameCalls++;
        }

        @Override
        public BooleanProperty isGameOverProperty() {
            return gameOver;
        }

        @Override
        public ObjectProperty<int[][]> boardMatrixProperty() {
            return boardMatrix;
        }

        @Override
        public IntegerProperty scoreProperty() {
            return score.scoreProperty();
        }
    }

    @Test
    void constructor_CallsCreateNewBrickOnce() {
        FakeBoard board = new FakeBoard();
        assertEquals(0, board.createNewBrickCalls);

        new GameController(board);

        assertEquals(1, board.createNewBrickCalls,
                "GameController constructor should call board.createNewBrick() once");
    }

    @Test
    void onDownEvent_BrickCanMove_UserEvent_AddsManualScoreAndDoesNotCreateNewBrickAgain() {
        FakeBoard board = new FakeBoard();
        GameController controller = new GameController(board);

        board.nextMoveDownResult = true;
        int initialScore = board.getScore().scoreProperty().get();
        int initialCreateCalls = board.createNewBrickCalls;

        DownData result = controller.onDownEvent(
                new MoveEvent(EventType.DOWN, EventSource.USER));

        assertEquals(1, board.moveDownCalls);
        assertEquals(initialCreateCalls, board.createNewBrickCalls,
                "createNewBrick should not be called when piece can move down");
        assertEquals(initialScore + GameConstants.MANUAL_DOWN_SCORE,
                board.getScore().scoreProperty().get(),
                "Manual down from USER should increase score");
        assertNull(result.getClearRow());
        assertSame(board.viewDataToReturn, result.getViewData());
    }

    @Test
    void onDownEvent_BrickStopsAndClearsRows_AddsScoreMergesAndCreatesNewBrick() {
        FakeBoard board = new FakeBoard();
        GameController controller = new GameController(board);

        board.nextMoveDownResult = false;
        board.clearRowToReturn = new ClearRow(2, new int[][]{{0}}, 100);

        int initialScore = board.getScore().scoreProperty().get();
        int initialCreateCalls = board.createNewBrickCalls;

        DownData result = controller.onDownEvent(
                new MoveEvent(EventType.DOWN, EventSource.THREAD));

        assertEquals(1, board.moveDownCalls);
        assertEquals(1, board.mergeCalls, "mergeBrickToBackground should be called when piece stops");
        assertEquals(1, board.clearRowsCalls, "clearRows should be called when piece stops");
        assertEquals(initialCreateCalls + 1, board.createNewBrickCalls,
                "createNewBrick should be called after merging");
        assertEquals(initialScore + 100,
                board.getScore().scoreProperty().get(),
                "Score should increase by ClearRow.getScoreBonus()");
        assertNotNull(result.getClearRow());
        assertEquals(2, result.getClearRow().getLinesRemoved());
        assertSame(board.viewDataToReturn, result.getViewData());
    }

    @Test
    void onLeftEvent_DelegatesToBoardAndReturnsViewData() {
        FakeBoard board = new FakeBoard();
        GameController controller = new GameController(board);

        ViewData result = controller.onLeftEvent(
                new MoveEvent(EventType.LEFT, EventSource.USER));

        assertEquals(1, board.moveLeftCalls);
        assertSame(board.viewDataToReturn, result);
    }

    @Test
    void onRightEvent_DelegatesToBoardAndReturnsViewData() {
        FakeBoard board = new FakeBoard();
        GameController controller = new GameController(board);

        ViewData result = controller.onRightEvent(
                new MoveEvent(EventType.RIGHT, EventSource.USER));

        assertEquals(1, board.moveRightCalls);
        assertSame(board.viewDataToReturn, result);
    }

    @Test
    void onRotateEvent_DelegatesToBoardAndReturnsViewData() {
        FakeBoard board = new FakeBoard();
        GameController controller = new GameController(board);

        ViewData result = controller.onRotateEvent(
                new MoveEvent(EventType.ROTATE, EventSource.USER));

        assertEquals(1, board.rotateLeftCalls);
        assertSame(board.viewDataToReturn, result);
    }

    @Test
    void createNewGame_DelegatesToBoardAndReturnsViewData() {
        FakeBoard board = new FakeBoard();
        GameController controller = new GameController(board);

        ViewData result = controller.createNewGame();

        assertEquals(1, board.newGameCalls,
                "createNewGame should call board.newGame()");
        assertSame(board.viewDataToReturn, result);
    }
}
