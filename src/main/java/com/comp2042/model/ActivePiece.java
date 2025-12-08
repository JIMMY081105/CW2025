package com.comp2042.model;

import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.NextShapeInfo;
import com.comp2042.util.GameConfig;
import com.comp2042.util.MatrixOperations;

/**
 * The {@code ActivePiece} class encapsulates the falling piece currently under player control, tracking its
 * orientation and position on the board. It delegates rotation state to {@link BrickRotator}, enforces collision
 * checks against the board matrix, and computes ghost drop locations for rendering.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/ActivePiece.java">
 * ActivePiece.java</a>
 */
public class ActivePiece {

    private final BrickRotator brickRotator = new BrickRotator();
    private int x;
    private int y;

    /**
     * Spawns a new brick at the configured starting coordinates.
     *
     * @param brick brick to activate.
     */
    public void spawn(Brick brick) {
        brickRotator.setBrick(brick);
        this.x = GameConfig.SPAWN_X;
        this.y = GameConfig.SPAWN_Y;
    }

    /**
     * Attempts to move the active piece by the given delta.
     *
     * @param boardMatrix board state used for collision checks.
     * @param dx          horizontal delta in cells.
     * @param dy          vertical delta in cells.
     * @return {@code true} if movement is valid and applied.
     */
    public boolean move(int[][] boardMatrix, int dx, int dy) {
        int nextX = x + dx;
        int nextY = y + dy;

        if (collides(boardMatrix, brickRotator.getCurrentShape(), nextX, nextY)) {
            return false;
        }

        x = nextX;
        y = nextY;
        return true;
    }

    /**
     * Attempts to rotate the active piece counter-clockwise.
     *
     * @param boardMatrix board state used for collision checks.
     * @return {@code true} if rotation is valid.
     */
    public boolean rotateLeft(int[][] boardMatrix) {
        NextShapeInfo nextShape = brickRotator.getNextShape();

        if (collides(boardMatrix, nextShape.getShape(), x, y)) {
            return false;
        }

        brickRotator.setCurrentShape(nextShape.getPosition());
        return true;
    }

    /**
     * Returns the matrix for the current orientation of the active piece.
     *
     * @return current shape matrix.
     */
    public int[][] getShape() {
        return brickRotator.getCurrentShape();
    }

    /**
     * Returns the x-coordinate of the active piece.
     *
     * @return x position in cells.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the active piece.
     *
     * @return y position in cells.
     */
    public int getY() {
        return y;
    }

    /**
     * Calculates the y-coordinate where the piece would land if hard-dropped.
     *
     * @param boardMatrix board state used for collision checks.
     * @return ghost drop y position.
     */
    public int getGhostY(int[][] boardMatrix) {
        int ghostY = y;
        int[][] shape = brickRotator.getCurrentShape();

        while (!collides(boardMatrix, shape, x, ghostY + 1)) {
            ghostY++;
        }

        return ghostY;
    }

    private boolean collides(int[][] boardMatrix, int[][] shape, int targetX, int targetY) {
        return MatrixOperations.intersect(boardMatrix, shape, targetX, targetY);
    }
}
