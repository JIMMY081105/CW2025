package com.comp2042.model.brick;

import java.util.List;

public interface BrickGenerator {

    Brick getBrick();

    Brick getNextBrick();

    List<Brick> preview(int count);
}
