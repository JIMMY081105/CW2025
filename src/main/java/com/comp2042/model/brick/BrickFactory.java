package com.comp2042.model.brick;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public final class BrickFactory {

    private static boolean plusEnabled = true;

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
        List<Supplier<Brick>> activeSuppliers = getActiveSuppliers();
        if (id < 0 || id >= activeSuppliers.size()) {
            throw new IllegalArgumentException("Invalid brick ID: " + id);
        }
        return activeSuppliers.get(id).get();
    }

    public static int getBrickCount() {
        return getActiveSuppliers().size();
    }

    public static void setPlusEnabled(boolean enabled) {
        plusEnabled = enabled;
    }

    private static List<Supplier<Brick>> getActiveSuppliers() {
        if (plusEnabled) {
            return BRICK_SUPPLIERS;
        }
        return BRICK_SUPPLIERS.subList(0, BRICK_SUPPLIERS.size() - 1);
    }
}
