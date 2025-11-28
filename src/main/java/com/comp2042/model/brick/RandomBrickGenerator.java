package com.comp2042.model.brick;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBrickGenerator implements BrickGenerator {

    private final Random random;

    public RandomBrickGenerator() {
        this(new Random());
    }

    RandomBrickGenerator(Random random) {
        this.random = random;
    }

    @Override
    public Brick getBrick() {
        return createRandomBrick();
    }

    @Override
    public Brick getNextBrick() {
        return createRandomBrick();
    }

    private Brick createRandomBrick() {
        int brickCount = BrickFactory.getBrickCount();
        int id = random.nextInt(brickCount);
        return BrickFactory.createBrick(id);
    }

    @Override
    public List<Brick> preview(int count) {
        List<Brick> previewBricks = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            previewBricks.add(createRandomBrick());
        }
        return previewBricks;
    }
}
