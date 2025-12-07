package com.comp2042.view.manager;

import com.comp2042.model.Board;
import com.comp2042.util.GameConfig;
import com.comp2042.util.LayoutMetrics;
import com.comp2042.view.effect.BoardVibrationEffect;
import com.comp2042.view.render.BoardRenderer;
import com.comp2042.model.BombEffectService;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public final class BombManager {

    private static final double ACTIVE_OPACITY = 1.0;
    private static final double DISABLED_OPACITY = 0.4;
    private static final double DRAGGING_OPACITY = 0.5;

    private static final double HIGHLIGHT_STROKE_WIDTH = 2.0;
    private static final Color HIGHLIGHT_FILL =
            Color.rgb(255, 100, 50, 0.4);
    private static final Color HIGHLIGHT_STROKE =
            Color.rgb(255, 140, 0, 0.8);

    private final StackPane bombToolbar;
    private final Label bombEmoji;
    private final Label bombCountLabel;
    private final Pane gameLayer;
    private final GridPane gamePanel;

    private final IntegerProperty bombCount;
    private final BooleanProperty isGameOver;

    private final BoardRenderer boardRenderer;
    private final BoardVibrationEffect vibrationEffect;

    private final Runnable onBombDragStarted;
    private final Runnable onBombDragFinished;

    private Board board;

    private Pane bombTargetOverlay;
    private boolean draggingBomb = false;

    private final double gridStep = LayoutMetrics.brickStep();

    public BombManager(StackPane bombToolbar,
                       Label bombEmoji,
                       Label bombCountLabel,
                       Pane gameLayer,
                       GridPane gamePanel,
                       IntegerProperty bombCount,
                       BooleanProperty isGameOver,
                       BoardRenderer boardRenderer,
                       BoardVibrationEffect vibrationEffect,
                       Runnable onBombDragStarted,
                       Runnable onBombDragFinished) {

        this.bombToolbar = bombToolbar;
        this.bombEmoji = bombEmoji;
        this.bombCountLabel = bombCountLabel;
        this.gameLayer = gameLayer;
        this.gamePanel = gamePanel;
        this.bombCount = Objects.requireNonNull(bombCount, "bombCount must not be null");
        this.isGameOver = Objects.requireNonNull(isGameOver, "isGameOver must not be null");
        this.boardRenderer = boardRenderer;
        this.vibrationEffect = vibrationEffect;
        this.onBombDragStarted = onBombDragStarted;
        this.onBombDragFinished = onBombDragFinished;
    }

    public void initialise() {
        if (bombToolbar == null) {
            return;
        }

        bombToolbar.setOnMousePressed(this::onMousePressed);
        bombToolbar.setOnMouseDragged(this::onMouseDragged);
        bombToolbar.setOnMouseReleased(this::onMouseReleased);

        bombCount.addListener((obs, oldVal, newVal) -> updateBombVisuals());
        updateBombVisuals();
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    private void onMousePressed(MouseEvent event) {
        if (isGameOver.get() || bombCount.get() <= 0) {
            return;
        }

        draggingBomb = true;
        if (onBombDragStarted != null) {
            onBombDragStarted.run();
        }

        createOverlay();

        if (bombToolbar != null) {
            bombToolbar.setOpacity(DRAGGING_OPACITY);
        }

        event.consume();
    }

    private void onMouseDragged(MouseEvent event) {
        if (!draggingBomb || bombTargetOverlay == null) {
            return;
        }

        int[] grid = screenToGrid(event.getSceneX(), event.getSceneY());
        if (grid != null) {
            updateTargetHighlight(grid[0], grid[1]);
        } else {
            clearTargetHighlight();
        }

        event.consume();
    }

    private void onMouseReleased(MouseEvent event) {
        if (!draggingBomb) {
            return;
        }

        draggingBomb = false;

        if (bombToolbar != null) {
            bombToolbar.setOpacity(bombCount.get() > 0 ? ACTIVE_OPACITY : DISABLED_OPACITY);
        }

        int[] grid = screenToGrid(event.getSceneX(), event.getSceneY());
        if (grid != null && bombCount.get() > 0) {
            placeBombAt(grid[0], grid[1]);
        }

        removeOverlay();

        if (onBombDragFinished != null) {
            onBombDragFinished.run();
        }

        event.consume();
    }

    private void createOverlay() {
        if (bombTargetOverlay != null) {
            removeOverlay();
        }

        bombTargetOverlay = new Pane();
        bombTargetOverlay.setMouseTransparent(true);
        bombTargetOverlay.setPrefSize(
                LayoutMetrics.gridContentWidth(),
                LayoutMetrics.gridContentHeight()
        );

        if (gamePanel != null) {
            double offsetX = LayoutMetrics.gridCenterOffsetX();
            double offsetY = LayoutMetrics.gridCenterOffsetY();
            bombTargetOverlay.setLayoutX(gamePanel.getLayoutX() + offsetX);
            bombTargetOverlay.setLayoutY(gamePanel.getLayoutY() + offsetY);
        }

        if (gameLayer != null) {
            gameLayer.getChildren().add(bombTargetOverlay);
            bombTargetOverlay.toFront();
        }
    }

    private void removeOverlay() {
        if (bombTargetOverlay != null && gameLayer != null) {
            gameLayer.getChildren().remove(bombTargetOverlay);
            bombTargetOverlay = null;
        }
    }

    private void clearTargetHighlight() {
        if (bombTargetOverlay != null) {
            bombTargetOverlay.getChildren().clear();
        }
    }

    private void updateTargetHighlight(int gridX, int gridY) {
        if (bombTargetOverlay == null) {
            return;
        }

        bombTargetOverlay.getChildren().clear();

        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                int cellX = gridX + dx;
                int cellY = gridY + dy;

                if (cellX >= 0 && cellX < GameConfig.BOARD_WIDTH &&
                        cellY >= 0 && cellY < GameConfig.visibleRows()) {

                    Rectangle highlight = new Rectangle(
                            cellX * gridStep,
                            cellY * gridStep,
                            LayoutMetrics.BRICK_SIZE,
                            LayoutMetrics.BRICK_SIZE
                    );
                    highlight.setFill(HIGHLIGHT_FILL);
                    highlight.setStroke(HIGHLIGHT_STROKE);
                    highlight.setStrokeWidth(HIGHLIGHT_STROKE_WIDTH);
                    highlight.setArcWidth(LayoutMetrics.BRICK_ARC_SIZE);
                    highlight.setArcHeight(LayoutMetrics.BRICK_ARC_SIZE);
                    bombTargetOverlay.getChildren().add(highlight);
                }
            }
        }
    }

    private int[] screenToGrid(double sceneX, double sceneY) {
        if (gamePanel == null) {
            return null;
        }

        Point2D local = gamePanel.sceneToLocal(sceneX, sceneY);

        double offsetX = LayoutMetrics.gridCenterOffsetX();
        double offsetY = LayoutMetrics.gridCenterOffsetY();
        double adjustedX = local.getX() - offsetX;
        double adjustedY = local.getY() - offsetY;

        int gridX = (int) (adjustedX / gridStep);
        int gridY = (int) (adjustedY / gridStep);

        if (gridX >= 0 && gridX < GameConfig.BOARD_WIDTH &&
                gridY >= 0 && gridY < GameConfig.visibleRows()) {
            return new int[]{gridX, gridY};
        }
        return null;
    }

    private void placeBombAt(int gridX, int gridY) {
        if (board == null || bombCount.get() <= 0) {
            return;
        }

        int actualY = gridY + GameConfig.HIDDEN_BUFFER_ROWS;
        BombEffectService.applyBomb(board, gridX, actualY);

        if (boardRenderer != null) {
            boardRenderer.refreshBackground(board.getBoardMatrix());
        }
        if (vibrationEffect != null) {
            vibrationEffect.vibrate();
        }

        bombCount.set(bombCount.get() - 1);
        updateBombVisuals();
    }

    private void updateBombVisuals() {
        int count = bombCount.get();

        if (bombCountLabel != null) {
            bombCountLabel.setText(String.valueOf(count));
            bombCountLabel.setVisible(count > 0);
        }

        double opacity = (count > 0) ? ACTIVE_OPACITY : DISABLED_OPACITY;

        if (bombToolbar != null) {
            bombToolbar.setOpacity(opacity);
        }
        if (bombEmoji != null) {
            bombEmoji.setOpacity(opacity);
        }
    }
}
