package com.comp2042.model.brick;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrickFactoryTest {

    @Test
    void getBrickCount_ReturnsSeven() {
        assertEquals(7, BrickFactory.getBrickCount());
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
    }

    @Test
    void createBrick_EachBrickHasExpectedRotationStates() {
        assertEquals(2, BrickFactory.createBrick(0).getShapeMatrix().size(), "IBrick should have 2 rotation states");
        assertEquals(4, BrickFactory.createBrick(1).getShapeMatrix().size(), "JBrick should have 4 rotation states");
        assertEquals(4, BrickFactory.createBrick(2).getShapeMatrix().size(), "LBrick should have 4 rotation states");
        assertEquals(1, BrickFactory.createBrick(3).getShapeMatrix().size(), "OBrick should have 1 rotation state");
        assertEquals(2, BrickFactory.createBrick(4).getShapeMatrix().size(), "SBrick should have 2 rotation states");
        assertEquals(4, BrickFactory.createBrick(5).getShapeMatrix().size(), "TBrick should have 4 rotation states");
        assertEquals(2, BrickFactory.createBrick(6).getShapeMatrix().size(), "ZBrick should have 2 rotation states");
    }

    @Test
    void createBrick_InvalidId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> BrickFactory.createBrick(-1));
        assertThrows(IllegalArgumentException.class, () -> BrickFactory.createBrick(7));
        assertThrows(IllegalArgumentException.class, () -> BrickFactory.createBrick(100));
    }
}
