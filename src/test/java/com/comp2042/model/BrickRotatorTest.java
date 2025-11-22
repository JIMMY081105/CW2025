package com.comp2042.model;

import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.NextShapeInfo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BrickRotatorTest {

    private static class TestBrick implements Brick {
        private final List<int[][]> shapes;

        TestBrick(List<int[][]> shapes) {
            this.shapes = shapes;
        }

        @Override
        public List<int[][]> getShapeMatrix() {
            return shapes;
        }
    }

    @Test
    void setBrick_ResetsToFirstShape() {
        int[][] shape0 = {{1}};
        int[][] shape1 = {{2}};

        BrickRotator rotator = new BrickRotator();
        rotator.setBrick(new TestBrick(List.of(shape0, shape1)));

        int[][] current = rotator.getCurrentShape();

        assertTrue(Arrays.deepEquals(shape0, current));
    }

    @Test
    void getNextShape_ReturnsNextRotationAndPosition() {
        int[][] shape0 = {{1}};
        int[][] shape1 = {{2}};
        int[][] shape2 = {{3}};

        BrickRotator rotator = new BrickRotator();
        rotator.setBrick(new TestBrick(List.of(shape0, shape1, shape2)));

        NextShapeInfo next = rotator.getNextShape();

        assertTrue(Arrays.deepEquals(shape1, next.getShape()));
        assertEquals(1, next.getPosition());
    }

    @Test
    void getNextShape_WrapsAroundAfterLastShape() {
        int[][] shape0 = {{1}};
        int[][] shape1 = {{2}};
        int[][] shape2 = {{3}};

        BrickRotator rotator = new BrickRotator();
        rotator.setBrick(new TestBrick(List.of(shape0, shape1, shape2)));

        rotator.setCurrentShape(2);

        NextShapeInfo next = rotator.getNextShape();

        assertTrue(Arrays.deepEquals(shape0, next.getShape()));
        assertEquals(0, next.getPosition());
    }
}
