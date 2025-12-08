package com.comp2042.model;

import com.comp2042.data.ClearRow;
import com.comp2042.data.ViewData;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;

/**
 * The {@code Board} interface represents the logical Tetris grid, exposing movement, rotation, spawning,
 * line-clearing, and scoring operations for the game loop and controllers. Implementations provide observable
 * properties for UI binding and decouple model state from rendering concerns.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/Board.java">
 * Board.java</a>
 */
public interface Board {

    /**
     * Attempts to move the active brick one row down.
     *
     * @return {@code true} if the brick moved; {@code false} if blocked and should be locked.
     */
    boolean moveBrickDown();

    /**
     * Attempts to move the active brick one column left.
     *
     * @return {@code true} if the brick moved; {@code false} if blocked.
     */
    boolean moveBrickLeft();

    /**
     * Attempts to move the active brick one column right.
     *
     * @return {@code true} if the brick moved; {@code false} if blocked.
     */
    boolean moveBrickRight();

    /**
     * Attempts to rotate the active brick counter-clockwise.
     *
     * @return {@code true} if rotation succeeded; {@code false} if it collides.
     */
    boolean rotateLeftBrick();

    /**
     * Spawns a new brick at the configured spawn location.
     *
     * @return {@code true} if spawning immediately triggers game over due to collision.
     */
    boolean createNewBrick();

    /**
     * Returns a defensive copy of the current board matrix, including hidden rows.
     *
     * @return matrix where non-zero entries mark occupied cells.
     */
    int[][] getBoardMatrix();

    /**
     * Builds a view-friendly snapshot of the active piece, ghost projection, and previews.
     *
     * @return current {@link ViewData} for rendering.
     */
    ViewData getViewData();

    /**
     * Merges the active brick into the settled background layer.
     */
    void mergeBrickToBackground();

    /**
     * Clears any full rows from the board and returns the result.
     *
     * @return {@link ClearRow} describing removed lines and the new matrix.
     */
    ClearRow clearRows();

    /**
     * Exposes the current score model for mutation and binding.
     *
     * @return score object.
     */
    Score getScore();

    /**
     * Property signalling whether the game has ended.
     *
     * @return game-over property.
     */
    BooleanProperty isGameOverProperty();

    /**
     * Observable board matrix property for UI bindings.
     *
     * @return matrix property.
     */
    ObjectProperty<int[][]> boardMatrixProperty();

    /**
     * Observable score property for UI bindings.
     *
     * @return score property.
     */
    IntegerProperty scoreProperty();
}
