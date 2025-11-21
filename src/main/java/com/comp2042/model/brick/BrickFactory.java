package com.comp2042.model.brick;

public class BrickFactory {

    private static final int BRICK_COUNT = 7;

    public static Brick createBrick(int id) {
        switch (id) {
            case 0: return new IBrick();
            case 1: return new JBrick();
            case 2: return new LBrick();
            case 3: return new OBrick();
            case 4: return new SBrick();
            case 5: return new TBrick();
            case 6: return new ZBrick();
            default: throw new IllegalArgumentException("Invalid brick ID: " + id);
        }
    }

    public static int getBrickCount() {
        return BRICK_COUNT;
    }
}