package com.comp2042.model.brick;

import java.util.List;

/**
 * The {@code BrickGenerator} interface supplies new bricks and preview information for the board.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/brick/BrickGenerator.java">
 * BrickGenerator.java</a>
 */
public interface BrickGenerator {

    /**
     * Retrieves and consumes the next brick to spawn.
     *
     * @return the next brick instance.
     */
    Brick getBrick();

    /**
     * Peeks at the upcoming brick without consuming it.
     *
     * @return next brick in the queue.
     */
    Brick getNextBrick();

    /**
     * Returns a preview list of upcoming bricks in spawn order.
     *
     * @param count maximum number of bricks to preview.
     * @return list of preview bricks.
     */
    List<Brick> preview(int count);
}
