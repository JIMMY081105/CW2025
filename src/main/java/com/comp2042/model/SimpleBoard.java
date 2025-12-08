package com.comp2042.model;

import java.util.List;

import com.comp2042.data.ClearRow;
import com.comp2042.data.ViewData;
import com.comp2042.data.ViewDataFactory;
import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.BrickGenerator;
import com.comp2042.model.brick.RandomBrickGenerator;
import com.comp2042.util.GameConfig;
import com.comp2042.util.MatrixOperations;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * The {@code SimpleBoard} class maintains the core Tetris board state, including active piece movement,
 * collision detection, line clearing, and score updates. It collaborates with {@link ActivePiece},
 * {@link BrickGenerator}, and {@link MatrixOperations} while exposing observable properties for the UI layer.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/SimpleBoard.java">
 * SimpleBoard.java</a>
 */
public final class SimpleBoard implements Board {

    private final BrickGenerator brickGenerator;
    private final ActivePiece activePiece;
    private final Score score;

    private final BooleanProperty isGameOver = new SimpleBooleanProperty(false);
    private final ObjectProperty<int[][]> boardMatrix = new SimpleObjectProperty<>();

    private int[][] currentGameMatrix;

    /**
     * Creates a board with the default random brick generator.
     *
     * @param width  number of columns in the grid.
     * @param height number of rows (including hidden buffer).
     */
    public SimpleBoard(int width, int height) {
        this(width, height, new RandomBrickGenerator());
    }

    /**
     * Creates a board with a supplied brick generator.
     *
     * @param width          number of columns in the grid.
     * @param height         number of rows (including hidden buffer).
     * @param brickGenerator source for spawning new bricks.
     */
    public SimpleBoard(int width, int height, BrickGenerator brickGenerator) {
        this.brickGenerator = brickGenerator;
        this.activePiece = new ActivePiece();
        this.score = new Score();
        updateBoardMatrix(new int[height][width]);
    }

    @Override
    /**
     * Observable flag indicating whether the board reached a game over state.
     *
     * @return game-over property.
     */
    public BooleanProperty isGameOverProperty() {
        return isGameOver;
    }

    @Override
    /**
     * Observable board matrix for UI bindings.
     *
     * @return matrix property.
     */
    public ObjectProperty<int[][]> boardMatrixProperty() {
        return boardMatrix;
    }

    @Override
    /**
     * Exposes the score property for binding.
     *
     * @return score property.
     */
    public IntegerProperty scoreProperty() {
        return score.scoreProperty();
    }

    @Override
    /**
     * Moves the active brick one row down if possible.
     *
     * @return {@code true} if moved successfully; {@code false} if blocked.
     */
    public boolean moveBrickDown() {
        return activePiece.move(currentGameMatrix, 0, 1);
    }

    @Override
    /**
     * Moves the active brick one column left if possible.
     *
     * @return {@code true} if moved successfully; {@code false} otherwise.
     */
    public boolean moveBrickLeft() {
        return activePiece.move(currentGameMatrix, -1, 0);
    }

    @Override
    /**
     * Moves the active brick one column right if possible.
     *
     * @return {@code true} if moved successfully; {@code false} otherwise.
     */
    public boolean moveBrickRight() {
        return activePiece.move(currentGameMatrix, 1, 0);
    }

    @Override
    /**
     * Rotates the active brick counter-clockwise if no collision occurs.
     *
     * @return {@code true} if rotation is valid.
     */
    public boolean rotateLeftBrick() {
        return activePiece.rotateLeft(currentGameMatrix);
    }

    @Override
    /**
     * Spawns a new brick and checks for immediate collision to determine game over.
     *
     * @return {@code true} if spawning causes collision; otherwise {@code false}.
     */
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
    /**
     * Returns a copy of the current board matrix.
     *
     * @return defensive copy of the grid state.
     */
    public int[][] getBoardMatrix() {
        return MatrixOperations.copy(currentGameMatrix);
    }

    @Override
    /**
     * Builds view data describing the active piece, ghost position, and next previews.
     *
     * @return {@link ViewData} snapshot.
     */
    public ViewData getViewData() {
        List<Brick> nextBricks = brickGenerator.preview(GameConfig.NEXT_PREVIEW_COUNT);
        return ViewDataFactory.createViewData(activePiece, currentGameMatrix, nextBricks);
    }

    @Override
    /**
     * Merges the active piece into the background matrix.
     */
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
    /**
     * Clears any completed rows and updates the board matrix.
     *
     * @return result describing removed lines and new matrix.
     */
    public ClearRow clearRows() {
        ClearRow clearRow = MatrixOperations.checkRemoving(currentGameMatrix);
        updateBoardMatrix(clearRow.getNewMatrix());
        return clearRow;
    }

    @Override
    /**
     * Returns the score model for mutation or binding.
     *
     * @return score instance.
     */
    public Score getScore() {
        return score;
    }

    /**
     * Applies a new matrix after external effects such as a bomb.
     *
     * @param newMatrix processed matrix to set.
     */
    void applyBombMatrix(int[][] newMatrix) {
        updateBoardMatrix(newMatrix);
    }

    /**
     * Replaces the current matrix with a copied version to keep bindings in sync.
     *
     * @param newMatrix matrix to apply.
     */
    private void updateBoardMatrix(int[][] newMatrix) {
        int[][] copy = MatrixOperations.copy(newMatrix);
        this.currentGameMatrix = copy;
        this.boardMatrix.set(copy);
    }
}
