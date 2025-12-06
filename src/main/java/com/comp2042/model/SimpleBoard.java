package com.comp2042.model;

import com.comp2042.data.ClearRow;
import com.comp2042.data.ViewData;
import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.BrickGenerator;
import com.comp2042.model.brick.RandomBrickGenerator;
import com.comp2042.util.GameConstants;
import com.comp2042.util.MatrixOperations;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class SimpleBoard implements Board {
    
    private final BrickGenerator brickGenerator;
    private final ActivePiece activePiece;
    private final Score score;

    private final BooleanProperty isGameOver = new SimpleBooleanProperty(false);
    private final ObjectProperty<int[][]> boardMatrix = new SimpleObjectProperty<>();

    private int[][] currentGameMatrix;

    public SimpleBoard(int width, int height) {
        this(width, height, new RandomBrickGenerator());
    }

    public SimpleBoard(int width, int height, BrickGenerator brickGenerator) {
        this.brickGenerator = brickGenerator;
        this.activePiece = new ActivePiece();
        this.score = new Score();

updateBoardMatrix(new int[height][width]);
    }

    @Override
    public BooleanProperty isGameOverProperty() {
        return isGameOver;
    }

    @Override
    public ObjectProperty<int[][]> boardMatrixProperty() {
        return boardMatrix;
    }

    @Override
    public IntegerProperty scoreProperty() {
        return score.scoreProperty();
    }

    @Override
    public boolean moveBrickDown() {
        return activePiece.move(currentGameMatrix, 0, 1);
    }

    @Override
    public boolean moveBrickLeft() {
        return activePiece.move(currentGameMatrix, -1, 0);
    }

    @Override
    public boolean moveBrickRight() {
        return activePiece.move(currentGameMatrix, 1, 0);
    }

    @Override
    public boolean rotateLeftBrick() {
        return activePiece.rotateLeft(currentGameMatrix);
    }

    @Override
    public boolean createNewBrick() {
        Brick currentBrick = brickGenerator.getBrick();
        activePiece.spawn(currentBrick);

        boolean gameOver = MatrixOperations.intersect(
                currentGameMatrix,
                activePiece.getShape(),
                activePiece.getX(),
                activePiece.getY()
        );

        if (gameOver) {
            isGameOver.set(true);
        }

        return gameOver;
    }

    @Override
    public int[][] getBoardMatrix() {

        return currentGameMatrix;
    }

    @Override
    public ViewData getViewData() {
        return activePiece.toViewData(
                brickGenerator.preview(GameConstants.NEXT_PREVIEW_COUNT),
                currentGameMatrix
        );
    }

    @Override
    public void mergeBrickToBackground() {
        int[][] merged = MatrixOperations.merge(
                currentGameMatrix,
                activePiece.getShape(),
                activePiece.getX(),
                activePiece.getY()
        );
        updateBoardMatrix(merged);
    }

    @Override
    public ClearRow clearRows() {
        ClearRow clearRow = MatrixOperations.checkRemoving(currentGameMatrix);
        updateBoardMatrix(clearRow.getNewMatrix());
        return clearRow;
    }

    @Override
    public Score getScore() {
        return score;
    }

    @Override
    public void explodeBomb(int centerX, int centerY) {
        int[][] exploded = MatrixOperations.explodeBomb(currentGameMatrix, centerX, centerY);
        updateBoardMatrix(exploded);
        ClearRow clearRow = MatrixOperations.checkRemoving(exploded);
        updateBoardMatrix(clearRow.getNewMatrix());
    }

private void updateBoardMatrix(int[][] newMatrix) {
        this.currentGameMatrix = newMatrix;
        this.boardMatrix.set(newMatrix);
    }
}
