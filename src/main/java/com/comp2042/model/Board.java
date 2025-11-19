package com.comp2042.model;

import com.comp2042.data.ClearRow;
import com.comp2042.data.ViewData;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;

public interface Board {

    boolean moveBrickDown();
    boolean moveBrickLeft();
    boolean moveBrickRight();
    boolean rotateLeftBrick();
    boolean createNewBrick();
    int[][] getBoardMatrix();
    ViewData getViewData();
    void mergeBrickToBackground();
    ClearRow clearRows();
    Score getScore();
    void newGame();
    
    BooleanProperty isGameOverProperty();
    ObjectProperty<int[][]> boardMatrixProperty();
    IntegerProperty scoreProperty();
}

