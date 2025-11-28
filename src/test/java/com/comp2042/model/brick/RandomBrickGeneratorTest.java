package com.comp2042.model.brick;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RandomBrickGeneratorTest {

    @Test
    void constructor_InitialNextBrick_IsNotNull() {
        RandomBrickGenerator generator = new RandomBrickGenerator();

        Brick next = generator.getNextBrick();

        assertNotNull(next, "Initial next brick should not be null");
    }

    @Test
    void getBrick_NeverReturnsNull() {
        RandomBrickGenerator generator = new RandomBrickGenerator();

        for (int i = 0; i < 50; i++) {
            Brick brick = generator.getBrick();
            assertNotNull(brick, "getBrick() should never return null (iteration " + i + ")");
        }
    }

    @Test
    void getNextBrick_DoesNotConsumeQueue() {
        RandomBrickGenerator generator = new RandomBrickGenerator();

        Brick preview1 = generator.getNextBrick();
        Brick preview2 = generator.getNextBrick();

        assertSame(preview1, preview2);
    }

    @Test
    void getBrick_RespectsPreviewLogic() {
        RandomBrickGenerator generator = new RandomBrickGenerator();

        Brick previewBefore = generator.getNextBrick();
        Brick firstBrick = generator.getBrick();
        Brick previewAfter = generator.getNextBrick();

        assertSame(previewBefore, firstBrick,
                "First getBrick() should return the brick previously shown by getNextBrick()");

        assertNotNull(previewAfter,
                "Preview after consuming first brick should not be null");
    }

    @Test
    void getBrick_ProducesVarietyOverTime() {
        RandomBrickGenerator generator = new RandomBrickGenerator();
        Set<Class<?>> types = new HashSet<>();

        for (int i = 0; i < 50; i++) {
            Brick brick = generator.getBrick();
            types.add(brick.getClass());
        }

        assertTrue(types.size() > 1,
                "RandomBrickGenerator should produce more than one brick type over time");
    }

    @Test
    void preview_ReturnsRequestedNumberOfBricksWithoutConsuming() {
        RandomBrickGenerator generator = new RandomBrickGenerator();

        List<Brick> preview = generator.preview(3);
        Brick nextBefore = generator.getNextBrick();

        assertEquals(3, preview.size(), "Preview should return three upcoming bricks");
        assertSame(nextBefore, preview.get(0),
                "First preview element should match the next brick returned by getNextBrick()");
    }
}
