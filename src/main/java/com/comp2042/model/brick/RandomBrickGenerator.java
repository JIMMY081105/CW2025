package com.comp2042.model.brick;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.comp2042.util.GameConstants;

public class RandomBrickGenerator implements BrickGenerator {

    private final List<Brick> brickList;
    private final Deque<Brick> nextBricks = new ArrayDeque<>();

    public RandomBrickGenerator() {
        brickList = new ArrayList<>();
        for (int i = 0; i < BrickFactory.getBrickCount(); i++) {
            brickList.add(BrickFactory.createBrick(i));
        }

        fillQueue(GameConstants.NEXT_PREVIEW_COUNT);
    }

    @Override
    public Brick getBrick() {
        ensurePreviewCapacity(GameConstants.NEXT_PREVIEW_COUNT);
        Brick brick = nextBricks.poll();
        fillQueue(GameConstants.NEXT_PREVIEW_COUNT);
        return brick;
    }

    @Override
    public Brick getNextBrick() {
        ensurePreviewCapacity(GameConstants.NEXT_PREVIEW_COUNT);
        return nextBricks.peek();
    }

    @Override
    public List<Brick> preview(int count) {
        ensurePreviewCapacity(Math.max(count, GameConstants.NEXT_PREVIEW_COUNT));
        return new ArrayList<>(nextBricks).subList(0, Math.min(count, nextBricks.size()));
    }

    private void ensurePreviewCapacity(int desiredSize) {
        if (nextBricks.size() < desiredSize) {
            fillQueue(desiredSize);
        }
    }

    private void fillQueue(int desiredSize) {
        while (nextBricks.size() < desiredSize) {
            nextBricks.add(randomBrick());
        }
    }

    private Brick randomBrick() {
        return brickList.get(ThreadLocalRandom.current().nextInt(brickList.size()));
    }
}
