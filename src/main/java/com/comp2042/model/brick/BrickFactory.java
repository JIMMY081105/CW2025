package com.comp2042.model.brick;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * The {@code BrickFactory} class creates brick instances from identifiers, optionally including the plus brick.
 * It centralises brick supplier management and allows the pool to be toggled for specific modes.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/brick/BrickFactory.java">
 * BrickFactory.java</a>
 */
public final class BrickFactory {

    private static boolean plusEnabled = false;

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

    private static final int PLUS_BRICK_INDEX = BRICK_SUPPLIERS.size() - 1;

    private BrickFactory() {
    }

    /**
     * Creates a new brick instance for the given identifier from the active pool.
     *
     * @param id brick type index.
     * @return new brick instance.
     */
    public static Brick createBrick(int id) {
        List<Supplier<Brick>> activeSuppliers = getActiveSuppliers();
        if (id < 0 || id >= activeSuppliers.size()) {
            throw new IllegalArgumentException("Invalid brick ID: " + id);
        }
        return activeSuppliers.get(id).get();
    }

    /**
     * Returns the current number of enabled brick types.
     *
     * @return active brick count.
     */
    public static int getBrickCount() {
        return getActiveSuppliers().size();
    }

    /**
     * Enables or disables the optional plus brick.
     *
     * @param enabled {@code true} to include the plus brick in the pool.
     */
    public static void setPlusEnabled(boolean enabled) {
        plusEnabled = enabled;
    }

    private static List<Supplier<Brick>> getActiveSuppliers() {
        if (plusEnabled) {
            return BRICK_SUPPLIERS;
        }
        return BRICK_SUPPLIERS.subList(0, PLUS_BRICK_INDEX);
    }
}
