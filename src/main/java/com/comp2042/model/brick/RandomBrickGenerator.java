package com.comp2042.model.brick;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBrickGenerator implements BrickGenerator {

    private final Random random;
    private final List<Brick> brickQueue = new ArrayList<>();
    private int lastBrickPoolSize;

    public RandomBrickGenerator() {
        this(new Random());
    }

    RandomBrickGenerator(Random random) {
        this.random = random;
        initializeQueue();
    }

    private void initializeQueue() {
        lastBrickPoolSize = BrickFactory.getBrickCount();
        for (int i = 0; i < 10; i++) {
            brickQueue.add(createRandomBrick());
        }
    }

    private void refreshIfPoolChanged() {
        int currentPoolSize = BrickFactory.getBrickCount();
        if (currentPoolSize != lastBrickPoolSize) {
            brickQueue.clear();
            lastBrickPoolSize = currentPoolSize;
            initializeQueue();
        }
    }

    @Override
    public Brick getBrick() {
        refreshIfPoolChanged();
        if (brickQueue.isEmpty()) {
            initializeQueue();
        }
        Brick nextBrick = brickQueue.remove(0);
        brickQueue.add(createRandomBrick());
        return nextBrick;
    }

    @Override
    public Brick getNextBrick() {
        refreshIfPoolChanged();
        if (brickQueue.isEmpty()) {
            initializeQueue();
        }
        return brickQueue.get(0);
    }

    private Brick createRandomBrick() {
        int brickCount = BrickFactory.getBrickCount();
        int id = random.nextInt(brickCount);
        return BrickFactory.createBrick(id);
    }

    @Override
    public List<Brick> preview(int count) {
        if (brickQueue.isEmpty()) {
            initializeQueue();
        }
        List<Brick> previewBricks = new ArrayList<>();
        for (int i = 0; i < count && i < brickQueue.size(); i++) {
            previewBricks.add(brickQueue.get(i));
        }
        return previewBricks;
    }
}
