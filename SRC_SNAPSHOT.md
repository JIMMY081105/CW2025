# Source Snapshot

## File Structure
`
Folder PATH listing for volume New Volume
Volume serial number is 1AA1-4DAF
E:\CW2025\SRC
+---main
|   +---java
|   |   \---com
|   |       \---comp2042
|   |           |   Main.java
|   |           |   
|   |           +---controller
|   |           |       GameController.java
|   |           |       
|   |           +---data
|   |           |       ChinaStageDescriptionProvider.java
|   |           |       ClearRow.java
|   |           |       DownData.java
|   |           |       ViewData.java
|   |           |       ViewDataFactory.java
|   |           |       
|   |           +---event
|   |           |       EventSource.java
|   |           |       EventType.java
|   |           |       InputEventListener.java
|   |           |       MoveEvent.java
|   |           |       
|   |           +---model
|   |           |   |   ActivePiece.java
|   |           |   |   Board.java
|   |           |   |   BombEffectService.java
|   |           |   |   BrickRotator.java
|   |           |   |   Score.java
|   |           |   |   SimpleBoard.java
|   |           |   |   
|   |           |   +---brick
|   |           |   |       AbstractBrick.java
|   |           |   |       Brick.java
|   |           |   |       BrickFactory.java
|   |           |   |       BrickGenerator.java
|   |           |   |       IBrick.java
|   |           |   |       JBrick.java
|   |           |   |       LBrick.java
|   |           |   |       NextShapeInfo.java
|   |           |   |       OBrick.java
|   |           |   |       PlusBrick.java
|   |           |   |       RandomBrickGenerator.java
|   |           |   |       SBrick.java
|   |           |   |       TBrick.java
|   |           |   |       ZBrick.java
|   |           |   |       
|   |           |   \---scoring
|   |           |           ClassicScoringStrategy.java
|   |           |           ScoringStrategy.java
|   |           |           
|   |           +---util
|   |           |       BlockTextureProvider.java
|   |           |       GameConfig.java
|   |           |       LayoutMetrics.java
|   |           |       MatrixOperations.java
|   |           |       
|   |           \---view
|   |               |   GameInputHandler.java
|   |               |   GameLoop.java
|   |               |   HomeSelection.java
|   |               |   
|   |               +---effect
|   |               |       BoardVibrationEffect.java
|   |               |       
|   |               +---manager
|   |               |       BackgroundMusicManager.java
|   |               |       BackgroundVideoManager.java
|   |               |       BombManager.java
|   |               |       ChinaStageManager.java
|   |               |       GameLayoutManager.java
|   |               |       GameNotificationManager.java
|   |               |       GameSessionManager.java
|   |               |       NotificationManager.java
|   |               |       TimeAttackManager.java
|   |               |       
|   |               +---render
|   |               |       BoardRenderer.java
|   |               |       GameOverPanel.java
|   |               |       NextBricksRenderer.java
|   |               |       NotificationPanel.java
|   |               |       
|   |               \---screen
|   |                       GameScreenController.java
|   |                       HomeController.java
|   |                       ModeSelectionController.java
|   |                       
|   \---resources
|       |   china_stages.properties
|       |   digital.ttf
|       |   gameLayout.fxml
|       |   home_layout.fxml
|       |   selection_layout.fxml
|       |   window_style.css
|       |   
|       +---audio
|       |       explorechina.mp3
|       |       mainmusic.mp3
|       |       timeracing.mp3
|       |       
|       +---images
|       |   |   bomb.png
|       |   |   
|       |   +---Bricks
|       |   |       IBrick.png
|       |   |       JBrick.png
|       |   |       LBrick.png
|       |   |       OBrick.png
|       |   |       PlusBrick.png
|       |   |       SBrick.png
|       |   |       TBrick.png
|       |   |       ZBrick.png
|       |   |       
|       |   +---china
|       |   |       1.jpg
|       |   |       10.jpg
|       |   |       11.jpg
|       |   |       12.jpg
|       |   |       13.jpg
|       |   |       14.jpg
|       |   |       15.jpg
|       |   |       16.jpg
|       |   |       17.jpg
|       |   |       18.jpg
|       |   |       19.jpg
|       |   |       2.jpg
|       |   |       20.jpg
|       |   |       21.jpg
|       |   |       22.jpg
|       |   |       23.jpg
|       |   |       24.jpg
|       |   |       25.jpg
|       |   |       26.jpg
|       |   |       27.jpg
|       |   |       28.jpg
|       |   |       29.jpg
|       |   |       3.jpg
|       |   |       30.jpg
|       |   |       4.jpg
|       |   |       5.jpg
|       |   |       6.jpg
|       |   |       7.jpg
|       |   |       8.jpg
|       |   |       9.jpg
|       |   |       
|       |   \---time stages
|       |           1.jpg
|       |           3.jpg
|       |           5.jpg
|       |           
|       \---video
|               mainpage.mp4
|               
\---test
    \---java
        \---com
            \---comp2042
                +---controller
                |       GameControllerTest.java
                |       
                +---data
                |       ClearRowTest.java
                |       DownDataTest.java
                |       ViewDataTest.java
                |       
                +---model
                |   |   ActivePieceTest.java
                |   |   BombEffectServiceTest.java
                |   |   BrickRotatorTest.java
                |   |   ScoreTest.java
                |   |   SimpleBoardTest.java
                |   |   
                |   \---brick
                |           BrickFactoryTest.java
                |           RandomBrickGeneratorTest.java
                |           
                +---testutil
                |       JavaFxTestUtil.java
                |       
                +---util
                |       GameConfigTest.java
                |       LayoutMetricsTest.java
                |       MatrixOperationsTest.java
                |       
                \---view
                    |   GameInputHandlerTest.java
                    |   HomeSelectionTest.java
                    |   
                    +---manager
                    |       ChinaStageManagerTest.java
                    |       GameNotificationManagerTest.java
                    |       TimeAttackManagerTest.java
                    |       
                    +---render
                    |       NextBricksRendererTest.java
                    |       NotificationPanelTest.java
                    |       
                    \---screen
                            ModeSelectionControllerTest.java
                            
`

## Files

### E:\CW2025\src\main\java\com\comp2042\controller\GameController.java
`$lang
package com.comp2042.controller;

import com.comp2042.data.ClearRow;
import com.comp2042.data.DownData;
import com.comp2042.data.ViewData;
import com.comp2042.event.EventSource;
import com.comp2042.event.InputEventListener;
import com.comp2042.event.MoveEvent;
import com.comp2042.model.Board;
import com.comp2042.model.scoring.ClassicScoringStrategy;
import com.comp2042.model.scoring.ScoringStrategy;

/**
 * The {@code GameController} class translates movement events into board actions and updates the score using a
 * pluggable {@link ScoringStrategy}. It acts as a façade over the {@link Board} for the UI layer, handling line
 * clears, bonuses, and spawning of subsequent pieces.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/controller/GameController.java">
 * GameController.java</a>
 */
public class GameController implements InputEventListener {

    private final Board board;
    private final ScoringStrategy scoringStrategy;

    /**
     * Creates a controller using the classic scoring strategy.
     *
     * @param board board to manipulate.
     */
    public GameController(Board board) {
        this(board, new ClassicScoringStrategy());
    }

    /**
     * Creates a controller with a supplied scoring strategy.
     *
     * @param board            board to manipulate.
     * @param scoringStrategy  scoring rules to apply.
     */
    public GameController(Board board, ScoringStrategy scoringStrategy) {
        this.board = board;
        this.scoringStrategy = scoringStrategy;
        board.createNewBrick();
    }

    /**
     * Handles a soft drop tick; locks and clears when movement stops, awarding any bonuses.
     *
     * @param event move event metadata.
     * @return result containing clears and updated view data.
     */
    @Override
    public DownData onDownEvent(MoveEvent event) {
        boolean canMove = board.moveBrickDown();
        ClearRow clearRow = null;
        int lineClearBonus = 0;

        if (!canMove) {
            clearRow = lockPieceAndClearLines();
            if (clearRow != null && clearRow.getLinesRemoved() > 0) {
                lineClearBonus = scoringStrategy.scoreForLineClear(clearRow.getLinesRemoved());
                if (lineClearBonus > 0) {
                    board.getScore().add(lineClearBonus);
                }
            }
        } else {
            awardManualDownScore(event.getEventSource(), 1);
        }

        return new DownData(clearRow, board.getViewData(), lineClearBonus);
    }

    /**
     * Performs a hard drop, awarding manual drop points and resolving any line clears.
     *
     * @param event move event metadata.
     * @return result containing clears and updated view data.
     */
    @Override
    public DownData onHardDropEvent(MoveEvent event) {
        int steps = dropPieceToBottom();
        awardManualDownScore(event.getEventSource(), steps);

        ClearRow clearRow = lockPieceAndClearLines();
        int lineClearBonus = 0;
        if (clearRow != null && clearRow.getLinesRemoved() > 0) {
            lineClearBonus = scoringStrategy.scoreForLineClear(clearRow.getLinesRemoved());
            if (lineClearBonus > 0) {
                board.getScore().add(lineClearBonus);
            }
        }

        return new DownData(clearRow, board.getViewData(), lineClearBonus);
    }

    /**
     * Moves the active piece left, if possible.
     *
     * @param event move event metadata.
     * @return updated view data after movement.
     */
    @Override
    public ViewData onLeftEvent(MoveEvent event) {
        board.moveBrickLeft();
        return board.getViewData();
    }

    /**
     * Moves the active piece right, if possible.
     *
     * @param event move event metadata.
     * @return updated view data after movement.
     */
    @Override
    public ViewData onRightEvent(MoveEvent event) {
        board.moveBrickRight();
        return board.getViewData();
    }

    /**
     * Attempts to rotate the active piece.
     *
     * @param event move event metadata.
     * @return updated view data after rotation.
     */
    @Override
    public ViewData onRotateEvent(MoveEvent event) {
        board.rotateLeftBrick();
        return board.getViewData();
    }

    private int dropPieceToBottom() {
        int steps = 0;
        while (board.moveBrickDown()) {
            steps++;
        }
        return steps;
    }

    private void awardManualDownScore(EventSource source, int steps) {
        if (source != EventSource.USER || steps <= 0) {
            return;
        }
        int score = scoringStrategy.scoreForManualDrop(steps);
        if (score > 0) {
            board.getScore().add(score);
        }
    }
    
    private ClearRow lockPieceAndClearLines() {
        board.mergeBrickToBackground();
        ClearRow clearRow = board.clearRows();
        board.createNewBrick();
        return clearRow;
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\data\ChinaStageDescriptionProvider.java
`$lang
package com.comp2042.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * The {@code ChinaStageDescriptionProvider} class loads Explore China stage metadata from a bundled properties
 * file and exposes immutable descriptions for use by the China stage manager.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/data/ChinaStageDescriptionProvider.java">
 * ChinaStageDescriptionProvider.java</a>
 */
public final class ChinaStageDescriptionProvider {

    public static final class ChinaStage {
        private final String name;
        private final String backgroundResource;
        private final String description;

        public ChinaStage(String name, String backgroundResource, String description) {
            this.name = name;
            this.backgroundResource = backgroundResource;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getBackgroundResource() {
            return backgroundResource;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "ChinaStage{" +
                    "name='" + name + '\'' +
                    ", backgroundResource='" + backgroundResource + '\'' +
                    '}';
        }
    }

    private static final String CONFIG_FILE = "china_stages.properties";
    private static final List<ChinaStage> STAGES = loadStages();

    private ChinaStageDescriptionProvider() {

    }

    public static List<ChinaStage> getStages() {
        return STAGES;
    }

    private static List<ChinaStage> loadStages() {
        Properties props = new Properties();

        System.out.println("[ChinaStageDescriptionProvider] Loading properties from: " + CONFIG_FILE);

        try (InputStream in = ChinaStageDescriptionProvider.class
                .getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {

            if (in == null) {
                throw new IllegalStateException("Missing resource: " + CONFIG_FILE);
            }

            props.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load " + CONFIG_FILE, e);
        }

        int count = Integer.parseInt(props.getProperty("stage.count", "0"));
        System.out.println("[ChinaStageDescriptionProvider] stage.count=" + count);

        if (count <= 0) {
            throw new IllegalStateException("Invalid or missing 'stage.count' in " + CONFIG_FILE);
        }

        List<ChinaStage> stages = new ArrayList<>(count);

        for (int i = 1; i <= count; i++) {
            String prefix = "stage." + i + ".";

            String name = props.getProperty(prefix + "name");
            String image = props.getProperty(prefix + "image");
            String description = props.getProperty(prefix + "description");

            if (name == null || image == null || description == null) {
                throw new IllegalStateException("Missing properties for " + prefix + " in " + CONFIG_FILE);
            }

            ChinaStage stage = new ChinaStage(name, image, description);
            System.out.println("[ChinaStageDescriptionProvider] Loaded " + stage);
            stages.add(stage);
        }

        return Collections.unmodifiableList(stages);
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\data\ClearRow.java
`$lang
package com.comp2042.data;

/**
 * The {@code ClearRow} class describes the outcome of clearing completed rows, capturing the number removed
 * and the resulting board matrix.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/data/ClearRow.java">
 * ClearRow.java</a>
 */
public final class ClearRow {

    private final int linesRemoved;
    private final int[][] newMatrix;

    /**
     * Creates a clear-row result.
     *
     * @param linesRemoved number of rows removed.
     * @param newMatrix    resulting board matrix.
     */
    public ClearRow(int linesRemoved, int[][] newMatrix) {
        this.linesRemoved = linesRemoved;
        this.newMatrix = newMatrix;
    }

    /**
     * Returns how many lines were removed.
     *
     * @return count of cleared rows.
     */
    public int getLinesRemoved() {
        return linesRemoved;
    }

    /**
     * Returns the updated matrix after clearing.
     *
     * @return new board matrix.
     */
    public int[][] getNewMatrix() {
        return newMatrix;
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\data\DownData.java
`$lang
package com.comp2042.data;

/**
 * The {@code DownData} class aggregates the result of a downward movement, combining cleared-row data,
 * updated view data, and any score bonus awarded. It is used by controllers to update the UI after movement.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/data/DownData.java">
 * DownData.java</a>
 */
public final class DownData {

    private final ClearRow clearRow;
    private final ViewData viewData;
    private final int scoreBonus;
    /**
     * Creates a result without an explicit score bonus.
     *
     * @param clearRow details about cleared lines.
     * @param viewData view snapshot after the move.
     */
    public DownData(ClearRow clearRow, ViewData viewData) {
        this(clearRow, viewData, 0);
    }

    /**
     * Creates a result including a score bonus.
     *
     * @param clearRow   details about cleared lines.
     * @param viewData   view snapshot after the move.
     * @param scoreBonus bonus points awarded for the move.
     */
    public DownData(ClearRow clearRow, ViewData viewData, int scoreBonus) {
        this.clearRow = clearRow;
        this.viewData = viewData;
        this.scoreBonus = scoreBonus;
    }

    /**
     * Returns the line-clear details.
     *
     * @return {@link ClearRow} result.
     */
    public ClearRow getClearRow() {
        return clearRow;
    }

    /**
     * Returns the view data to render.
     *
     * @return {@link ViewData} after movement.
     */
    public ViewData getViewData() {
        return viewData;
    }

    /**
     * Returns any score bonus from the move.
     *
     * @return bonus value (may be zero).
     */
    public int getScoreBonus() {
        return scoreBonus;
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\data\ViewData.java
`$lang
package com.comp2042.data;

import com.comp2042.util.MatrixOperations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The {@code ViewData} class is an immutable snapshot of data required to render the active piece, ghost
 * projection, and upcoming previews. It provides defensive copies to decouple view rendering from model state.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/data/ViewData.java">
 * ViewData.java</a>
 */
public final class ViewData {

    private final int[][] brickData;
    private final int xPosition;
    private final int yPosition;
    private final int ghostYPosition;
    private final List<int[][]> nextBricksData;

    /**
     * Constructs a snapshot of the active piece and previews.
     *
     * @param brickData      shape matrix of the active piece.
     * @param xPosition      x-coordinate of the active piece.
     * @param yPosition      y-coordinate of the active piece.
     * @param ghostYPosition y-coordinate of the ghost drop position.
     * @param nextBricksData preview shapes for upcoming bricks.
     */
    public ViewData(int[][] brickData, int xPosition, int yPosition, int ghostYPosition, List<int[][]> nextBricksData) {
        this.brickData = MatrixOperations.copy(brickData);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.ghostYPosition = ghostYPosition;
        this.nextBricksData = copyNextBricks(nextBricksData);
    }

    /**
     * Returns a defensive copy of the active brick matrix.
     *
     * @return active brick data.
     */
    public int[][] getBrickData() {
        return MatrixOperations.copy(brickData);
    }

    /**
     * Returns the x-coordinate of the active piece.
     *
     * @return x position.
     */
    public int getXPosition() {
        return xPosition;
    }

    /**
     * Returns the y-coordinate of the active piece.
     *
     * @return y position.
     */
    public int getYPosition() {
        return yPosition;
    }

    /**
     * Returns the ghost drop y-coordinate.
     *
     * @return ghost y position.
     */
    public int getGhostYPosition() {
        return ghostYPosition;
    }

    /**
     * Returns deep copies of preview brick matrices.
     *
     * @return list of preview shapes.
     */
    public List<int[][]> getNextBricksData() {
        return copyNextBricks(nextBricksData);
    }

    private static List<int[][]> copyNextBricks(List<int[][]> source) {
        if (source == null || source.isEmpty()) {
            return Collections.emptyList();
        }
        List<int[][]> copies = new ArrayList<>(source.size());
        for (int[][] shape : source) {
            copies.add(MatrixOperations.copy(shape));
        }
        return copies;
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\data\ViewDataFactory.java
`$lang
package com.comp2042.data;

import com.comp2042.model.ActivePiece;
import com.comp2042.model.brick.Brick;
import com.comp2042.util.GameConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code ViewDataFactory} class builds {@link ViewData} snapshots from the active piece, board matrix,
 * and upcoming bricks. It centralises preview selection and ghost position calculation for rendering.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/data/ViewDataFactory.java">
 * ViewDataFactory.java</a>
 */
public final class ViewDataFactory {

    private ViewDataFactory() {
    }

    /**
     * Creates a {@link ViewData} instance for the current active piece and preview queue.
     *
     * @param activePiece the active falling piece.
     * @param boardMatrix current board matrix for ghost computation.
     * @param nextBricks  preview bricks to display.
     * @return populated view data snapshot.
     */
    public static ViewData createViewData(ActivePiece activePiece,
                                          int[][] boardMatrix,
                                          List<Brick> nextBricks) {
        List<int[][]> previews = buildPreviews(nextBricks);
        int ghostY = activePiece.getGhostY(boardMatrix);
        return new ViewData(activePiece.getShape(), activePiece.getX(), activePiece.getY(), ghostY, previews);
    }

    private static List<int[][]> buildPreviews(List<Brick> nextBricks) {
        List<int[][]> previews = new ArrayList<>();
        if (nextBricks == null) {
            return previews;
        }

        int maxCount = GameConfig.NEXT_PREVIEW_COUNT;
        int size = nextBricks.size();

        for (int i = 0; i < maxCount && i < size; i++) {
            Brick brick = nextBricks.get(i);
            if (brick == null) {
                continue;
            }
            List<int[][]> shapes = brick.getShapeMatrix();
            if (shapes.isEmpty()) {
                continue;
            }
            previews.add(shapes.get(0));
        }

        return previews;
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\event\EventSource.java
`$lang
package com.comp2042.event;

/**
 * The {@code EventSource} enum identifies whether an input event originated from the user or the game loop.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/event/EventSource.java">
 * EventSource.java</a>
 */
public enum EventSource {
    USER, THREAD
}
```

### E:\CW2025\src\main\java\com\comp2042\event\EventType.java
`$lang
package com.comp2042.event;

/**
 * The {@code EventType} enum lists movement and rotation commands that can be triggered in the game.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/event/EventType.java">
 * EventType.java</a>
 */
public enum EventType {
    DOWN, LEFT, RIGHT, ROTATE, HARD_DROP
}
```

### E:\CW2025\src\main\java\com\comp2042\event\InputEventListener.java
`$lang
package com.comp2042.event;

import com.comp2042.data.DownData;
import com.comp2042.data.ViewData;

/**
 * The {@code InputEventListener} interface defines callbacks for movement and drop events generated by user input
 * or the game loop. Implementations update the board and return view data or movement results for rendering.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/event/InputEventListener.java">
 * InputEventListener.java</a>
 */
public interface InputEventListener {

    /**
     * Handles a downward move, potentially locking and clearing rows.
     *
     * @param event move metadata.
     * @return result containing clear information and view data.
     */
    DownData onDownEvent(MoveEvent event);

    /**
     * Handles a left move request.
     *
     * @param event move metadata.
     * @return updated view data.
     */
    ViewData onLeftEvent(MoveEvent event);

    /**
     * Handles a right move request.
     *
     * @param event move metadata.
     * @return updated view data.
     */
    ViewData onRightEvent(MoveEvent event);

    /**
     * Handles a rotation request.
     *
     * @param event move metadata.
     * @return updated view data after rotation attempt.
     */
    ViewData onRotateEvent(MoveEvent event);

    /**
     * Handles a hard drop request.
     *
     * @param event move metadata.
     * @return result containing clear information and view data.
     */
    DownData onHardDropEvent(MoveEvent event);
}
```

### E:\CW2025\src\main\java\com\comp2042\event\MoveEvent.java
`$lang
package com.comp2042.event;

/**
 * The {@code MoveEvent} class encapsulates a movement command and its origin, allowing controllers to distinguish
 * user input from automated ticks.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/event/MoveEvent.java">
 * MoveEvent.java</a>
 */
public final class MoveEvent {

    private final EventType eventType;
    private final EventSource eventSource;

    /**
     * Constructs a move event with the given type and source.
     *
     * @param eventType   movement command.
     * @param eventSource originator of the event.
     */
    public MoveEvent(EventType eventType, EventSource eventSource) {
        this.eventType = eventType;
        this.eventSource = eventSource;
    }

    /**
     * Returns the movement command type.
     *
     * @return event type.
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * Returns the origin of the command.
     *
     * @return event source.
     */
    public EventSource getEventSource() {
        return eventSource;
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\Main.java
`$lang
package com.comp2042;

import com.comp2042.controller.GameController;
import com.comp2042.model.Board;
import com.comp2042.model.SimpleBoard;
import com.comp2042.util.GameConfig;
import com.comp2042.util.LayoutMetrics;
import com.comp2042.view.HomeSelection;
import com.comp2042.view.manager.BackgroundMusicManager;
import com.comp2042.view.manager.BackgroundVideoManager;
import com.comp2042.view.screen.GameScreenController;
import com.comp2042.view.screen.HomeController;
import com.comp2042.view.screen.ModeSelectionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The {@code Main} class serves as the JavaFX entry point, orchestrating navigation between home, mode selection,
 * and game screens. It wires controllers to boards, applies UI scaling, and configures background media when
 * launching different gameplay modes.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/Main.java">
 * Main.java</a>
 */
public class Main extends Application {

    private static final double HOME_UI_SCALE = 1.3;
    private static final double SELECTION_UI_SCALE = 1.3;
    private static final double GAME_UI_SCALE = 1.0;

    private static final String OPTION_1_MINUTE_SPRINT = "1 Minute Sprint";
    private static final String OPTION_3_MINUTE_RUSH = "3 Minute Rush";
    private static final String OPTION_5_MINUTE_MARATHON = "5 Minute Marathon";

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        showHome(primaryStage);
    }

    private void showHome(Stage primaryStage) throws IOException {
        URL homeLocation = getClass().getClassLoader().getResource("home_layout.fxml");
        if (homeLocation == null) {
            throw new IllegalStateException("Missing FXML: home_layout.fxml");
        }

        FXMLLoader homeLoader = new FXMLLoader(homeLocation);
        Parent homeRoot = homeLoader.load();
        HomeController homeController = homeLoader.getController();

        applyScale(homeRoot, HOME_UI_SCALE);
        setSceneAndMaximize(primaryStage, homeRoot);
        primaryStage.show();

        homeController.setSelectionHandler(mode -> showModeSelection(primaryStage, mode));
    }

    private void showModeSelection(Stage primaryStage, HomeSelection.Mode mode) {
        try {
            if (mode == HomeSelection.Mode.COUNTRY_EXPLORE) {
                safeLaunchGame(primaryStage,
                        new HomeSelection(HomeSelection.Mode.COUNTRY_EXPLORE, "China"));
                return;
            }

            URL selectionLocation = getClass().getClassLoader().getResource("selection_layout.fxml");
            if (selectionLocation == null) {
                throw new IllegalStateException("Missing FXML: selection_layout.fxml");
            }

            FXMLLoader selectionLoader = new FXMLLoader(selectionLocation);
            Parent selectionRoot = selectionLoader.load();
            ModeSelectionController controller = selectionLoader.getController();

            controller.configure(
                    mode,
                    selection -> safeLaunchGame(primaryStage, selection),
                    () -> safeShowHome(primaryStage)
            );

            applyScale(selectionRoot, SELECTION_UI_SCALE);
            setSceneAndMaximize(primaryStage, selectionRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void launchGame(Stage primaryStage, HomeSelection selection) throws Exception {
        Board board = new SimpleBoard(GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT);

        URL location = getClass().getClassLoader().getResource("gameLayout.fxml");
        if (location == null) {
            throw new IllegalStateException("Missing FXML: gameLayout.fxml");
        }

        ResourceBundle resources = null;
        FXMLLoader fxmlLoader = new FXMLLoader(location, resources);
        Parent root = fxmlLoader.load();
        GameScreenController gameScreenController = fxmlLoader.getController();

        applyScale(root, GAME_UI_SCALE);
        setSceneAndMaximize(primaryStage, root);

        GameController gameController = new GameController(board);
        gameScreenController.setEventListener(gameController);
        gameScreenController.bind(board);
        gameScreenController.bindScore(board.scoreProperty());

        gameScreenController.setNavigationHandlers(
                () -> safeShowHome(primaryStage),
                () -> safeLaunchGame(primaryStage, selection)
        );

        applySelectionToGame(selection, gameScreenController);
    }

    private void applySelectionToGame(HomeSelection selection, GameScreenController gameScreenController) {
        if (selection == null) {
            return;
        }

        if (selection.mode() == HomeSelection.Mode.COUNTRY_EXPLORE) {
            BackgroundMusicManager.playExploreChinaMusic();
            gameScreenController.configureExploreChinaMode();
            return;
        }

        if (selection.mode() == HomeSelection.Mode.TIME_RACING) {
            gameScreenController.showModeLabel("Time Racing: " + selection.option());

            int minutes = switch (selection.option()) {
                case OPTION_1_MINUTE_SPRINT -> 1;
                case OPTION_3_MINUTE_RUSH -> 3;
                case OPTION_5_MINUTE_MARATHON -> 5;
                default -> 0;
            };

            if (minutes > 0) {
                BackgroundMusicManager.playTimeRacingMusic();
                gameScreenController.configureTimeAttack(minutes);
            }
        }
    }

    private void setSceneAndMaximize(Stage stage, Parent root) {
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(
                    root,
                    LayoutMetrics.initialWindowWidth(),
                    LayoutMetrics.initialWindowHeight()
            );
            stage.setScene(scene);
            stage.setMinWidth(LayoutMetrics.initialWindowWidth());
            stage.setMinHeight(LayoutMetrics.initialWindowHeight());

            stage.setMaximized(true);
            stage.setFullScreen(true);
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            stage.setFullScreenExitHint("");
        } else {
            scene.setRoot(root);
        }
    }

    private void applyScale(Parent root, double scale) {
        if (root != null) {
            root.setScaleX(scale);
            root.setScaleY(scale);
        }
    }


    private void safeLaunchGame(Stage primaryStage, HomeSelection selection) {
        try {
            launchGame(primaryStage, selection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void safeShowHome(Stage primaryStage) {
        try {
            showHome(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        BackgroundVideoManager.dispose();
        BackgroundMusicManager.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\model\ActivePiece.java
`$lang
package com.comp2042.model;

import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.NextShapeInfo;
import com.comp2042.util.GameConfig;
import com.comp2042.util.MatrixOperations;

/**
 * The {@code ActivePiece} class encapsulates the falling piece currently under player control, tracking its
 * orientation and position on the board. It delegates rotation state to {@link BrickRotator}, enforces collision
 * checks against the board matrix, and computes ghost drop locations for rendering.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/ActivePiece.java">
 * ActivePiece.java</a>
 */
public class ActivePiece {

    private final BrickRotator brickRotator = new BrickRotator();
    private int x;
    private int y;

    /**
     * Spawns a new brick at the configured starting coordinates.
     *
     * @param brick brick to activate.
     */
    public void spawn(Brick brick) {
        brickRotator.setBrick(brick);
        this.x = GameConfig.SPAWN_X;
        this.y = GameConfig.SPAWN_Y;
    }

    /**
     * Attempts to move the active piece by the given delta.
     *
     * @param boardMatrix board state used for collision checks.
     * @param dx          horizontal delta in cells.
     * @param dy          vertical delta in cells.
     * @return {@code true} if movement is valid and applied.
     */
    public boolean move(int[][] boardMatrix, int dx, int dy) {
        int nextX = x + dx;
        int nextY = y + dy;

        if (collides(boardMatrix, brickRotator.getCurrentShape(), nextX, nextY)) {
            return false;
        }

        x = nextX;
        y = nextY;
        return true;
    }

    /**
     * Attempts to rotate the active piece counter-clockwise.
     *
     * @param boardMatrix board state used for collision checks.
     * @return {@code true} if rotation is valid.
     */
    public boolean rotateLeft(int[][] boardMatrix) {
        NextShapeInfo nextShape = brickRotator.getNextShape();

        if (collides(boardMatrix, nextShape.getShape(), x, y)) {
            return false;
        }

        brickRotator.setCurrentShape(nextShape.getPosition());
        return true;
    }

    /**
     * Returns the matrix for the current orientation of the active piece.
     *
     * @return current shape matrix.
     */
    public int[][] getShape() {
        return brickRotator.getCurrentShape();
    }

    /**
     * Returns the x-coordinate of the active piece.
     *
     * @return x position in cells.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the active piece.
     *
     * @return y position in cells.
     */
    public int getY() {
        return y;
    }

    /**
     * Calculates the y-coordinate where the piece would land if hard-dropped.
     *
     * @param boardMatrix board state used for collision checks.
     * @return ghost drop y position.
     */
    public int getGhostY(int[][] boardMatrix) {
        int ghostY = y;
        int[][] shape = brickRotator.getCurrentShape();

        while (!collides(boardMatrix, shape, x, ghostY + 1)) {
            ghostY++;
        }

        return ghostY;
    }

    private boolean collides(int[][] boardMatrix, int[][] shape, int targetX, int targetY) {
        return MatrixOperations.intersect(boardMatrix, shape, targetX, targetY);
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\model\Board.java
`$lang
package com.comp2042.model;

import com.comp2042.data.ClearRow;
import com.comp2042.data.ViewData;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;

/**
 * The {@code Board} interface represents the logical Tetris grid, exposing movement, rotation, spawning,
 * line-clearing, and scoring operations for the game loop and controllers. Implementations provide observable
 * properties for UI binding and decouple model state from rendering concerns.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/Board.java">
 * Board.java</a>
 */
public interface Board {

    /**
     * Attempts to move the active brick one row down.
     *
     * @return {@code true} if the brick moved; {@code false} if blocked and should be locked.
     */
    boolean moveBrickDown();

    /**
     * Attempts to move the active brick one column left.
     *
     * @return {@code true} if the brick moved; {@code false} if blocked.
     */
    boolean moveBrickLeft();

    /**
     * Attempts to move the active brick one column right.
     *
     * @return {@code true} if the brick moved; {@code false} if blocked.
     */
    boolean moveBrickRight();

    /**
     * Attempts to rotate the active brick counter-clockwise.
     *
     * @return {@code true} if rotation succeeded; {@code false} if it collides.
     */
    boolean rotateLeftBrick();

    /**
     * Spawns a new brick at the configured spawn location.
     *
     * @return {@code true} if spawning immediately triggers game over due to collision.
     */
    boolean createNewBrick();

    /**
     * Returns a defensive copy of the current board matrix, including hidden rows.
     *
     * @return matrix where non-zero entries mark occupied cells.
     */
    int[][] getBoardMatrix();

    /**
     * Builds a view-friendly snapshot of the active piece, ghost projection, and previews.
     *
     * @return current {@link ViewData} for rendering.
     */
    ViewData getViewData();

    /**
     * Merges the active brick into the settled background layer.
     */
    void mergeBrickToBackground();

    /**
     * Clears any full rows from the board and returns the result.
     *
     * @return {@link ClearRow} describing removed lines and the new matrix.
     */
    ClearRow clearRows();

    /**
     * Exposes the current score model for mutation and binding.
     *
     * @return score object.
     */
    Score getScore();

    /**
     * Property signalling whether the game has ended.
     *
     * @return game-over property.
     */
    BooleanProperty isGameOverProperty();

    /**
     * Observable board matrix property for UI bindings.
     *
     * @return matrix property.
     */
    ObjectProperty<int[][]> boardMatrixProperty();

    /**
     * Observable score property for UI bindings.
     *
     * @return score property.
     */
    IntegerProperty scoreProperty();
}
```

### E:\CW2025\src\main\java\com\comp2042\model\BombEffectService.java
`$lang
package com.comp2042.model;

import com.comp2042.data.ClearRow;
import com.comp2042.util.MatrixOperations;

public final class BombEffectService {

    private BombEffectService() {
    }

    /**
     * Applies a bomb explosion at the specified coordinates, clears resulting lines, and updates the board matrix.
     *
     * @param board   board whose matrix will be mutated.
     * @param centerX x-coordinate of the explosion centre.
     * @param centerY y-coordinate of the explosion centre.
     * @return {@link ClearRow} describing removed lines and the new matrix.
     */
    public static ClearRow applyBomb(Board board, int centerX, int centerY) {
        int[][] exploded = MatrixOperations.explodeBomb(
                board.getBoardMatrix(),
                centerX,
                centerY
        );

        ClearRow clearRow = MatrixOperations.checkRemoving(exploded);

        if (board instanceof SimpleBoard) {
            SimpleBoard simpleBoard = (SimpleBoard) board;
            simpleBoard.applyBombMatrix(clearRow.getNewMatrix());
        }

        return clearRow;
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\model\brick\AbstractBrick.java
`$lang
package com.comp2042.model.brick;

import com.comp2042.util.MatrixOperations;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code AbstractBrick} class provides shared storage and deep-copying support for concrete brick shapes,
 * requiring subclasses to initialise their rotation matrices.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/brick/AbstractBrick.java">
 * AbstractBrick.java</a>
 */
public abstract class AbstractBrick implements Brick {

    protected final List<int[][]> brickMatrix = new ArrayList<>();

    /**
     * Constructs a brick and triggers shape initialisation.
     */
    protected AbstractBrick() {
        initializeShapes();
    }

    protected abstract void initializeShapes();

    @Override
    /**
     * Returns deep copies of the rotation matrices for this brick.
     *
     * @return list of shape matrices.
     */
    public List<int[][]> getShapeMatrix() {
        return MatrixOperations.deepCopyList(brickMatrix);
    }
}

```

### E:\CW2025\src\main\java\com\comp2042\model\brick\Brick.java
`$lang
package com.comp2042.model.brick;

import java.util.List;

/**
 * The {@code Brick} interface exposes rotation matrices for a Tetris piece.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/brick/Brick.java">
 * Brick.java</a>
 */
public interface Brick {
    List<int[][]> getShapeMatrix();
}

```

### E:\CW2025\src\main\java\com\comp2042\model\brick\BrickFactory.java
`$lang
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
```

### E:\CW2025\src\main\java\com\comp2042\model\brick\BrickGenerator.java
`$lang
package com.comp2042.model.brick;

import java.util.List;

/**
 * The {@code BrickGenerator} interface supplies new bricks and preview information for the board.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/brick/BrickGenerator.java">
 * BrickGenerator.java</a>
 */
public interface BrickGenerator {

    /**
     * Retrieves and consumes the next brick to spawn.
     *
     * @return the next brick instance.
     */
    Brick getBrick();

    /**
     * Peeks at the upcoming brick without consuming it.
     *
     * @return next brick in the queue.
     */
    Brick getNextBrick();

    /**
     * Returns a preview list of upcoming bricks in spawn order.
     *
     * @param count maximum number of bricks to preview.
     * @return list of preview bricks.
     */
    List<Brick> preview(int count);
}
```

### E:\CW2025\src\main\java\com\comp2042\model\brick\IBrick.java
`$lang
package com.comp2042.model.brick;

final class IBrick extends AbstractBrick {

    @Override
    protected void initializeShapes() {
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });
        brickMatrix.add(new int[][]{
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0}
        });
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\model\brick\JBrick.java
`$lang
package com.comp2042.model.brick;

final class JBrick extends AbstractBrick {

    @Override
    protected void initializeShapes() {
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {2, 2, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0}
        });
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {0, 2, 2, 0},
                {0, 2, 0, 0},
                {0, 2, 0, 0}
        });
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 2, 2, 2},
                {0, 0, 0, 0}
        });
        brickMatrix.add(new int[][]{
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 2, 2, 0},
                {0, 0, 0, 0}
        });
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\model\brick\LBrick.java
`$lang
package com.comp2042.model.brick;

final class LBrick extends AbstractBrick {

    @Override
    protected void initializeShapes() {
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {0, 3, 3, 3},
                {0, 3, 0, 0},
                {0, 0, 0, 0}
        });
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {0, 3, 3, 0},
                {0, 0, 3, 0},
                {0, 0, 3, 0}
        });
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {0, 0, 3, 0},
                {3, 3, 3, 0},
                {0, 0, 0, 0}
        });
        brickMatrix.add(new int[][]{
                {0, 3, 0, 0},
                {0, 3, 0, 0},
                {0, 3, 3, 0},
                {0, 0, 0, 0}
        });
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\model\brick\NextShapeInfo.java
`$lang
package com.comp2042.model.brick;

import com.comp2042.util.MatrixOperations;

/**
 * The {@code NextShapeInfo} class holds details about an upcoming brick rotation, including its shape matrix and index.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/brick/NextShapeInfo.java">
 * NextShapeInfo.java</a>
 */
public final class NextShapeInfo {

    private final int[][] shape;
    private final int position;

    /**
     * Constructs a descriptor for the next shape orientation.
     *
     * @param shape     matrix representing the next orientation.
     * @param position  index of the orientation within the brick.
     */
    public NextShapeInfo(final int[][] shape, final int position) {
        this.shape = shape;
        this.position = position;
    }

    /**
     * Returns a defensive copy of the shape matrix.
     *
     * @return next shape matrix.
     */
    public int[][] getShape() {
        return MatrixOperations.copy(shape);
    }

    /**
     * Returns the index of the next orientation.
     *
     * @return rotation index.
     */
    public int getPosition() {
        return position;
    }
}

```

### E:\CW2025\src\main\java\com\comp2042\model\brick\OBrick.java
`$lang
package com.comp2042.model.brick;

final class OBrick extends AbstractBrick {

    @Override
    protected void initializeShapes() {
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {0, 4, 4, 0},
                {0, 4, 4, 0},
                {0, 0, 0, 0}
        });
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\model\brick\PlusBrick.java
`$lang
package com.comp2042.model.brick;

final class PlusBrick extends AbstractBrick {

    @Override
    protected void initializeShapes() {
        brickMatrix.add(new int[][]{
                {0, 8, 0, 0},
                {8, 8, 8, 0},
                {0, 8, 0, 0},
                {0, 0, 0, 0}
        });
    }
}

```

### E:\CW2025\src\main\java\com\comp2042\model\brick\RandomBrickGenerator.java
`$lang
package com.comp2042.model.brick;

import com.comp2042.util.GameConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The {@code RandomBrickGenerator} class maintains a queue of randomly selected bricks, refreshing when the
 * available brick pool changes. It supports previewing upcoming pieces and ensures deterministic supply based
 * on an underlying {@link Random} source.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/brick/RandomBrickGenerator.java">
 * RandomBrickGenerator.java</a>
 */
public class RandomBrickGenerator implements BrickGenerator {

    private final Random random;
    private final List<Brick> brickQueue = new ArrayList<>();
    private int lastBrickPoolSize;

    /**
     * Creates a generator using a new random source.
     */
    public RandomBrickGenerator() {
        this(new Random());
    }

    /**
     * Creates a generator using the provided random source.
     *
     * @param random random number generator for brick selection.
     */
    RandomBrickGenerator(Random random) {
        this.random = random;
        initializeQueue();
    }

    private void initializeQueue() {
        lastBrickPoolSize = BrickFactory.getBrickCount();
        brickQueue.clear();
        for (int i = 0; i < GameConfig.INITIAL_QUEUE_SIZE; i++) {
            brickQueue.add(createRandomBrick());
        }
    }

    private void refreshIfPoolChanged() {
        int currentPoolSize = BrickFactory.getBrickCount();
        if (currentPoolSize != lastBrickPoolSize) {
            initializeQueue();
        }
    }

    private void ensureQueueReady() {
        refreshIfPoolChanged();
        if (brickQueue.isEmpty()) {
            initializeQueue();
        }
    }

    @Override
    public Brick getBrick() {
        ensureQueueReady();
        Brick nextBrick = brickQueue.remove(0);
        brickQueue.add(createRandomBrick());
        return nextBrick;
    }

    @Override
    public Brick getNextBrick() {
        ensureQueueReady();
        return brickQueue.get(0);
    }

    private Brick createRandomBrick() {
        int brickCount = BrickFactory.getBrickCount();
        int id = random.nextInt(brickCount);
        return BrickFactory.createBrick(id);
    }

    @Override
    public List<Brick> preview(int count) {
        ensureQueueReady();
        List<Brick> previewBricks = new ArrayList<>();
        for (int i = 0; i < count && i < brickQueue.size(); i++) {
            previewBricks.add(brickQueue.get(i));
        }
        return previewBricks;
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\model\brick\SBrick.java
`$lang
package com.comp2042.model.brick;

final class SBrick extends AbstractBrick {

    @Override
    protected void initializeShapes() {
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {0, 5, 5, 0},
                {5, 5, 0, 0},
                {0, 0, 0, 0}
        });
        brickMatrix.add(new int[][]{
                {5, 0, 0, 0},
                {5, 5, 0, 0},
                {0, 5, 0, 0},
                {0, 0, 0, 0}
        });
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\model\brick\TBrick.java
`$lang
package com.comp2042.model.brick;

final class TBrick extends AbstractBrick {

    @Override
    protected void initializeShapes() {
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {6, 6, 6, 0},
                {0, 6, 0, 0},
                {0, 0, 0, 0}
        });
        brickMatrix.add(new int[][]{
                {0, 6, 0, 0},
                {0, 6, 6, 0},
                {0, 6, 0, 0},
                {0, 0, 0, 0}
        });
        brickMatrix.add(new int[][]{
                {0, 6, 0, 0},
                {6, 6, 6, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });
        brickMatrix.add(new int[][]{
                {0, 6, 0, 0},
                {6, 6, 0, 0},
                {0, 6, 0, 0},
                {0, 0, 0, 0}
        });
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\model\brick\ZBrick.java
`$lang
package com.comp2042.model.brick;

final class ZBrick extends AbstractBrick {

    @Override
    protected void initializeShapes() {
        brickMatrix.add(new int[][]{
                {0, 0, 0, 0},
                {7, 7, 0, 0},
                {0, 7, 7, 0},
                {0, 0, 0, 0}
        });
        brickMatrix.add(new int[][]{
                {0, 7, 0, 0},
                {7, 7, 0, 0},
                {7, 0, 0, 0},
                {0, 0, 0, 0}
        });
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\model\BrickRotator.java
`$lang
package com.comp2042.model;

import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.NextShapeInfo;

import java.util.List;

/**
 * The {@code BrickRotator} class coordinates rotation state for a single brick, exposing current and next
 * orientations while validating index bounds. It separates rotation management from placement logic in the board.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/BrickRotator.java">
 * BrickRotator.java</a>
 */
public class BrickRotator {

    private Brick brick;
    private int currentShape = 0;

    /**
     * Returns the next rotation for the current brick without mutating state.
     *
     * @return info about the next shape matrix and its index.
     */
    public NextShapeInfo getNextShape() {
        ensureBrickSet();
        List<int[][]> shapes = brick.getShapeMatrix();
        if (shapes.isEmpty()) {
            throw new IllegalStateException("Brick has no shapes");
        }

        int nextShape = (currentShape + 1) % shapes.size();
        return new NextShapeInfo(shapes.get(nextShape), nextShape);
    }

    /**
     * Retrieves the current orientation matrix.
     *
     * @return the active shape matrix.
     */
    public int[][] getCurrentShape() {
        ensureBrickSet();
        List<int[][]> shapes = brick.getShapeMatrix();
        if (shapes.isEmpty()) {
            throw new IllegalStateException("Brick has no shapes");
        }
        return shapes.get(currentShape);
    }

    /**
     * Sets the active orientation index for the current brick.
     *
     * @param currentShape index into the brick's shape list.
     */
    public void setCurrentShape(int currentShape) {
        ensureBrickSet();
        List<int[][]> shapes = brick.getShapeMatrix();
        int size = shapes.size();
        if (currentShape < 0 || currentShape >= size) {
            throw new IllegalArgumentException("Invalid shape index: " + currentShape);
        }
        this.currentShape = currentShape;
    }

    /**
     * Assigns the brick to manage and resets rotation to the first orientation.
     *
     * @param brick the brick to rotate.
     */
    public void setBrick(Brick brick) {
        if (brick == null) {
            throw new IllegalArgumentException("Brick cannot be null");
        }
        this.brick = brick;
        currentShape = 0;
    }

    private void ensureBrickSet() {
        if (brick == null) {
            throw new IllegalStateException("Brick not set in BrickRotator");
        }
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\model\Score.java
`$lang
package com.comp2042.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * The {@code Score} class tracks the player's score as an observable property for UI binding and updates,
 * encapsulating mutation helpers without exposing internal state.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/Score.java">
 * Score.java</a>
 */
public final class Score {

    private final IntegerProperty score = new SimpleIntegerProperty(0);

    /**
     * Returns the observable score property for UI binding.
     *
     * @return score property.
     */
    public IntegerProperty scoreProperty() {
        return score;
    }

    /**
     * Increments the current score by the specified amount.
     *
     * @param i amount to add; may be negative to subtract points.
     */
    public void add(int i){
        score.setValue(score.getValue() + i);
    }

    /**
     * Resets the score to zero.
     */
    public void reset() {
        score.setValue(0);
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\model\scoring\ClassicScoringStrategy.java
`$lang
package com.comp2042.model.scoring;

import com.comp2042.util.GameConfig;

/**
 * The {@code ClassicScoringStrategy} class applies the default scoring rules, granting bonuses for manual drops
 * and quadratic rewards for multi-line clears.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/scoring/ClassicScoringStrategy.java">
 * ClassicScoringStrategy.java</a>
 */
public final class ClassicScoringStrategy implements ScoringStrategy {

    @Override
    /**
     * Awards linear points for manual drops based on configured per-step value.
     *
     * @param steps number of rows advanced manually.
     * @return score earned for the manual drop.
     */
    public int scoreForManualDrop(int steps) {
        if (steps <= 0) {
            return 0;
        }
        return steps * GameConfig.MANUAL_DOWN_SCORE;
    }

    @Override
    /**
     * Awards quadratic points for clearing multiple lines at once.
     *
     * @param linesRemoved number of rows cleared.
     * @return score earned for the clear.
     */
    public int scoreForLineClear(int linesRemoved) {
        if (linesRemoved <= 0) {
            return 0;
        }
        return GameConfig.SCORE_PER_LINE * linesRemoved * linesRemoved;
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\model\scoring\ScoringStrategy.java
`$lang
package com.comp2042.model.scoring;

/**
 * The {@code ScoringStrategy} interface defines how score bonuses are calculated for manual drops and line clears.
 * It enables alternate scoring schemes to be plugged into the controller.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/scoring/ScoringStrategy.java">
 * ScoringStrategy.java</a>
 */
public interface ScoringStrategy {

    /**
     * Calculates points awarded for manually dropping a piece by the given number of steps.
     *
     * @param steps rows advanced by player input.
     * @return score bonus for the manual drop.
     */
    int scoreForManualDrop(int steps);

    /**
     * Calculates points awarded for clearing the specified number of lines.
     *
     * @param linesRemoved number of lines cleared in one lock.
     * @return score bonus for the clear.
     */
    int scoreForLineClear(int linesRemoved);
}
```

### E:\CW2025\src\main\java\com\comp2042\model\SimpleBoard.java
`$lang
package com.comp2042.model;

import java.util.List;

import com.comp2042.data.ClearRow;
import com.comp2042.data.ViewData;
import com.comp2042.data.ViewDataFactory;
import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.BrickGenerator;
import com.comp2042.model.brick.RandomBrickGenerator;
import com.comp2042.util.GameConfig;
import com.comp2042.util.MatrixOperations;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * The {@code SimpleBoard} class maintains the core Tetris board state, including active piece movement,
 * collision detection, line clearing, and score updates. It collaborates with {@link ActivePiece},
 * {@link BrickGenerator}, and {@link MatrixOperations} while exposing observable properties for the UI layer.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/model/SimpleBoard.java">
 * SimpleBoard.java</a>
 */
public final class SimpleBoard implements Board {

    private final BrickGenerator brickGenerator;
    private final ActivePiece activePiece;
    private final Score score;

    private final BooleanProperty isGameOver = new SimpleBooleanProperty(false);
    private final ObjectProperty<int[][]> boardMatrix = new SimpleObjectProperty<>();

    private int[][] currentGameMatrix;

    /**
     * Creates a board with the default random brick generator.
     *
     * @param width  number of columns in the grid.
     * @param height number of rows (including hidden buffer).
     */
    public SimpleBoard(int width, int height) {
        this(width, height, new RandomBrickGenerator());
    }

    /**
     * Creates a board with a supplied brick generator.
     *
     * @param width          number of columns in the grid.
     * @param height         number of rows (including hidden buffer).
     * @param brickGenerator source for spawning new bricks.
     */
    public SimpleBoard(int width, int height, BrickGenerator brickGenerator) {
        this.brickGenerator = brickGenerator;
        this.activePiece = new ActivePiece();
        this.score = new Score();
        updateBoardMatrix(new int[height][width]);
    }

    @Override
    /**
     * Observable flag indicating whether the board reached a game over state.
     *
     * @return game-over property.
     */
    public BooleanProperty isGameOverProperty() {
        return isGameOver;
    }

    @Override
    /**
     * Observable board matrix for UI bindings.
     *
     * @return matrix property.
     */
    public ObjectProperty<int[][]> boardMatrixProperty() {
        return boardMatrix;
    }

    @Override
    /**
     * Exposes the score property for binding.
     *
     * @return score property.
     */
    public IntegerProperty scoreProperty() {
        return score.scoreProperty();
    }

    @Override
    /**
     * Moves the active brick one row down if possible.
     *
     * @return {@code true} if moved successfully; {@code false} if blocked.
     */
    public boolean moveBrickDown() {
        return activePiece.move(currentGameMatrix, 0, 1);
    }

    @Override
    /**
     * Moves the active brick one column left if possible.
     *
     * @return {@code true} if moved successfully; {@code false} otherwise.
     */
    public boolean moveBrickLeft() {
        return activePiece.move(currentGameMatrix, -1, 0);
    }

    @Override
    /**
     * Moves the active brick one column right if possible.
     *
     * @return {@code true} if moved successfully; {@code false} otherwise.
     */
    public boolean moveBrickRight() {
        return activePiece.move(currentGameMatrix, 1, 0);
    }

    @Override
    /**
     * Rotates the active brick counter-clockwise if no collision occurs.
     *
     * @return {@code true} if rotation is valid.
     */
    public boolean rotateLeftBrick() {
        return activePiece.rotateLeft(currentGameMatrix);
    }

    @Override
    /**
     * Spawns a new brick and checks for immediate collision to determine game over.
     *
     * @return {@code true} if spawning causes collision; otherwise {@code false}.
     */
    public boolean createNewBrick() {
        Brick currentBrick = brickGenerator.getBrick();
        activePiece.spawn(currentBrick);

        boolean gameOver = MatrixOperations.intersect(
                currentGameMatrix,
                activePiece.getShape(),
                activePiece.getX(),
                activePiece.getY()
        );

        if (gameOver) {
            isGameOver.set(true);
        }

        return gameOver;
    }

    @Override
    /**
     * Returns a copy of the current board matrix.
     *
     * @return defensive copy of the grid state.
     */
    public int[][] getBoardMatrix() {
        return MatrixOperations.copy(currentGameMatrix);
    }

    @Override
    /**
     * Builds view data describing the active piece, ghost position, and next previews.
     *
     * @return {@link ViewData} snapshot.
     */
    public ViewData getViewData() {
        List<Brick> nextBricks = brickGenerator.preview(GameConfig.NEXT_PREVIEW_COUNT);
        return ViewDataFactory.createViewData(activePiece, currentGameMatrix, nextBricks);
    }

    @Override
    /**
     * Merges the active piece into the background matrix.
     */
    public void mergeBrickToBackground() {
        int[][] merged = MatrixOperations.merge(
                currentGameMatrix,
                activePiece.getShape(),
                activePiece.getX(),
                activePiece.getY()
        );
        updateBoardMatrix(merged);
    }

    @Override
    /**
     * Clears any completed rows and updates the board matrix.
     *
     * @return result describing removed lines and new matrix.
     */
    public ClearRow clearRows() {
        ClearRow clearRow = MatrixOperations.checkRemoving(currentGameMatrix);
        updateBoardMatrix(clearRow.getNewMatrix());
        return clearRow;
    }

    @Override
    /**
     * Returns the score model for mutation or binding.
     *
     * @return score instance.
     */
    public Score getScore() {
        return score;
    }

    /**
     * Applies a new matrix after external effects such as a bomb.
     *
     * @param newMatrix processed matrix to set.
     */
    void applyBombMatrix(int[][] newMatrix) {
        updateBoardMatrix(newMatrix);
    }

    /**
     * Replaces the current matrix with a copied version to keep bindings in sync.
     *
     * @param newMatrix matrix to apply.
     */
    private void updateBoardMatrix(int[][] newMatrix) {
        int[][] copy = MatrixOperations.copy(newMatrix);
        this.currentGameMatrix = copy;
        this.boardMatrix.set(copy);
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\util\BlockTextureProvider.java
`$lang
package com.comp2042.util;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code BlockTextureProvider} class loads and caches paint patterns for each brick type, cropping images
 * where necessary to remove padding. It serves the rendering layer by mapping brick IDs to reusable
 * {@link Paint} instances.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/util/BlockTextureProvider.java">
 * BlockTextureProvider.java</a>
 */
public final class BlockTextureProvider {

    private static final int IMAGE_PADDING = 4;
    private static final String BRICK_IMAGE_PATH_FORMAT = "images/Bricks/%s.png";

    private static final String[] IMAGE_NAMES = {
            null, "IBrick", "JBrick", "LBrick", "OBrick", "SBrick", "TBrick", "ZBrick", "PlusBrick"
    };

    private static final Map<Integer, Paint> PATTERNS;

    static {
        Map<Integer, Paint> patterns = new HashMap<>();
        patterns.put(0, Color.TRANSPARENT);
        for (int id = 1; id < IMAGE_NAMES.length; id++) {
            patterns.put(id, loadPattern(id));
        }
        PATTERNS = Collections.unmodifiableMap(patterns);
    }

    private static Paint loadPattern(int id) {
        String fileName = IMAGE_NAMES[id];
        if (fileName == null) {
            return Color.TRANSPARENT;
        }

        String path = String.format(BRICK_IMAGE_PATH_FORMAT, fileName);
        URL resource = BlockTextureProvider.class.getClassLoader().getResource(path);
        if (resource == null) {
            return Color.GRAY;
        }

        Image image = new Image(resource.toExternalForm());
        int imgWidth = (int) image.getWidth();
        int imgHeight = (int) image.getHeight();

        if (imgWidth > IMAGE_PADDING * 2 && imgHeight > IMAGE_PADDING * 2) {
            PixelReader reader = image.getPixelReader();
            int cropWidth = imgWidth - IMAGE_PADDING * 2;
            int cropHeight = imgHeight - IMAGE_PADDING * 2;
            WritableImage cropped = new WritableImage(reader, IMAGE_PADDING, IMAGE_PADDING, cropWidth, cropHeight);
            return new ImagePattern(cropped);
        }

        return new ImagePattern(image);
    }

    /**
     * Returns a cached paint pattern corresponding to the given brick identifier.
     *
     * @param id brick identifier used in board matrices.
     * @return paint pattern for rendering; transparent if unknown.
     */
    public static Paint getPattern(int id) {
        return PATTERNS.getOrDefault(id, Color.TRANSPARENT);
    }

    private BlockTextureProvider() {
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\util\GameConfig.java
`$lang
package com.comp2042.util;

/**
 * The {@code GameConfig} class centralises static gameplay constants such as board dimensions, scoring values,
 * spawn positions, and preview settings, ensuring consistent configuration across the application.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/util/GameConfig.java">
 * GameConfig.java</a>
 */
public final class GameConfig {

    public static final int BOARD_HEIGHT = 23;
    public static final int BOARD_WIDTH = 10;
    public static final int HIDDEN_BUFFER_ROWS = 2;

    public static final int GAME_TICK_MS = 400;
    public static final int MIN_GAME_TICK_MS = 120;
    public static final int CHINA_STAGE_SPEED_STEP = 10;

    public static final int SCORE_PER_LINE = 50;
    public static final int MANUAL_DOWN_SCORE = 1;
    public static final int POINTS_PER_CHINA_STAGE = 200;
    public static final int POINTS_PER_BOMB = 1000;

    public static final int SPAWN_X = 4;
    public static final int SPAWN_Y = 0;

    public static final int NEXT_PREVIEW_COUNT = 3;

    public static final int INITIAL_QUEUE_SIZE = 10;

    /**
     * Returns the number of rows visible to the player, excluding the hidden spawn buffer.
     *
     * @return visible row count.
     */
    public static int visibleRows() {
        return BOARD_HEIGHT - HIDDEN_BUFFER_ROWS;
    }

    private GameConfig() {
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\util\LayoutMetrics.java
`$lang
package com.comp2042.util;

/**
 * The {@code LayoutMetrics} class centralises pixel measurements and spacing values used across the game UI,
 * providing derived sizes for the board, side panels, overlays, and window defaults to keep layout consistent.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/util/LayoutMetrics.java">
 * LayoutMetrics.java</a>
 */
public final class LayoutMetrics {

    public static final int BRICK_SIZE = 28;
    public static final double GRID_GAP = 2.5;
    public static final int BRICK_ARC_SIZE = 10;

    public static final int NEXT_BRICK_SIZE = 18;
    public static final int NEXT_BRICK_GAP = 2;
    public static final int NEXT_PREVIEW_SPACING = 12;

    public static final int BOARD_FRAME_THICKNESS = 12;
    public static final int BOARD_LEFT_PADDING = 40;
    public static final int BOARD_TOP_PADDING = 30;

    public static final int SIDE_PANEL_WIDTH = 240;
    public static final int SIDE_PANEL_SPACING = 24;
    public static final int SIDE_PANEL_PADDING = 12;

    public static final int PANEL_GAP = 28;
    public static final int BOTTOM_PADDING = 60;

    /**
     * Returns the pixel distance between adjacent bricks, including the gap.
     *
     * @return brick step size in pixels.
     */
    public static double brickStep() {
        return BRICK_SIZE + GRID_GAP;
    }

    /**
     * Calculates the visible board width in pixels.
     *
     * @return board width.
     */
    public static double boardPixelWidth() {
        return GameConfig.BOARD_WIDTH * brickStep();
    }

    /**
     * Calculates the visible board height in pixels (excluding hidden buffer rows).
     *
     * @return board height.
     */
    public static double boardPixelHeight() {
        int visibleRows = GameConfig.visibleRows();
        return visibleRows * brickStep();
    }

    /**
     * Returns the width of the grid content area without frames.
     *
     * @return grid width.
     */
    public static double gridContentWidth() {
        return boardPixelWidth();
    }

    /**
     * Returns the height of the grid content area without frames.
     *
     * @return grid height.
     */
    public static double gridContentHeight() {
        return boardPixelHeight();
    }

    /**
     * Returns the x-offset used when centering the grid.
     *
     * @return grid x-offset.
     */
    public static double gridCenterOffsetX() {
        return 0;
    }

    /**
     * Returns the y-offset used when centering the grid.
     *
     * @return grid y-offset.
     */
    public static double gridCenterOffsetY() {
        return (boardPixelHeight() - gridContentHeight()) / 2;
    }

    /**
     * Returns the y-offset applied to the brick panel to hide buffer rows.
     *
     * @return y-offset in pixels.
     */
    public static double brickPanelYOffset() {
        return -GameConfig.HIDDEN_BUFFER_ROWS * brickStep();
    }

    /**
     * Calculates the board width including frame.
     *
     * @return total board width.
     */
    public static double boardAreaWidth() {
        return boardPixelWidth() + BOARD_FRAME_THICKNESS * 2;
    }

    /**
     * Calculates the board height including frame.
     *
     * @return total board height.
     */
    public static double boardAreaHeight() {
        return boardPixelHeight() + BOARD_FRAME_THICKNESS * 2;
    }

    /**
     * Calculates the width of the main content area (board plus side panel).
     *
     * @return content width.
     */
    public static double contentWidth() {
        return boardAreaWidth() + PANEL_GAP + SIDE_PANEL_WIDTH;
    }

    /**
     * Returns the minimum width that allows centering side panels around the board.
     *
     * @return minimum centered width.
     */
    public static double minimumCenteredWindowWidth() {
        return boardAreaWidth() + 2 * (SIDE_PANEL_WIDTH + PANEL_GAP);
    }

    /**
     * Returns the y-coordinate for positioning notification panels.
     *
     * @return notification y-offset.
     */
    public static double notificationPanelY() {
        return BOARD_TOP_PADDING + SIDE_PANEL_PADDING;
    }

    /**
     * Calculates the recommended initial window width based on content and padding.
     *
     * @return initial window width.
     */
    public static double initialWindowWidth() {
        return Math.max(
                contentWidth() + 2 * SIDE_PANEL_PADDING,
                minimumCenteredWindowWidth()
        );
    }

    /**
     * Calculates the recommended initial window height based on board and padding.
     *
     * @return initial window height.
     */
    public static double initialWindowHeight() {
        return BOARD_TOP_PADDING + boardAreaHeight() + BOTTOM_PADDING;
    }

    private LayoutMetrics() {
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\util\MatrixOperations.java
`$lang
package com.comp2042.util;

import com.comp2042.data.ClearRow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code MatrixOperations} class encapsulates matrix utilities for copying, collision checks, merging,
 * clearing rows, deep copying lists of shapes, and simulating bomb explosions. It isolates low-level grid
 * manipulation from higher-level board and controller logic.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/util/MatrixOperations.java">
 * MatrixOperations.java</a>
 */
public final class MatrixOperations {

    private MatrixOperations() {
    }

    /**
     * Determines whether placing a brick at the given coordinates intersects boundaries or occupied cells.
     *
     * @param matrix board matrix.
     * @param brick  brick matrix to test.
     * @param x      left coordinate for placement.
     * @param y      top coordinate for placement.
     * @return {@code true} if a collision occurs.
     */
    public static boolean intersect(final int[][] matrix, final int[][] brick, int x, int y) {
        requireValidMatrix(matrix, "matrix");
        requireValidMatrix(brick, "brick");

        for (int row = 0; row < brick.length; row++) {
            for (int col = 0; col < brick[row].length; col++) {
                if (brick[row][col] == 0) {
                    continue;
                }
                int targetX = x + col;
                int targetY = y + row;

                if (isOutOfBounds(matrix, targetX, targetY)) {
                    return true;
                }
                if (matrix[targetY][targetX] != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Merges a brick matrix into a copy of the board at the specified location.
     *
     * @param matrix board matrix.
     * @param brick  brick to overlay.
     * @param x      target x-coordinate.
     * @param y      target y-coordinate.
     * @return new matrix containing both board and brick.
     */
    public static int[][] merge(final int[][] matrix, final int[][] brick, int x, int y) {
        requireValidMatrix(matrix, "matrix");
        requireValidMatrix(brick, "brick");

        int[][] result = copy(matrix);

        for (int row = 0; row < brick.length; row++) {
            for (int col = 0; col < brick[row].length; col++) {
                if (brick[row][col] == 0) {
                    continue;
                }

                int targetX = x + col;
                int targetY = y + row;

                if (isOutOfBounds(result, targetX, targetY)) {
                    continue;
                }

                result[targetY][targetX] = brick[row][col];
            }
        }
        return result;
    }

    /**
     * Produces a deep copy of the supplied matrix.
     *
     * @param original matrix to copy.
     * @return copied matrix.
     */
    public static int[][] copy(int[][] original) {
        requireValidMatrix(original, "original");

        int[][] copy = new int[original.length][];
        for (int row = 0; row < original.length; row++) {
            int[] sourceRow = original[row];
            copy[row] = new int[sourceRow.length];
            System.arraycopy(sourceRow, 0, copy[row], 0, sourceRow.length);
        }
        return copy;
    }

    /**
     * Iteratively removes any full rows, compacts remaining rows downward, and reports how many were cleared.
     *
     * @param matrix matrix to process.
     * @return {@link ClearRow} describing removed lines and the resulting matrix.
     */
    public static ClearRow checkRemoving(final int[][] matrix) {
        requireValidMatrix(matrix, "matrix");

        int height = matrix.length;
        int width = matrix[0].length;
        int[][] currentMatrix = copy(matrix);
        int totalLinesRemoved = 0;

        while (true) {
            Deque<int[]> remainingRows = new ArrayDeque<>();
            List<Integer> clearedRows = new ArrayList<>();

            for (int row = height - 1; row >= 0; row--) {
                int[] currentRow = currentMatrix[row];

                if (isRowFull(currentRow)) {
                    clearedRows.add(row);
                } else {
                    remainingRows.addLast(cloneRow(currentRow));
                }
            }

            if (clearedRows.isEmpty()) {
                break;
            }

            totalLinesRemoved += clearedRows.size();

            int[][] newMatrix = new int[height][width];

            int writeRow = height - 1;
            while (!remainingRows.isEmpty() && writeRow >= 0) {
                newMatrix[writeRow] = remainingRows.removeFirst();
                writeRow--;
            }

            while (writeRow >= 0) {
                newMatrix[writeRow] = new int[width];
                writeRow--;
            }

            currentMatrix = newMatrix;
        }

        return new ClearRow(totalLinesRemoved, currentMatrix);
    }

    /**
     * Deep-copies each matrix in the provided list.
     *
     * @param list list of matrices to copy.
     * @return list containing copied matrices.
     */
    public static List<int[][]> deepCopyList(List<int[][]> list) {
        if (list == null) {
            throw new IllegalArgumentException("list must not be null");
        }
        return list.stream()
                .map(MatrixOperations::copy)
                .collect(Collectors.toList());
    }

    /**
     * Simulates a bomb explosion by clearing a 3x3 area and letting blocks above fall down.
     *
     * @param matrix  board matrix to copy and mutate.
     * @param centerX x-coordinate of the blast centre.
     * @param centerY y-coordinate of the blast centre.
     * @return resulting matrix after the explosion.
     */
    public static int[][] explodeBomb(final int[][] matrix, int centerX, int centerY) {
        requireValidMatrix(matrix, "matrix");

        int height = matrix.length;
        int width = matrix[0].length;

        int[][] result = copy(matrix);

        int top = Math.max(0, centerY - 1);
        int bottom = Math.min(height - 1, centerY + 1);
        int left = Math.max(0, centerX - 1);
        int right = Math.min(width - 1, centerX + 1);

        for (int row = top; row <= bottom; row++) {
            for (int col = left; col <= right; col++) {
                result[row][col] = 0;
            }
        }

        for (int col = left; col <= right; col++) {
            int writeRow = bottom;
            for (int row = bottom; row >= 0; row--) {
                if (result[row][col] != 0) {
                    if (writeRow != row) {
                        result[writeRow][col] = result[row][col];
                        result[row][col] = 0;
                    }
                    writeRow--;
                }
            }
        }

        return result;
    }

    private static boolean isOutOfBounds(int[][] matrix, int x, int y) {
        return x < 0
                || y < 0
                || y >= matrix.length
                || x >= matrix[y].length;
    }

    private static boolean isRowFull(int[] row) {
        if (row == null || row.length == 0) {
            return false;
        }
        for (int cell : row) {
            if (cell == 0) {
                return false;
            }
        }
        return true;
    }

    private static int[] cloneRow(int[] source) {
        int[] copy = new int[source.length];
        System.arraycopy(source, 0, copy, 0, source.length);
        return copy;
    }

    private static void requireValidMatrix(int[][] matrix, String name) {
        if (matrix == null) {
            throw new IllegalArgumentException(name + " must not be null");
        }
        if (matrix.length == 0) {
            throw new IllegalArgumentException(name + " must have at least one row");
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == null) {
                throw new IllegalArgumentException(name + " row " + i + " must not be null");
            }
        }
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\view\effect\BoardVibrationEffect.java
`$lang
package com.comp2042.view.effect;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The {@code BoardVibrationEffect} class produces a short vibration animation on supplied nodes to emphasize
 * events such as bomb explosions. It encapsulates timeline setup and safely resets targets after playback.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/effect/BoardVibrationEffect.java">
 * BoardVibrationEffect.java</a>
 */
public final class BoardVibrationEffect {

    private static final double STEP_1_TIME_MS = 30;
    private static final double STEP_2_TIME_MS = 60;
    private static final double STEP_3_TIME_MS = 90;
    private static final double STEP_4_TIME_MS = 120;
    private static final double STEP_5_TIME_MS = 150;

    private static final double OFFSET_INITIAL = 0;
    private static final double OFFSET_STEP_1 = 8;
    private static final double OFFSET_STEP_2 = -6;
    private static final double OFFSET_STEP_3 = 5;
    private static final double OFFSET_STEP_4 = -3;
    private static final double OFFSET_FINAL = 0;

    private final List<Node> targets = new ArrayList<>();
    private Timeline vibrationTimeline;

    /**
     * Creates an effect targeting the given nodes.
     *
     * @param nodes nodes to translate during vibration.
     */
    public BoardVibrationEffect(Node... nodes) {
        if (nodes != null) {
            Arrays.stream(nodes)
                    .filter(node -> node != null)
                    .forEach(targets::add);
        }
    }

    /**
     * Starts the vibration animation, restarting it if already running.
     */
    public void vibrate() {
        if (targets.isEmpty()) {
            return;
        }

        if (vibrationTimeline != null && vibrationTimeline.getStatus() == Animation.Status.RUNNING) {
            vibrationTimeline.stop();
            resetTargets();
        }

        vibrationTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, keyValuesForTargets(OFFSET_INITIAL)),
                new KeyFrame(Duration.millis(STEP_1_TIME_MS), keyValuesForTargets(OFFSET_STEP_1)),
                new KeyFrame(Duration.millis(STEP_2_TIME_MS), keyValuesForTargets(OFFSET_STEP_2)),
                new KeyFrame(Duration.millis(STEP_3_TIME_MS), keyValuesForTargets(OFFSET_STEP_3)),
                new KeyFrame(Duration.millis(STEP_4_TIME_MS), keyValuesForTargets(OFFSET_STEP_4)),
                new KeyFrame(Duration.millis(STEP_5_TIME_MS), keyValuesForTargets(OFFSET_FINAL))
        );
        vibrationTimeline.setOnFinished(event -> resetTargets());
        vibrationTimeline.play();
    }

    private void resetTargets() {
        targets.forEach(target -> target.setTranslateY(0));
    }

    private KeyValue[] keyValuesForTargets(double value) {
        return targets.stream()
                .map(target -> new KeyValue(target.translateYProperty(), value))
                .toArray(KeyValue[]::new);
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\view\GameInputHandler.java
`$lang
package com.comp2042.view;

import com.comp2042.event.EventSource;
import com.comp2042.event.EventType;
import com.comp2042.event.MoveEvent;
import javafx.beans.property.BooleanProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.function.Consumer;

/**
 * The {@code GameInputHandler} class listens for key events and forwards them as movement commands to the session
 * manager, respecting pause and game-over states. It acts as a thin adapter between JavaFX input and the
 * {@link com.comp2042.event.InputEventListener}.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/GameInputHandler.java">
 * GameInputHandler.java</a>
 */
public final class GameInputHandler {

    private final BooleanProperty isPause;
    private final BooleanProperty isGameOver;

    private final Consumer<MoveEvent> leftHandler;
    private final Consumer<MoveEvent> rightHandler;
    private final Consumer<MoveEvent> rotateHandler;
    private final Consumer<MoveEvent> downHandler;
    private final Consumer<MoveEvent> hardDropHandler;

    /**
     * Creates an input handler bound to pause and game-over flags and downstream consumers.
     *
     * @param isPause         pause flag.
     * @param isGameOver      game-over flag.
     * @param leftHandler     consumer for left movement.
     * @param rightHandler    consumer for right movement.
     * @param rotateHandler   consumer for rotation.
     * @param downHandler     consumer for soft drop.
     * @param hardDropHandler consumer for hard drop.
     */
    public GameInputHandler(BooleanProperty isPause,
                            BooleanProperty isGameOver,
                            Consumer<MoveEvent> leftHandler,
                            Consumer<MoveEvent> rightHandler,
                            Consumer<MoveEvent> rotateHandler,
                            Consumer<MoveEvent> downHandler,
                            Consumer<MoveEvent> hardDropHandler) {

        this.isPause = isPause;
        this.isGameOver = isGameOver;
        this.leftHandler = leftHandler;
        this.rightHandler = rightHandler;
        this.rotateHandler = rotateHandler;
        this.downHandler = downHandler;
        this.hardDropHandler = hardDropHandler;
    }

    /**
     * Handles a key press event, dispatching movement commands when appropriate.
     *
     * @param keyEvent the key event.
     */
    public void handleKeyPressed(KeyEvent keyEvent) {
        if (isPause.get() || isGameOver.get()) {
            return;
        }

        KeyCode code = keyEvent.getCode();

        if (code == KeyCode.LEFT || code == KeyCode.A) {
            MoveEvent moveEvent = new MoveEvent(EventType.LEFT, EventSource.USER);
            leftHandler.accept(moveEvent);
            keyEvent.consume();
            return;
        }

        if (code == KeyCode.RIGHT || code == KeyCode.D) {
            MoveEvent moveEvent = new MoveEvent(EventType.RIGHT, EventSource.USER);
            rightHandler.accept(moveEvent);
            keyEvent.consume();
            return;
        }

        if (code == KeyCode.UP || code == KeyCode.W) {
            MoveEvent moveEvent = new MoveEvent(EventType.ROTATE, EventSource.USER);
            rotateHandler.accept(moveEvent);
            keyEvent.consume();
            return;
        }

        if (code == KeyCode.DOWN || code == KeyCode.S) {
            MoveEvent moveEvent = new MoveEvent(EventType.DOWN, EventSource.USER);
            downHandler.accept(moveEvent);
            keyEvent.consume();
            return;
        }

        if (code == KeyCode.SPACE) {
            MoveEvent moveEvent = new MoveEvent(EventType.HARD_DROP, EventSource.USER);
            hardDropHandler.accept(moveEvent);
            keyEvent.consume();
        }
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\view\GameLoop.java
`$lang
package com.comp2042.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.util.Duration;

/**
 * The {@code GameLoop} class wraps a JavaFX {@link Timeline} to drive periodic game ticks for automatic movement.
 * It offers lifecycle controls to start, pause, stop, and query the running state of the loop.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/GameLoop.java">
 * GameLoop.java</a>
 */
public class GameLoop {

    private final Timeline timeline;

    /**
     * Creates a repeating loop that invokes the provided callback every tick.
     *
     * @param tickMillis duration of each tick in milliseconds.
     * @param onTick     callback executed per tick.
     */
    public GameLoop(int tickMillis, Runnable onTick) {
        this.timeline = new Timeline(
                new KeyFrame(Duration.millis(tickMillis), e -> onTick.run())
        );
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Starts or resumes the loop.
     */
    public void start() {
        timeline.play();
    }

    /**
     * Pauses the loop without resetting progress.
     */
    public void pause() {
        timeline.pause();
    }

    /**
     * Stops the loop and resets progress.
     */
    public void stop() {
        timeline.stop();
    }

    /**
     * Indicates whether the loop is currently running.
     *
     * @return {@code true} if running.
     */
    public boolean isRunning() {
        return timeline.getStatus() == Animation.Status.RUNNING;
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\view\HomeSelection.java
`$lang
package com.comp2042.view;

/**
 * The {@code HomeSelection} record captures a player's menu choice, pairing the selected mode with a specific
 * option label such as a time-attack duration.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/HomeSelection.java">
 * HomeSelection.java</a>
 */
public record HomeSelection(Mode mode, String option) {
    public enum Mode {
        COUNTRY_EXPLORE,
        TIME_RACING
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\view\manager\BackgroundMusicManager.java
`$lang
package com.comp2042.view.manager;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The {@code BackgroundMusicManager} class controls playback of looping music tracks for different screens and
 * modes, caching a single player instance and exposing volume control.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/manager/BackgroundMusicManager.java">
 * BackgroundMusicManager.java</a>
 */
public final class BackgroundMusicManager {

    private static final String MAIN_MUSIC_PATH = "audio/mainmusic.mp3";
    private static final String EXPLORE_CHINA_MUSIC_PATH = "audio/explorechina.mp3";
    private static final String TIME_RACING_MUSIC_PATH = "audio/timeracing.mp3";

    private static MediaPlayer currentPlayer;
    private static String currentTrack;
    private static double volume = 0.6;

    private BackgroundMusicManager() {
    }

    /**
     * Plays the main menu music on loop.
     */
    public static void playMainMusic() {
        playLoop(MAIN_MUSIC_PATH);
    }

    /**
     * Plays the Explore China music on loop.
     */
    public static void playExploreChinaMusic() {
        playLoop(EXPLORE_CHINA_MUSIC_PATH);
    }

    /**
     * Plays the time-racing music on loop.
     */
    public static void playTimeRacingMusic() {
        playLoop(TIME_RACING_MUSIC_PATH);
    }

    /**
     * Stops and disposes of the current music player, if any.
     */
    public static void stop() {
        if (currentPlayer != null) {
            currentPlayer.stop();
            currentPlayer.dispose();
            currentPlayer = null;
            currentTrack = null;
        }
    }

    /**
     * Adjusts the playback volume, clamped between 0 and 1.
     *
     * @param newVolume desired volume level.
     */
    public static void setVolume(double newVolume) {
        volume = Math.max(0.0, Math.min(1.0, newVolume));
        if (currentPlayer != null) {
            currentPlayer.setVolume(volume);
        }
    }

    private static void playLoop(String resourcePath) {
        if (resourcePath == null || resourcePath.isBlank()) {
            return;
        }

        if (resourcePath.equals(currentTrack) && currentPlayer != null) {
            if (currentPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
                currentPlayer.play();
            }
            return;
        }

        stop();

        var url = BackgroundMusicManager.class.getClassLoader().getResource(resourcePath);
        if (url == null) {
            System.err.println("Missing music resource: " + resourcePath);
            return;
        }

        Media media = new Media(url.toExternalForm());
        MediaPlayer player = new MediaPlayer(media);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setVolume(volume);
        player.play();

        currentPlayer = player;
        currentTrack = resourcePath;
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\view\manager\BackgroundVideoManager.java
`$lang
package com.comp2042.view.manager;

import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;

/**
 * The {@code BackgroundVideoManager} class manages a shared looping background video for home and selection
 * screens, attaching it to views as needed and handling disposal.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/manager/BackgroundVideoManager.java">
 * BackgroundVideoManager.java</a>
 */
public final class BackgroundVideoManager {

    private static final String MAIN_PAGE_VIDEO_PATH = "video/mainpage.mp4";

    private static MediaPlayer sharedPlayer;

    private BackgroundVideoManager() {
    }

    /**
     * Attaches the shared background player to the provided media view and binds sizing to the container.
     *
     * @param mediaView media view to display the video.
     * @param container container used for sizing bindings.
     */
    public static void attach(MediaView mediaView, StackPane container) {
        if (mediaView == null || container == null) {
            return;
        }

        MediaPlayer player = getOrCreatePlayer();
        if (player == null) {
            return;
        }

        mediaView.setMediaPlayer(player);
        mediaView.setPreserveRatio(true);
        mediaView.fitWidthProperty().bind(container.widthProperty());
        mediaView.fitHeightProperty().bind(container.heightProperty());

        if (player.getStatus() != MediaPlayer.Status.PLAYING) {
            player.seek(player.getStartTime());
            player.play();
        }
    }

    /**
     * Stops and disposes of the shared player.
     */
    public static void dispose() {
        if (sharedPlayer != null) {
            sharedPlayer.stop();
            sharedPlayer.dispose();
            sharedPlayer = null;
        }
    }

    private static MediaPlayer getOrCreatePlayer() {
        if (sharedPlayer == null) {
            URL videoUrl = BackgroundVideoManager.class
                    .getClassLoader()
                    .getResource(MAIN_PAGE_VIDEO_PATH);

            if (videoUrl == null) {
                return null;
            }

            Media media = new Media(videoUrl.toExternalForm());
            sharedPlayer = new MediaPlayer(media);
            sharedPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            sharedPlayer.setAutoPlay(true);
            sharedPlayer.setMute(true);
            sharedPlayer.setOnError(() ->
                    System.err.println("Background video error: " + sharedPlayer.getError())
            );
            sharedPlayer.setOnReady(() -> {
                if (sharedPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
                    sharedPlayer.play();
                }
            });
        }
        return sharedPlayer;
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\view\manager\BombManager.java
`$lang
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

/**
 * The {@code BombManager} class encapsulates the UI and interaction logic for bomb power-ups, handling drag-and-drop
 * placement, visual targeting overlays, explosion triggering, and related board updates. It collaborates with the
 * {@link BoardRenderer}, {@link com.comp2042.model.Board}, and vibration effects to keep visuals in sync.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/manager/BombManager.java">
 * BombManager.java</a>
 */
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

    /**
     * Registers mouse handlers and synchronises toolbar visuals based on bomb availability.
     */
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

    /**
     * Sets the board that will be mutated when bombs explode.
     *
     * @param board active board instance.
     */
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
```

### E:\CW2025\src\main\java\com\comp2042\view\manager\ChinaStageManager.java
`$lang
package com.comp2042.view.manager;

import com.comp2042.data.ChinaStageDescriptionProvider;
import com.comp2042.model.brick.BrickFactory;
import com.comp2042.util.GameConfig;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * The {@code ChinaStageManager} class drives the Explore China mode by advancing stage descriptions, applying
 * backgrounds, and adjusting tick speed as the player's score increases. It hides or reveals related UI elements
 * and signals completion when all stages are finished.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/manager/ChinaStageManager.java">
 * ChinaStageManager.java</a>
 */
public final class ChinaStageManager {

    private static final int PLUS_BRICK_STAGE_INDEX = 14;

    private final List<ChinaStageDescriptionProvider.ChinaStage> stages;
    private final VBox descriptionBox;
    private final Text stateTitleText;
    private final Text stateDescriptionText;

    private final Consumer<String> backgroundApplier;
    private final IntConsumer gameTickUpdater;
    private final Runnable onJourneyCompleted;

    private boolean enabled;
    private int currentStageIndex;

    public ChinaStageManager(VBox descriptionBox,
                             Text stateTitleText,
                             Text stateDescriptionText,
                             Consumer<String> backgroundApplier,
                             IntConsumer gameTickUpdater,
                             Runnable onJourneyCompleted) {

        this.stages = ChinaStageDescriptionProvider.getStages();
        this.descriptionBox = descriptionBox;
        this.stateTitleText = stateTitleText;
        this.stateDescriptionText = stateDescriptionText;
        this.backgroundApplier = backgroundApplier;
        this.gameTickUpdater = gameTickUpdater;
        this.onJourneyCompleted = onJourneyCompleted;

        System.out.println("[ChinaStageManager] Loaded " + stages.size() + " stages from provider");
        hideDescriptionBox();
    }

    /**
     * Enables Explore China mode and applies the first stage.
     */
    public void enableExploreMode() {
        System.out.println("[ChinaStageManager] enableExploreMode() called");
        if (stages.isEmpty()) {
            System.out.println("[ChinaStageManager] No stages available, disabling mode");
            enabled = false;
            hideDescriptionBox();
            return;
        }
        enabled = true;
        currentStageIndex = 0;
        System.out.println("[ChinaStageManager] Enabling mode, applying initial stage index 0");
        applyStage(currentStageIndex);
    }

    /**
     * Disables Explore China mode and hides related UI.
     */
    public void disableExploreMode() {
        System.out.println("[ChinaStageManager] disableExploreMode() called");
        enabled = false;
        hideDescriptionBox();
    }

    /**
     * Returns whether Explore China mode is active.
     *
     * @return {@code true} if enabled.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Handles score changes, advancing stages or completing the journey as needed.
     *
     * @param newScore latest score value.
     */
    public void handleScoreChanged(int newScore) {
        if (!enabled || stages.isEmpty()) {
            return;
        }

        int targetIndex = Math.min(
                newScore / GameConfig.POINTS_PER_CHINA_STAGE,
                stages.size() - 1
        );

        if (targetIndex > currentStageIndex) {
            System.out.println("[ChinaStageManager] Score=" + newScore +
                    " advancing from stage " + currentStageIndex + " to " + targetIndex);
            applyStage(targetIndex);
        }

        checkCompletion(newScore);
    }

    private void applyStage(int stageIndex) {
        if (!enabled || stages.isEmpty()) {
            System.out.println("[ChinaStageManager] applyStage called but mode disabled or no stages");
            return;
        }

        int safeIndex = Math.min(stageIndex, stages.size() - 1);
        currentStageIndex = safeIndex;

        ChinaStageDescriptionProvider.ChinaStage stage = stages.get(safeIndex);

        System.out.println("[ChinaStageManager] Applying stage index " + safeIndex +
                ", name='" + stage.getName() +
                "', bg='" + stage.getBackgroundResource() + "'");

        boolean enablePlusBrick = safeIndex >= PLUS_BRICK_STAGE_INDEX;
        BrickFactory.setPlusEnabled(enablePlusBrick);
        System.out.println("[ChinaStageManager] Plus brick enabled=" + enablePlusBrick +
                " at stage index " + safeIndex);

        if (backgroundApplier != null) {
            backgroundApplier.accept(stage.getBackgroundResource());
        } else {
            System.out.println("[ChinaStageManager] backgroundApplier is null, cannot apply background");
        }

        if (descriptionBox != null) {
            descriptionBox.setVisible(true);
            descriptionBox.setManaged(true);
        }

        if (stateTitleText != null) {
            stateTitleText.setText(stage.getName());
        }

        if (stateDescriptionText != null) {
            stateDescriptionText.setText(stage.getDescription());
        }

        int newTick = Math.max(
                GameConfig.MIN_GAME_TICK_MS,
                GameConfig.GAME_TICK_MS - (safeIndex * GameConfig.CHINA_STAGE_SPEED_STEP)
        );
        if (gameTickUpdater != null) {
            System.out.println("[ChinaStageManager] Updating game tick to " + newTick + " ms");
            gameTickUpdater.accept(newTick);
        } else {
            System.out.println("[ChinaStageManager] gameTickUpdater is null, not updating tick");
        }
    }

    private void checkCompletion(int score) {
        if (!enabled || stages.isEmpty()) {
            return;
        }

        boolean atFinalStage = currentStageIndex >= stages.size() - 1;
        int completionScore = GameConfig.POINTS_PER_CHINA_STAGE * stages.size();

        if (atFinalStage && score >= completionScore) {
            System.out.println("[ChinaStageManager] Journey complete at score=" + score +
                    ", finalStageIndex=" + currentStageIndex +
                    ", completionScore=" + completionScore);
            if (onJourneyCompleted != null) {
                onJourneyCompleted.run();
            } else {
                System.out.println("[ChinaStageManager] onJourneyCompleted callback is null");
            }
        }
    }

    private void hideDescriptionBox() {
        if (descriptionBox != null) {
            descriptionBox.setVisible(false);
            descriptionBox.setManaged(false);
        }
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\view\manager\GameLayoutManager.java
`$lang
package com.comp2042.view.manager;

import com.comp2042.util.LayoutMetrics;
import com.comp2042.view.render.BoardRenderer;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.util.Objects;

/**
 * The {@code GameLayoutManager} class coordinates sizing, positioning, and end-screen overlays for the game view,
 * applying measurements from {@link LayoutMetrics} and binding background media where appropriate.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/manager/GameLayoutManager.java">
 * GameLayoutManager.java</a>
 */
public final class GameLayoutManager {

    private static final double VISUAL_BUFFER = 4.0;
    private static final double BOMB_TOOLBAR_PADDING = 8.0;
    private static final double BOMB_TOOLBAR_Y_OFFSET = 35.0;
    private static final double BOMB_TOOLBAR_WIDTH_OFFSET = 50.0;
    private static final double END_OVERLAY_ANIMATION_MS = 520.0;

    private final Pane rootPane;
    private final BorderPane gameBoard;
    private final GridPane gamePanel;
    private final Pane gridLinesPane;
    private final VBox sidePanel;
    private final VBox timerBox;
    private final VBox nextBricksContainer;
    private final VBox nextBricksList;
    private final Group notificationGroup;
    private final BoardRenderer boardRenderer;
    private final StackPane bombToolbar;
    private final VBox chinaDescriptionBox;

    private StackPane endOverlay;
    private MediaView endBackgroundVideo;
    private Label endTitle;
    private Label endSubtitle;

    public GameLayoutManager(Pane rootPane,
                             BorderPane gameBoard,
                             GridPane gamePanel,
                             Pane gridLinesPane,
                             VBox sidePanel,
                             VBox timerBox,
                             VBox nextBricksContainer,
                             VBox nextBricksList,
                             Group notificationGroup,
                             BoardRenderer boardRenderer,
                             StackPane bombToolbar,
                             VBox chinaDescriptionBox) {

        this.rootPane = Objects.requireNonNull(rootPane, "rootPane must not be null");
        this.gameBoard = Objects.requireNonNull(gameBoard, "gameBoard must not be null");
        this.gamePanel = Objects.requireNonNull(gamePanel, "gamePanel must not be null");
        this.gridLinesPane = Objects.requireNonNull(gridLinesPane, "gridLinesPane must not be null");
        this.sidePanel = Objects.requireNonNull(sidePanel, "sidePanel must not be null");
        this.timerBox = Objects.requireNonNull(timerBox, "timerBox must not be null");
        this.nextBricksContainer = Objects.requireNonNull(nextBricksContainer, "nextBricksContainer must not be null");
        this.nextBricksList = Objects.requireNonNull(nextBricksList, "nextBricksList must not be null");
        this.notificationGroup = Objects.requireNonNull(notificationGroup, "notificationGroup must not be null");
        this.boardRenderer = Objects.requireNonNull(boardRenderer, "boardRenderer must not be null");
        this.bombToolbar = Objects.requireNonNull(bombToolbar, "bombToolbar must not be null");
        this.chinaDescriptionBox = Objects.requireNonNull(chinaDescriptionBox, "chinaDescriptionBox must not be null");

        // Important: make sure any .root black background in CSS does not override our images.
        this.rootPane.setStyle("-fx-background-color: transparent;");
    }

    /**
     * Applies initial sizing, padding, and spacing to board and side panels.
     */
    public void applyInitialLayout() {
        double boardWidth = LayoutMetrics.boardPixelWidth();
        double boardHeight = LayoutMetrics.boardPixelHeight();

        gamePanel.setHgap(LayoutMetrics.GRID_GAP);
        gamePanel.setVgap(LayoutMetrics.GRID_GAP);
        gamePanel.setPrefSize(boardWidth, boardHeight);
        gamePanel.setMinSize(boardWidth, boardHeight);
        gamePanel.setMaxSize(boardWidth, boardHeight);

        gridLinesPane.setPrefSize(boardWidth, boardHeight);
        gridLinesPane.setMinSize(boardWidth, boardHeight);
        gridLinesPane.setMaxSize(boardWidth, boardHeight);

        double bufferedWidth = LayoutMetrics.boardAreaWidth() + (VISUAL_BUFFER * 2);
        double bufferedHeight = LayoutMetrics.boardAreaHeight() + (VISUAL_BUFFER * 2);
        gameBoard.setPrefSize(bufferedWidth, bufferedHeight);
        gameBoard.setMinSize(bufferedWidth, bufferedHeight);
        gameBoard.setMaxSize(bufferedWidth, bufferedHeight);
        gameBoard.setPadding(new Insets(
                LayoutMetrics.BOARD_FRAME_THICKNESS,
                LayoutMetrics.BOARD_FRAME_THICKNESS,
                LayoutMetrics.BOARD_FRAME_THICKNESS + LayoutMetrics.BOTTOM_PADDING,
                LayoutMetrics.BOARD_FRAME_THICKNESS
        ));

        sidePanel.setSpacing(LayoutMetrics.SIDE_PANEL_SPACING);
        sidePanel.setPrefWidth(LayoutMetrics.SIDE_PANEL_WIDTH);
        sidePanel.setPadding(new Insets(LayoutMetrics.SIDE_PANEL_PADDING));

        timerBox.setSpacing(LayoutMetrics.SIDE_PANEL_SPACING / 2.0);
        timerBox.setPrefWidth(LayoutMetrics.SIDE_PANEL_WIDTH);
        timerBox.setPadding(new Insets(LayoutMetrics.SIDE_PANEL_PADDING));

        nextBricksContainer.setSpacing(LayoutMetrics.NEXT_PREVIEW_SPACING);
        nextBricksList.setSpacing(LayoutMetrics.NEXT_PREVIEW_SPACING);

        notificationGroup.setLayoutY(LayoutMetrics.notificationPanelY());

        bombToolbar.setPadding(new Insets(BOMB_TOOLBAR_PADDING));

        double chinaBufferedWidth = LayoutMetrics.boardAreaWidth() + (VISUAL_BUFFER * 2);
        double chinaBufferedHeight = LayoutMetrics.boardAreaHeight() + (VISUAL_BUFFER * 2);
        chinaDescriptionBox.setPrefSize(chinaBufferedWidth, chinaBufferedHeight);
        chinaDescriptionBox.setMinSize(chinaBufferedWidth, chinaBufferedHeight);
        chinaDescriptionBox.setMaxSize(chinaBufferedWidth, chinaBufferedHeight);

        rootPane.setPrefWidth(LayoutMetrics.initialWindowWidth());
        rootPane.setPrefHeight(LayoutMetrics.initialWindowHeight());

        boardRenderer.redrawGridLines();
    }

    /**
     * Positions board, panels, notifications, and bomb toolbar within the available width.
     *
     * @param availableWidth current window width.
     */
    public void positionContent(double availableWidth) {
        double safeWidth = Math.max(availableWidth, LayoutMetrics.initialWindowWidth());
        double boardAreaWidth = LayoutMetrics.boardAreaWidth();

        double centeredBoardLeft = (safeWidth - boardAreaWidth) / 2.0;
        double boardLeft;

        if (safeWidth < LayoutMetrics.minimumCenteredWindowWidth()) {
            double centeredContent = (safeWidth - LayoutMetrics.contentWidth()) / 2.0;
            boardLeft = Math.max(LayoutMetrics.BOARD_LEFT_PADDING, centeredContent);
        } else {
            boardLeft = centeredBoardLeft;
        }

        double boardTop = LayoutMetrics.BOARD_TOP_PADDING;

        gamePanel.setLayoutX(boardLeft);
        gamePanel.setLayoutY(boardTop);

        gridLinesPane.setLayoutX(boardLeft);
        gridLinesPane.setLayoutY(boardTop);

        gameBoard.setLayoutX(boardLeft - LayoutMetrics.BOARD_FRAME_THICKNESS - VISUAL_BUFFER);
        gameBoard.setLayoutY(boardTop - LayoutMetrics.BOARD_FRAME_THICKNESS - VISUAL_BUFFER);

        double sidePanelLeft = boardLeft + boardAreaWidth + LayoutMetrics.PANEL_GAP;
        sidePanel.setLayoutX(sidePanelLeft);
        sidePanel.setLayoutY(boardTop);

        double timerLeft = Math.max(
                LayoutMetrics.SIDE_PANEL_PADDING,
                boardLeft - LayoutMetrics.SIDE_PANEL_WIDTH - LayoutMetrics.PANEL_GAP
        );
        timerBox.setLayoutX(timerLeft);
        timerBox.setLayoutY(boardTop);

        notificationGroup.setLayoutX(sidePanelLeft);

        double bombToolbarY = boardTop + LayoutMetrics.boardPixelHeight() + BOMB_TOOLBAR_Y_OFFSET;
        double bombToolbarX = boardLeft + (LayoutMetrics.boardPixelWidth() - BOMB_TOOLBAR_WIDTH_OFFSET) / 2.0;
        bombToolbar.setLayoutX(bombToolbarX);
        bombToolbar.setLayoutY(bombToolbarY);

        double descBufferedWidth = chinaDescriptionBox.getPrefWidth();
        double descLeft = Math.max(
                LayoutMetrics.SIDE_PANEL_PADDING,
                boardLeft - descBufferedWidth - LayoutMetrics.PANEL_GAP
        );
        double descTop = boardTop - LayoutMetrics.BOARD_FRAME_THICKNESS - VISUAL_BUFFER;
        chinaDescriptionBox.setLayoutX(descLeft);
        chinaDescriptionBox.setLayoutY(descTop);
    }

    /**
     * Applies a background image from the classpath to the root pane.
     *
     * @param resourcePath classpath to the image.
     */
    public void applyBackgroundImage(String resourcePath) {
        if (resourcePath == null || resourcePath.isBlank()) {
            return;
        }

        System.out.println("[Layout] Request background: '" + resourcePath + "'");

        var classLoader = getClass().getClassLoader();
        var resourceUrl = classLoader.getResource(resourcePath);

        if (resourceUrl == null && resourcePath.contains(" ")) {
            resourceUrl = classLoader.getResource(resourcePath.replace(" ", "%20"));
        }
        if (resourceUrl == null && !resourcePath.startsWith("/")) {
            resourceUrl = classLoader.getResource("/" + resourcePath);
        }
        if (resourceUrl == null) {
            System.err.println("[Layout] Missing background resource: " + resourcePath);
            return;
        }

        String url = resourceUrl.toExternalForm();
        System.out.println("[Layout] Loaded background URL: " + url);

        Image image = new Image(url, true);

        BackgroundSize size = new BackgroundSize(
                1.0, 1.0,
                true,  true,
                false, true
        );

        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                size
        );

        // Clear any previous background and make sure CSS black doesn't override.
        rootPane.setBackground(null);
        rootPane.setStyle("-fx-background-color: transparent;");
        rootPane.setBackground(new Background(backgroundImage));
        rootPane.requestLayout();
    }

    /**
     * Configures the end overlay, binding sizes and attaching background video.
     *
     * @param endOverlay         overlay container.
     * @param endBackgroundVideo media view for the overlay background.
     * @param endTitle           label for the end title.
     * @param endSubtitle        label for the end subtitle.
     */
    public void setupEndOverlay(StackPane endOverlay,
                                MediaView endBackgroundVideo,
                                Label endTitle,
                                Label endSubtitle) {
        this.endOverlay = endOverlay;
        this.endBackgroundVideo = endBackgroundVideo;
        this.endTitle = endTitle;
        this.endSubtitle = endSubtitle;

        if (this.endOverlay == null) {
            return;
        }

        this.endOverlay.setVisible(false);
        this.endOverlay.setManaged(false);

        this.endOverlay.prefWidthProperty().bind(rootPane.widthProperty());
        this.endOverlay.prefHeightProperty().bind(rootPane.heightProperty());

        if (this.endBackgroundVideo != null) {
            BackgroundVideoManager.attach(this.endBackgroundVideo, this.endOverlay);
        }
    }

    /**
     * Shows the end overlay with sliding animation and optional text.
     *
     * @param title    title text to display.
     * @param subtitle subtitle text to display.
     */
    public void showEndScreen(String title, String subtitle) {
        if (endOverlay == null) {
            return;
        }

        if (endTitle != null && title != null) {
            endTitle.setText(title);
        }
        if (endSubtitle != null && subtitle != null) {
            endSubtitle.setText(subtitle);
        }

        endOverlay.setVisible(true);
        endOverlay.setManaged(true);
        endOverlay.toFront();

        if (endBackgroundVideo != null) {
            BackgroundVideoManager.attach(endBackgroundVideo, endOverlay);
            MediaPlayer player = endBackgroundVideo.getMediaPlayer();
            if (player != null && player.getStatus() != MediaPlayer.Status.PLAYING) {
                player.seek(player.getStartTime());
                player.play();
            }
        }

        double width = LayoutMetrics.initialWindowWidth();
        if (rootPane.getWidth() > 0) {
            width = rootPane.getWidth();
        }

        endOverlay.setTranslateX(-width);
        TranslateTransition slideIn = new TranslateTransition(
                Duration.millis(END_OVERLAY_ANIMATION_MS),
                endOverlay
        );
        slideIn.setFromX(-width);
        slideIn.setToX(0);
        slideIn.setInterpolator(Interpolator.EASE_OUT);
        slideIn.play();
    }

    /**
     * Hides the end overlay if present.
     */
    public void hideEndOverlay() {
        if (endOverlay != null) {
            endOverlay.setVisible(false);
            endOverlay.setManaged(false);
        }
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\view\manager\GameNotificationManager.java
`$lang
package com.comp2042.view.manager;

import com.comp2042.data.DownData;
import com.comp2042.util.GameConfig;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.util.Objects;

public final class GameNotificationManager {

    private static final String SCORE_NOTIFICATION_STYLE_CLASS = "score-notification";
    private static final int MAX_NOTIFICATIONS = 5;

    private final Group notificationGroup;
    private final IntegerProperty bombCountProperty;
    private int lastBombMilestone = 0;

    /**
     * Creates a notification manager bound to the given UI container and bomb counter.
     *
     * @param notificationGroup group to host notification labels.
     * @param bombCountProperty property tracking available bombs.
     */
    public GameNotificationManager(Group notificationGroup, IntegerProperty bombCountProperty) {
        this.notificationGroup = Objects.requireNonNull(notificationGroup, "notificationGroup must not be null");
        this.bombCountProperty = Objects.requireNonNull(bombCountProperty, "bombCountProperty must not be null");
    }

    /**
     * Displays score notifications in response to downward movement results.
     *
     * @param downData movement result including any bonus.
     */
    public void handleDownMovement(DownData downData) {
        if (downData == null) {
            return;
        }

        int bonus = downData.getScoreBonus();
        if (bonus > 0) {
            showScoreNotification(bonus);
        }
    }

    /**
     * Awards bombs at score milestones and shows bomb notifications.
     *
     * @param totalScore current total score.
     */
    public void handleScoreChanged(int totalScore) {
        int milestonesReached = totalScore / GameConfig.POINTS_PER_BOMB;
        int newBombs = milestonesReached - lastBombMilestone;

        if (newBombs <= 0) {
            return;
        }

        bombCountProperty.set(bombCountProperty.get() + newBombs);
        lastBombMilestone = milestonesReached;

        showBombNotification(newBombs);
    }

    private void showScoreNotification(int scoreBonus) {
        showTextNotification("+" + scoreBonus);
    }

    private void showBombNotification(int bombsAwarded) {
        if (bombsAwarded <= 0) {
            return;
        }
        showTextNotification("+" + bombsAwarded + " \uD83D\uDCA3");
    }

    private void showTextNotification(String text) {
        Label label = new Label(text);
        label.getStyleClass().add(SCORE_NOTIFICATION_STYLE_CLASS);

        ObservableList<Node> children = notificationGroup.getChildren();
        if (children.size() >= MAX_NOTIFICATIONS) {
            children.remove(0);
        }
        children.add(label);
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\view\manager\GameSessionManager.java
`$lang
package com.comp2042.view.manager;

import com.comp2042.data.DownData;
import com.comp2042.data.ViewData;
import com.comp2042.event.EventSource;
import com.comp2042.event.EventType;
import com.comp2042.event.InputEventListener;
import com.comp2042.event.MoveEvent;
import com.comp2042.model.Board;
import com.comp2042.util.GameConfig;
import com.comp2042.view.GameLoop;
import com.comp2042.view.effect.BoardVibrationEffect;
import com.comp2042.view.render.BoardRenderer;
import com.comp2042.view.render.GameOverPanel;
import com.comp2042.view.render.NextBricksRenderer;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

import java.util.Objects;

/**
 * The {@code GameSessionManager} class coordinates gameplay flow by wiring the board model, renderers,
 * time-attack timer, bomb handling, notifications, and layout transitions. It forwards input events to
 * the controller, maintains game loop timing, and triggers end overlays when sessions conclude.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/manager/GameSessionManager.java">
 * GameSessionManager.java</a>
 */
public final class GameSessionManager {

    private static final String TITLE_GAME_OVER = "Game Over";
    private static final String MESSAGE_GAME_OVER = "The bricks reached the ceiling.";
    private static final String TITLE_EXIT = "Exit Game";
    private static final String MESSAGE_EXIT = "Choose what to do next.";

    private final BooleanProperty pauseProperty;
    private final BooleanProperty gameOverProperty;

    private final GridPane gamePanel;
    private final BoardRenderer boardRenderer;
    private final NextBricksRenderer nextBricksRenderer;
    private final BoardVibrationEffect vibrationEffect;
    private final TimeAttackManager timeAttackManager;
    private final GameLayoutManager layoutManager;
    private final GameNotificationManager notificationManager;
    private final GameOverPanel gameOverPanel;

    private GameLoop gameLoop;
    private InputEventListener eventListener;

    private int currentTickMillis = GameConfig.GAME_TICK_MS;
    private boolean endScreenShown = false;

    /**
     * Constructs a session manager coordinating renderers, timers, and notifications.
     *
     * @param pauseProperty       pause flag.
     * @param gameOverProperty    game-over flag.
     * @param gamePanel           grid pane receiving focus and input.
     * @param boardRenderer       renderer for the board.
     * @param nextBricksRenderer  renderer for preview bricks.
     * @param vibrationEffect     effect for hard drops or bombs.
     * @param timeAttackManager   time-attack controller (nullable).
     * @param layoutManager       layout coordinator.
     * @param notificationManager notification handler.
     * @param gameOverPanel       panel shown when the game ends.
     */
    public GameSessionManager(BooleanProperty pauseProperty,
                              BooleanProperty gameOverProperty,
                              GridPane gamePanel,
                              BoardRenderer boardRenderer,
                              NextBricksRenderer nextBricksRenderer,
                              BoardVibrationEffect vibrationEffect,
                              TimeAttackManager timeAttackManager,
                              GameLayoutManager layoutManager,
                              GameNotificationManager notificationManager,
                              GameOverPanel gameOverPanel) {

        this.pauseProperty = Objects.requireNonNull(pauseProperty, "pauseProperty must not be null");
        this.gameOverProperty = Objects.requireNonNull(gameOverProperty, "gameOverProperty must not be null");
        this.gamePanel = Objects.requireNonNull(gamePanel, "gamePanel must not be null");
        this.boardRenderer = Objects.requireNonNull(boardRenderer, "boardRenderer must not be null");
        this.nextBricksRenderer = Objects.requireNonNull(nextBricksRenderer, "nextBricksRenderer must not be null");
        this.vibrationEffect = Objects.requireNonNull(vibrationEffect, "vibrationEffect must not be null");
        this.timeAttackManager = timeAttackManager;
        this.layoutManager = layoutManager;
        this.notificationManager = notificationManager;
        this.gameOverPanel = gameOverPanel;
    }

    /**
     * Registers the controller that will process movement events.
     *
     * @param listener input listener.
     */
    public void setEventListener(InputEventListener listener) {
        this.eventListener = listener;
    }

    /**
     * Binds renderers and listeners to the supplied board, starting the game if state is available.
     *
     * @param board active board instance.
     */
    public void bindBoard(Board board) {

        board.boardMatrixProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                boardRenderer.refreshBackground(newVal);
            }
        });

        board.isGameOverProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal) {
                handleGameEnd(TITLE_GAME_OVER, MESSAGE_GAME_OVER);
            } else {
                if (gameOverPanel != null) {
                    gameOverPanel.setVisible(false);
                }
                gameOverProperty.set(false);
                endScreenShown = false;
                if (layoutManager != null) {
                    layoutManager.hideEndOverlay();
                }
            }
        });

        if (board.getBoardMatrix() != null && board.getViewData() != null) {
            startGame(board.getBoardMatrix(), board.getViewData());
        }
    }

    /**
     * Initialises rendering from the provided state and starts the game loop (and time attack, if enabled).
     *
     * @param boardMatrix board state including background.
     * @param viewData    active piece view data.
     */
    public void startGame(int[][] boardMatrix, ViewData viewData) {
        boardRenderer.initialiseBoard(boardMatrix, viewData);
        nextBricksRenderer.renderNextBricks(viewData.getNextBricksData());
        ensureGameLoopInitialised();
        gameLoop.start();

        if (timeAttackManager != null && timeAttackManager.isEnabled()) {
            timeAttackManager.start();
        }
    }

    /**
     * Handles a left movement input and refreshes active brick rendering.
     *
     * @param event move event metadata.
     */
    public void onMoveLeft(MoveEvent event) {
        if (pauseProperty.get() || eventListener == null) {
            return;
        }
        ViewData viewData = eventListener.onLeftEvent(event);
        updateActiveBrick(viewData);
        gamePanel.requestFocus();
    }

    /**
     * Handles a right movement input and refreshes active brick rendering.
     *
     * @param event move event metadata.
     */
    public void onMoveRight(MoveEvent event) {
        if (pauseProperty.get() || eventListener == null) {
            return;
        }
        ViewData viewData = eventListener.onRightEvent(event);
        updateActiveBrick(viewData);
        gamePanel.requestFocus();
    }

    /**
     * Handles a rotation request and refreshes active brick rendering.
     *
     * @param event move event metadata.
     */
    public void onRotate(MoveEvent event) {
        if (pauseProperty.get() || eventListener == null) {
            return;
        }
        ViewData viewData = eventListener.onRotateEvent(event);
        updateActiveBrick(viewData);
        gamePanel.requestFocus();
    }

    /**
     * Handles a soft drop from the user or game loop and updates visuals/notifications.
     *
     * @param event move event metadata.
     */
    public void onMoveDown(MoveEvent event) {
        if (pauseProperty.get() || eventListener == null) {
            return;
        }
        DownData downData = eventListener.onDownEvent(event);
        handleDownMovement(downData, false);
        gamePanel.requestFocus();
    }

    /**
     * Handles a hard drop from the user, applying vibration and updating visuals.
     *
     * @param event move event metadata.
     */
    public void onHardDrop(MoveEvent event) {
        if (pauseProperty.get() || eventListener == null) {
            return;
        }
        DownData downData = eventListener.onHardDropEvent(event);
        handleDownMovement(downData, true);
        gamePanel.requestFocus();
    }

    private void handleDownMovement(DownData downData, boolean vibrate) {
        if (downData == null) {
            return;
        }

        if (notificationManager != null) {
            notificationManager.handleDownMovement(downData);
        }

        ViewData viewData = downData.getViewData();
        updateActiveBrick(viewData);

        if (vibrate) {
            vibrationEffect.vibrate();
        }
    }

    private void updateActiveBrick(ViewData viewData) {
        if (viewData == null) {
            return;
        }

        if (!pauseProperty.get()) {
            boardRenderer.refreshBrick(viewData);
        }
        nextBricksRenderer.renderNextBricks(viewData.getNextBricksData());
    }

    /**
     * Toggles pause state and updates the pause button label accordingly.
     *
     * @param pauseButton button to update.
     */
    public void togglePause(ToggleButton pauseButton) {
        if (gameOverProperty.get()) {
            return;
        }

        boolean paused = !pauseProperty.get();
        pauseProperty.set(paused);

        if (paused) {
            pauseLoopAndTimeAttack();
            if (pauseButton != null) {
                pauseButton.setText("Resume");
            }
        } else {
            resumeLoopAndTimeAttack();
            if (pauseButton != null) {
                pauseButton.setText("Pause");
            }
        }

        gamePanel.requestFocus();
    }

    /**
     * Pauses the session and shows the exit end-screen overlay.
     */
    public void exitGame() {
        pauseLoopAndTimeAttack();
        pauseProperty.set(true);
        if (layoutManager != null) {
            layoutManager.showEndScreen(TITLE_EXIT, MESSAGE_EXIT);
        }
    }

    /**
     * Handles session end triggered by loss or completion by stopping timers and showing the overlay.
     *
     * @param title    title text to display.
     * @param subtitle subtitle text to display.
     */
    public void handleGameEnd(String title, String subtitle) {
        if (gameOverProperty.get() || endScreenShown) {
            return;
        }
        gameOverProperty.set(true);
        endScreenShown = true;

        if (gameLoop != null) {
            gameLoop.stop();
        }
        if (timeAttackManager != null) {
            timeAttackManager.handleGameStopped();
        }
        if (gameOverPanel != null) {
            gameOverPanel.setVisible(false);
        }

        if (layoutManager != null) {
            layoutManager.showEndScreen(title, subtitle);
        }
    }

    /**
     * Temporarily pauses timers while a bomb drag interaction is active.
     */
    public void pauseForBombDrag() {
        if (gameLoop != null && !pauseProperty.get()) {
            gameLoop.pause();
        }
        if (timeAttackManager != null && timeAttackManager.isEnabled() && !pauseProperty.get()) {
            timeAttackManager.pause();
        }
    }

    /**
     * Resumes timers after a bomb drag interaction finishes.
     */
    public void resumeAfterBombDrag() {
        if (gameLoop != null && !pauseProperty.get()) {
            gameLoop.start();
        }
        if (timeAttackManager != null && timeAttackManager.isEnabled() && !pauseProperty.get()) {
            timeAttackManager.resume();
        }
        gamePanel.requestFocus();
    }

    /**
     * Updates the tick duration for the game loop, preserving running state when appropriate.
     *
     * @param newTickMillis new tick duration in milliseconds.
     */
    public void updateGameLoopSpeed(int newTickMillis) {
        int clamped = Math.max(GameConfig.MIN_GAME_TICK_MS, newTickMillis);
        if (clamped == currentTickMillis) {
            return;
        }
        currentTickMillis = clamped;

        if (gameLoop == null) {
            return;
        }

        boolean wasRunning = gameLoop.isRunning()
                && !pauseProperty.get()
                && !gameOverProperty.get();

        gameLoop.stop();
        gameLoop = new GameLoop(
                currentTickMillis,
                () -> onMoveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))
        );
        if (wasRunning) {
            gameLoop.start();
        }
    }

    /**
     * Indicates whether the game loop is currently running.
     *
     * @return {@code true} if running.
     */
    public boolean isRunning() {
        return gameLoop != null && gameLoop.isRunning();
    }

    private void ensureGameLoopInitialised() {
        if (gameLoop == null) {
            gameLoop = new GameLoop(
                    currentTickMillis,
                    () -> onMoveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))
            );
        }
    }

    private void pauseLoopAndTimeAttack() {
        if (gameLoop != null) {
            gameLoop.pause();
        }
        if (timeAttackManager != null) {
            timeAttackManager.pause();
        }
    }

    private void resumeLoopAndTimeAttack() {
        if (gameLoop != null) {
            gameLoop.start();
        }
        if (timeAttackManager != null) {
            timeAttackManager.resume();
        }
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\view\manager\NotificationManager.java
`$lang
package com.comp2042.view.manager;

import com.comp2042.view.render.NotificationPanel;
import javafx.scene.Group;

/**
 * The {@code NotificationManager} class is a façade for displaying transient score and bomb notifications on
 * legacy screens, creating {@link NotificationPanel} instances and attaching them to a supplied {@link Group}.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/manager/NotificationManager.java">
 * NotificationManager.java</a>
 */
public final class NotificationManager {

    private final Group notificationGroup;

    /**
     * Creates a notification manager bound to the specified group.
     *
     * @param notificationGroup group used to display notifications.
     */
    public NotificationManager(Group notificationGroup) {
        this.notificationGroup = notificationGroup;
    }

    /**
     * Displays a temporary score notification if the bonus is positive.
     *
     * @param scoreBonus amount to display.
     */
    public void showScoreNotification(int scoreBonus) {
        if (notificationGroup == null || scoreBonus <= 0) {
            return;
        }
        NotificationPanel panel = new NotificationPanel("+" + scoreBonus);
        notificationGroup.getChildren().add(panel);
        panel.showScore(notificationGroup.getChildren());
    }

    /**
     * Displays a temporary bomb award notification if at least one bomb is granted.
     *
     * @param bombsAwarded number of bombs awarded.
     */
    public void showBombNotification(int bombsAwarded) {
        if (notificationGroup == null || bombsAwarded <= 0) {
            return;
        }
        NotificationPanel panel = new NotificationPanel("+" + bombsAwarded + " 💣");
        notificationGroup.getChildren().add(panel);
        panel.showScore(notificationGroup.getChildren());
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\view\manager\TimeAttackManager.java
`$lang
package com.comp2042.view.manager;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Objects;

public final class TimeAttackManager {

    private static final int ONE_MINUTE = 1;
    private static final int THREE_MINUTES = 3;
    private static final int FIVE_MINUTES = 5;

    private static final String CLASSIC_MODE_TITLE = "CLASSIC MODE";
    private static final String TIME_ATTACK_TITLE_SUFFIX = " MIN TIME ATTACK";
    private static final String BEST_SCORE_LABEL = "BEST SCORE";
    private static final String BEST_1_MIN_LABEL = "BEST 1 MIN";
    private static final String BEST_3_MIN_LABEL = "BEST 3 MIN";
    private static final String BEST_5_MIN_LABEL = "BEST 5 MIN";
    private static final String DEFAULT_TIME_DISPLAY = "--:--";

    private final Label timerTitleLabel;
    private final Text timerValueLabel;
    private final Label bestScoreTitleLabel;
    private final Text bestScoreValueLabel;

    private final BooleanProperty pauseProperty;
    private final BooleanProperty gameOverProperty;

    private IntegerProperty boundScoreProperty;

    private Timeline timeline;
    private boolean enabled;
    private int configuredMinutes;
    private int totalSeconds;
    private int remainingSeconds;

    private int bestScore1Min;
    private int bestScore3Min;
    private int bestScore5Min;

    private Runnable onTimeOverCallback;

    /**
     * Creates a manager responsible for time-attack countdowns and best score tracking.
     *
     * @param timerTitleLabel     label showing the mode title.
     * @param timerValueLabel     text displaying remaining time.
     * @param bestScoreTitleLabel label describing the best score shown.
     * @param bestScoreValueLabel text displaying the best score value.
     * @param pauseProperty       property indicating pause state.
     * @param gameOverProperty    property indicating game-over state.
     */
    public TimeAttackManager(Label timerTitleLabel,
                             Text timerValueLabel,
                             Label bestScoreTitleLabel,
                             Text bestScoreValueLabel,
                             BooleanProperty pauseProperty,
                             BooleanProperty gameOverProperty) {

        this.timerTitleLabel = timerTitleLabel;
        this.timerValueLabel = timerValueLabel;
        this.bestScoreTitleLabel = bestScoreTitleLabel;
        this.bestScoreValueLabel = bestScoreValueLabel;
        this.pauseProperty = Objects.requireNonNull(pauseProperty, "pauseProperty must not be null");
        this.gameOverProperty = Objects.requireNonNull(gameOverProperty, "gameOverProperty must not be null");

        disableTimeAttack();
    }

    /**
     * Binds the current score property so best scores can be updated when runs end.
     *
     * @param scoreProperty observable score from the board.
     */
    public void bindScoreProperty(IntegerProperty scoreProperty) {
        this.boundScoreProperty = scoreProperty;
        updateBestScoreLabel();
    }

    /**
     * Registers a callback to invoke when the countdown reaches zero.
     *
     * @param callback action to run when time expires.
     */
    public void setOnTimeOver(Runnable callback) {
        this.onTimeOverCallback = callback;
    }

    /**
     * Indicates whether time-attack mode is configured and active.
     *
     * @return {@code true} if enabled.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Configures the time-attack duration and updates UI labels; disables when minutes are non-positive.
     *
     * @param minutes duration in minutes.
     */
    public void configure(int minutes) {
        if (minutes <= 0) {
            disableTimeAttack();
            return;
        }

        this.enabled = true;
        this.configuredMinutes = minutes;
        this.totalSeconds = minutes * 60;
        this.remainingSeconds = totalSeconds;

        if (timerTitleLabel != null) {
            timerTitleLabel.setText(configuredMinutes + TIME_ATTACK_TITLE_SUFFIX);
        }

        updateTimerLabel();
        updateBestScoreLabel();
        recreateTimeline();
    }

    /**
     * Starts the countdown from the configured duration.
     */
    public void start() {
        if (!enabled || timeline == null || gameOverProperty.get()) {
            return;
        }
        timeline.playFromStart();
    }

    /**
     * Pauses the countdown without resetting remaining time.
     */
    public void pause() {
        if (timeline != null) {
            timeline.pause();
        }
    }

    /**
     * Resumes the countdown if enabled and not blocked by pause or game over.
     */
    public void resume() {
        if (!enabled || timeline == null || gameOverProperty.get()) {
            return;
        }
        if (!pauseProperty.get()) {
            timeline.play();
        }
    }

    /**
     * Stops the countdown timer.
     */
    public void stop() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    /**
     * Handles game termination by stopping the timer and updating best scores if applicable.
     */
    public void handleGameStopped() {
        stop();
        updateBestScoreIfNeeded();
        updateBestScoreLabel();
    }

    private void disableTimeAttack() {
        this.enabled = false;
        this.configuredMinutes = 0;
        this.totalSeconds = 0;
        this.remainingSeconds = 0;

        if (timerTitleLabel != null) {
            timerTitleLabel.setText(CLASSIC_MODE_TITLE);
        }
        if (timerValueLabel != null) {
            timerValueLabel.setText(DEFAULT_TIME_DISPLAY);
        }
        updateBestScoreLabel();
        if (timeline != null) {
            timeline.stop();
            timeline = null;
        }
    }

    private void recreateTimeline() {
        if (timeline != null) {
            timeline.stop();
        }

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> onTick())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void onTick() {
        if (!enabled || pauseProperty.get() || gameOverProperty.get()) {
            return;
        }

        remainingSeconds--;
        if (remainingSeconds <= 0) {
            remainingSeconds = 0;
            updateTimerLabel();
            stop();
            updateBestScoreIfNeeded();
            updateBestScoreLabel();
            if (onTimeOverCallback != null) {
                onTimeOverCallback.run();
            }
        } else {
            updateTimerLabel();
        }
    }

    private void updateTimerLabel() {
        if (timerValueLabel == null) {
            return;
        }
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        timerValueLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void updateBestScoreIfNeeded() {
        if (!enabled || boundScoreProperty == null) {
            return;
        }

        int currentScore = boundScoreProperty.get();
        switch (configuredMinutes) {
            case ONE_MINUTE:
                if (currentScore > bestScore1Min) {
                    bestScore1Min = currentScore;
                }
                break;
            case THREE_MINUTES:
                if (currentScore > bestScore3Min) {
                    bestScore3Min = currentScore;
                }
                break;
            case FIVE_MINUTES:
                if (currentScore > bestScore5Min) {
                    bestScore5Min = currentScore;
                }
                break;
            default:
        }
    }

    private void updateBestScoreLabel() {
        if (bestScoreTitleLabel == null || bestScoreValueLabel == null) {
            return;
        }

        String label;
        int value;

        switch (configuredMinutes) {
            case ONE_MINUTE:
                label = BEST_1_MIN_LABEL;
                value = bestScore1Min;
                break;
            case THREE_MINUTES:
                label = BEST_3_MIN_LABEL;
                value = bestScore3Min;
                break;
            case FIVE_MINUTES:
                label = BEST_5_MIN_LABEL;
                value = bestScore5Min;
                break;
            default:
                label = BEST_SCORE_LABEL;
                value = 0;
        }

        bestScoreTitleLabel.setText(label);
        bestScoreValueLabel.setText(String.valueOf(value));
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\view\render\BoardRenderer.java
`$lang
package com.comp2042.view.render;

import com.comp2042.data.ViewData;
import com.comp2042.util.BlockTextureProvider;
import com.comp2042.util.GameConfig;
import com.comp2042.util.LayoutMetrics;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

/**
 * The {@code BoardRenderer} class handles visual rendering of the board grid, settled bricks, active piece, ghost
 * projection, and grid lines based on {@link com.comp2042.model.Board} view data.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/render/BoardRenderer.java">
 * BoardRenderer.java</a>
 */
public class BoardRenderer {

    private static final Color GRID_LINE_COLOR = Color.rgb(60, 60, 80, 0.6);
    private static final double GRID_LINE_WIDTH = 1.0;

    private static final Color GHOST_OUTLINE_COLOR = Color.rgb(180, 220, 255, 0.65);
    private static final Color GHOST_DETAIL_COLOR = Color.rgb(180, 220, 255, 0.4);
    private static final double GHOST_STROKE_WIDTH = 1.5;
    private static final double GHOST_CORNER_RADIUS_MIN = 2.0;
    private static final double GHOST_CORNER_RADIUS_OFFSET = 4.0;

    private final GridPane gamePanel;
    private final GridPane brickPanel;
    private final Pane ghostPane;
    private final Pane gridLinesPane;

    private Rectangle[][] displayMatrix;
    private Rectangle[][] activeRectangles;

    /**
     * Creates a renderer bound to the provided panes.
     *
     * @param gamePanel     grid pane for settled bricks.
     * @param brickPanel    grid pane for the active piece.
     * @param ghostPane     pane for ghost projections.
     * @param gridLinesPane pane for drawing grid lines.
     */
    public BoardRenderer(GridPane gamePanel,
                         GridPane brickPanel,
                         Pane ghostPane,
                         Pane gridLinesPane) {

        this.gamePanel = Objects.requireNonNull(gamePanel, "gamePanel must not be null");
        this.brickPanel = Objects.requireNonNull(brickPanel, "brickPanel must not be null");
        this.ghostPane = ghostPane;
        this.gridLinesPane = gridLinesPane;

        this.gamePanel.setAlignment(Pos.CENTER);

        this.brickPanel.setHgap(LayoutMetrics.GRID_GAP);
        this.brickPanel.setVgap(LayoutMetrics.GRID_GAP);
    }

    /**
     * Initialises board cells, active brick, ghost projection, and grid lines from the given state.
     *
     * @param boardMatrix initial board matrix.
     * @param viewData    active piece view data.
     */
    public void initialiseBoard(int[][] boardMatrix, ViewData viewData) {
        createBackgroundCells(boardMatrix);
        createActiveBrick(viewData.getBrickData());
        updateBrickPosition(viewData);
        drawGhost(viewData);
        redrawGridLines();
    }

    /**
     * Updates the settled brick background using the supplied matrix.
     *
     * @param boardMatrix latest board state.
     */
    public void refreshBackground(int[][] boardMatrix) {
        if (displayMatrix == null) {
            return;
        }

        for (int i = GameConfig.HIDDEN_BUFFER_ROWS; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                setRectangleData(boardMatrix[i][j], displayMatrix[i][j]);
            }
        }
    }

    /**
     * Updates the active brick rendering and ghost projection.
     *
     * @param viewData latest active piece view data.
     */
    public void refreshBrick(ViewData viewData) {
        if (activeRectangles == null) {
            return;
        }

        updateBrickPosition(viewData);
        drawGhost(viewData);

        int[][] brickData = viewData.getBrickData();
        for (int row = 0; row < brickData.length; row++) {
            for (int col = 0; col < brickData[row].length; col++) {
                setRectangleData(brickData[row][col], activeRectangles[row][col]);
            }
        }
    }

    /**
     * Redraws the grid lines overlay to match current board dimensions.
     */
    public void redrawGridLines() {
        if (gridLinesPane == null) {
            return;
        }

        gridLinesPane.getChildren().clear();

        double gridWidth = LayoutMetrics.boardPixelWidth();
        double gridHeight = LayoutMetrics.boardPixelHeight();
        double step = LayoutMetrics.brickStep();
        int visibleRows = GameConfig.visibleRows();
        int cols = GameConfig.BOARD_WIDTH;

        for (int col = 0; col <= cols; col++) {
            double x = col * step;
            Line verticalLine = new Line(x, 0, x, gridHeight);
            verticalLine.setStroke(GRID_LINE_COLOR);
            verticalLine.setStrokeWidth(GRID_LINE_WIDTH);
            gridLinesPane.getChildren().add(verticalLine);
        }

        for (int row = 0; row <= visibleRows; row++) {
            double y = row * step;
            Line horizontalLine = new Line(0, y, gridWidth, y);
            horizontalLine.setStroke(GRID_LINE_COLOR);
            horizontalLine.setStrokeWidth(GRID_LINE_WIDTH);
            gridLinesPane.getChildren().add(horizontalLine);
        }

        gridLinesPane.setPrefSize(gridWidth, gridHeight);
        gridLinesPane.setMinSize(gridWidth, gridHeight);
        gridLinesPane.setMaxSize(gridWidth, gridHeight);
    }

    private void createBackgroundCells(int[][] boardMatrix) {
        gamePanel.getChildren().clear();
        int rows = boardMatrix.length;
        int cols = boardMatrix[0].length;

        displayMatrix = new Rectangle[rows][cols];

        for (int i = GameConfig.HIDDEN_BUFFER_ROWS; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Rectangle rectangle = new Rectangle(LayoutMetrics.BRICK_SIZE, LayoutMetrics.BRICK_SIZE);
                rectangle.setFill(Color.TRANSPARENT);
                displayMatrix[i][j] = rectangle;
                gamePanel.add(rectangle, j, i - GameConfig.HIDDEN_BUFFER_ROWS);
            }
        }
    }

    private void createActiveBrick(int[][] brickData) {
        brickPanel.getChildren().clear();

        int rows = brickData.length;
        int cols = brickData[0].length;

        activeRectangles = new Rectangle[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Rectangle rectangle = new Rectangle(LayoutMetrics.BRICK_SIZE, LayoutMetrics.BRICK_SIZE);
                setRectangleData(brickData[row][col], rectangle);
                activeRectangles[row][col] = rectangle;
                brickPanel.add(rectangle, col, row);
            }
        }
    }

    private void updateBrickPosition(ViewData brick) {
        double boardOriginX = gamePanel.getLayoutX();
        double boardOriginY = gamePanel.getLayoutY();

        brickPanel.setLayoutX(
                boardOriginX + brick.getXPosition() * LayoutMetrics.brickStep()
        );
        brickPanel.setLayoutY(
                LayoutMetrics.brickPanelYOffset()
                        + boardOriginY
                        + brick.getYPosition() * LayoutMetrics.brickStep()
        );
    }

    private void drawGhost(ViewData brick) {
        if (ghostPane == null) {
            return;
        }

        ghostPane.getChildren().clear();

        int[][] brickData = brick.getBrickData();
        int ghostY = brick.getGhostYPosition();
        int brickX = brick.getXPosition();

        if (ghostY == brick.getYPosition()) {
            return;
        }

        double step = LayoutMetrics.brickStep();
        double brickSize = LayoutMetrics.BRICK_SIZE;
        double strokeWidth = GHOST_STROKE_WIDTH;
        double cornerRadius = Math.max(GHOST_CORNER_RADIUS_MIN,
                LayoutMetrics.BRICK_ARC_SIZE - GHOST_CORNER_RADIUS_OFFSET);
        double inset = strokeWidth * 0.5;

        for (int row = 0; row < brickData.length; row++) {
            for (int col = 0; col < brickData[row].length; col++) {
                if (brickData[row][col] == 0) {
                    continue;
                }

                double cellX = col * step;
                double cellY = row * step;

                Rectangle outline = new Rectangle(cellX, cellY, brickSize, brickSize);
                outline.setFill(Color.TRANSPARENT);
                outline.setStroke(GHOST_OUTLINE_COLOR);
                outline.setStrokeWidth(strokeWidth);
                outline.setArcWidth(cornerRadius);
                outline.setArcHeight(cornerRadius);
                ghostPane.getChildren().add(outline);

                Polygon diamond = new Polygon(
                        cellX + brickSize / 2, cellY + inset,
                        cellX + brickSize - inset, cellY + brickSize / 2,
                        cellX + brickSize / 2, cellY + brickSize - inset,
                        cellX + inset, cellY + brickSize / 2
                );
                diamond.setFill(Color.TRANSPARENT);
                diamond.setStroke(GHOST_DETAIL_COLOR);
                diamond.setStrokeWidth(strokeWidth);
                ghostPane.getChildren().add(diamond);
            }
        }

        double boardOriginX = gamePanel.getLayoutX();
        double boardOriginY = gamePanel.getLayoutY();

        ghostPane.setLayoutX(boardOriginX + brickX * step);
        ghostPane.setLayoutY(
                LayoutMetrics.brickPanelYOffset()
                        + boardOriginY
                        + ghostY * step
        );
    }

    private void setRectangleData(int color, Rectangle rectangle) {
        rectangle.setFill(BlockTextureProvider.getPattern(color));
        rectangle.setArcHeight(LayoutMetrics.BRICK_ARC_SIZE);
        rectangle.setArcWidth(LayoutMetrics.BRICK_ARC_SIZE);
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\view\render\GameOverPanel.java
`$lang
package com.comp2042.view.render;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * The {@code GameOverPanel} class is a simple UI component displaying a styled "Game Over" label.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/render/GameOverPanel.java">
 * GameOverPanel.java</a>
 */
public final class GameOverPanel extends BorderPane {

    private static final String GAME_OVER_TEXT = "GAME OVER";
    private static final String GAME_OVER_STYLE_CLASS = "gameOverStyle";

    /**
     * Constructs the panel and initialises its label content.
     */
    public GameOverPanel() {
        Label gameOverLabel = new Label(GAME_OVER_TEXT);
        gameOverLabel.getStyleClass().add(GAME_OVER_STYLE_CLASS);
        setCenter(gameOverLabel);
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\view\render\NextBricksRenderer.java
`$lang
package com.comp2042.view.render;

import com.comp2042.util.BlockTextureProvider;
import com.comp2042.util.GameConfig;
import com.comp2042.util.LayoutMetrics;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The {@code NextBricksRenderer} class renders the list of upcoming bricks into separate preview grids for display
 * in the side panel.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/render/NextBricksRenderer.java">
 * NextBricksRenderer.java</a>
 */
public final class NextBricksRenderer {

    private final VBox nextBricksList;
    private final List<GridPane> nextPreviewGrids = new ArrayList<>();

    /**
     * Creates a renderer bound to the container that will host preview grids.
     *
     * @param nextBricksList container for preview grids.
     */
    public NextBricksRenderer(VBox nextBricksList) {
        this.nextBricksList = Objects.requireNonNull(nextBricksList, "nextBricksList must not be null");
    }

    /**
     * Prepares empty preview panels according to the configured preview count.
     */
    public void initialisePanels() {
        nextBricksList.getChildren().clear();
        nextPreviewGrids.clear();

        for (int i = 0; i < GameConfig.NEXT_PREVIEW_COUNT; i++) {
            GridPane previewGrid = new GridPane();
            previewGrid.setHgap(LayoutMetrics.NEXT_BRICK_GAP);
            previewGrid.setVgap(LayoutMetrics.NEXT_BRICK_GAP);
            previewGrid.setAlignment(Pos.CENTER);
            nextPreviewGrids.add(previewGrid);
            nextBricksList.getChildren().add(previewGrid);
        }
    }

    /**
     * Renders the given preview matrices into the prepared grids.
     *
     * @param nextBricksData list of brick matrices in spawn order.
     */
    public void renderNextBricks(List<int[][]> nextBricksData) {
        if (nextPreviewGrids.isEmpty()) {
            initialisePanels();
        }

        for (int i = 0; i < nextPreviewGrids.size(); i++) {
            GridPane previewGrid = nextPreviewGrids.get(i);
            previewGrid.getChildren().clear();

            if (nextBricksData == null || nextBricksData.size() <= i) {
                continue;
            }

            int[][] brickMatrix = nextBricksData.get(i);
            for (int row = 0; row < brickMatrix.length; row++) {
                for (int col = 0; col < brickMatrix[row].length; col++) {
                    int colorIndex = brickMatrix[row][col];
                    if (colorIndex != 0) {
                        Rectangle rectangle = createPreviewRectangle(colorIndex);
                        previewGrid.add(rectangle, col, row);
                    }
                }
            }
        }
    }

    private Rectangle createPreviewRectangle(int colorIndex) {
        Rectangle rectangle = new Rectangle(LayoutMetrics.NEXT_BRICK_SIZE, LayoutMetrics.NEXT_BRICK_SIZE);
        rectangle.setFill(BlockTextureProvider.getPattern(colorIndex));
        rectangle.setArcHeight(LayoutMetrics.BRICK_ARC_SIZE);
        rectangle.setArcWidth(LayoutMetrics.BRICK_ARC_SIZE);
        return rectangle;
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\view\render\NotificationPanel.java
`$lang
package com.comp2042.view.render;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Objects;

/**
 * The {@code NotificationPanel} class displays transient score or bonus messages with fade and translate
 * animations, removing itself from the parent list when finished.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/render/NotificationPanel.java">
 * NotificationPanel.java</a>
 */
public class NotificationPanel extends BorderPane {

    private static final double MIN_WIDTH = 220.0;
    private static final double MIN_HEIGHT = 200.0;
    private static final double GLOW_LEVEL = 0.6;
    private static final String BONUS_STYLE_CLASS = "bonusStyle";

    private static final double FADE_DURATION_MS = 2000.0;
    private static final double MOVE_DURATION_MS = 2500.0;
    private static final double MOVE_OFFSET_Y = 40.0;

    /**
     * Creates a notification panel displaying the provided text.
     *
     * @param text text to display.
     */
    public NotificationPanel(String text) {
        setMinHeight(MIN_HEIGHT);
        setMinWidth(MIN_WIDTH);

        Label score = new Label(text);
        score.getStyleClass().add(BONUS_STYLE_CLASS);

        Effect glow = new Glow(GLOW_LEVEL);
        score.setEffect(glow);
        score.setTextFill(Color.WHITE);

        setCenter(score);
    }

    /**
     * Plays the notification animation and removes the panel from the given list on completion.
     *
     * @param list node list containing this panel.
     */
    public void showScore(ObservableList<Node> list) {
        Objects.requireNonNull(list, "list must not be null");

        FadeTransition fadeTransition =
                new FadeTransition(Duration.millis(FADE_DURATION_MS), this);
        TranslateTransition translateTransition =
                new TranslateTransition(Duration.millis(MOVE_DURATION_MS), this);

        translateTransition.setToY(getLayoutY() - MOVE_OFFSET_Y);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);

        ParallelTransition transition = new ParallelTransition(translateTransition, fadeTransition);
        transition.setOnFinished(event -> list.remove(NotificationPanel.this));
        transition.play();
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\view\screen\GameScreenController.java
`$lang
package com.comp2042.view.screen;

import com.comp2042.event.InputEventListener;
import com.comp2042.model.Board;
import com.comp2042.model.brick.BrickFactory;
import com.comp2042.util.LayoutMetrics;
import com.comp2042.view.GameInputHandler;
import com.comp2042.view.effect.BoardVibrationEffect;
import com.comp2042.view.manager.BackgroundMusicManager;
import com.comp2042.view.manager.BombManager;
import com.comp2042.view.manager.ChinaStageManager;
import com.comp2042.view.manager.GameLayoutManager;
import com.comp2042.view.manager.GameNotificationManager;
import com.comp2042.view.manager.GameSessionManager;
import com.comp2042.view.manager.TimeAttackManager;
import com.comp2042.view.render.BoardRenderer;
import com.comp2042.view.render.GameOverPanel;
import com.comp2042.view.render.NextBricksRenderer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The {@code GameScreenController} class serves as the main JavaFX controller for the Tetris game screen, wiring
 * UI elements to the board model and managers such as time attack, bombs, notifications, and China stages.
 * It initialises renderers, input handling, and navigation callbacks to coordinate gameplay.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/screen/GameScreenController.java">
 * GameScreenController.java</a>
 */
public class GameScreenController implements Initializable {

    private static final int ONE_MINUTE = 1;
    private static final int THREE_MINUTES = 3;
    private static final int FIVE_MINUTES = 5;

    private static final String TIME_STAGE_BACKGROUND_1 = "images/time stages/1.jpg";
    private static final String TIME_STAGE_BACKGROUND_3 = "images/time stages/3.jpg";
    private static final String TIME_STAGE_BACKGROUND_5 = "images/time stages/5.jpg";
    private static final String DIGITAL_FONT_RESOURCE = "digital.ttf";

    @FXML
    private Pane rootPane;

    @FXML
    private Pane gameLayer;

    @FXML
    private BorderPane gameBoard;

    @FXML
    private Pane gridLinesPane;

    @FXML
    private GridPane gamePanel;

    @FXML
    private Group groupNotification;

    @FXML
    private GridPane brickPanel;

    @FXML
    private Pane ghostPane;

    @FXML
    private VBox sidePanel;

    @FXML
    private VBox scoreBox;

    @FXML
    private VBox nextBricksContainer;

    @FXML
    private VBox nextBricksList;

    @FXML
    private GameOverPanel gameOverPanel;

    @FXML
    private Text scoreValue;

    @FXML
    private ToggleButton pauseButton;

    @FXML
    private StackPane bombToolbar;

    @FXML
    private Label bombEmoji;

    @FXML
    private Label bombCountLabel;

    @FXML
    private VBox timerBox;

    @FXML
    private VBox bestScoreBox;

    @FXML
    private Label timerTitle;

    @FXML
    private Label bestScoreTitle;

    @FXML
    private Text timerValue;

    @FXML
    private Text bestScoreValue;

    @FXML
    private VBox chinaDescriptionBox;

    @FXML
    private Text chinaStateTitle;

    @FXML
    private Text chinaStateDescription;

    @FXML
    private StackPane endOverlay;

    @FXML
    private MediaView endBackgroundVideo;

    @FXML
    private Label endTitle;

    @FXML
    private Label endSubtitle;

    private Board board;

    private final BooleanProperty isPause = new SimpleBooleanProperty();
    private final BooleanProperty isGameOver = new SimpleBooleanProperty();
    private final IntegerProperty bombCount = new SimpleIntegerProperty(0);

    private BoardRenderer boardRenderer;
    private NextBricksRenderer nextBricksRenderer;
    private GameLayoutManager layoutManager;
    private BoardVibrationEffect vibrationEffect;
    private TimeAttackManager timeAttackManager;
    private GameNotificationManager notificationManager;
    private GameSessionManager sessionManager;
    private BombManager bombManager;
    private ChinaStageManager chinaStageManager;
    private GameInputHandler inputHandler;

    private Runnable backToHomeHandler;
    private Runnable restartHandler;

    /**
     * Initializes the game screen by wiring UI components to renderers, managers, and input handlers.
     *
     * @param location  FXML location.
     * @param resources resource bundle.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        URL fontUrl = getClass().getClassLoader().getResource(DIGITAL_FONT_RESOURCE);
        if (fontUrl != null) {
            Font.loadFont(fontUrl.toExternalForm(), 38);
        } else {
            System.err.println("Missing font resource: " + DIGITAL_FONT_RESOURCE);
        }

        boardRenderer = new BoardRenderer(gamePanel, brickPanel, ghostPane, gridLinesPane);
        nextBricksRenderer = new NextBricksRenderer(nextBricksList);
        layoutManager = new GameLayoutManager(
                rootPane,
                gameBoard,
                gamePanel,
                gridLinesPane,
                sidePanel,
                timerBox,
                nextBricksContainer,
                nextBricksList,
                groupNotification,
                boardRenderer,
                bombToolbar,
                chinaDescriptionBox
        );
        vibrationEffect = new BoardVibrationEffect(gameBoard, scoreBox, nextBricksContainer);

        layoutManager.applyInitialLayout();
        nextBricksRenderer.initialisePanels();

        if (rootPane != null) {
            rootPane.widthProperty().addListener((obs, oldWidth, newWidth) -> {
                layoutManager.positionContent(newWidth.doubleValue());
                if (board != null && board.getViewData() != null) {
                    boardRenderer.refreshBrick(board.getViewData());
                }
            });

            double initialWidth = Math.max(rootPane.getWidth(), LayoutMetrics.initialWindowWidth());
            layoutManager.positionContent(initialWidth);
        }

        if (timerBox != null) {
            timerBox.setVisible(false);
            timerBox.setManaged(false);
        }
        if (bestScoreBox != null) {
            bestScoreBox.setVisible(false);
            bestScoreBox.setManaged(false);
        }
        if (chinaDescriptionBox != null) {
            chinaDescriptionBox.setVisible(false);
            chinaDescriptionBox.setManaged(false);
        }

        timeAttackManager = new TimeAttackManager(
                timerTitle,
                timerValue,
                bestScoreTitle,
                bestScoreValue,
                isPause,
                isGameOver
        );

        notificationManager = new GameNotificationManager(
                groupNotification,
                bombCount
        );

        sessionManager = new GameSessionManager(
                isPause,
                isGameOver,
                gamePanel,
                boardRenderer,
                nextBricksRenderer,
                vibrationEffect,
                timeAttackManager,
                layoutManager,
                notificationManager,
                gameOverPanel
        );

        timeAttackManager.setOnTimeOver(() ->
                sessionManager.handleGameEnd("Time's Up", "The clock reached zero. Try another run!")
        );

        chinaStageManager = new ChinaStageManager(
                chinaDescriptionBox,
                chinaStateTitle,
                chinaStateDescription,
                layoutManager::applyBackgroundImage,
                sessionManager::updateGameLoopSpeed,
                () -> sessionManager.handleGameEnd("Journey Complete", "You finished every China stage!")
        );

        bombManager = new BombManager(
                bombToolbar,
                bombEmoji,
                bombCountLabel,
                gameLayer,
                gamePanel,
                bombCount,
                isGameOver,
                boardRenderer,
                vibrationEffect,
                sessionManager::pauseForBombDrag,
                sessionManager::resumeAfterBombDrag
        );
        bombManager.initialise();

        layoutManager.setupEndOverlay(endOverlay, endBackgroundVideo, endTitle, endSubtitle);

        inputHandler = new GameInputHandler(
                isPause,
                isGameOver,
                sessionManager::onMoveLeft,
                sessionManager::onMoveRight,
                sessionManager::onRotate,
                sessionManager::onMoveDown,
                sessionManager::onHardDrop
        );

        if (gamePanel != null) {
            gamePanel.setFocusTraversable(true);
            gamePanel.requestFocus();
            gamePanel.setOnKeyPressed(inputHandler::handleKeyPressed);
        }

        if (gameOverPanel != null) {
            gameOverPanel.setVisible(false);
        }
    }

    /**
     * Binds this controller to the given {@link Board} so renderers and managers reflect current game state.
     *
     * @param board active game board.
     */
    public void bind(Board board) {
        this.board = board;

        if (bombManager != null) {
            bombManager.setBoard(board);
        }
        if (sessionManager != null) {
            sessionManager.bindBoard(board);
        }
    }

    /**
     * Registers the listener that translates UI events into board actions.
     *
     * @param eventListener controller handling movement events.
     */
    public void setEventListener(InputEventListener eventListener) {
        if (sessionManager != null) {
            sessionManager.setEventListener(eventListener);
        }
    }

    /**
     * Sets callbacks for returning home or restarting the current game.
     *
     * @param backToHomeHandler handler invoked when navigating home.
     * @param restartHandler    handler invoked when restarting.
     */
    public void setNavigationHandlers(Runnable backToHomeHandler, Runnable restartHandler) {
        this.backToHomeHandler = backToHomeHandler;
        this.restartHandler = restartHandler;
    }

    /**
     * Binds score UI elements and forwards score changes to notification and China stage managers.
     *
     * @param scoreProperty observable score property.
     */
    public void bindScore(IntegerProperty scoreProperty) {
        if (scoreValue != null) {
            scoreValue.textProperty().bind(scoreProperty.asString());
        }

        if (timeAttackManager != null) {
            timeAttackManager.bindScoreProperty(scoreProperty);
        }

        scoreProperty.addListener((obs, oldVal, newVal) -> {
            int newScore = newVal.intValue();
            if (notificationManager != null) {
                notificationManager.handleScoreChanged(newScore);
            }
            if (chinaStageManager != null && chinaStageManager.isEnabled()) {
                chinaStageManager.handleScoreChanged(newScore);
            }
        });
    }

    /**
     * Toggles pause state through the session manager.
     *
     * @param actionEvent pause button event.
     */
    public void pauseGame(ActionEvent actionEvent) {
        if (sessionManager != null) {
            sessionManager.togglePause(pauseButton);
        }
    }

    @FXML
    private void handleBackToMain(ActionEvent actionEvent) {
        if (backToHomeHandler != null) {
            backToHomeHandler.run();
        }
    }

    @FXML
    private void handleRestartGame(ActionEvent actionEvent) {
        if (restartHandler != null) {
            restartHandler.run();
        }
    }

    @FXML
    private void handleExitGame(ActionEvent actionEvent) {
        if (sessionManager != null) {
            sessionManager.exitGame();
        }
    }

    /**
     * Signals a game-over condition to the session manager with default messaging.
     */
    public void gameOver() {
        if (sessionManager != null) {
            sessionManager.handleGameEnd("Game Over", "The bricks reached the ceiling.");
        }
    }

    /**
     * Configures managers and UI for Explore China mode, disabling time attack and enabling stage progression.
     */
    public void configureExploreChinaMode() {
        BrickFactory.setPlusEnabled(false);
        BackgroundMusicManager.playExploreChinaMusic();

        configureTimeAttack(0);

        if (timerBox != null) {
            timerBox.setVisible(false);
            timerBox.setManaged(false);
        }
        if (bestScoreBox != null) {
            bestScoreBox.setVisible(false);
            bestScoreBox.setManaged(false);
        }

        if (chinaStageManager != null) {
            chinaStageManager.enableExploreMode();
        }
    }

    /**
     * Configures time-attack mode for the specified duration, updating UI, backgrounds, and music.
     *
     * @param minutes duration in minutes; non-positive disables time attack.
     */
    public void configureTimeAttack(int minutes) {
        timeAttackManager.configure(minutes);

        if (minutes <= 0) {
            if (timerBox != null) {
                timerBox.setVisible(false);
                timerBox.setManaged(false);
            }
            if (bestScoreBox != null) {
                bestScoreBox.setVisible(false);
                bestScoreBox.setManaged(false);
            }
            return;
        }

        if (timerBox != null) {
            timerBox.setVisible(true);
            timerBox.setManaged(true);
        }
        if (bestScoreBox != null) {
            bestScoreBox.setVisible(true);
            bestScoreBox.setManaged(true);
        }

        applyTimeAttackBackground(minutes);

        BackgroundMusicManager.playTimeRacingMusic();
        BrickFactory.setPlusEnabled(minutes == FIVE_MINUTES);

        if (sessionManager != null && sessionManager.isRunning()) {
            timeAttackManager.start();
        }
    }

    /**
     * Displays the provided mode label in the best-score title area.
     *
     * @param modeLabel text to show.
     */
    public void showModeLabel(String modeLabel) {
        if (bestScoreTitle != null) {
            bestScoreTitle.setText(modeLabel);
        }
    }

    private void applyTimeAttackBackground(int minutes) {
        String resourcePath = null;
        if (minutes == ONE_MINUTE) {
            resourcePath = TIME_STAGE_BACKGROUND_1;
        } else if (minutes == THREE_MINUTES) {
            resourcePath = TIME_STAGE_BACKGROUND_3;
        } else if (minutes == FIVE_MINUTES) {
            resourcePath = TIME_STAGE_BACKGROUND_5;
        }

        if (resourcePath != null && layoutManager != null) {
            layoutManager.applyBackgroundImage(resourcePath);
        }
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\view\screen\HomeController.java
`$lang
package com.comp2042.view.screen;

import com.comp2042.view.HomeSelection;
import com.comp2042.view.manager.BackgroundMusicManager;
import com.comp2042.view.manager.BackgroundVideoManager;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;

import java.util.function.Consumer;

/**
 * The {@code HomeController} class manages the home screen, loading fonts, background media, and forwarding mode
 * selections to the application.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/screen/HomeController.java">
 * HomeController.java</a>
 */
public class HomeController {

    @FXML
    private StackPane homeRoot;

    @FXML
    private MediaView backgroundVideo;

    private Consumer<HomeSelection.Mode> selectionHandler;

    /**
     * Registers a callback invoked when the user selects a game mode.
     *
     * @param selectionHandler consumer receiving the selected mode.
     */
    public void setSelectionHandler(Consumer<HomeSelection.Mode> selectionHandler) {
        this.selectionHandler = selectionHandler;
    }

    @FXML
    private void exitGame() {
        javafx.application.Platform.exit();
    }

    @FXML
    private void initialize() {
        loadFonts();
        initBackgroundVideo();
    }

    private void initBackgroundVideo() {
        BackgroundVideoManager.attach(backgroundVideo, homeRoot);
        BackgroundMusicManager.playMainMusic();
    }

    private void loadFonts() {
        Font.loadFont(
                getClass().getClassLoader().getResource("digital.ttf").toExternalForm(),
                38
        );
    }

    @FXML
    private void selectCountryExplore() {
        fireSelection(HomeSelection.Mode.COUNTRY_EXPLORE);
    }

    @FXML
    private void selectTimeRacing() {
        fireSelection(HomeSelection.Mode.TIME_RACING);
    }

    private void fireSelection(HomeSelection.Mode mode) {
        if (selectionHandler != null) {
            selectionHandler.accept(mode);
        }
    }
}
```

### E:\CW2025\src\main\java\com\comp2042\view\screen\ModeSelectionController.java
`$lang
package com.comp2042.view.screen;

import com.comp2042.view.HomeSelection;
import com.comp2042.view.manager.BackgroundMusicManager;
import com.comp2042.view.manager.BackgroundVideoManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.function.Consumer;

/**
 * The {@code ModeSelectionController} class manages the mode selection screen, presenting time-attack options and
 * forwarding the user's choice to the application.
 *
 * <p>See the source code at
 * <a href="https://github.com/JIMMY081105/CW2025/tree/master/src/main/java/com/comp2042/view/screen/ModeSelectionController.java">
 * ModeSelectionController.java</a>
 */
public class ModeSelectionController {

    
    private static final String DIGITAL_FONT_RESOURCE = "digital.ttf";
    private static final double DIGITAL_FONT_SIZE = 38.0;
    
    private static final String TITLE_ONE_MINUTE = "1 Minute Sprint";
    private static final String TITLE_THREE_MINUTES = "3 Minute Rush";
    private static final String TITLE_FIVE_MINUTES = "5 Minute Marathon";

    private static final String BADGE_ONE_MINUTE = "1M";
    private static final String BADGE_THREE_MINUTES = "3M";
    private static final String BADGE_FIVE_MINUTES = "5M";

    private static final String SUBTITLE_ONE_MINUTE = "Ultra fast, pure reaction";
    private static final String SUBTITLE_THREE_MINUTES = "Balance speed and control";
    private static final String SUBTITLE_FIVE_MINUTES = "Endurance and consistency";

    @FXML
    private StackPane selectionRoot;

    @FXML
    private MediaView backgroundVideo;

    @FXML
    private Button optionOne;

    @FXML
    private Button optionTwo;

    @FXML
    private Button optionThree;

    @FXML
    private Label badgeOne;

    @FXML
    private Label badgeTwo;

    @FXML
    private Label badgeThree;

    @FXML
    private Label optionOneTitle;

    @FXML
    private Label optionTwoTitle;

    @FXML
    private Label optionThreeTitle;

    @FXML
    private Label optionOneSubtitle;

    @FXML
    private Label optionTwoSubtitle;

    @FXML
    private Label optionThreeSubtitle;

    private HomeSelection.Mode mode;
    private Consumer<HomeSelection> selectionHandler;
    private Runnable backHandler;

    @FXML
    private void initialize() {
        loadFonts();
        initBackgroundVideo();
    }

    private void initBackgroundVideo() {
        BackgroundVideoManager.attach(backgroundVideo, selectionRoot);
    }

    private void loadFonts() {
        URL fontUrl = getClass().getClassLoader().getResource(DIGITAL_FONT_RESOURCE);
        if (fontUrl != null) {
            Font.loadFont(fontUrl.toExternalForm(), DIGITAL_FONT_SIZE);
        } else {
            System.err.println("Missing font resource: " + DIGITAL_FONT_RESOURCE);
        }
    }

    /**
     * Configures the controller for the given mode and attaches navigation callbacks.
     *
     * @param mode             selected mode.
     * @param selectionHandler handler invoked when an option is chosen.
     * @param backHandler      handler invoked when navigating back.
     */
    public void configure(HomeSelection.Mode mode,
                          Consumer<HomeSelection> selectionHandler,
                          Runnable backHandler) {
        this.mode = mode;
        this.selectionHandler = selectionHandler;
        this.backHandler = backHandler;

        BackgroundMusicManager.playMainMusic();
        setupTimeRacing();
    }

    private void setupTimeRacing() {
        if (optionOneTitle != null) {
            optionOneTitle.setText(TITLE_ONE_MINUTE);
        }
        if (optionTwoTitle != null) {
            optionTwoTitle.setText(TITLE_THREE_MINUTES);
        }
        if (optionThreeTitle != null) {
            optionThreeTitle.setText(TITLE_FIVE_MINUTES);
        }

        if (badgeOne != null) {
            badgeOne.setText(BADGE_ONE_MINUTE);
        }
        if (badgeTwo != null) {
            badgeTwo.setText(BADGE_THREE_MINUTES);
        }
        if (badgeThree != null) {
            badgeThree.setText(BADGE_FIVE_MINUTES);
        }

        if (optionOneSubtitle != null) {
            optionOneSubtitle.setText(SUBTITLE_ONE_MINUTE);
        }
        if (optionTwoSubtitle != null) {
            optionTwoSubtitle.setText(SUBTITLE_THREE_MINUTES);
        }
        if (optionThreeSubtitle != null) {
            optionThreeSubtitle.setText(SUBTITLE_FIVE_MINUTES);
        }
    }

    @FXML
    private void handleOptionOne() {
        fireSelection(optionOneTitle != null ? optionOneTitle.getText() : null);
    }

    @FXML
    private void handleOptionTwo() {
        fireSelection(optionTwoTitle != null ? optionTwoTitle.getText() : null);
    }

    @FXML
    private void handleOptionThree() {
        fireSelection(optionThreeTitle != null ? optionThreeTitle.getText() : null);
    }

    @FXML
    private void handleBack() {
        if (backHandler != null) {
            backHandler.run();
        }
    }

    private void fireSelection(String option) {
        if (selectionHandler != null && mode != null && option != null) {
            selectionHandler.accept(new HomeSelection(mode, option));
        }
    }
}
```

### E:\CW2025\src\main\resources\china_stages.properties
`$lang
stage.count=30

stage.1.name=Stage 1 - Si Chuan
stage.1.image=images/china/1.jpg
stage.1.description=Sichuan is a southwestern province famous for spicy food that makes your mouth tingle with numbing pepper. It is also the home of giant pandas, with large reserves where people can visit and learn about them. The landscape includes mountains, rivers, and stunning places like Jiuzhaigou with colorful lakes. Sichuan feels lively and full of flavor - both in nature and in its culture.

stage.2.name=Stage 2 - Shan Dong
stage.2.image=images/china/2.jpg
stage.2.description=Shandong is a coastal province in eastern China, known as the birthplace of Confucius. It is home to Mount Tai, a historic mountain important in Chinese culture. Cities like Qingdao are known for beaches and well-known local beer. Shandong has a mix of history, culture, and seaside living.

stage.3.name=Stage 3 - Zhe Jiang
stage.3.image=images/china/3.jpg
stage.3.description=Zhejiang is a coastal province known for strong business spirit and fast-growing companies. Its many islands create a unique ocean lifestyle found in few other places in China. Ancient water towns with canals and stone bridges give it a special charm. Zhejiang stands out by blending economic success with cultural beauty.

stage.4.name=Stage 4 - Bei Jing
stage.4.image=images/china/4.jpg
stage.4.description=Beijing is China's capital and a city where ancient history stands right beside modern skyscrapers. It is home to landmarks like the Forbidden City and the Great Wall nearby - sights you cannot find anywhere else. People come here for art, technology, and big national events. Beijing feels powerful and cultural, all at the same time.

stage.5.name=Stage 5 - Hu Bei
stage.5.image=images/china/5.jpg
stage.5.description=Hubei is a province in central China shaped by the Yangtze River and its many lakes. Its capital, Wuhan, is famous for cherry blossoms in spring, turning parks and universities into pink tunnels of flowers. The region has a strong education scene, tasty hot dry noodles, and important transport connections. With both lively cities and peaceful nature, Hubei has a mix of energy and beauty.

stage.6.name=Stage 6 - Chong Qing
stage.6.image=images/china/6.jpg
stage.6.description=Chongqing is a major city in southwest China built among steep hills, so roads and buildings often rise up and down like a giant maze. It is famous for spicy hotpot and nighttime city views glowing above the rivers. The city's unique mountain city style means elevators, bridges, and cable cars are part of daily life. Chongqing feels bold, energetic, and full of heat - just like its food.

stage.7.name=Stage 7 - Ji Lin
stage.7.image=images/china/7.jpg
stage.7.description=Jilin is a northeastern province known for snowy winters and magical rime ice that covers trees along the Songhua River. It has rich forests and wildlife, making it an important home for animals like the Siberian tiger. In winter, people enjoy skiing and ice festivals. Jilin blends nature, cold-weather fun, and a strong northern culture.

stage.8.name=Stage 8 - Jiang Su
stage.8.image=images/china/8.jpg
stage.8.description=Jiangsu is a wealthy eastern Chinese province known for its smooth canals and elegant gardens. Many of its towns grew around waterways, giving daily life a calm river atmosphere. It has a strong economy, especially in tech and manufacturing, making it one of China's most developed regions. Jiangsu brings together culture, business, and peaceful scenery in a special way.

stage.9.name=Stage 9 - An Hui
stage.9.image=images/china/9.jpg
stage.9.description=Anhui is a province in eastern China known for its misty mountains and old villages with white walls and black roofs. Huangshan, one of China's most famous mountains, rises there with floating clouds and strange pine trees. The province is also known for Huizhou culture - beautiful calligraphy, tea, and historic architecture. Anhui feels peaceful and artistic, with scenery that looks like a classic painting.

stage.10.name=Stage 10 - He Nan
stage.10.image=images/china/10.jpg
stage.10.description=Henan is a central Chinese province often called one of the birthplaces of Chinese civilization. It has ancient cities and famous sites like the Shaolin Temple, where kung fu legends began. The Yellow River runs through it, shaping its long history. Henan stands out for its deep cultural roots and powerful historical stories.

stage.11.name=Stage 11 - Hei Long Jiang
stage.11.image=images/china/11.jpg
stage.11.description=Heilongjiang is China's northernmost province, known for freezing winters and huge snow festivals. Its capital, Harbin, is famous for Russian-style buildings and incredible ice sculptures that glow at night. The province has vast forests and wildlife, including rare animals like the Siberian tiger. Heilongjiang stands out for its strong winter culture and big, snowy adventures.

stage.12.name=Stage 12 - Hong Kong
stage.12.image=images/china/12.jpg
stage.12.description=Hong Kong is a vibrant coastal city known for its tall skyline and busy harbor. It blends Eastern and Western influences, so you will find dim sum restaurants next to neon-lit shopping streets. It is also home to one of Asia's top universities, giving the city a reputation for strong education and bright academic minds. Nature is close too - mountains and islands are just a short ride away. Hong Kong stands out for its fast energy, unique culture, beautiful sea views, and academic excellence.

stage.13.name=Stage 13 - Nei Mongol
stage.13.image=images/china/13.jpg
stage.13.description=Inner Mongolia is a northern region of China known for its wide grasslands and strong traditional Mongolian culture. Many people there enjoy horseback riding and celebrate colorful festivals. It also has large deserts, like the Gobi, with unique landscapes and wildlife. Inner Mongolia blends modern development with a lifestyle that is closely connected to nature.

stage.14.name=Stage 14 - Fujian
stage.14.image=images/china/14.jpg
stage.14.description=Fujian is a southeastern coastal province known for its tea-growing mountains and strong sea traditions. Unique round earthen houses called tulou can be found in its countryside, built by communities living together like a fortress. The province has beautiful coastlines and islands facing Taiwan. Fujian stands out for its mix of mountain tea culture, ocean life, and distinct architecture.

stage.15.name=Stage 15 - Shang Hai
stage.15.image=images/china/15.jpg
stage.15.description=Shanghai is a major coastal city known for its futuristic skyline along the Huangpu River. It mixes modern style with history - from towering skyscrapers to old streets in the Bund. The city is also a global financial hub where ideas, fashion, and business move fast. Shanghai stands out for its energy, international vibe, and bright nights that feel like they never end.

stage.16.name=Stage 16 - Xin Jiang
stage.16.image=images/china/16.jpg
stage.16.description=Xinjiang is a vast region in northwest China known for deserts, snowy mountains, and long ancient routes of the Silk Road. It is home to many cultures, especially the Muslim Uyghur community, whose music, dance, and foods like lamb kebabs and naan are part of daily life. The region's mosques and traditional bazaars show its unique cultural identity. Xinjiang feels adventurous and full of stories from different peoples meeting over centuries.

stage.17.name=Stage 17 - Tian Jing
stage.17.image=images/china/17.jpg
stage.17.description=Tianjin is a major port city in northern China, known for its riverside European-style buildings left from history. It has tasty local snacks like goubuli steamed buns that people line up to try. The city developed strong aerospace and industrial technology, helping drive China's modern industry. Tianjin stands out with its mix of old foreign influences, Chinese culture, and strong innovation.

stage.18.name=Stage 18 - Shaan Xi
stage.18.image=images/china/18.jpg
stage.18.description=Shaanxi is a central Chinese province known for Xi'an, the ancient capital where the Terracotta Army guards the resting place of China's first emperor. The province sits at a key starting point of the historic Silk Road, connecting China to distant lands. Its cuisine, especially chewy biangbiang noodles, has a bold and hearty flavor. Shaanxi stands out for deep history, strong cultural roots, and food you will not forget.

stage.19.name=Stage 19 - Hai Nan
stage.19.image=images/china/19.jpg
stage.19.description=Hainan is China's southern island province, known for its warm beaches, palm trees, and blue ocean. It is a popular place for vacations, surfing, and fresh tropical fruits. The island also has volcanic parks and rainforests that show a different side of nature. With sunshine, nature, and island culture, Hainan feels like China's tropical getaway.

stage.20.name=Stage 20 - Shan Xi
stage.20.image=images/china/20.jpg
stage.20.description=Shanxi is a northern Chinese province known for its ancient architecture, including the well-preserved old city of Pingyao. It has deep coal resources, which helped shape its industry and economy. The province is famous for hand-pulled noodles and strong vinegar that locals add to many dishes. Shanxi stands out for its mix of hard-working industrial life and rich historical heritage.

stage.21.name=Stage 21 - Yunnan
stage.21.image=images/china/21.jpg
stage.21.description=Yunnan is a southwestern Chinese province known for its huge mix of ethnic cultures, each with its own festivals, clothes, and foods. Its landscapes range from snow-capped mountains to tropical rainforests, making every area feel different. The region grows some of China's best tea, like Pu'er. Yunnan stands out for its incredible diversity in nature, people, and flavors.

stage.22.name=Stage 22 - He Bei
stage.22.image=images/china/22.jpg
stage.22.description=Hebei is a northern province that wraps around Beijing and Tianjin, connecting mountains, plains, and the Bohai Sea. It includes parts of the Great Wall, where you can hike along ancient stones with wide views. The province has a long history of martial arts and strong northern culture. Hebei stands out as a place balancing tradition, industry, and key locations near China's capital.

stage.23.name=Stage 23 - Tai Bei
stage.23.image=images/china/23.jpg
stage.23.description=Taipei is a lively city known for night markets filled with tasty street food like bubble tea and crispy chicken. Modern sights like Taipei 101 stand beside peaceful temples and old streets. The city is surrounded by green mountains, so hiking and hot springs are close to everyday life. Taipei stands out for its friendly atmosphere, great snacks, and fun mix of modern and traditional style.

stage.24.name=Stage 24 - Liao Ning
stage.24.image=images/china/24.jpg
stage.24.description=Liaoning is a northeastern province known for its strong industry, shipbuilding, and important ports along the Yellow Sea. It has rich history - including old Qing Dynasty sites where emperors once lived before moving to Beijing. Winters are cold, but people enjoy hot springs and warm comfort foods like barbecue and dumplings. Liaoning stands out for its mix of industrial power, northern culture, and historical heritage.

stage.25.name=Stage 25 - Guang Xi
stage.25.image=images/china/25.jpg
stage.25.description=Guangxi is a southern region of China famous for its amazing karst mountains and rivers that look like scenes from a painting. It is home to many ethnic groups, especially the Zhuang people, with colorful festivals and music. Popular places like Guilin and Yangshuo attract travelers who love nature and adventure. Guangxi stands out for its beautiful landscapes and diverse cultural traditions.

stage.26.name=Stage 26 - Xi Zang
stage.26.image=images/china/26.jpg
stage.26.description=Xizang is a high-altitude region in southwest China, often called the Roof of the World because of the Himalayas. It is known for Tibetan culture with prayer flags, monasteries, and unique festivals. The breathtaking scenery includes snowy mountains, clear lakes, and wide grasslands. Xizang stands out for its spiritual atmosphere and majestic natural beauty.

stage.27.name=Stage 27 - Guang Zhou
stage.27.image=images/china/27.jpg
stage.27.description=Guangzhou is a major southern city known for its Cantonese culture and delicious dim sum. Its long history of trade made it one of China's earliest international ports, so the city feels open and global. The Pearl River lights up at night with colorful city views. Guangzhou stands out for its food, fast development, and lively modern energy.

stage.28.name=Stage 28 - Gui Zhou
stage.28.image=images/china/28.jpg
stage.28.description=Guizhou is a southwestern province known for its dramatic mountains, waterfalls, and cool climate. Many ethnic groups live here, especially the Miao and Dong people, with unique clothing, music, and wooden village architecture. The famous Huangguoshu Waterfall shows off the province's powerful nature. Guizhou stands out for its rich cultural traditions and hidden scenic gems.

stage.29.name=Stage 29 - Hu Nan
stage.29.image=images/china/29.jpg
stage.29.description=Hunan is a central Chinese province famous for extremely spicy food that makes even chili lovers sweat. It has stunning landscapes like Zhangjiajie, with tall stone pillars that look like floating mountains. The region is also known for its lively culture and being the birthplace of several important historical figures. Hunan stands out for strong flavors, dramatic nature, and bold local spirit.

stage.30.name=Stage 30 - Jiang Xi
stage.30.image=images/china/30.jpg
stage.30.description=Jiangxi is a southeastern province known for its green mountains and calm lakes, especially the famous Poyang Lake full of migrating birds. It has a long history of porcelain making in Jingdezhen, often called the Porcelain Capital of the World. The region also grows high-quality tea in misty villages. Jiangxi stands out for its peaceful nature and traditional craftsmanship.
```

### E:\CW2025\src\main\resources\gameLayout.fxml
`$lang
<?xml version="1.0" encoding="UTF-8"?>
<?import com.comp2042.view.render.GameOverPanel?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.control.ToggleButton?>
<?import javafx.scene.Group?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.media.MediaView?>
<?import javafx.scene.text.Text?>
<?import java.net.URL?>

<StackPane fx:id="rootPane"
           fx:controller="com.comp2042.view.screen.GameScreenController"
           xmlns:fx="http://javafx.com/fxml">

    <Pane fx:id="gameLayer">
        <BorderPane fx:id="gameBoard" styleClass="gameBoard"/>
        <Pane fx:id="gridLinesPane"/>
        <GridPane fx:id="gamePanel"/>
        <Pane fx:id="ghostPane"/>
        <GridPane fx:id="brickPanel"/>

        <VBox fx:id="timerBox" styleClass="sectionBox" alignment="CENTER" spacing="6">
            <Label fx:id="timerTitle"
                   text="TIME LEFT"
                   style="-fx-font-size: 16px; -fx-text-fill: white; -fx-font-weight: bold;"/>
            <Text fx:id="timerValue" text="--:--" styleClass="scoreClass"/>
        </VBox>

        <VBox fx:id="sidePanel" alignment="TOP_CENTER" spacing="16">

            <VBox fx:id="scoreBox" styleClass="sectionBox" alignment="CENTER" spacing="8">
                <Label text="SCORE"
                       style="-fx-font-size: 16px; -fx-text-fill: white; -fx-font-weight: bold;"/>
                <Text fx:id="scoreValue" text="0" styleClass="scoreClass"/>
            </VBox>

            <VBox fx:id="bestScoreBox" styleClass="sectionBox" alignment="CENTER" spacing="6">
                <Label fx:id="bestScoreTitle"
                       text="BEST SCORE"
                       style="-fx-font-size: 16px; -fx-text-fill: white; -fx-font-weight: bold;"/>
                <Text fx:id="bestScoreValue" text="0" styleClass="scoreClass"/>
            </VBox>

            <VBox fx:id="nextBricksContainer"
                  alignment="TOP_CENTER"
                  spacing="8"
                  styleClass="sectionBox">
                <Label text="NEXT 3"
                       style="-fx-font-size: 16px; -fx-text-fill: white; -fx-font-weight: bold;"/>
                <VBox fx:id="nextBricksList" spacing="10" alignment="CENTER"/>
            </VBox>

            <VBox styleClass="sectionBox" alignment="CENTER" spacing="8">
                <ToggleButton fx:id="pauseButton"
                              text="Pause"
                              onAction="#pauseGame"
                              focusTraversable="false"
                              prefWidth="120"
                              styleClass="pause-button"/>
            </VBox>

            <VBox styleClass="sectionBox" alignment="CENTER" spacing="8">
                <Button text="Exit Game"
                        onAction="#handleExitGame"
                        focusTraversable="false"
                        prefWidth="120"
                        styleClass="pause-button"/>
            </VBox>
        </VBox>

    <VBox fx:id="chinaDescriptionBox"
        layoutX="40"
        layoutY="80"
        spacing="12"
        alignment="TOP_LEFT"
        prefWidth="300"
        styleClass="china-box">
        <Text fx:id="chinaStateTitle"
            text="Stage 1"
            styleClass="china-title"/>
        <Text fx:id="chinaStateDescription"
            text="TODO: description goes here."
            wrappingWidth="260"
            styleClass="china-description"/>
    </VBox>

        <Group fx:id="groupNotification">
            <VBox alignment="CENTER">
                <GameOverPanel fx:id="gameOverPanel"/>
            </VBox>
        </Group>

        <StackPane fx:id="bombToolbar"
                   alignment="CENTER"
                   styleClass="bombToolbarBox">
            <Label fx:id="bombEmoji" text="💣" styleClass="bombEmojiLabel"/>
            <Label fx:id="bombCountLabel"
                   text="0"
                   styleClass="bombCountBadge"
                   StackPane.alignment="TOP_RIGHT"
                   translateX="12"
                   translateY="-8"/>
        </StackPane>
    </Pane>

    <StackPane fx:id="endOverlay"
               visible="false"
               managed="false"
               styleClass="end-overlay">
        <MediaView fx:id="endBackgroundVideo" preserveRatio="true"/>
        <StackPane styleClass="end-overlay-scrim">
            <VBox alignment="CENTER" spacing="18" styleClass="end-overlay-content">
                <Label fx:id="endTitle" text="Game Over" styleClass="end-title"/>
                <Label fx:id="endSubtitle"
                       text="Choose what to do next."
                       styleClass="end-subtitle"
                       wrapText="true"
                       maxWidth="520"/>
                <VBox spacing="14" alignment="CENTER" styleClass="mode-stack end-actions">
                    <Button onAction="#handleBackToMain"
                            styleClass="mode-button">
                        <graphic>
                            <HBox alignment="CENTER_LEFT" spacing="18" styleClass="mode-content">
                                <Label text="MB" styleClass="mode-badge"/>
                                <VBox alignment="CENTER_LEFT" spacing="4">
                                    <Label text="Back to Mainboard" styleClass="mode-title"/>
                                    <Label text="Return to the home screen" styleClass="mode-subtitle"/>
                                </VBox>
                            </HBox>
                        </graphic>
                    </Button>
                    <Button onAction="#handleRestartGame"
                            styleClass="mode-button">
                        <graphic>
                            <HBox alignment="CENTER_LEFT" spacing="18" styleClass="mode-content">
                                <Label text="NG" styleClass="mode-badge"/>
                                <VBox alignment="CENTER_LEFT" spacing="4">
                                    <Label text="New Game" styleClass="mode-title"/>
                                    <Label text="Replay this mode from the start" styleClass="mode-subtitle"/>
                                </VBox>
                            </HBox>
                        </graphic>
                    </Button>
                </VBox>
            </VBox>
        </StackPane>
    </StackPane>

    <stylesheets>
        <URL value="@window_style.css"/>
    </stylesheets>
</StackPane>
```

### E:\CW2025\src\main\resources\home_layout.fxml
`$lang
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.layout.BorderPane?>
<?import javafx.scene.layout.HBox?>
<?import javafx.scene.layout.StackPane?>
<?import javafx.scene.layout.VBox?>
<?import javafx.scene.media.MediaView?>
<?import java.net.URL?>
<StackPane fx:id="homeRoot" xmlns:fx="http://javafx.com/fxml"
           fx:controller="com.comp2042.view.screen.HomeController"
           styleClass="home-root">
    <MediaView fx:id="backgroundVideo" preserveRatio="true"/>
    <BorderPane styleClass="home-overlay">
        <center>
            <VBox alignment="CENTER" spacing="24" styleClass="menu-panel">
                <Label text="TETRIS ADVENTURE" styleClass="home-title"/>
                <VBox spacing="14" styleClass="mode-stack">
                    <Button fx:id="countryButton"
                            onAction="#selectCountryExplore"
                            styleClass="mode-button">
                        <graphic>
                            <HBox alignment="CENTER_LEFT" spacing="18" styleClass="mode-content">
                                <Label text="EC" styleClass="mode-badge"/>
                                <VBox alignment="CENTER_LEFT" spacing="4">
                                    <Label text="Explore China" styleClass="mode-title"/>
                                    <Label text="Jump straight into the China map" styleClass="mode-subtitle"/>
                                </VBox>
                            </HBox>
                        </graphic>
                    </Button>
                    <Button fx:id="timeButton"
                            onAction="#selectTimeRacing"
                            styleClass="mode-button">
                        <graphic>
                            <HBox alignment="CENTER_LEFT" spacing="18" styleClass="mode-content">
                                <Label text="TR" styleClass="mode-badge"/>
                                <VBox alignment="CENTER_LEFT" spacing="4">
                                    <Label text="Time Racing" styleClass="mode-title"/>
                                    <Label text="Beat the clock with precision drops" styleClass="mode-subtitle"/>
                                </VBox>
                            </HBox>
                        </graphic>
                    </Button>
                    <HBox alignment="CENTER_RIGHT" styleClass="exit-row">
                        <Button fx:id="exitButton"
                                onAction="#exitGame"
                                styleClass="mode-button exit-button">
                            <graphic>
                                <HBox alignment="CENTER" spacing="10" styleClass="mode-content">
                                    <Label text="Exit Game" styleClass="exit-title"/>
                                </HBox>
                            </graphic>
                        </Button>
                    </HBox>
                </VBox>
            </VBox>
        </center>
    </BorderPane>
    <stylesheets>
        <URL value="@window_style.css"/>
    </stylesheets>
</StackPane>
```

### E:\CW2025\src\main\resources\selection_layout.fxml
`$lang
<?xml version="1.0" encoding="UTF-8"?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.layout.BorderPane?>
<?import javafx.scene.layout.HBox?>
<?import javafx.scene.layout.StackPane?>
<?import javafx.scene.layout.VBox?>
<?import javafx.scene.media.MediaView?>
<?import java.net.URL?>

<StackPane fx:id="selectionRoot"
           xmlns:fx="http://javafx.com/fxml"
           fx:controller="com.comp2042.view.screen.ModeSelectionController"
           styleClass="home-root">

    <MediaView fx:id="backgroundVideo" preserveRatio="true"/>

    <BorderPane styleClass="home-overlay">

        <center>
            <VBox alignment="CENTER"
                  spacing="24"
                  styleClass="menu-panel selection-panel">

                <HBox alignment="CENTER_LEFT"
                      spacing="10"
                      maxWidth="720"
                      styleClass="back-bar">
                    <Button onAction="#handleBack" styleClass="back-link" text="&lt; Back to Home"/>
                </HBox>

                <VBox spacing="14" styleClass="mode-stack">

                    <Button fx:id="optionOne"
                            onAction="#handleOptionOne"
                            styleClass="mode-button">
                        <graphic>
                            <HBox alignment="CENTER_LEFT" spacing="18" styleClass="mode-content">
                                <Label fx:id="badgeOne" text="O1" styleClass="mode-badge"/>
                                <VBox alignment="CENTER_LEFT" spacing="4">
                                    <Label fx:id="optionOneTitle"
                                           text="Option 1"
                                           styleClass="mode-title"/>
                                    <Label fx:id="optionOneSubtitle"
                                           text="Subtitle 1"
                                           styleClass="mode-subtitle"/>
                                </VBox>
                            </HBox>
                        </graphic>
                    </Button>

                    <Button fx:id="optionTwo"
                            onAction="#handleOptionTwo"
                            styleClass="mode-button">
                        <graphic>
                            <HBox alignment="CENTER_LEFT" spacing="18" styleClass="mode-content">
                                <Label fx:id="badgeTwo" text="O2" styleClass="mode-badge"/>
                                <VBox alignment="CENTER_LEFT" spacing="4">
                                    <Label fx:id="optionTwoTitle"
                                           text="Option 2"
                                           styleClass="mode-title"/>
                                    <Label fx:id="optionTwoSubtitle"
                                           text="Subtitle 2"
                                           styleClass="mode-subtitle"/>
                                </VBox>
                            </HBox>
                        </graphic>
                    </Button>

                    <Button fx:id="optionThree"
                            onAction="#handleOptionThree"
                            styleClass="mode-button">
                        <graphic>
                            <HBox alignment="CENTER_LEFT" spacing="18" styleClass="mode-content">
                                <Label fx:id="badgeThree" text="O3" styleClass="mode-badge"/>
                                <VBox alignment="CENTER_LEFT" spacing="4">
                                    <Label fx:id="optionThreeTitle"
                                           text="Option 3"
                                           styleClass="mode-title"/>
                                    <Label fx:id="optionThreeSubtitle"
                                           text="Subtitle 3"
                                           styleClass="mode-subtitle"/>
                                </VBox>
                            </HBox>
                        </graphic>
                    </Button>

                </VBox>
            </VBox>
        </center>

        <bottom>
            <HBox alignment="CENTER" styleClass="footer-bar">
                <Label text="CHOOSE YOUR ADVENTURE" styleClass="footer-text"/>
            </HBox>
        </bottom>

    </BorderPane>

    <stylesheets>
        <URL value="@window_style.css"/>
    </stylesheets>
</StackPane>
```

### E:\CW2025\src\main\resources\window_style.css
`$lang
.root {
    -fx-background-color: #000000;
}


.sectionBox {
    -fx-background-color: rgb(0, 0, 0);
    -fx-border-color: rgba(255, 255, 255, 0.65);
    -fx-border-width: 2px;
    -fx-border-style: solid;
    -fx-border-radius: 16px;
    -fx-background-radius: 16px;
    -fx-padding: 16px 24px;
    -fx-spacing: 10px;
    -fx-pref-width: 220px;
    -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.4), 18, 0.1, 0, 3);
}


.sectionBox * {
    -fx-text-fill: white;
}

.nextBrick {
    -fx-border-width: 2px;
    -fx-border-color: whitesmoke;
    -fx-border-radius: 17px;
}


.gameBoard {
    -fx-background-color: rgba(40, 28, 20, 0.55);
    -fx-border-color: linear-gradient(
        from 0% 0% to 100% 100%,
        rgba(122, 74, 31, 0.95) 0%,
        rgba(181, 127, 58, 0.95) 45%,
        rgba(107, 60, 26, 0.95) 100%
    );
    -fx-border-width: 12px;
    -fx-border-radius: 4px;
    -fx-border-style: solid inside;
    -fx-background-radius: 4px;
    -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.7), 22, 0.1, 0, 2);
}

.nextBrickLabel {
    -fx-font-family: "Let's go Digital";
    -fx-padding: 20px 0 0 0;
    -fx-font-size: 16px;
    -fx-text-fill: yellow;
}

.ipad-dark-grey {
    -fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),
    linear-gradient(#020b02, #3a3a3a),
    linear-gradient(#9d9e9d 0%, #6b6a6b 20%, #343534 80%, #242424 100%),
    linear-gradient(#8a8a8a 0%, #6b6a6b 20%, #343534 80%, #262626 100%),
    linear-gradient(#777777 0%, #606060 50%, #505250 51%, #2a2b2a 100%);
    -fx-background-insets: 0, 1, 4, 5, 6;
    -fx-background-radius: 9, 8, 5, 4, 3;
    -fx-padding: 8;
    -fx-font-family: "Let's go Digital";
    -fx-font-size: 22px;
    -fx-font-weight: bold;
    -fx-text-fill: white;
    -fx-effect: dropshadow(three-pass-box, rgba(255, 255, 255, 0.2), 1, 0.0, 0, 1);
}

.rectangleStyle {
    -fx-fill: linear-gradient(from 41px 34px to 50px 50px, reflect, #ff7f50 30%, #faebd7 47%);
}

.vbox {
    -fx-spacing: 12;
}

.helpInfo {
    -fx-fill: white;
    -fx-alignment: center-left;
    -fx-text-alignment: left;
    -fx-font-size: 10px;
}

.bonusStyle {
    -fx-font-size: 40px;
    -fx-font-weight: bold;
}

.gameOverStyle {
    -fx-font-family: "Let's go Digital";
    -fx-font-size: 48;
    -fx-background-color: red;
}

.scoreClass {
    -fx-font-family: "Let's go Digital";
    -fx-font-size: 38;
    -fx-fill: yellow;
    -fx-text-fill: yellow;
}

.bombToolbarBox {
    -fx-background-color: rgba(10, 20, 30, 0.70);
    -fx-border-color: rgba(255, 140, 0, 0.8);
    -fx-border-width: 2px;
    -fx-border-style: solid;
    -fx-border-radius: 10px;
    -fx-background-radius: 10px;
    -fx-padding: 8px;
    -fx-pref-width: 50px;
    -fx-pref-height: 50px;
    -fx-min-width: 50px;
    -fx-min-height: 50px;
    -fx-max-width: 50px;
    -fx-max-height: 50px;
    -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.5), 12, 0.1, 0, 2);
}

.bombEmojiLabel {
    -fx-font-size: 28px;
}

.bombCountBadge {
    -fx-background-color: #ff4444;
    -fx-text-fill: white;
    -fx-font-size: 11px;
    -fx-font-weight: bold;
    -fx-padding: 1px 5px;
    -fx-background-radius: 8px;
    -fx-min-width: 16px;
    -fx-min-height: 16px;
    -fx-alignment: center;
}

.bombDragPreview {
    -fx-opacity: 0.7;
}

.home-root {
    -fx-background-color: #000000;
}

.home-title {
    -fx-text-fill: linear-gradient(#ffffff, #b2f7ff);
    -fx-font-size: 52px;
    -fx-font-weight: bold;
    -fx-padding: 0 0 10px 0;
    -fx-font-family: "Let's go Digital";
    -fx-effect: dropshadow(gaussian, rgba(0, 255, 220, 0.45), 22, 0.35, 0, 1);
}

.home-ellipse, .selection-ellipse {
    -fx-pref-width: 420px;
    -fx-pref-height: 150px;
    -fx-background-color: rgba(10, 20, 30, 0.9);
    -fx-background-radius: 100px;
    -fx-border-radius: 100px;
    -fx-border-color: rgba(255, 255, 255, 0.85);
    -fx-border-width: 2px;
    -fx-text-fill: white;
    -fx-font-size: 26px;
    -fx-font-weight: bold;
    -fx-font-family: "Let's go Digital";
    -fx-cursor: hand;
    -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.4), 16, 0, 0, 5);
}

.home-ellipse:hover, .selection-ellipse:hover {
    -fx-background-color: rgba(25, 40, 55, 0.95);
}

.selection-root {
    -fx-background-color: #000000;
}

.selection-panel {
    -fx-translate-y: 24;
}


.home-overlay {
    -fx-padding: 36 32 64 32;
}

.back-bar {
    -fx-padding: 8 0 4 0;
    -fx-background-color: transparent;
    -fx-border-color: transparent;
    -fx-effect: none;
}

.back-link {
    -fx-background-color: transparent;
    -fx-background-insets: 0;
    -fx-background-radius: 0;
    -fx-border-color: transparent;
    -fx-border-width: 0;
    -fx-border-radius: 0;
    -fx-text-fill: #8af3ff;
    -fx-font-family: "Let's go Digital";
    -fx-font-size: 18px;
    -fx-font-weight: bold;
    -fx-cursor: hand;
    -fx-effect: dropshadow(gaussian, rgba(0, 255, 220, 0.55), 12, 0.35, 0, 0);
    -fx-padding: 6 10;
}

.back-link:hover {
    -fx-text-fill: #eaffff;
    -fx-effect: dropshadow(gaussian, rgba(0, 255, 220, 0.8), 16, 0.5, 0, 0);
}

.menu-panel {
    -fx-alignment: center;
    -fx-spacing: 24;
    -fx-max-width: 840px;
    -fx-min-width: 520px;
}

.mode-stack {
    -fx-fill-width: true;
    -fx-alignment: center;
}

.mode-button {
    -fx-background-color:
        linear-gradient(from 0% 0% to 100% 120%, rgba(12, 22, 32, 0.78) 0%, rgba(20, 30, 50, 0.9) 60%, rgba(32, 58, 88, 0.95) 100%),
        linear-gradient(from 0% 100% to 100% 0%, rgba(0, 255, 191, 0.2), rgba(0, 255, 191, 0.0));
    -fx-background-insets: 0, 1;
    -fx-background-radius: 10;
    -fx-border-color: rgba(0, 255, 191, 0.85);
    -fx-border-width: 2px;
    -fx-border-radius: 10;
    -fx-padding: 18 24;
    -fx-alignment: center-left;
    -fx-content-display: left;
    -fx-graphic-text-gap: 0;
    -fx-cursor: hand;
    -fx-pref-width: 720px;
    -fx-min-height: 110px;
    -fx-effect: dropshadow(gaussian, rgba(0, 255, 191, 0.35), 18, 0.3, 8, 12);
    -fx-text-fill: white;
    -fx-background-position: right center;
}

.mode-button:hover {
    -fx-background-color:
        linear-gradient(from 0% 0% to 100% 120%, rgba(20, 34, 48, 0.9) 0%, rgba(24, 46, 74, 0.92) 55%, rgba(48, 92, 128, 0.98) 100%),
        linear-gradient(from 0% 100% to 100% 0%, rgba(0, 255, 220, 0.35), rgba(0, 255, 191, 0.05));
    -fx-border-color: rgba(255, 255, 245, 0.95);
    -fx-effect: dropshadow(gaussian, rgba(0, 255, 220, 0.55), 26, 0.35, 10, 14);
    -fx-scale-x: 1.03;
    -fx-scale-y: 1.0;
    -fx-padding: 18 24 18 32;
    -fx-translate-x: -6;
}

.mode-button:pressed {
    -fx-effect: dropshadow(gaussian, rgba(0, 255, 220, 0.35), 16, 0.4, 4, 8);
    -fx-translate-y: 1;
}

.exit-row {
    -fx-padding: 0 120 0 0;
}

.exit-button {
    -fx-pref-width: 180;
    -fx-max-width: 180;
    -fx-padding: 12 16;
    -fx-border-color: rgba(0, 255, 191, 0.9);
    -fx-background-color:
        linear-gradient(from 0% 0% to 100% 120%, rgba(20, 34, 48, 0.9) 0%, rgba(24, 46, 74, 0.92) 55%, rgba(48, 92, 128, 0.98) 100%),
        linear-gradient(from 0% 100% to 100% 0%, rgba(0, 255, 220, 0.35), rgba(0, 255, 191, 0.05));
    -fx-effect: dropshadow(gaussian, rgba(0, 255, 220, 0.55), 20, 0.3, 8, 10);
}

.exit-title {
    -fx-text-fill: #0ef5ff;
    -fx-font-size: 20px;
    -fx-font-family: "Let's go Digital";
    -fx-font-weight: bold;
}

.mode-content {
    -fx-padding: 4 8;
}

.mode-badge {
    -fx-font-family: "Let's go Digital";
    -fx-font-size: 42px;
    -fx-font-weight: bold;
    -fx-text-fill: #0ef5ff;
    -fx-background-color: linear-gradient(from 0% 0% to 0% 100%, rgba(0, 255, 191, 0.35), rgba(0, 255, 191, 0.08));
    -fx-padding: 14 16;
    -fx-border-color: rgba(0, 255, 191, 0.9);
    -fx-border-radius: 8;
    -fx-background-radius: 8;
    -fx-effect: innershadow(gaussian, rgba(0, 0, 0, 0.55), 14, 0.6, 0, 2);
}

.mode-title {
    -fx-text-fill: white;
    -fx-font-size: 26px;
    -fx-font-weight: bold;
    -fx-font-family: "Let's go Digital";
}

.mode-subtitle {
    -fx-text-fill: rgba(210, 230, 255, 0.85);
    -fx-font-size: 14px;
    -fx-font-family: "Let's go Digital";
    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.45), 8, 0.2, 0, 1);
}

.mode-button:hover .mode-title,
.mode-button:hover .mode-subtitle,
.mode-button:hover .mode-badge {
    -fx-effect: dropshadow(gaussian, rgba(0, 255, 220, 0.55), 16, 0.55, 0, 0);
    -fx-text-fill: #eaffff;
}

.footer-bar {
    -fx-padding: 12 24;
    -fx-background-color: rgba(10, 20, 30, 0.7);
    -fx-border-color: rgba(255, 255, 255, 0.12);
    -fx-border-width: 1px 0 0 0;
    -fx-background-radius: 12 12 0 0;
    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 24, 0.4, 0, -4);
    -fx-opacity: 0.94;
}

.footer-text {
    -fx-text-fill: #c7f9ff;
    -fx-font-family: "Let's go Digital";
    -fx-font-size: 16px;
    -fx-font-weight: bold;
}

.pause-button {
    -fx-background-color: #000000;
    -fx-text-fill: #ffffff;
    -fx-font-size: 15px;
    -fx-font-weight: bold;
    -fx-padding: 10 18;
    -fx-background-radius: 8;
    -fx-border-color: transparent;
    -fx-border-width: 0;
    -fx-border-radius: 8;
    -fx-cursor: hand;
}

.pause-button:hover {
    -fx-background-color: #0a0a0a;
    -fx-border-color: rgba(255, 255, 255, 0.85);
}

.pause-button:selected {
    -fx-background-color: #111111;
    -fx-border-color: #0ef5ff;
    -fx-text-fill: #eaffff;
}

.china-box {
    -fx-background-color: rgba(0, 0, 0, 0.65);
    -fx-padding: 16 16 18 16;
    -fx-background-radius: 12;
    -fx-border-radius: 12;
    -fx-border-color: rgba(255, 255, 255, 0.65);
    -fx-border-width: 1.5px;
}

.china-title {
    -fx-text-fill: #ffffff;
    -fx-fill: #ffffff;
    -fx-font-family: "Arial";
    -fx-font-size: 28px;
    -fx-font-weight: bold;
}

.china-description {
    -fx-text-fill: #ffffff;
    -fx-fill: #ffffff;
    -fx-font-family: "Arial";
    -fx-font-size: 20px;
    -fx-font-weight: bold;
    -fx-line-spacing: 6px;
}

.end-overlay {
    -fx-alignment: center;
}

.end-overlay * {
    -fx-font-family: "Let's go Digital";
}

.end-overlay-scrim {
    -fx-background-color: rgba(0, 0, 0, 0.58);
    -fx-padding: 32 28;
}

.end-overlay-content {
    -fx-background-color: rgba(10, 20, 30, 0.9);
    -fx-background-radius: 18;
    -fx-border-radius: 18;
    -fx-border-color: rgba(0, 255, 191, 0.75);
    -fx-border-width: 2px;
    -fx-padding: 22 28 32 28;
    -fx-alignment: center;
    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.55), 28, 0.2, 0, 6);
}

.end-title {
    -fx-text-fill: linear-gradient(#ffffff, #b2f7ff);
    -fx-font-size: 46px;
    -fx-font-weight: bold;
    -fx-font-family: "Let's go Digital";
    -fx-effect: dropshadow(gaussian, rgba(0, 255, 220, 0), 22, 0.35, 0, 1);
}

.end-subtitle {
    -fx-text-fill: #d8f8ff;
    -fx-font-size: 18px;
    -fx-font-family: "Let's go Digital";
    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 16, 0.15, 0, 2);
    -fx-alignment: center;
}

.end-actions .mode-button {
    -fx-pref-width: 640px;
}

.end-actions .mode-badge {
    -fx-min-width: 80px;
    -fx-alignment: center;
}
```

### E:\CW2025\src\test\java\com\comp2042\controller\GameControllerTest.java
`$lang
package com.comp2042.controller;

import com.comp2042.data.ClearRow;
import com.comp2042.data.DownData;
import com.comp2042.data.ViewData;
import com.comp2042.event.EventSource;
import com.comp2042.event.EventType;
import com.comp2042.event.MoveEvent;
import com.comp2042.model.Board;
import com.comp2042.model.Score;
import com.comp2042.util.GameConfig;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    private static class FakeBoard implements Board {

        int moveDownCalls;
        int moveLeftCalls;
        int moveRightCalls;
        int rotateLeftCalls;
        int createNewBrickCalls;
        int mergeCalls;
        int clearRowsCalls;

        boolean nextMoveDownResult = true;

        ClearRow clearRowToReturn;
        ViewData viewDataToReturn;

        private final Score score = new Score();
        private final BooleanProperty gameOver = new SimpleBooleanProperty(false);
        private final ObjectProperty<int[][]> boardMatrix =
                new SimpleObjectProperty<>(new int[0][0]);

        FakeBoard() {
            int[][] currentBrick = new int[][]{{1}};
            int[][] nextBrick = new int[][]{{2}};
            viewDataToReturn = new ViewData(
                    currentBrick,
                    0,
                    0,
                    10,
                    Collections.singletonList(nextBrick)
            );
            clearRowToReturn = new ClearRow(0, new int[][]{{0}});
        }

        @Override
        public boolean moveBrickDown() {
            moveDownCalls++;
            return nextMoveDownResult;
        }

        @Override
        public boolean moveBrickLeft() {
            moveLeftCalls++;
            return true;
        }

        @Override
        public boolean moveBrickRight() {
            moveRightCalls++;
            return true;
        }

        @Override
        public boolean rotateLeftBrick() {
            rotateLeftCalls++;
            return true;
        }

        @Override
        public boolean createNewBrick() {
            createNewBrickCalls++;
            return false;
        }

        @Override
        public int[][] getBoardMatrix() {
            return boardMatrix.get();
        }

        @Override
        public ViewData getViewData() {
            return viewDataToReturn;
        }

        @Override
        public void mergeBrickToBackground() {
            mergeCalls++;
        }

        @Override
        public ClearRow clearRows() {
            clearRowsCalls++;
            return clearRowToReturn;
        }

        @Override
        public Score getScore() {
            return score;
        }

        @Override
        public BooleanProperty isGameOverProperty() {
            return gameOver;
        }

        @Override
        public ObjectProperty<int[][]> boardMatrixProperty() {
            return boardMatrix;
        }

        @Override
        public IntegerProperty scoreProperty() {
            return score.scoreProperty();
        }

    }

    @Test
    void constructor_CallsCreateNewBrickOnce() {
        FakeBoard board = new FakeBoard();
        assertEquals(0, board.createNewBrickCalls);

        new GameController(board);

        assertEquals(1, board.createNewBrickCalls,
                "GameController constructor should call board.createNewBrick() once");
    }

    @Test
    void onDownEvent_BrickCanMove_UserEvent_AddsManualScoreAndDoesNotCreateNewBrickAgain() {
        FakeBoard board = new FakeBoard();
        GameController controller = new GameController(board);

        board.nextMoveDownResult = true;
        int initialScore = board.getScore().scoreProperty().get();
        int initialCreateCalls = board.createNewBrickCalls;

        DownData result = controller.onDownEvent(
                new MoveEvent(EventType.DOWN, EventSource.USER));

        assertEquals(1, board.moveDownCalls);
        assertEquals(initialCreateCalls, board.createNewBrickCalls,
                "createNewBrick should not be called when piece can move down");
        assertEquals(initialScore + GameConfig.MANUAL_DOWN_SCORE,
                board.getScore().scoreProperty().get(),
                "Manual down from USER should increase score");
        assertNull(result.getClearRow());
        assertSame(board.viewDataToReturn, result.getViewData());
        assertEquals(0, result.getScoreBonus(),
                "No line clear -> scoreBonus in DownData should be 0");
    }

    @Test
    void onDownEvent_BrickStopsAndClearsRows_AddsScoreMergesAndCreatesNewBrick() {
        FakeBoard board = new FakeBoard();
        GameController controller = new GameController(board);

        board.nextMoveDownResult = false;
        board.clearRowToReturn = new ClearRow(2, new int[][]{{0}});

        int initialScore = board.getScore().scoreProperty().get();
        int initialCreateCalls = board.createNewBrickCalls;
        int expectedBonus = GameConfig.SCORE_PER_LINE * 2 * 2;

        DownData result = controller.onDownEvent(
                new MoveEvent(EventType.DOWN, EventSource.THREAD));

        assertEquals(1, board.moveDownCalls);
        assertEquals(1, board.mergeCalls, "mergeBrickToBackground should be called when piece stops");
        assertEquals(1, board.clearRowsCalls, "clearRows should be called when piece stops");
        assertEquals(initialCreateCalls + 1, board.createNewBrickCalls,
                "createNewBrick should be called after merging");
        assertEquals(initialScore + expectedBonus,
                board.getScore().scoreProperty().get(),
                "Score should increase by strategy-computed bonus");
        assertNotNull(result.getClearRow());
        assertEquals(2, result.getClearRow().getLinesRemoved());
        assertSame(board.viewDataToReturn, result.getViewData());
        assertEquals(expectedBonus, result.getScoreBonus(),
                "DownData should expose the same line-clear bonus used for scoring");
    }

    @Test
    void onLeftEvent_DelegatesToBoardAndReturnsViewData() {
        FakeBoard board = new FakeBoard();
        GameController controller = new GameController(board);

        ViewData result = controller.onLeftEvent(
                new MoveEvent(EventType.LEFT, EventSource.USER));

        assertEquals(1, board.moveLeftCalls);
        assertSame(board.viewDataToReturn, result);
    }

    @Test
    void onRightEvent_DelegatesToBoardAndReturnsViewData() {
        FakeBoard board = new FakeBoard();
        GameController controller = new GameController(board);

        ViewData result = controller.onRightEvent(
                new MoveEvent(EventType.RIGHT, EventSource.USER));

        assertEquals(1, board.moveRightCalls);
        assertSame(board.viewDataToReturn, result);
    }

    @Test
    void onRotateEvent_DelegatesToBoardAndReturnsViewData() {
        FakeBoard board = new FakeBoard();
        GameController controller = new GameController(board);

        ViewData result = controller.onRotateEvent(
                new MoveEvent(EventType.ROTATE, EventSource.USER));

        assertEquals(1, board.rotateLeftCalls);
        assertSame(board.viewDataToReturn, result);
    }
}
```

### E:\CW2025\src\test\java\com\comp2042\data\ClearRowTest.java
`$lang
package com.comp2042.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClearRowTest {

    @Test
    void constructor_SetsLinesRemovedAndMatrix() {
        int[][] matrix = {
                {1, 0},
                {0, 1}
        };

        ClearRow clearRow = new ClearRow(2, matrix);

        assertEquals(2, clearRow.getLinesRemoved(),
                "linesRemoved should be stored correctly");
        int[][] resultMatrix = clearRow.getNewMatrix();

        // Same dimensions
        assertEquals(matrix.length, resultMatrix.length);
        for (int i = 0; i < matrix.length; i++) {
            assertArrayEquals(matrix[i], resultMatrix[i],
                    "Row " + i + " should match");
        }
    }

    @Test
    void zeroLinesRemoved_AllowsNullMatrix() {
        ClearRow clearRow = new ClearRow(0, null);

        assertEquals(0, clearRow.getLinesRemoved(),
                "linesRemoved should be 0");
        assertNull(clearRow.getNewMatrix(),
                "newMatrix may be null when no lines are removed");
    }
}
```

### E:\CW2025\src\test\java\com\comp2042\data\DownDataTest.java
`$lang
package com.comp2042.data;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DownDataTest {

    @Test
    void constructor_StoresFieldsCorrectly() {
        int[][] boardMatrix = {
                {1, 0},
                {0, 1}
        };
        int[][] currentBrick = {
                {2}
        };
        int[][] nextBrick = {
                {3}
        };

        ViewData viewData = new ViewData(
                currentBrick,
                1,
                2,
                10,
                Collections.singletonList(nextBrick)
        );
        ClearRow clearRow = new ClearRow(2, boardMatrix);

        int scoreBonus = 400;

        DownData downData = new DownData(clearRow, viewData, scoreBonus);

        assertSame(clearRow, downData.getClearRow(),
                "ClearRow reference should be stored as given");
        assertSame(viewData, downData.getViewData(),
                "ViewData reference should be stored as given");
        assertEquals(scoreBonus, downData.getScoreBonus(),
                "scoreBonus should be stored as given");
    }

    @Test
    void allowsNullClearRow_WhenNoLinesCleared() {
        int[][] currentBrick = {
                {1}
        };

        ViewData viewData = new ViewData(
                currentBrick,
                0,
                0,
                0,
                Collections.emptyList()
        );

        DownData downData = new DownData(null, viewData, 0);

        assertNull(downData.getClearRow(),
                "clearRow may be null when no lines were cleared");
        assertSame(viewData, downData.getViewData());
        assertEquals(0, downData.getScoreBonus());
    }
}
```

### E:\CW2025\src\test\java\com\comp2042\data\ViewDataTest.java
`$lang
package com.comp2042.data;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ViewDataTest {

    private void assertMatrixEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length, "Matrix row count should match");
        for (int y = 0; y < expected.length; y++) {
            assertArrayEquals(expected[y], actual[y], "Row " + y + " should match");
        }
    }

    @Test
    void constructor_StoresFieldsCorrectly() {
        int[][] currentBrick = {
                {1, 0},
                {0, 1}
        };
        int x = 3;
        int y = 5;
        int score = 120; 

        int[][] next1 = {
                {2, 2},
                {0, 2}
        };
        int[][] next2 = {
                {3, 3},
                {3, 0}
        };

        List<int[][]> nextBricks = Arrays.asList(next1, next2);

        ViewData viewData = new ViewData(
                currentBrick,
                x,
                y,
                score,
                nextBricks
        );

        int[][] brickData = viewData.getBrickData();
        assertNotNull(brickData, "getBrickData() should not return null");
        assertMatrixEquals(currentBrick, brickData);

        assertEquals(x, viewData.getXPosition());
        assertEquals(y, viewData.getYPosition());

        List<int[][]> resultNext = viewData.getNextBricksData();
        assertNotNull(resultNext, "getNextBricksData() should not return null");
        assertEquals(2, resultNext.size());

        assertMatrixEquals(next1, resultNext.get(0));
        assertMatrixEquals(next2, resultNext.get(1));
    }

    @Test
    void nextBricksData_CanBeEmptyList() {
        int[][] currentBrick = {
                {1}
        };

        ViewData viewData = new ViewData(
                currentBrick,
                0,
                0,
                0,
                Collections.emptyList()
        );

        int[][] brickData = viewData.getBrickData();
        assertNotNull(brickData);
        assertMatrixEquals(currentBrick, brickData);

        assertEquals(0, viewData.getXPosition());
        assertEquals(0, viewData.getYPosition());

        List<int[][]> next = viewData.getNextBricksData();
        assertNotNull(next, "getNextBricksData() should not return null even if no previews");
        assertTrue(next.isEmpty(), "When constructed with empty list, previews should be empty");
    }
}
```

### E:\CW2025\src\test\java\com\comp2042\model\ActivePieceTest.java
`$lang
package com.comp2042.model;

import com.comp2042.model.brick.Brick;
import com.comp2042.util.GameConfig;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActivePieceTest {

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
    void spawn_SetsInitialPositionAndShape() {
        ActivePiece activePiece = new ActivePiece();

        List<int[][]> shapes = new ArrayList<>();
        shapes.add(new int[][]{
                {1, 0},
                {1, 1}
        });
        Brick brick = new TestBrick(shapes);

        activePiece.spawn(brick);

        assertEquals(GameConfig.SPAWN_X, activePiece.getX());
        assertEquals(GameConfig.SPAWN_Y, activePiece.getY());

        int[][] shape = activePiece.getShape();
        assertEquals(2, shape.length);
        assertEquals(2, shape[0].length);
        assertEquals(1, shape[0][0]);
    }

    @Test
    void move_OnEmptyBoard_UpdatesPosition() {
        int[][] board = new int[GameConfig.BOARD_HEIGHT][GameConfig.BOARD_WIDTH];

        ActivePiece activePiece = new ActivePiece();
        List<int[][]> shapes = new ArrayList<>();
        shapes.add(new int[][]{
                {1, 1},
                {1, 1}
        });
        Brick brick = new TestBrick(shapes);
        activePiece.spawn(brick);

        int startX = activePiece.getX();
        int startY = activePiece.getY();

        boolean movedDown = activePiece.move(board, 0, 1);
        assertTrue(movedDown);
        assertEquals(startY + 1, activePiece.getY());
        assertEquals(startX, activePiece.getX());

        boolean movedLeft = activePiece.move(board, -1, 0);
        assertTrue(movedLeft);
        assertEquals(startX - 1, activePiece.getX());
        assertEquals(startY + 1, activePiece.getY());
    }

    @Test
    void move_BlockedByBottom_ReturnsFalseAndKeepsPosition() {
        int[][] board = new int[GameConfig.BOARD_HEIGHT][GameConfig.BOARD_WIDTH];

        ActivePiece activePiece = new ActivePiece();
        List<int[][]> shapes = new ArrayList<>();
        shapes.add(new int[][]{
                {1, 1},
                {1, 1}
        });
        Brick brick = new TestBrick(shapes);
        activePiece.spawn(brick);

        while (activePiece.move(board, 0, 1)) {
        }

        int yBefore = activePiece.getY();

        boolean moved = activePiece.move(board, 0, 1);

        assertFalse(moved);
        assertEquals(yBefore, activePiece.getY());
    }

    @Test
    void rotateLeft_FreeSpace_ChangesShape() {
        int[][] board = new int[GameConfig.BOARD_HEIGHT][GameConfig.BOARD_WIDTH];

        int[][] shape0 = {
                {1, 0},
                {1, 1}
        };
        int[][] shape1 = {
                {0, 1},
                {1, 1}
        };

        List<int[][]> shapes = new ArrayList<>();
        shapes.add(shape0);
        shapes.add(shape1);

        ActivePiece activePiece = new ActivePiece();
        Brick brick = new TestBrick(shapes);
        activePiece.spawn(brick);

        int[][] before = activePiece.getShape();

        boolean rotated = activePiece.rotateLeft(board);
        int[][] after = activePiece.getShape();

        assertTrue(rotated);
        assertFalse(Arrays.deepEquals(before, after));
    }

    @Test
    void rotateLeft_WithCollision_ReturnsFalseAndKeepsShape() {
        int[][] board = new int[GameConfig.BOARD_HEIGHT][GameConfig.BOARD_WIDTH];

        board[GameConfig.SPAWN_Y][GameConfig.SPAWN_X + 1] = 9;

        int[][] shape0 = {
                {1, 0},
                {0, 0}
        };
        int[][] shape1 = {
                {0, 1},
                {0, 0}
        };

        List<int[][]> shapes = new ArrayList<>();
        shapes.add(shape0);
        shapes.add(shape1);

        ActivePiece activePiece = new ActivePiece();
        Brick brick = new TestBrick(shapes);
        activePiece.spawn(brick);

        int[][] before = activePiece.getShape();

        boolean rotated = activePiece.rotateLeft(board);
        int[][] after = activePiece.getShape();

        assertFalse(rotated);
        assertTrue(Arrays.deepEquals(before, after));
    }
}
```

### E:\CW2025\src\test\java\com\comp2042\model\BombEffectServiceTest.java
`$lang
package com.comp2042.model;

import com.comp2042.data.ClearRow;
import com.comp2042.util.MatrixOperations;
import com.comp2042.data.ViewData;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BombEffectServiceTest {

    private static class FakeBoard implements Board {

        private final int[][] matrix;
        private final BooleanProperty gameOver = new SimpleBooleanProperty(false);
        private final ObjectProperty<int[][]> matrixProperty = new SimpleObjectProperty<>();
        private final IntegerProperty scoreProperty = new SimpleIntegerProperty(0);

        FakeBoard(int[][] initialMatrix) {
            this.matrix = initialMatrix;
            this.matrixProperty.set(initialMatrix);
        }

        @Override
        public int[][] getBoardMatrix() {
            return matrix;
        }


        @Override
        public boolean moveBrickDown() {
            return false;
        }

        @Override
        public boolean moveBrickLeft() {
            return false;
        }

        @Override
        public boolean moveBrickRight() {
            return false;
        }

        @Override
        public boolean rotateLeftBrick() {
            return false;
        }

        @Override
        public boolean createNewBrick() {
            return false;
        }

        @Override
        public ViewData getViewData() {
            return null;
        }

        @Override
        public void mergeBrickToBackground() {
        }

        @Override
        public ClearRow clearRows() {
            return null;
        }

        @Override
        public Score getScore() {
            return null;
        }

        @Override
        public BooleanProperty isGameOverProperty() {
            return gameOver;
        }

        @Override
        public ObjectProperty<int[][]> boardMatrixProperty() {
            return matrixProperty;
        }

        @Override
        public IntegerProperty scoreProperty() {
            return scoreProperty;
        }
    }

    @Test
    void applyBomb_ReturnsClearRowWithExplodedArea() {
        int[][] matrix = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 9, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };

        FakeBoard board = new FakeBoard(matrix);

        ClearRow result = BombEffectService.applyBomb(board, 2, 2);

        assertNotNull(result, "applyBomb should return a ClearRow");

        int[][] exploded = MatrixOperations.explodeBomb(matrix, 2, 2);
        ClearRow expected = MatrixOperations.checkRemoving(exploded);

        assertEquals(expected.getLinesRemoved(), result.getLinesRemoved());
        int[][] newMatrix = result.getNewMatrix();
        int[][] expectedMatrix = expected.getNewMatrix();

        assertEquals(expectedMatrix.length, newMatrix.length);
        for (int y = 0; y < newMatrix.length; y++) {
            assertArrayEquals(expectedMatrix[y], newMatrix[y]);
        }
    }

    @Test
    void applyBomb_AtCorner_DoesNotThrow() {
        int[][] matrix = {
                {7, 7, 0},
                {7, 7, 0},
                {0, 0, 0}
        };

        FakeBoard board = new FakeBoard(matrix);

        assertDoesNotThrow(() -> BombEffectService.applyBomb(board, 0, 0));
    }
}
```

### E:\CW2025\src\test\java\com\comp2042\model\brick\BrickFactoryTest.java
`$lang
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
```

### E:\CW2025\src\test\java\com\comp2042\model\brick\RandomBrickGeneratorTest.java
`$lang
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
```

### E:\CW2025\src\test\java\com\comp2042\model\BrickRotatorTest.java
`$lang
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
```

### E:\CW2025\src\test\java\com\comp2042\model\ScoreTest.java
`$lang
package com.comp2042.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    void initialScore_IsZero() {
        Score score = new Score();
        assertEquals(0, score.scoreProperty().get());
    }

    @Test
    void add_IncreasesScore() {
        Score score = new Score();

        score.add(10);
        score.add(5);

        assertEquals(15, score.scoreProperty().get());
    }

    @Test
    void reset_SetsScoreBackToZero() {
        Score score = new Score();

        score.add(42);
        score.reset();

        assertEquals(0, score.scoreProperty().get());
    }
}
```

### E:\CW2025\src\test\java\com\comp2042\model\SimpleBoardTest.java
`$lang
package com.comp2042.model;

import com.comp2042.data.ViewData;
import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.BrickFactory;
import com.comp2042.model.brick.BrickGenerator;
import com.comp2042.util.GameConfig;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleBoardTest {

    @Test
    void createNewBrick_WhenSpawnBlocked_DoesNotCrashAndKeepsMatrix() {
        SimpleBoard board = new SimpleBoard(GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT);

        int[][] matrix = board.getBoardMatrix();
        for (int y = GameConfig.SPAWN_Y; y < GameConfig.SPAWN_Y + 4 && y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                matrix[y][x] = 1;
            }
        }

        board.createNewBrick();

        assertNotNull(board.getBoardMatrix(), "Board matrix should remain non-null after createNewBrick()");
    }

    @Test
    void moveBrickLeftRightDown_UpdatesViewDataPosition() {
        SimpleBoard boardLeft = new SimpleBoard(GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT);
        boardLeft.createNewBrick();
        ViewData initialLeft = boardLeft.getViewData();

        boolean movedLeft = boardLeft.moveBrickLeft();
        ViewData afterLeft = boardLeft.getViewData();

        assertTrue(movedLeft);
        assertEquals(initialLeft.getXPosition() - 1, afterLeft.getXPosition());
        assertEquals(initialLeft.getYPosition(), afterLeft.getYPosition());

        SimpleBoard boardRight = new SimpleBoard(GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT);
        boardRight.createNewBrick();
        ViewData initialRight = boardRight.getViewData();

        boolean movedRight = boardRight.moveBrickRight();
        ViewData afterRight = boardRight.getViewData();

        assertTrue(movedRight);
        assertEquals(initialRight.getXPosition() + 1, afterRight.getXPosition());
        assertEquals(initialRight.getYPosition(), afterRight.getYPosition());

        SimpleBoard boardDown = new SimpleBoard(GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT);
        boardDown.createNewBrick();
        ViewData initialDown = boardDown.getViewData();

        boolean movedDown = boardDown.moveBrickDown();
        ViewData afterDown = boardDown.getViewData();

        assertTrue(movedDown);
        assertEquals(initialDown.getXPosition(), afterDown.getXPosition());
        assertEquals(initialDown.getYPosition() + 1, afterDown.getYPosition());
    }

    @Test
    void clearRows_RemovesFullRows_AndBoardMatrixPropertyReflectsChange() {
        int width = 4;
        int height = 4;
        SimpleBoard board = new SimpleBoard(width, height);

        int[][] matrix = board.getBoardMatrix();
        for (int x = 0; x < width; x++) {
            matrix[height - 1][x] = 1;
        }

        board.clearRows();

        int[][] newMatrixFromGetter = board.getBoardMatrix();
        int[][] newMatrixFromProperty = board.boardMatrixProperty().get();

        for (int x = 0; x < width; x++) {
            assertEquals(0, newMatrixFromGetter[height - 1][x]);
            assertEquals(0, newMatrixFromProperty[height - 1][x]);
        }
    }

    @Test
    void clearRows_SeparatedRows_RemovesFullRows() {
        SimpleBoard board = new SimpleBoard(4, 4);
        int[][] matrix = board.getBoardMatrix();
        for (int x = 0; x < 4; x++) {
            matrix[1][x] = 1;
        }

        matrix[2][1] = 1;

        for (int x = 0; x < 4; x++) {
            matrix[3][x] = 1;
        }

        board.clearRows();

        int[][] newMatrix = board.getBoardMatrix();

        assertFalse(isRowFull(newMatrix[1]),
                "Row 1 should not remain full after clearRows");
        assertFalse(isRowFull(newMatrix[3]),
                "Row 3 should not remain full after clearRows");
    }

    @Test
    void nextPreviews_UpdateWhenNewBricksSpawned() {
        List<Brick> bricks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            bricks.add(BrickFactory.createBrick(i % BrickFactory.getBrickCount()));
        }
        BrickGenerator generator = new FixedBrickGenerator(bricks);
        SimpleBoard board = new SimpleBoard(GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT, generator);

        board.createNewBrick();
        List<int[][]> firstPreview = board.getViewData().getNextBricksData();

        assertEquals(GameConfig.NEXT_PREVIEW_COUNT, firstPreview.size());
        assertMatrixEquals(bricks.get(1).getShapeMatrix().get(0), firstPreview.get(0));
        assertMatrixEquals(bricks.get(2).getShapeMatrix().get(0), firstPreview.get(1));

        board.mergeBrickToBackground();
        clearBoard(board.getBoardMatrix());
        board.createNewBrick();
        List<int[][]> secondPreview = board.getViewData().getNextBricksData();

        assertMatrixEquals(bricks.get(2).getShapeMatrix().get(0), secondPreview.get(0));
        assertMatrixEquals(bricks.get(3).getShapeMatrix().get(0), secondPreview.get(1));
    }

    private boolean isRowFull(int[] row) {
        for (int value : row) {
            if (value == 0) {
                return false;
            }
        }
        return true;
    }

    private void assertMatrixEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int y = 0; y < expected.length; y++) {
            assertArrayEquals(expected[y], actual[y]);
        }
    }

    private void clearBoard(int[][] matrix) {
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                matrix[y][x] = 0;
            }
        }
    }

    private static class FixedBrickGenerator implements BrickGenerator {
        private final Deque<Brick> bricks;

        FixedBrickGenerator(List<Brick> bricks) {
            this.bricks = new ArrayDeque<>(bricks);
        }

        @Override
        public Brick getBrick() {
            return bricks.poll();
        }

        @Override
        public Brick getNextBrick() {
            return bricks.peek();
        }

        @Override
        public List<Brick> preview(int count) {
            List<Brick> list = new ArrayList<>(bricks);
            return list.subList(0, Math.min(count, list.size()));
        }
    }
}
```

### E:\CW2025\src\test\java\com\comp2042\testutil\JavaFxTestUtil.java
`$lang
package com.comp2042.testutil;

import javafx.application.Platform;

public final class JavaFxTestUtil {

    private static boolean initialized = false;

    private JavaFxTestUtil() {
    }

    public static synchronized void initFx() {
        if (initialized) {
            return;
        }
        try {
            Platform.startup(() -> {

            });
        } catch (IllegalStateException e) {
            
        }
        initialized = true;
    }
}
```

### E:\CW2025\src\test\java\com\comp2042\util\GameConfigTest.java
`$lang
package com.comp2042.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameConfigTest {

    @Test
    void visibleRows_EqualsBoardHeightMinusHiddenBuffer() {
        int expected = GameConfig.BOARD_HEIGHT - GameConfig.HIDDEN_BUFFER_ROWS;
        assertEquals(expected, GameConfig.visibleRows());
    }

    @Test
    void minGameTick_IsNotGreaterThanDefaultTick() {
        assertTrue(
                GameConfig.MIN_GAME_TICK_MS <= GameConfig.GAME_TICK_MS,
                "MIN_GAME_TICK_MS should not be greater than GAME_TICK_MS"
        );
    }

    @Test
    void chinaStageSpeedStep_IsPositive() {
        assertTrue(GameConfig.CHINA_STAGE_SPEED_STEP > 0);
    }

    @Test
    void pointsPerStageAndBomb_ArePositive() {
        assertTrue(GameConfig.POINTS_PER_CHINA_STAGE > 0);
        assertTrue(GameConfig.POINTS_PER_BOMB > 0);
    }
}
```

### E:\CW2025\src\test\java\com\comp2042\util\LayoutMetricsTest.java
`$lang
package com.comp2042.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LayoutMetricsTest {

    @Test
    void brickStep_EqualsBrickSizePlusGridGap() {
        double expected = LayoutMetrics.BRICK_SIZE + LayoutMetrics.GRID_GAP;
        assertEquals(expected, LayoutMetrics.brickStep(), 0.0001);
    }

    @Test
    void boardPixelWidth_EqualsBrickStepTimesBoardWidth() {
        double expected = LayoutMetrics.brickStep() * GameConfig.BOARD_WIDTH;
        assertEquals(expected, LayoutMetrics.boardPixelWidth(), 0.0001);
    }

    @Test
    void boardPixelHeight_EqualsBrickStepTimesVisibleRows() {
        double expected = LayoutMetrics.brickStep() * GameConfig.visibleRows();
        assertEquals(expected, LayoutMetrics.boardPixelHeight(), 0.0001);
    }

    @Test
    void boardAreaDimensions_IncludeFrameThickness() {
        double contentWidth = LayoutMetrics.boardPixelWidth();
        double contentHeight = LayoutMetrics.boardPixelHeight();

        double expectedWidth = contentWidth + 2 * LayoutMetrics.BOARD_FRAME_THICKNESS;
        double expectedHeight =
                contentHeight
                        + LayoutMetrics.BOARD_FRAME_THICKNESS
                        + LayoutMetrics.BOARD_FRAME_THICKNESS;

        assertEquals(expectedWidth, LayoutMetrics.boardAreaWidth(), 0.0001);
        assertEquals(expectedHeight, LayoutMetrics.boardAreaHeight(), 0.0001);
    }

    @Test
    void brickPanelYOffset_IsNegativeHiddenBufferHeights() {
        double expected = -GameConfig.HIDDEN_BUFFER_ROWS * LayoutMetrics.brickStep();
        assertEquals(expected, LayoutMetrics.brickPanelYOffset(), 0.0001);
    }
}
```

### E:\CW2025\src\test\java\com\comp2042\util\MatrixOperationsTest.java
`$lang
package com.comp2042.util;

import com.comp2042.data.ClearRow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixOperationsTest {

    @Test
    void checkRemoving_NoFullRows_ReturnsZeroAndSameMatrix() {
        int[][] matrix = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };

        ClearRow result = MatrixOperations.checkRemoving(matrix);

        assertEquals(0, result.getLinesRemoved());
        int[][] newMatrix = result.getNewMatrix();

        assertArrayEquals(matrix[0], newMatrix[0]);
        assertArrayEquals(matrix[1], newMatrix[1]);
        assertArrayEquals(matrix[2], newMatrix[2]);
    }

    @Test
    void checkRemoving_SingleFullRow_RemovesRowAndShiftsDown() {
        int[][] matrix = {
                {0, 0, 0},
                {1, 1, 1},
                {2, 0, 2}
        };

        ClearRow result = MatrixOperations.checkRemoving(matrix);

        assertEquals(1, result.getLinesRemoved());

        int[][] newMatrix = result.getNewMatrix();
        assertArrayEquals(new int[]{0, 0, 0}, newMatrix[0]);
        assertArrayEquals(new int[]{0, 0, 0}, newMatrix[1]);
        assertArrayEquals(new int[]{2, 0, 2}, newMatrix[2]);
    }

    @Test
    void checkRemoving_MultipleFullRows_CascadesUntilStable() {
        int[][] matrix = {
                {1, 1, 1},
                {1, 1, 1},
                {0, 1, 0},
                {1, 1, 1}
        };

        ClearRow result = MatrixOperations.checkRemoving(matrix);

        assertEquals(3, result.getLinesRemoved());

        int[][] newMatrix = result.getNewMatrix();
        assertArrayEquals(new int[]{0, 0, 0}, newMatrix[0]);
        assertArrayEquals(new int[]{0, 0, 0}, newMatrix[1]);
        assertArrayEquals(new int[]{0, 0, 0}, newMatrix[2]);
        assertArrayEquals(new int[]{0, 1, 0}, newMatrix[3]);
    }

    @Test
    void explodeBomb_Clears3x3AreaAroundCenter() {
        int[][] matrix = {
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 1, 9, 1, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };

        int[][] result = MatrixOperations.explodeBomb(matrix, 2, 2);

        for (int y = 1; y <= 3; y++) {
            for (int x = 1; x <= 3; x++) {
                assertEquals(0, result[y][x], "3x3 area around bomb center should be cleared");
            }
        }

        assertEquals(0, result[0][0]);
        assertEquals(0, result[0][4]);
        assertEquals(0, result[4][0]);
        assertEquals(0, result[4][4]);
    }

    @Test
    void explodeBomb_AtEdge_OnlyClearsValidCells() {
        int[][] matrix = {
                {7, 7, 0},
                {7, 3, 0},
                {0, 0, 0}
        };

        int[][] result = MatrixOperations.explodeBomb(matrix, 0, 0);

        assertEquals(0, result[0][0]);
        assertEquals(0, result[0][1]);
        assertEquals(0, result[1][0]);
        assertEquals(0, result[1][1]);

        assertEquals(0, result[0][2]);
        assertEquals(0, result[2][2]);
    }
}
```

### E:\CW2025\src\test\java\com\comp2042\view\GameInputHandlerTest.java
`$lang
package com.comp2042.view;

import com.comp2042.event.MoveEvent;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class GameInputHandlerTest {

    private KeyEvent key(KeyCode code) {
        return new KeyEvent(
                KeyEvent.KEY_PRESSED,
                "",
                "",
                code,
                false,
                false,
                false,
                false
        );
    }

    @Test
    void handleKeyPressed_GameRunning_DispatchesMovementEvents() {
        BooleanProperty paused = new SimpleBooleanProperty(false);
        BooleanProperty gameOver = new SimpleBooleanProperty(false);

        AtomicInteger movementCalls = new AtomicInteger(0);

        // All movement callbacks just increment the same counter.
        Consumer<MoveEvent> movementCounter = move -> movementCalls.incrementAndGet();

        GameInputHandler handler = new GameInputHandler(
                paused,
                gameOver,
                movementCounter, // down
                movementCounter, // left
                movementCounter, // right
                movementCounter, // rotate
                movementCounter  // hard drop
        );

        handler.handleKeyPressed(key(KeyCode.DOWN));
        handler.handleKeyPressed(key(KeyCode.LEFT));
        handler.handleKeyPressed(key(KeyCode.RIGHT));

        assertEquals(3, movementCalls.get(),
                "Each movement key press should dispatch to one movement callback");
    }

    @Test
    void handleKeyPressed_PausedOrGameOver_IgnoresMovement() {
        BooleanProperty paused = new SimpleBooleanProperty(true);
        BooleanProperty gameOver = new SimpleBooleanProperty(false);

        AtomicInteger movementCalls = new AtomicInteger(0);

        Consumer<MoveEvent> movementCounter = move -> movementCalls.incrementAndGet();

        GameInputHandler handler = new GameInputHandler(
                paused,
                gameOver,
                movementCounter,
                movementCounter,
                movementCounter,
                movementCounter,
                movementCounter
        );

        // While paused → ignore keys
        handler.handleKeyPressed(key(KeyCode.DOWN));
        handler.handleKeyPressed(key(KeyCode.LEFT));
        assertEquals(0, movementCalls.get(),
                "When paused, movement keys should be ignored");

        // Unpause but set game over → still ignore
        paused.set(false);
        gameOver.set(true);

        handler.handleKeyPressed(key(KeyCode.DOWN));
        handler.handleKeyPressed(key(KeyCode.RIGHT));
        assertEquals(0, movementCalls.get(),
                "When game is over, movement keys should also be ignored");
    }
}
```

### E:\CW2025\src\test\java\com\comp2042\view\HomeSelectionTest.java
`$lang
package com.comp2042.view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomeSelectionTest {

    @Test
    void constructor_StoresModeAndOption() {
        HomeSelection selection =
                new HomeSelection(HomeSelection.Mode.TIME_RACING, "1 Minute Sprint");

        assertEquals(HomeSelection.Mode.TIME_RACING, selection.mode());
        assertEquals("1 Minute Sprint", selection.option());
    }

    @Test
    void equalsAndHashCode_BasedOnModeAndOption() {
        HomeSelection first =
                new HomeSelection(HomeSelection.Mode.COUNTRY_EXPLORE, "China");
        HomeSelection second =
                new HomeSelection(HomeSelection.Mode.COUNTRY_EXPLORE, "China");
        HomeSelection differentMode =
                new HomeSelection(HomeSelection.Mode.TIME_RACING, "China");
        HomeSelection differentOption =
                new HomeSelection(HomeSelection.Mode.COUNTRY_EXPLORE, "Japan");

        assertEquals(first, second, "Selections with same mode and option should be equal");
        assertEquals(first.hashCode(), second.hashCode(),
                "Equal selections should have same hashCode");

        assertNotEquals(first, differentMode,
                "Different mode should make selections unequal");
        assertNotEquals(first, differentOption,
                "Different option should make selections unequal");
    }
}
```

### E:\CW2025\src\test\java\com\comp2042\view\manager\ChinaStageManagerTest.java
`$lang
package com.comp2042.view.manager;

import com.comp2042.data.ChinaStageDescriptionProvider;
import com.comp2042.util.GameConfig;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class ChinaStageManagerTest {

    @Test
    void enableExploreMode_AppliesFirstStageAndShowsDescriptionBox() {
        VBox descriptionBox = new VBox();
        Text title = new Text();
        Text desc = new Text();

        List<String> appliedBackgrounds = new ArrayList<>();
        AtomicInteger lastTick = new AtomicInteger(0);
        AtomicBoolean completed = new AtomicBoolean(false);

        ChinaStageManager manager = new ChinaStageManager(
                descriptionBox,
                title,
                desc,
                appliedBackgrounds::add,
                lastTick::set,
                () -> completed.set(true)
        );

        assertFalse(manager.isEnabled());

        manager.enableExploreMode();

        assertTrue(manager.isEnabled());
        assertTrue(descriptionBox.isVisible());
        assertTrue(descriptionBox.isManaged());
        assertFalse(appliedBackgrounds.isEmpty());
        assertTrue(lastTick.get() > 0);
        assertFalse(completed.get());
    }

    @Test
    void handleScoreChanged_AdvancesStageWhenThresholdReached() {
        VBox descriptionBox = new VBox();
        Text title = new Text();
        Text desc = new Text();

        List<String> appliedBackgrounds = new ArrayList<>();
        AtomicInteger lastTick = new AtomicInteger(0);

        ChinaStageManager manager = new ChinaStageManager(
                descriptionBox,
                title,
                desc,
                appliedBackgrounds::add,
                lastTick::set,
                () -> {}
        );

        manager.enableExploreMode();
        appliedBackgrounds.clear();

        int scoreForNextStage = GameConfig.POINTS_PER_CHINA_STAGE;
        manager.handleScoreChanged(scoreForNextStage);

        assertFalse(appliedBackgrounds.isEmpty(),
                "Background should be re-applied when moving to a new stage");
        assertTrue(lastTick.get() <= GameConfig.GAME_TICK_MS,
                "Tick speed should not be slower than initial tick");
    }

    @Test
    void handleScoreChanged_WhenJourneyComplete_InvokesCallback() {
        VBox descriptionBox = new VBox();
        Text title = new Text();
        Text desc = new Text();

        List<String> appliedBackgrounds = new ArrayList<>();
        AtomicBoolean completed = new AtomicBoolean(false);

        ChinaStageManager manager = new ChinaStageManager(
                descriptionBox,
                title,
                desc,
                appliedBackgrounds::add,
                tick -> {},
                () -> completed.set(true)
        );

        int stageCount = ChinaStageDescriptionProvider.getStages().size();
        int completionScore = GameConfig.POINTS_PER_CHINA_STAGE * stageCount;

        manager.enableExploreMode();
        manager.handleScoreChanged(completionScore);

        assertTrue(completed.get(), "Journey completion callback should be invoked");
    }
}
```

### E:\CW2025\src\test\java\com\comp2042\view\manager\GameNotificationManagerTest.java
`$lang
package com.comp2042.view.manager;

import com.comp2042.util.GameConfig;
import com.comp2042.testutil.JavaFxTestUtil;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

class GameNotificationManagerTest {

    @BeforeAll
    static void initToolkit() {
        JavaFxTestUtil.initFx();
    }

    @Test
    void handleScoreChanged_AwardsBombsOnMilestones() {
        Group group = new Group();
        IntegerProperty bombCount = new SimpleIntegerProperty(0);

        GameNotificationManager manager = new GameNotificationManager(group, bombCount);

        int milestoneScore = GameConfig.POINTS_PER_BOMB;

        manager.handleScoreChanged(milestoneScore);

        assertEquals(1, bombCount.get(),
                "Reaching first milestone should award one bomb");
        assertFalse(group.getChildren().isEmpty());

        Node n = group.getChildren().get(group.getChildren().size() - 1);
        assertTrue(n instanceof Label);
        assertTrue(((Label) n).getText().contains("+1"),
                "Bomb notification label should include +1");
    }

    @Test
    void handleScoreChanged_DoesNotReawardSameMilestone() {
        Group group = new Group();
        IntegerProperty bombCount = new SimpleIntegerProperty(0);

        GameNotificationManager manager = new GameNotificationManager(group, bombCount);

        int milestoneScore = GameConfig.POINTS_PER_BOMB;

        manager.handleScoreChanged(milestoneScore);
        manager.handleScoreChanged(milestoneScore);

        assertEquals(1, bombCount.get(),
                "Same milestone reached twice should not award extra bombs");
    }

    @Test
    void handleScoreChanged_MultipleMilestonesInOneJump_AwardsAll() {
        Group group = new Group();
        IntegerProperty bombCount = new SimpleIntegerProperty(0);

        GameNotificationManager manager = new GameNotificationManager(group, bombCount);

        int score = GameConfig.POINTS_PER_BOMB * 3;

        manager.handleScoreChanged(score);

        assertEquals(3, bombCount.get(),
                "Jumping over several milestones should award multiple bombs once");
    }
}
```

### E:\CW2025\src\test\java\com\comp2042\view\manager\TimeAttackManagerTest.java
`$lang
package com.comp2042.view.manager;

import com.comp2042.testutil.JavaFxTestUtil;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

class TimeAttackManagerTest {

    @BeforeAll
    static void initToolkit() {
        JavaFxTestUtil.initFx();
    }

    @Test
    void configure_SetsEnabledAndTitlesAndTimer() {
        Label timerTitle = new Label();
        Text timerValue = new Text();
        Label bestTitle = new Label();
        Text bestValue = new Text();

        BooleanProperty pause = new SimpleBooleanProperty(false);
        BooleanProperty gameOver = new SimpleBooleanProperty(false);

        TimeAttackManager manager = new TimeAttackManager(
                timerTitle,
                timerValue,
                bestTitle,
                bestValue,
                pause,
                gameOver
        );

        IntegerProperty score = new SimpleIntegerProperty(0);
        manager.bindScoreProperty(score);

        manager.configure(3);

        assertTrue(manager.isEnabled());
        assertEquals("3 MIN TIME ATTACK", timerTitle.getText());
        assertEquals("03:00", timerValue.getText());
        assertEquals("BEST 3 MIN", bestTitle.getText());
        assertEquals("0", bestValue.getText());
    }

    @Test
    void disableTimeAttack_ResetsTitleAndTimerAndBestScoreLabel() {
        Label timerTitle = new Label();
        Text timerValue = new Text();
        Label bestTitle = new Label();
        Text bestValue = new Text();

        BooleanProperty pause = new SimpleBooleanProperty(false);
        BooleanProperty gameOver = new SimpleBooleanProperty(false);

        TimeAttackManager manager = new TimeAttackManager(
                timerTitle,
                timerValue,
                bestTitle,
                bestValue,
                pause,
                gameOver
        );

        IntegerProperty score = new SimpleIntegerProperty(123);
        manager.bindScoreProperty(score);

        manager.configure(1);
        manager.configure(0);

        assertFalse(manager.isEnabled());
        assertEquals("CLASSIC MODE", timerTitle.getText());
        assertEquals("--:--", timerValue.getText());
        assertEquals("BEST SCORE", bestTitle.getText());
        assertEquals("0", bestValue.getText());
    }

    @Test
    void handleGameStopped_UpdatesBestScoreAndKeepsMax() {
        Label timerTitle = new Label();
        Text timerValue = new Text();
        Label bestTitle = new Label();
        Text bestValue = new Text();

        BooleanProperty pause = new SimpleBooleanProperty(false);
        BooleanProperty gameOver = new SimpleBooleanProperty(false);

        TimeAttackManager manager = new TimeAttackManager(
                timerTitle,
                timerValue,
                bestTitle,
                bestValue,
                pause,
                gameOver
        );

        IntegerProperty score = new SimpleIntegerProperty(0);
        manager.bindScoreProperty(score);

        manager.configure(1);

        score.set(100);
        manager.handleGameStopped();
        assertEquals("100", bestValue.getText());

        score.set(80);
        manager.handleGameStopped();
        assertEquals("100", bestValue.getText());
    }
}
```

### E:\CW2025\src\test\java\com\comp2042\view\render\NextBricksRendererTest.java
`$lang
package com.comp2042.view.render;

import com.comp2042.util.GameConfig;
import com.comp2042.util.LayoutMetrics;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NextBricksRendererTest {

    @Test
    void initialisePanels_CreatesCorrectNumberOfPreviewGrids() {
        VBox container = new VBox();
        NextBricksRenderer renderer = new NextBricksRenderer(container);

        renderer.initialisePanels();

        assertEquals(
                GameConfig.NEXT_PREVIEW_COUNT,
                container.getChildren().size(),
                "initialisePanels() should create one GridPane per preview slot"
        );

        for (Node node : container.getChildren()) {
            assertTrue(node instanceof GridPane, "Each child should be a GridPane");
            GridPane grid = (GridPane) node;

            assertEquals(LayoutMetrics.NEXT_BRICK_GAP, grid.getHgap(), 0.0001);
            assertEquals(LayoutMetrics.NEXT_BRICK_GAP, grid.getVgap(), 0.0001);
            assertEquals(Pos.CENTER, grid.getAlignment(), "Preview grids should be center-aligned");
        }
    }

    @Test
    void renderNextBricks_PopulatesFirstGridWithRectanglesForNonZeroCells() {
        VBox container = new VBox();
        NextBricksRenderer renderer = new NextBricksRenderer(container);
        renderer.initialisePanels();

        int[][] brick = {
                {1, 0},
                {2, 3}
        };

        List<int[][]> nextBricks = new ArrayList<>();
        nextBricks.add(brick);

        renderer.renderNextBricks(nextBricks);

        assertFalse(container.getChildren().isEmpty(), "There should be at least one preview grid");
        GridPane firstGrid = (GridPane) container.getChildren().get(0);

        long rectangleCount = firstGrid.getChildren().stream()
                .filter(n -> n instanceof Rectangle)
                .count();
        assertEquals(3, rectangleCount, "Should render one Rectangle for each non-zero cell");

        for (Node node : firstGrid.getChildren()) {
            assertTrue(node instanceof Rectangle);

            Integer colIdx = GridPane.getColumnIndex(node);
            Integer rowIdx = GridPane.getRowIndex(node);
            int col = colIdx == null ? 0 : colIdx;
            int row = rowIdx == null ? 0 : rowIdx;

            assertNotEquals(
                    0,
                    brick[row][col],
                    "No Rectangle should be created for zero cells in the brick matrix"
            );
        }
    }
}
```

### E:\CW2025\src\test\java\com\comp2042\view\render\NotificationPanelTest.java
`$lang
package com.comp2042.view.render;

import com.comp2042.testutil.JavaFxTestUtil;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationPanelTest {

    @BeforeAll
    static void initFx() {
        JavaFxTestUtil.initFx();
    }

    @Test
    void constructor_SetsCenterLabelWithTextAndStyle() {
        String message = "+1 BOMB";
        NotificationPanel panel = new NotificationPanel(message);

        assertTrue(panel instanceof BorderPane);

        Node center = panel.getCenter();
        assertNotNull(center, "Center node should not be null");
        assertTrue(center instanceof Label, "Center node should be a Label");

        Label label = (Label) center;
        assertEquals(message, label.getText(), "Label text should match constructor argument");
        assertTrue(label.getStyleClass().contains("bonusStyle"),
                "Label should have 'bonusStyle' CSS class");
    }

    @Test
    void constructor_SetsMinimumSize() {
        NotificationPanel panel = new NotificationPanel("test");

        assertTrue(panel.getMinWidth() > 0, "Panel should have a positive minimum width");
        assertTrue(panel.getMinHeight() > 0, "Panel should have a positive minimum height");
    }
}
```

### E:\CW2025\src\test\java\com\comp2042\view\screen\ModeSelectionControllerTest.java
`$lang
package com.comp2042.view.screen;

import com.comp2042.view.HomeSelection;
import com.comp2042.testutil.JavaFxTestUtil;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.MediaView;
import javafx.scene.layout.StackPane;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class ModeSelectionControllerTest {

    @BeforeAll
    static void initToolkit() {
        JavaFxTestUtil.initFx();
    }

    private static class ControllerFixture {
        final ModeSelectionController controller;
        final Label optionOneTitle;
        final Label optionTwoTitle;
        final Label optionThreeTitle;
        final Label badgeOne;
        final Label badgeTwo;
        final Label badgeThree;

        ControllerFixture(ModeSelectionController controller,
                          Label optionOneTitle,
                          Label optionTwoTitle,
                          Label optionThreeTitle,
                          Label badgeOne,
                          Label badgeTwo,
                          Label badgeThree) {
            this.controller = controller;
            this.optionOneTitle = optionOneTitle;
            this.optionTwoTitle = optionTwoTitle;
            this.optionThreeTitle = optionThreeTitle;
            this.badgeOne = badgeOne;
            this.badgeTwo = badgeTwo;
            this.badgeThree = badgeThree;
        }
    }

    private ControllerFixture createFixture() {
        ModeSelectionController controller = new ModeSelectionController();

        StackPane selectionRoot = new StackPane();
        MediaView backgroundVideo = new MediaView();

        Button optionOne = new Button();
        Button optionTwo = new Button();
        Button optionThree = new Button();

        Label badgeOne = new Label();
        Label badgeTwo = new Label();
        Label badgeThree = new Label();

        Label optionOneTitle = new Label();
        Label optionTwoTitle = new Label();
        Label optionThreeTitle = new Label();

        Label optionOneSubtitle = new Label();
        Label optionTwoSubtitle = new Label();
        Label optionThreeSubtitle = new Label();

        setPrivateField(controller, "selectionRoot", selectionRoot);
        setPrivateField(controller, "backgroundVideo", backgroundVideo);

        setPrivateField(controller, "optionOne", optionOne);
        setPrivateField(controller, "optionTwo", optionTwo);
        setPrivateField(controller, "optionThree", optionThree);

        setPrivateField(controller, "badgeOne", badgeOne);
        setPrivateField(controller, "badgeTwo", badgeTwo);
        setPrivateField(controller, "badgeThree", badgeThree);

        setPrivateField(controller, "optionOneTitle", optionOneTitle);
        setPrivateField(controller, "optionTwoTitle", optionTwoTitle);
        setPrivateField(controller, "optionThreeTitle", optionThreeTitle);

        setPrivateField(controller, "optionOneSubtitle", optionOneSubtitle);
        setPrivateField(controller, "optionTwoSubtitle", optionTwoSubtitle);
        setPrivateField(controller, "optionThreeSubtitle", optionThreeSubtitle);


        return new ControllerFixture(
                controller,
                optionOneTitle,
                optionTwoTitle,
                optionThreeTitle,
                badgeOne,
                badgeTwo,
                badgeThree
        );
    }

    private void setPrivateField(Object target, String fieldName, Object value) {
        try {
            Field f = target.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set field: " + fieldName, e);
        }
    }

    private void invokePrivateNoArgs(Object target, String methodName) {
        try {
            Method m = target.getClass().getDeclaredMethod(methodName);
            m.setAccessible(true);
            m.invoke(target);
        } catch (Exception e) {
            throw new RuntimeException("Failed to invoke method: " + methodName, e);
        }
    }

    @Test
    void configure_SetsUpTimeRacingTexts() {
        ControllerFixture fixture = createFixture();
        ModeSelectionController controller = fixture.controller;

        controller.configure(
                HomeSelection.Mode.TIME_RACING,
                selection -> {},
                () -> {}
        );

        assertEquals("1 Minute Sprint", fixture.optionOneTitle.getText());
        assertEquals("3 Minute Rush", fixture.optionTwoTitle.getText());
        assertEquals("5 Minute Marathon", fixture.optionThreeTitle.getText());

        assertEquals("1M", fixture.badgeOne.getText());
        assertEquals("3M", fixture.badgeTwo.getText());
        assertEquals("5M", fixture.badgeThree.getText());
    }

    @Test
    void handleOptionOne_FiresSelectionWithCorrectOption() {
        ControllerFixture fixture = createFixture();
        ModeSelectionController controller = fixture.controller;

        AtomicReference<HomeSelection> selectionRef = new AtomicReference<>();

        controller.configure(
                HomeSelection.Mode.TIME_RACING,
                selectionRef::set,
                () -> {}
        );

        invokePrivateNoArgs(controller, "handleOptionOne");

        HomeSelection selection = selectionRef.get();
        assertNotNull(selection);
        assertEquals(HomeSelection.Mode.TIME_RACING, selection.mode());
        assertEquals(fixture.optionOneTitle.getText(), selection.option());
    }
}
```
