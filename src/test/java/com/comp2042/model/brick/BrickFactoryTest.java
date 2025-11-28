package com.comp2042.model.brick;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrickFactoryTest {

    @Test
    void getBrickCount_MatchesNumberOfSupportedBricks() {

        assertEquals(8, BrickFactory.getBrickCount());
    }

    @Test
    void createBrick_ValidIds_ReturnsNonNullBricks() {
        for (int id = 0; id < BrickFactory.getBrickCount(); id++) {
            Brick brick = BrickFactory.createBrick(id);
            assertNotNull(brick, "Brick for id " + id + " should not be null");
        }
    }

    @Test
    void createBrick_ValidIds_ReturnsCorrectSubclass() {
        assertTrue(BrickFactory.createBrick(0) instanceof IBrick);
        assertTrue(BrickFactory.createBrick(1) instanceof JBrick);
        assertTrue(BrickFactory.createBrick(2) instanceof LBrick);
        assertTrue(BrickFactory.createBrick(3) instanceof OBrick);
        assertTrue(BrickFactory.createBrick(4) instanceof SBrick);
        assertTrue(BrickFactory.createBrick(5) instanceof TBrick);
        assertTrue(BrickFactory.createBrick(6) instanceof ZBrick);
        assertTrue(BrickFactory.createBrick(7) instanceof PlusBrick);
    }

    @Test
    void createBrick_InvalidId_ThrowsIllegalArgumentException() {
        int brickCount = BrickFactory.getBrickCount();

        assertThrows(IllegalArgumentException.class, () -> BrickFactory.createBrick(-1));
        assertThrows(IllegalArgumentException.class, () -> BrickFactory.createBrick(brickCount));
        assertThrows(IllegalArgumentException.class, () -> BrickFactory.createBrick(brickCount + 100));
    }
}
