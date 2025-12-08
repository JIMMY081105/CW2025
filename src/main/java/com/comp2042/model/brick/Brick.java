package com.comp2042.model.brick;

import java.util.List;

/**
 * The {@code Brick} interface exposes rotation matrices for a Tetris piece.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/brick/Brick.java">
 * Brick.java</a>
 */
public interface Brick {
    List<int[][]> getShapeMatrix();
}

