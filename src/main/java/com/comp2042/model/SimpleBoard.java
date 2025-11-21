package com.comp2042.model;

import com.comp2042.data.ClearRow;
import com.comp2042.data.ViewData;
import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.BrickGenerator;
import com.comp2042.model.brick.NextShapeInfo;
import com.comp2042.model.brick.RandomBrickGenerator;
import com.comp2042.util.GameConstants;
import com.comp2042.util.MatrixOperations;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class SimpleBoard implements Board {

    private final int width;
    private final int height;
    private final BrickGenerator brickGenerator;
    private final BrickRotator brickRotator;
    private int[][] currentGameMatrix;
    private int currentX;
    private int currentY;
    private final Score score;
    
    private final BooleanProperty isGameOver = new SimpleBooleanProperty(false);
    private final ObjectProperty<int[][]> boardMatrix = new SimpleObjectProperty<>();

    public SimpleBoard(int width, int height) {
        this.width = width;
        this.height = height;
        currentGameMatrix = new int[height][width];
        brickGenerator = new RandomBrickGenerator();
        brickRotator = new BrickRotator();
        score = new Score();
        boardMatrix.set(currentGameMatrix);
    }

    public BooleanProperty isGameOverProperty() {
        return isGameOver;
    }

    public ObjectProperty<int[][]> boardMatrixProperty() {
        return boardMatrix;
    }

    public IntegerProperty scoreProperty() {
        return score.scoreProperty();
    }

    private boolean tryMove(int xOffset, int yOffset) {
        int[][] currentMatrix = MatrixOperations.copy(currentGameMatrix);
        int nextX = currentX + xOffset;
        int nextY = currentY + yOffset;
        boolean conflict = MatrixOperations.intersect(currentMatrix, brickRotator.getCurrentShape(), nextX, nextY);
        if (conflict) {
            return false;
        } else {
            currentX = nextX;
            currentY = nextY;
            return true;
        }
    }

    @Override
    public boolean moveBrickDown() {
        return tryMove(0, 1);
    }

    @Override
    public boolean moveBrickLeft() {
        return tryMove(-1, 0);
    }

    @Override
    public boolean moveBrickRight() {
        return tryMove(1, 0);
    }

    @Override
    public boolean rotateLeftBrick() {
        int[][] currentMatrix = MatrixOperations.copy(currentGameMatrix);
        NextShapeInfo nextShape = brickRotator.getNextShape();
        boolean conflict = MatrixOperations.intersect(currentMatrix, nextShape.getShape(), currentX, currentY);
        if (conflict) {
            return false;
        } else {
            brickRotator.setCurrentShape(nextShape.getPosition());
            return true;
        }
    }

    @Override
    public boolean createNewBrick() {
        Brick currentBrick = brickGenerator.getBrick();
        brickRotator.setBrick(currentBrick);
        currentX = GameConstants.SPAWN_X;
        currentY = GameConstants.SPAWN_Y;
        boolean gameOver = MatrixOperations.intersect(currentGameMatrix, brickRotator.getCurrentShape(), currentX, currentY);
        if (gameOver) {
            isGameOver.set(true);
        }
        return gameOver;
    }

    @Override
    public int[][] getBoardMatrix() {
        return currentGameMatrix;
    }

    @Override
    public ViewData getViewData() {
        return new ViewData(brickRotator.getCurrentShape(), currentX, currentY, brickGenerator.getNextBrick().getShapeMatrix().get(0));
    }

    @Override
    public void mergeBrickToBackground() {
        currentGameMatrix = MatrixOperations.merge(currentGameMatrix, brickRotator.getCurrentShape(), currentX, currentY);
        boardMatrix.set(currentGameMatrix);
    }

    @Override
    public ClearRow clearRows() {
        ClearRow clearRow = MatrixOperations.checkRemoving(currentGameMatrix);
        currentGameMatrix = clearRow.getNewMatrix();
        boardMatrix.set(currentGameMatrix);
        return clearRow;
    }

    @Override
    public Score getScore() {
        return score;
    }

    @Override
    public void newGame() {
        currentGameMatrix = new int[height][width];
        score.reset();
        isGameOver.set(false);
        createNewBrick();
        boardMatrix.set(currentGameMatrix);
    }
}