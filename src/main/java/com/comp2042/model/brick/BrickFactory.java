package com.comp2042.model.brick;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public final class BrickFactory {

    private static final List<Supplier<Brick>> BRICK_SUPPLIERS = Arrays.asList(
            IBrick::new,
            JBrick::new,
            LBrick::new,
            OBrick::new,
            SBrick::new,
            TBrick::new,
            ZBrick::new,
            PlusBrick::new
    );

    private BrickFactory() {

    }

    public static Brick createBrick(int id) {
        if (id < 0 || id >= BRICK_SUPPLIERS.size()) {
            throw new IllegalArgumentException("Invalid brick ID: " + id);
        }
        return BRICK_SUPPLIERS.get(id).get();
    }

    public static int getBrickCount() {
        return BRICK_SUPPLIERS.size();
    }
}
