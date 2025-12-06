# CW2025/src Overview

## File Structure
```
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
|   |           |       GameConstants.java
|   |           |       MatrixOperations.java
|   |           |       
|   |           \---view
|   |                   BackgroundMusicManager.java
|   |                   BackgroundVideoManager.java
|   |                   BoardRenderer.java
|   |                   BoardVibrationEffect.java
|   |                   BombManager.java
|   |                   ChinaStageManager.java
|   |                   GameInputHandler.java
|   |                   GameLayoutManager.java
|   |                   GameLoop.java
|   |                   GameNotificationManager.java
|   |                   GameOverPanel.java
|   |                   GameScreenController.java
|   |                   GameSessionManager.java
|   |                   HomeController.java
|   |                   HomeSelection.java
|   |                   ModeSelectionController.java
|   |                   NextBricksRenderer.java
|   |                   NotificationManager.java
|   |                   NotificationPanel.java
|   |                   TimeAttackManager.java
|   |                   
|   \---resources
|       |   background_image.png
|       |   digital.ttf
|       |   gameLayout.fxml
|       |   homebackground.png
|       |   home_layout.fxml
|       |   selection_layout.fxml
|       |   window_style.css
|       |   
|       +---audio
|       |       explorechina.mp3
|       |       mainmusic.mp3
|       |       timeracing.mp3
|       |       
|       +---China
|       |       1.jpg
|       |       10.jpg
|       |       11.jpg
|       |       12.jpg
|       |       13.jpg
|       |       14.jpg
|       |       15.jpg
|       |       16.jpg
|       |       17.jpg
|       |       18.jpg
|       |       19.jpg
|       |       2.jpg
|       |       20.jpg
|       |       21.jpg
|       |       22.jpg
|       |       23.jpg
|       |       24.jpg
|       |       25.jpg
|       |       26.jpg
|       |       27.jpg
|       |       28.jpg
|       |       29.jpg
|       |       3.jpg
|       |       30.jpg
|       |       4.jpg
|       |       5.jpg
|       |       6.jpg
|       |       7.jpg
|       |       8.jpg
|       |       9.jpg
|       |       
|       +---images
|       |       bomb.png
|       |       IBrick.png
|       |       JBrick.png
|       |       LBrick.png
|       |       mainpage.mp4
|       |       OBrick.png
|       |       PlusBrick.png
|       |       SBrick.png
|       |       TBrick.png
|       |       ZBrick.png
|       |       
|       \---Time stages
|               1.jpg
|               3.jpg
|               5.jpg
|               
\---test
    \---java
        \---com
            \---comp2042
                +---controller
                |       GameControllerTest.java
                |       
                +---model
                |   |   ActivePieceTest.java
                |   |   BrickRotatorTest.java
                |   |   ScoreTest.java
                |   |   SimpleBoardTest.java
                |   |   
                |   \---brick
                |           BrickFactoryTest.java
                |           RandomBrickGeneratorTest.java
                |           
                \---util
                        MatrixOperationsTest.java
                        
```

## Code
### src/main/java/com/comp2042/controller/GameController.java
```java
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

public class GameController implements InputEventListener {

    private final Board board;
    private final ScoringStrategy scoringStrategy;

    public GameController(Board board) {
        this(board, new ClassicScoringStrategy());
    }

    public GameController(Board board, ScoringStrategy scoringStrategy) {
        this.board = board;
        this.scoringStrategy = scoringStrategy;
        board.createNewBrick();
    }

    @Override
    public DownData onDownEvent(MoveEvent event) {
        boolean canMove = board.moveBrickDown();
        ClearRow clearRow = null;

        if (!canMove) {
            clearRow = lockPieceAndHandleLineClear();
        } else {
            awardManualDownScore(event.getEventSource(), 1);
        }

        return new DownData(clearRow, board.getViewData());
    }

    @Override
    public DownData onHardDropEvent(MoveEvent event) {
        int steps = dropPieceToBottom();
        awardManualDownScore(event.getEventSource(), steps);

        ClearRow clearRow = lockPieceAndHandleLineClear();

        return new DownData(clearRow, board.getViewData());
    }

    @Override
    public ViewData onLeftEvent(MoveEvent event) {
        board.moveBrickLeft();
        return board.getViewData();
    }

    @Override
    public ViewData onRightEvent(MoveEvent event) {
        board.moveBrickRight();
        return board.getViewData();
    }

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

    private ClearRow lockPieceAndHandleLineClear() {
        board.mergeBrickToBackground();

        ClearRow clearRow = board.clearRows();
        int bonus = scoringStrategy.scoreForLineClear(clearRow);
        if (bonus > 0) {
            board.getScore().add(bonus);
        }

        board.createNewBrick();
        return clearRow;
    }
}
```

### src/main/java/com/comp2042/data/ChinaStageDescriptionProvider.java
```java
package com.comp2042.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    }

    private static final List<ChinaStage> STAGES = createStages();

    private ChinaStageDescriptionProvider() {
    }

    public static List<ChinaStage> getStages() {
        return STAGES;
    }

    private static List<ChinaStage> createStages() {
        List<ChinaStage> stages = new ArrayList<>();
        stages.add(new ChinaStage("Stage 1 - Si Chuan", "China/1.jpg", "Sichuan is a southwestern province famous for spicy food that makes your mouth tingle with numbing pepper. It is also the home of giant pandas, with large reserves where people can visit and learn about them. The landscape includes mountains, rivers, and stunning places like Jiuzhaigou with colorful lakes. Sichuan feels lively and full of flavor - both in nature and in its culture."));
        stages.add(new ChinaStage("Stage 2 - Shan Dong", "China/2.jpg", "Shandong is a coastal province in eastern China, known as the birthplace of Confucius. It is home to Mount Tai, a historic mountain important in Chinese culture. Cities like Qingdao are known for beaches and well-known local beer. Shandong has a mix of history, culture, and seaside living."));
        stages.add(new ChinaStage("Stage 3 - Zhe Jiang", "China/3.jpg", "Zhejiang is a coastal province known for strong business spirit and fast-growing companies. Its many islands create a unique ocean lifestyle found in few other places in China. Ancient water towns with canals and stone bridges give it a special charm. Zhejiang stands out by blending economic success with cultural beauty."));
        stages.add(new ChinaStage("Stage 4 - Bei Jing ", "China/4.jpg", "Beijing is China s capital and a city where ancient history stands right beside modern skyscrapers. It is home to landmarks like the Forbidden City and the Great Wall nearby - sights you cannot find anywhere else. People come here for art, technology, and big national events. Beijing feels powerful and cultural, all at the same time."));
        stages.add(new ChinaStage("Stage 5 - Hu Bei", "China/5.jpg", "Hubei is a province in central China shaped by the Yangtze River and its many lakes. Its capital, Wuhan, is famous for cherry blossoms in spring, turning parks and universities into pink tunnels of flowers. The region has a strong education scene, tasty hot dry noodles, and important transport connections. With both lively cities and peaceful nature, Hubei has a mix of energy and beauty."));
        stages.add(new ChinaStage("Stage 6 - Chong Qing", "China/6.jpg", "Chongqing is a major city in southwest China built among steep hills, so roads and buildings often rise up and down like a giant maze. It is famous for spicy hotpot and nighttime city views glowing above the rivers. The city s unique mountain city style means elevators, bridges, and cable cars are part of daily life. Chongqing feels bold, energetic, and full of heat - just like its food."));
        stages.add(new ChinaStage("Stage 7 - Ji Lin", "China/7.jpg", "Jilin is a northeastern province known for snowy winters and magical rime ice that covers trees along the Songhua River. It has rich forests and wildlife, making it an important home for animals like the Siberian tiger. In winter, people enjoy skiing and ice festivals. Jilin blends nature, cold-weather fun, and a strong northern culture."));
        stages.add(new ChinaStage("Stage 8 - Jiang Su", "China/8.jpg", "Jiangsu is a wealthy eastern Chinese province known for its smooth canals and elegant gardens. Many of its towns grew around waterways, giving daily life a calm river atmosphere. It has a strong economy, especially in tech and manufacturing, making it one of China s most developed regions. Jiangsu brings together culture, business, and peaceful scenery in a special way."));
        stages.add(new ChinaStage("Stage 9 - An Hui", "China/9.jpg", "Anhui is a province in eastern China known for its misty mountains and old villages with white walls and black roofs. Huangshan, one of China s most famous mountains, rises there with floating clouds and strange pine trees. The province is also known for Huizhou culture - beautiful calligraphy, tea, and historic architecture. Anhui feels peaceful and artistic, with scenery that looks like a classic painting."));
        stages.add(new ChinaStage("Stage 10 - He Nan", "China/10.jpg", "Henan is a central Chinese province often called one of the birthplaces of Chinese civilization. It has ancient cities and famous sites like the Shaolin Temple, where kung fu legends began. The Yellow River runs through it, shaping its long history. Henan stands out for its deep cultural roots and powerful historical stories."));
        stages.add(new ChinaStage("Stage 11 - Hei Long Jiang", "China/11.jpg", "Heilongjiang is China s northernmost province, known for freezing winters and huge snow festivals. Its capital, Harbin, is famous for Russian-style buildings and incredible ice sculptures that glow at night. The province has vast forests and wildlife, including rare animals like the Siberian tiger. Heilongjiang stands out for its strong winter culture and big, snowy adventures."));
        stages.add(new ChinaStage("Stage 12 - Hong Kong", "China/12.jpg", "Hong Kong is a vibrant coastal city known for its tall skyline and busy harbor. It blends Eastern and Western influences, so you will find dim sum restaurants next to neon-lit shopping streets. It is also home to one of Asia s top universities, giving the city a reputation for strong education and bright academic minds. Nature is close too - mountains and islands are just a short ride away. Hong Kong stands out for its fast energy, unique culture, beautiful sea views, and academic excellence."));
        stages.add(new ChinaStage("Stage 13 - Nei Mongol", "China/13.jpg", "Inner Mongolia is a northern region of China known for its wide grasslands and strong traditional Mongolian culture. Many people there enjoy horseback riding and celebrate colorful festivals. It also has large deserts, like the Gobi, with unique landscapes and wildlife. Inner Mongolia blends modern development with a lifestyle that is closely connected to nature."));
        stages.add(new ChinaStage("Stage 14 - Fujian", "China/14.jpg", "Fujian is a southeastern coastal province known for its tea-growing mountains and strong sea traditions. Unique round earthen houses called tulou can be found in its countryside, built by communities living together like a fortress. The province has beautiful coastlines and islands facing Taiwan. Fujian stands out for its mix of mountain tea culture, ocean life, and distinct architecture."));
        stages.add(new ChinaStage("Stage 15 - Shang Hai", "China/15.jpg", "Shanghai is a major coastal city known for its futuristic skyline along the Huangpu River. It mixes modern style with history - from towering skyscrapers to old streets in the Bund. The city is also a global financial hub where ideas, fashion, and business move fast. Shanghai stands out for its energy, international vibe, and bright nights that feel like they never end."));
        stages.add(new ChinaStage("Stage 16 - Xin Jiang", "China/16.jpg", "Xinjiang is a vast region in northwest China known for deserts, snowy mountains, and long ancient routes of the Silk Road. It is home to many cultures, especially the Muslim Uyghur community, whose music, dance, and foods like lamb kebabs and naan are part of daily life. The region s mosques and traditional bazaars show its unique cultural identity. Xinjiang feels adventurous and full of stories from different peoples meeting over centuries."));
        stages.add(new ChinaStage("Stage 17 - Tian Jing", "China/17.jpg", "Tianjin is a major port city in northern China, known for its riverside European-style buildings left from history. It has tasty local snacks like goubuli steamed buns that people line up to try. The city developed strong aerospace and industrial technology, helping drive China s modern industry. Tianjin stands out with its mix of old foreign influences, Chinese culture, and strong innovation."));
        stages.add(new ChinaStage("Stage 18 - Shaan Xi", "China/18.jpg", "Shaanxi is a central Chinese province known for Xi an, the ancient capital where the Terracotta Army guards the resting place of China s first emperor. The province sits at a key starting point of the historic Silk Road, connecting China to distant lands. Its cuisine, especially chewy biangbiang noodles, has a bold and hearty flavor. Shaanxi stands out for deep history, strong cultural roots, and food you will not forget."));
        stages.add(new ChinaStage("Stage 19 - Hai Nan", "China/19.jpg", "Hainan is China s southern island province, known for its warm beaches, palm trees, and blue ocean. It is a popular place for vacations, surfing, and fresh tropical fruits. The island also has volcanic parks and rainforests that show a different side of nature. With sunshine, nature, and island culture, Hainan feels like China s tropical getaway."));
        stages.add(new ChinaStage("Stage 20 - Shan Xi", "China/20.jpg", "Shanxi is a northern Chinese province known for its ancient architecture, including the well-preserved old city of Pingyao. It has deep coal resources, which helped shape its industry and economy. The province is famous for hand-pulled noodles and strong vinegar that locals add to many dishes. Shanxi stands out for its mix of hard-working industrial life and rich historical heritage."));
        stages.add(new ChinaStage("Stage 21 - Yunnan", "China/21.jpg", "Yunnan is a southwestern Chinese province known for its huge mix of ethnic cultures, each with its own festivals, clothes, and foods. Its landscapes range from snow-capped mountains to tropical rainforests, making every area feel different. The region grows some of China s best tea, like Pu er. Yunnan stands out for its incredible diversity in nature, people, and flavors."));
        stages.add(new ChinaStage("Stage 22 - He Bei", "China/22.jpg", "Hebei is a northern province that wraps around Beijing and Tianjin, connecting mountains, plains, and the Bohai Sea. It includes parts of the Great Wall, where you can hike along ancient stones with wide views. The province has a long history of martial arts and strong northern culture. Hebei stands out as a place balancing tradition, industry, and key locations near China s capital."));
        stages.add(new ChinaStage("Stage 23 - Tai Bei", "China/23.jpg", "Taipei is a lively city known for night markets filled with tasty street food like bubble tea and crispy chicken. Modern sights like Taipei 101 stand beside peaceful temples and old streets. The city is surrounded by green mountains, so hiking and hot springs are close to everyday life. Taipei stands out for its friendly atmosphere, great snacks, and fun mix of modern and traditional style."));
        stages.add(new ChinaStage("Stage 24 - Liao Ning", "China/24.jpg", "Liaoning is a northeastern province known for its strong industry, shipbuilding, and important ports along the Yellow Sea. It has rich history - including old Qing Dynasty sites where emperors once lived before moving to Beijing. Winters are cold, but people enjoy hot springs and warm comfort foods like barbecue and dumplings. Liaoning stands out for its mix of industrial power, northern culture, and historical heritage."));
        stages.add(new ChinaStage("Stage 25 - Guang Xi", "China/25.jpg", "Guangxi is a southern region of China famous for its amazing karst mountains and rivers that look like scenes from a painting. It is home to many ethnic groups, especially the Zhuang people, with colorful festivals and music. Popular places like Guilin and Yangshuo attract travelers who love nature and adventure. Guangxi stands out for its beautiful landscapes and diverse cultural traditions."));
        stages.add(new ChinaStage("Stage 26 - Xi Zang", "China/26.jpg", "Xizang is a high-altitude region in southwest China, often called the Roof of the World because of the Himalayas. It is known for Tibetan culture with prayer flags, monasteries, and unique festivals. The breathtaking scenery includes snowy mountains, clear lakes, and wide grasslands. Xizang stands out for its spiritual atmosphere and majestic natural beauty."));
        stages.add(new ChinaStage("Stage 27 - Guang Zhou", "China/27.jpg", "Guangzhou is a major southern city known for its Cantonese culture and delicious dim sum. Its long history of trade made it one of China s earliest international ports, so the city feels open and global. The Pearl River lights up at night with colorful city views. Guangzhou stands out for its food, fast development, and lively modern energy."));
        stages.add(new ChinaStage("Stage 28 - Gui Zhou", "China/28.jpg", "Guizhou is a southwestern province known for its dramatic mountains, waterfalls, and cool climate. Many ethnic groups live here, especially the Miao and Dong people, with unique clothing, music, and wooden village architecture. The famous Huangguoshu Waterfall shows off the province s powerful nature. Guizhou stands out for its rich cultural traditions and hidden scenic gems."));
        stages.add(new ChinaStage("Stage 29 - Hu Nan", "China/29.jpg", "Hunan is a central Chinese province famous for extremely spicy food that makes even chili lovers sweat. It has stunning landscapes like Zhangjiajie, with tall stone pillars that look like floating mountains. The region is also known for its lively culture and being the birthplace of several important historical figures. Hunan stands out for strong flavors, dramatic nature, and bold local spirit."));
        stages.add(new ChinaStage("Stage 30 - Jiang Xi", "China/30.jpg", "Jiangxi is a southeastern province known for its green mountains and calm lakes, especially the famous Poyang Lake full of migrating birds. It has a long history of porcelain making in Jingdezhen, often called the Porcelain Capital of the World. The region also grows high-quality tea in misty villages. Jiangxi stands out for its peaceful nature and traditional craftsmanship."));

        return Collections.unmodifiableList(stages);
    }
}
```

### src/main/java/com/comp2042/data/ClearRow.java
```java
package com.comp2042.data;

import com.comp2042.util.MatrixOperations;

public final class ClearRow {

    private final int linesRemoved;
    private final int[][] newMatrix;
    private final int scoreBonus;

    public ClearRow(int linesRemoved, int[][] newMatrix, int scoreBonus) {
        this.linesRemoved = linesRemoved;
        this.newMatrix = newMatrix;
        this.scoreBonus = scoreBonus;
    }

    public int getLinesRemoved() {
        return linesRemoved;
    }

    public int[][] getNewMatrix() {
        return MatrixOperations.copy(newMatrix);
    }

    public int getScoreBonus() {
        return scoreBonus;
    }
}
```

### src/main/java/com/comp2042/data/DownData.java
```java
package com.comp2042.data;

public final class DownData {
    private final ClearRow clearRow;
    private final ViewData viewData;

    public DownData(ClearRow clearRow, ViewData viewData) {
        this.clearRow = clearRow;
        this.viewData = viewData;
    }

    public ClearRow getClearRow() {
        return clearRow;
    }

    public ViewData getViewData() {
        return viewData;
    }
}
```

### src/main/java/com/comp2042/data/ViewData.java
```java
package com.comp2042.data;

import com.comp2042.util.MatrixOperations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ViewData {

    private final int[][] brickData;
    private final int xPosition;
    private final int yPosition;
    private final int ghostYPosition;
    private final List<int[][]> nextBricksData;

    public ViewData(int[][] brickData, int xPosition, int yPosition, int ghostYPosition, List<int[][]> nextBricksData) {
        this.brickData = brickData;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.ghostYPosition = ghostYPosition;
        this.nextBricksData = copyNextBricks(nextBricksData);
    }

    public int[][] getBrickData() {
        return MatrixOperations.copy(brickData);
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public int getGhostYPosition() {
        return ghostYPosition;
    }

    public List<int[][]> getNextBricksData() {
        return copyNextBricks(nextBricksData);
    }

    private List<int[][]> copyNextBricks(List<int[][]> source) {
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

### src/main/java/com/comp2042/event/EventSource.java
```java
package com.comp2042.event;

public enum EventSource {
    USER, THREAD
}
```

### src/main/java/com/comp2042/event/EventType.java
```java
package com.comp2042.event;

public enum EventType {
    DOWN, LEFT, RIGHT, ROTATE,HARD_DROP
}
```

### src/main/java/com/comp2042/event/InputEventListener.java
```java
package com.comp2042.event;

import com.comp2042.data.DownData;
import com.comp2042.data.ViewData;

public interface InputEventListener {

    DownData onDownEvent(MoveEvent event);

    ViewData onLeftEvent(MoveEvent event);

    ViewData onRightEvent(MoveEvent event);

    ViewData onRotateEvent(MoveEvent event);

    DownData onHardDropEvent(MoveEvent event);
}
```

### src/main/java/com/comp2042/event/MoveEvent.java
```java
package com.comp2042.event;

public final class MoveEvent {

    private final EventType eventType;
    private final EventSource eventSource;

    public MoveEvent(EventType eventType, EventSource eventSource) {
        this.eventType = eventType;
        this.eventSource = eventSource;
    }

    public EventType getEventType() {
        return eventType;
    }

    public EventSource getEventSource() {
        return eventSource;
    }
}
```

### src/main/java/com/comp2042/Main.java
```java
package com.comp2042;

import com.comp2042.controller.GameController;
import com.comp2042.model.Board;
import com.comp2042.model.SimpleBoard;
import com.comp2042.util.GameConstants;
import com.comp2042.view.HomeController;
import com.comp2042.view.HomeSelection;
import com.comp2042.view.GameScreenController;
import com.comp2042.view.ModeSelectionController;
import com.comp2042.view.BackgroundVideoManager;
import com.comp2042.view.BackgroundMusicManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.StageStyle;
import javafx.stage.Stage;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;

public class Main extends Application {

    private static final double HOME_UI_SCALE = 1.3;
    private static final double SELECTION_UI_SCALE = 1.3;
    private static final double GAME_UI_SCALE = 1.0; 

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        showHome(primaryStage);
    }

    private void showHome(Stage primaryStage) throws IOException {
        URL homeLocation = getClass().getClassLoader().getResource("home_layout.fxml");
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
                try {
                    launchGame(primaryStage, new HomeSelection(HomeSelection.Mode.COUNTRY_EXPLORE, "China"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }

            URL selectionLocation = getClass().getClassLoader().getResource("selection_layout.fxml");
            FXMLLoader selectionLoader = new FXMLLoader(selectionLocation);
            Parent selectionRoot = selectionLoader.load();
            ModeSelectionController controller = selectionLoader.getController();
            controller.configure(mode, selection -> {
                try {
                    launchGame(primaryStage, selection);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, () -> {
                try {
                    showHome(primaryStage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            applyScale(selectionRoot, SELECTION_UI_SCALE);
            setSceneAndMaximize(primaryStage, selectionRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void launchGame(Stage primaryStage, HomeSelection selection) throws Exception {

        Board board = new SimpleBoard(GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT);

        URL location = getClass().getClassLoader().getResource("gameLayout.fxml");
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
                () -> {
                    try {
                        showHome(primaryStage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        launchGame(primaryStage, selection);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
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
                case "1 Minute Sprint" -> 1;
                case "3 Minute Rush" -> 3;
                case "5 Minute Marathon" -> 5;
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
                    GameConstants.initialWindowWidth(),
                    GameConstants.initialWindowHeight()
            );
            stage.setScene(scene);
            stage.setMinWidth(GameConstants.initialWindowWidth());
            stage.setMinHeight(GameConstants.initialWindowHeight());

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

### src/main/java/com/comp2042/model/ActivePiece.java
```java
package com.comp2042.model;

import com.comp2042.data.ViewData;
import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.NextShapeInfo;
import com.comp2042.util.GameConstants;
import com.comp2042.util.MatrixOperations;

import java.util.ArrayList;
import java.util.List;

public class ActivePiece {

    private final BrickRotator brickRotator = new BrickRotator();
    private int x;
    private int y;

    public void spawn(Brick brick) {
        brickRotator.setBrick(brick);
        this.x = GameConstants.SPAWN_X;
        this.y = GameConstants.SPAWN_Y;
    }

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

    public boolean rotateLeft(int[][] boardMatrix) {
        NextShapeInfo nextShape = brickRotator.getNextShape();

        if (collides(boardMatrix, nextShape.getShape(), x, y)) {
            return false;
        }

        brickRotator.setCurrentShape(nextShape.getPosition());
        return true;
    }

    public int[][] getShape() {
        return brickRotator.getCurrentShape();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ViewData toViewData(List<Brick> nextBricks, int[][] boardMatrix) {
        List<int[][]> previews = buildPreviews(nextBricks);
        int ghostY = calculateGhostY(boardMatrix);
        return new ViewData(getShape(), x, y, ghostY, previews);
    }

private boolean collides(int[][] boardMatrix, int[][] shape, int targetX, int targetY) {
        return MatrixOperations.intersect(boardMatrix, shape, targetX, targetY);
    }

    private int calculateGhostY(int[][] boardMatrix) {
        int ghostY = y;
        int[][] shape = brickRotator.getCurrentShape();

        while (!collides(boardMatrix, shape, x, ghostY + 1)) {
            ghostY++;
        }

        return ghostY;
    }

    private List<int[][]> buildPreviews(List<Brick> nextBricks) {
        List<int[][]> previews = new ArrayList<>();
        if (nextBricks == null) {
            return previews;
        }

        for (Brick brick : nextBricks) {
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

### src/main/java/com/comp2042/model/Board.java
```java
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

    BooleanProperty isGameOverProperty();
    ObjectProperty<int[][]> boardMatrixProperty();
    IntegerProperty scoreProperty();
    void explodeBomb(int centerX, int centerY);
}

```

### src/main/java/com/comp2042/model/brick/AbstractBrick.java
```java
package com.comp2042.model.brick;

import com.comp2042.util.MatrixOperations;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBrick implements Brick {

    protected final List<int[][]> brickMatrix = new ArrayList<>();

    protected AbstractBrick() {
        initializeShapes();
    }

    protected abstract void initializeShapes();

    @Override
    public List<int[][]> getShapeMatrix() {
        return MatrixOperations.deepCopyList(brickMatrix);
    }
}

```

### src/main/java/com/comp2042/model/brick/Brick.java
```java
package com.comp2042.model.brick;

import java.util.List;

public interface Brick {
    List<int[][]> getShapeMatrix();
}

```

### src/main/java/com/comp2042/model/brick/BrickFactory.java
```java
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
```

### src/main/java/com/comp2042/model/brick/BrickGenerator.java
```java
package com.comp2042.model.brick;

import java.util.List;

public interface BrickGenerator {

    Brick getBrick();

    Brick getNextBrick();

    List<Brick> preview(int count);
}
```

### src/main/java/com/comp2042/model/brick/IBrick.java
```java
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

### src/main/java/com/comp2042/model/brick/JBrick.java
```java
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

### src/main/java/com/comp2042/model/brick/LBrick.java
```java
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

### src/main/java/com/comp2042/model/brick/NextShapeInfo.java
```java
package com.comp2042.model.brick;

import com.comp2042.util.MatrixOperations;

public final class NextShapeInfo {

    private final int[][] shape;
    private final int position;

    public NextShapeInfo(final int[][] shape, final int position) {
        this.shape = shape;
        this.position = position;
    }

    public int[][] getShape() {
        return MatrixOperations.copy(shape);
    }

    public int getPosition() {
        return position;
    }
}

```

### src/main/java/com/comp2042/model/brick/OBrick.java
```java
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

### src/main/java/com/comp2042/model/brick/PlusBrick.java
```java
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

### src/main/java/com/comp2042/model/brick/RandomBrickGenerator.java
```java
package com.comp2042.model.brick;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBrickGenerator implements BrickGenerator {

    private final Random random;
    private final List<Brick> brickQueue = new ArrayList<>();
    private int lastBrickPoolSize;

    public RandomBrickGenerator() {
        this(new Random());
    }

    RandomBrickGenerator(Random random) {
        this.random = random;
        initializeQueue();
    }

    private void initializeQueue() {
        lastBrickPoolSize = BrickFactory.getBrickCount();
        for (int i = 0; i < 10; i++) {
            brickQueue.add(createRandomBrick());
        }
    }

    private void refreshIfPoolChanged() {
        int currentPoolSize = BrickFactory.getBrickCount();
        if (currentPoolSize != lastBrickPoolSize) {
            brickQueue.clear();
            lastBrickPoolSize = currentPoolSize;
            initializeQueue();
        }
    }

    @Override
    public Brick getBrick() {
        refreshIfPoolChanged();
        if (brickQueue.isEmpty()) {
            initializeQueue();
        }
        Brick nextBrick = brickQueue.remove(0);
        brickQueue.add(createRandomBrick());
        return nextBrick;
    }

    @Override
    public Brick getNextBrick() {
        refreshIfPoolChanged();
        if (brickQueue.isEmpty()) {
            initializeQueue();
        }
        return brickQueue.get(0);
    }

    private Brick createRandomBrick() {
        int brickCount = BrickFactory.getBrickCount();
        int id = random.nextInt(brickCount);
        return BrickFactory.createBrick(id);
    }

    @Override
    public List<Brick> preview(int count) {
        if (brickQueue.isEmpty()) {
            initializeQueue();
        }
        List<Brick> previewBricks = new ArrayList<>();
        for (int i = 0; i < count && i < brickQueue.size(); i++) {
            previewBricks.add(brickQueue.get(i));
        }
        return previewBricks;
    }
}
```

### src/main/java/com/comp2042/model/brick/SBrick.java
```java
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

### src/main/java/com/comp2042/model/brick/TBrick.java
```java
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

### src/main/java/com/comp2042/model/brick/ZBrick.java
```java
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

### src/main/java/com/comp2042/model/BrickRotator.java
```java
package com.comp2042.model;

import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.NextShapeInfo;

public class BrickRotator {

    private Brick brick;
    private int currentShape = 0;

    public NextShapeInfo getNextShape() {
        int nextShape = currentShape;
        nextShape = (++nextShape) % brick.getShapeMatrix().size();
        return new NextShapeInfo(brick.getShapeMatrix().get(nextShape), nextShape);
    }

    public int[][] getCurrentShape() {
        return brick.getShapeMatrix().get(currentShape);
    }

    public void setCurrentShape(int currentShape) {
        this.currentShape = currentShape;
    }

    public void setBrick(Brick brick) {
        this.brick = brick;
        currentShape = 0;
    }

}
```

### src/main/java/com/comp2042/model/Score.java
```java
package com.comp2042.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public final class Score {

    private final IntegerProperty score = new SimpleIntegerProperty(0);

    public IntegerProperty scoreProperty() {
        return score;
    }

    public void add(int i){
        score.setValue(score.getValue() + i);
    }

    public void reset() {
        score.setValue(0);
    }
}
```

### src/main/java/com/comp2042/model/scoring/ClassicScoringStrategy.java
```java
package com.comp2042.model.scoring;

import com.comp2042.data.ClearRow;
import com.comp2042.util.GameConstants;

public class ClassicScoringStrategy implements ScoringStrategy {

    @Override
    public int scoreForManualDrop(int steps) {
        if (steps <= 0) {
            return 0;
        }
        return steps * GameConstants.MANUAL_DOWN_SCORE;
    }

    @Override
    public int scoreForLineClear(ClearRow clearRow) {
        if (clearRow == null || clearRow.getLinesRemoved() <= 0) {
            return 0;
        }

        return clearRow.getScoreBonus();
    }
}
```

### src/main/java/com/comp2042/model/scoring/ScoringStrategy.java
```java
package com.comp2042.model.scoring;

import com.comp2042.data.ClearRow;

public interface ScoringStrategy {

    int scoreForManualDrop(int steps);

    int scoreForLineClear(ClearRow clearRow);
}
```

### src/main/java/com/comp2042/model/SimpleBoard.java
```java
package com.comp2042.model;

import com.comp2042.data.ClearRow;
import com.comp2042.data.ViewData;
import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.BrickGenerator;
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
    private final ActivePiece activePiece;
    private final Score score;

    private final BooleanProperty isGameOver = new SimpleBooleanProperty(false);
    private final ObjectProperty<int[][]> boardMatrix = new SimpleObjectProperty<>();

    private int[][] currentGameMatrix;

    public SimpleBoard(int width, int height) {
        this(width, height, new RandomBrickGenerator());
    }

    public SimpleBoard(int width, int height, BrickGenerator brickGenerator) {
        this.width = width;
        this.height = height;
        this.brickGenerator = brickGenerator;
        this.activePiece = new ActivePiece();
        this.score = new Score();

updateBoardMatrix(new int[height][width]);
    }

    @Override
    public BooleanProperty isGameOverProperty() {
        return isGameOver;
    }

    @Override
    public ObjectProperty<int[][]> boardMatrixProperty() {
        return boardMatrix;
    }

    @Override
    public IntegerProperty scoreProperty() {
        return score.scoreProperty();
    }

    @Override
    public boolean moveBrickDown() {
        return activePiece.move(currentGameMatrix, 0, 1);
    }

    @Override
    public boolean moveBrickLeft() {
        return activePiece.move(currentGameMatrix, -1, 0);
    }

    @Override
    public boolean moveBrickRight() {
        return activePiece.move(currentGameMatrix, 1, 0);
    }

    @Override
    public boolean rotateLeftBrick() {
        return activePiece.rotateLeft(currentGameMatrix);
    }

    @Override
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
    public int[][] getBoardMatrix() {

        return currentGameMatrix;
    }

    @Override
    public ViewData getViewData() {
        return activePiece.toViewData(
                brickGenerator.preview(GameConstants.NEXT_PREVIEW_COUNT),
                currentGameMatrix
        );
    }

    @Override
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
    public ClearRow clearRows() {
        ClearRow clearRow = MatrixOperations.checkRemoving(currentGameMatrix);
        updateBoardMatrix(clearRow.getNewMatrix());
        return clearRow;
    }

    @Override
    public Score getScore() {
        return score;
    }

    @Override
    public void explodeBomb(int centerX, int centerY) {
        int[][] exploded = MatrixOperations.explodeBomb(currentGameMatrix, centerX, centerY);
        updateBoardMatrix(exploded);
        ClearRow clearRow = MatrixOperations.checkRemoving(exploded);
        updateBoardMatrix(clearRow.getNewMatrix());
    }

private void updateBoardMatrix(int[][] newMatrix) {
        this.currentGameMatrix = newMatrix;
        this.boardMatrix.set(newMatrix);
    }
}
```

### src/main/java/com/comp2042/util/BlockTextureProvider.java
```java
package com.comp2042.util;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public final class BlockTextureProvider {

    private static final int IMAGE_PADDING = 4;
    private static final Map<Integer, Paint> PATTERNS = new HashMap<>();
    private static final String[] IMAGE_NAMES = {
            null, "IBrick", "JBrick", "LBrick", "OBrick", "SBrick", "TBrick", "ZBrick", "PlusBrick"
    };

    static {
        PATTERNS.put(0, Color.TRANSPARENT);
        for (int id = 1; id < IMAGE_NAMES.length; id++) {
            PATTERNS.put(id, loadPattern(id));
        }
    }

    private static Paint loadPattern(int id) {
        String fileName = IMAGE_NAMES[id];
        if (fileName == null) {
            return Color.TRANSPARENT;
        }
        String path = String.format("images/%s.png", fileName);
        URL resource = BlockTextureProvider.class.getClassLoader().getResource(path);
        if (resource == null) {
            return Color.GRAY;
        }
        Image image = new Image(resource.toExternalForm());

        int targetSize = GameConstants.BRICK_SIZE;
        int imgWidth = (int) image.getWidth();
        int imgHeight = (int) image.getHeight();

        if (imgWidth > targetSize && imgHeight > targetSize) {
            PixelReader reader = image.getPixelReader();
            int cropWidth = imgWidth - IMAGE_PADDING * 2;
            int cropHeight = imgHeight - IMAGE_PADDING * 2;
            WritableImage cropped = new WritableImage(reader, IMAGE_PADDING, IMAGE_PADDING, cropWidth, cropHeight);
            return new ImagePattern(cropped);
        }

        return new ImagePattern(image);
    }

    public static Paint getPattern(int id) {
        return PATTERNS.getOrDefault(id, Color.TRANSPARENT);
    }

    private BlockTextureProvider() {
    }
}

```

### src/main/java/com/comp2042/util/GameConstants.java
```java
package com.comp2042.util;

public final class GameConstants {

    public static final int BOARD_HEIGHT = 23;
    public static final int BOARD_WIDTH = 10;
    public static final int GAME_TICK_MS = 400;
    public static final int BRICK_SIZE = 28;
    public static final double GRID_GAP = 2.5;
    public static final int NEXT_PREVIEW_COUNT = 3;
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
    public static final int SCORE_PER_LINE = 50;
    public static final int SPAWN_X = 4;
    public static final int SPAWN_Y = 0;
    public static final int HIDDEN_BUFFER_ROWS = 2;
    public static final int BRICK_ARC_SIZE = 10;
    public static final int MANUAL_DOWN_SCORE = 1;
    public static final int MIN_GAME_TICK_MS = 120;
    public static final int POINTS_PER_CHINA_STAGE = 200;
    public static final int POINTS_PER_BOMB = 1000;
    public static final int CHINA_STAGE_SPEED_STEP = 10;


    public static int visibleRows() {
        return BOARD_HEIGHT - HIDDEN_BUFFER_ROWS;
    }

    public static double brickStep() {
        return BRICK_SIZE + GRID_GAP;
    }

    public static double boardPixelWidth() {
        return BOARD_WIDTH * brickStep();
    }

    public static double boardPixelHeight() {
        int visibleRows = visibleRows();
        return visibleRows * brickStep();
    }

    public static double gridContentWidth() {
        return boardPixelWidth();
    }

    public static double gridContentHeight() {
        return boardPixelHeight();
    }

    public static double gridCenterOffsetX() {
        return 0;
    }

    public static double gridCenterOffsetY() {
        return (boardPixelHeight() - gridContentHeight()) / 2;
    }

    public static double brickPanelYOffset() {
        return -HIDDEN_BUFFER_ROWS * brickStep();
    }

    public static double boardAreaWidth() {
        return boardPixelWidth() + BOARD_FRAME_THICKNESS * 2;
    }

    public static double boardAreaHeight() {
        return boardPixelHeight() + BOARD_FRAME_THICKNESS * 2;
    }

    public static double contentWidth() {
        return boardAreaWidth() + PANEL_GAP + SIDE_PANEL_WIDTH;
    }

    public static double minimumCenteredWindowWidth() {
        return boardAreaWidth() + 2 * (SIDE_PANEL_WIDTH + PANEL_GAP);
    }

    public static double notificationPanelY() {
        return BOARD_TOP_PADDING + SIDE_PANEL_PADDING;
    }

    public static double initialWindowWidth() {
        return Math.max(
                contentWidth() + 2 * SIDE_PANEL_PADDING,
                minimumCenteredWindowWidth()
        );
    }

    public static double initialWindowHeight() {
        return BOARD_TOP_PADDING + boardAreaHeight() + BOTTOM_PADDING;
    }

    private GameConstants() {
    }
}
```

### src/main/java/com/comp2042/util/MatrixOperations.java
```java
package com.comp2042.util;

import com.comp2042.data.ClearRow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public final class MatrixOperations {

    private MatrixOperations() {

    }

    public static boolean intersect(final int[][] matrix, final int[][] brick, int x, int y) {
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

    public static int[][] merge(final int[][] matrix, final int[][] brick, int x, int y) {
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

    public static int[][] copy(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int row = 0; row < original.length; row++) {
            int[] sourceRow = original[row];
            copy[row] = new int[sourceRow.length];
            System.arraycopy(sourceRow, 0, copy[row], 0, sourceRow.length);
        }
        return copy;
    }

    public static ClearRow checkRemoving(final int[][] matrix) {
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

            currentMatrix = newMatrix;
        }

        int scoreBonus = GameConstants.SCORE_PER_LINE * totalLinesRemoved * totalLinesRemoved;

        return new ClearRow(totalLinesRemoved, currentMatrix, scoreBonus);
    }

    public static List<int[][]> deepCopyList(List<int[][]> list) {
        return list.stream()
                .map(MatrixOperations::copy)
                .collect(Collectors.toList());
    }

    private static boolean isOutOfBounds(int[][] matrix, int x, int y) {
        return x < 0
                || y < 0
                || y >= matrix.length
                || x >= matrix[y].length;
    }

    private static boolean isRowFull(int[] row) {
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

    public static int[][] explodeBomb(final int[][] matrix, int centerX, int centerY) {
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
}
```

### src/main/java/com/comp2042/view/BackgroundMusicManager.java
```java
package com.comp2042.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public final class BackgroundMusicManager {

    private static MediaPlayer currentPlayer;
    private static String currentTrack;
    private static double volume = 0.6;

    private BackgroundMusicManager() {
    }

    public static void playMainMusic() {
        playLoop("audio/mainmusic.mp3");
    }

    public static void playExploreChinaMusic() {
        playLoop("audio/explorechina.mp3");
    }

    public static void playTimeRacingMusic() {
        playLoop("audio/timeracing.mp3");
    }

    public static void stop() {
        if (currentPlayer != null) {
            currentPlayer.stop();
            currentPlayer.dispose();
            currentPlayer = null;
            currentTrack = null;
        }
    }

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

### src/main/java/com/comp2042/view/BackgroundVideoManager.java
```java
package com.comp2042.view;

import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;

/**
 * Provides a shared background video player so we don't recreate it
 * every time the user switches screens.
 */
public final class BackgroundVideoManager {

    private static MediaPlayer sharedPlayer;

    private BackgroundVideoManager() {
    }

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

    public static void dispose() {
        if (sharedPlayer != null) {
            sharedPlayer.stop();
            sharedPlayer.dispose();
            sharedPlayer = null;
        }
    }

    private static MediaPlayer getOrCreatePlayer() {
        if (sharedPlayer == null) {
            URL videoUrl = BackgroundVideoManager.class.getClassLoader().getResource("images/mainpage.mp4");
            if (videoUrl == null) {
                return null;
            }

            Media media = new Media(videoUrl.toExternalForm());
            sharedPlayer = new MediaPlayer(media);
            sharedPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            sharedPlayer.setAutoPlay(true);
            sharedPlayer.setMute(true);
            sharedPlayer.setOnError(() -> System.err.println("Background video error: " + sharedPlayer.getError()));
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

### src/main/java/com/comp2042/view/BoardRenderer.java
```java
package com.comp2042.view;

import com.comp2042.data.ViewData;
import com.comp2042.util.BlockTextureProvider;
import com.comp2042.util.GameConstants;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class BoardRenderer {

    private final GridPane gamePanel;
    private final GridPane brickPanel;
    private final Pane ghostPane;
    private final Pane gridLinesPane;

    private Rectangle[][] displayMatrix;
    private Rectangle[][] activeRectangles;

    public BoardRenderer(GridPane gamePanel,
                         GridPane brickPanel,
                         Pane ghostPane,
                         Pane gridLinesPane) {
        this.gamePanel = gamePanel;
        this.brickPanel = brickPanel;
        this.ghostPane = ghostPane;
        this.gridLinesPane = gridLinesPane;

        if (this.gamePanel != null) {
            this.gamePanel.setAlignment(Pos.CENTER);
        }

        if (this.brickPanel != null) {
            this.brickPanel.setHgap(GameConstants.GRID_GAP);
            this.brickPanel.setVgap(GameConstants.GRID_GAP);
        }
    }

public void initialiseBoard(int[][] boardMatrix, ViewData viewData) {
        createBackgroundCells(boardMatrix);
        createActiveBrick(viewData.getBrickData());
        updateBrickPosition(viewData);
        drawGhost(viewData);
        redrawGridLines();
    }

public void refreshBackground(int[][] boardMatrix) {
        if (displayMatrix == null) {
            return;
        }

        for (int i = GameConstants.HIDDEN_BUFFER_ROWS; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                setRectangleData(boardMatrix[i][j], displayMatrix[i][j]);
            }
        }
    }

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

public void redrawGridLines() {
        if (gridLinesPane == null) {
            return;
        }

        gridLinesPane.getChildren().clear();

        double gridWidth = GameConstants.boardPixelWidth();
        double gridHeight = GameConstants.boardPixelHeight();
        double step = GameConstants.brickStep();
        int visibleRows = GameConstants.visibleRows();
        int cols = GameConstants.BOARD_WIDTH;

        Color lineColor = Color.rgb(60, 60, 80, 0.6);

        for (int col = 0; col <= cols; col++) {
            double x = col * step;
            Line verticalLine = new Line(x, 0, x, gridHeight);
            verticalLine.setStroke(lineColor);
            verticalLine.setStrokeWidth(1);
            gridLinesPane.getChildren().add(verticalLine);
        }

        for (int row = 0; row <= visibleRows; row++) {
            double y = row * step;
            Line horizontalLine = new Line(0, y, gridWidth, y);
            horizontalLine.setStroke(lineColor);
            horizontalLine.setStrokeWidth(1);
            gridLinesPane.getChildren().add(horizontalLine);
        }

        gridLinesPane.setPrefSize(gridWidth, gridHeight);
        gridLinesPane.setMinSize(gridWidth, gridHeight);
        gridLinesPane.setMaxSize(gridWidth, gridHeight);
    }

private void createBackgroundCells(int[][] boardMatrix) {
        if (gamePanel == null) {
            return;
        }

        gamePanel.getChildren().clear();
        int rows = boardMatrix.length;
        int cols = boardMatrix[0].length;

        displayMatrix = new Rectangle[rows][cols];

        for (int i = GameConstants.HIDDEN_BUFFER_ROWS; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Rectangle rectangle = new Rectangle(GameConstants.BRICK_SIZE, GameConstants.BRICK_SIZE);
                rectangle.setFill(Color.TRANSPARENT);
                displayMatrix[i][j] = rectangle;
                gamePanel.add(rectangle, j, i - GameConstants.HIDDEN_BUFFER_ROWS);
            }
        }
    }

    private void createActiveBrick(int[][] brickData) {
        if (brickPanel == null) {
            return;
        }

        brickPanel.getChildren().clear();

        int rows = brickData.length;
        int cols = brickData[0].length;

        activeRectangles = new Rectangle[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Rectangle rectangle = new Rectangle(GameConstants.BRICK_SIZE, GameConstants.BRICK_SIZE);
                setRectangleData(brickData[row][col], rectangle);
                activeRectangles[row][col] = rectangle;
                brickPanel.add(rectangle, col, row);
            }
        }
    }

    private void updateBrickPosition(ViewData brick) {
        if (gamePanel == null || brickPanel == null) {
            return;
        }

        double boardOriginX = gamePanel.getLayoutX();
        double boardOriginY = gamePanel.getLayoutY();

        brickPanel.setLayoutX(
                boardOriginX + brick.getXPosition() * GameConstants.brickStep()
        );
        brickPanel.setLayoutY(
                GameConstants.brickPanelYOffset()
                        + boardOriginY
                        + brick.getYPosition() * GameConstants.brickStep()
        );
    }

    private void drawGhost(ViewData brick) {
        if (ghostPane == null || gamePanel == null) {
            return;
        }

        ghostPane.getChildren().clear();

        int[][] brickData = brick.getBrickData();
        int ghostY = brick.getGhostYPosition();
        int brickX = brick.getXPosition();

        if (ghostY == brick.getYPosition()) {
            return;
        }

        double step = GameConstants.brickStep();
        double brickSize = GameConstants.BRICK_SIZE;
        Color ghostOutline = Color.rgb(180, 220, 255, 0.65);
        Color ghostDetail = Color.rgb(180, 220, 255, 0.4);
        double strokeWidth = 1.5;
        double cornerRadius = Math.max(2, GameConstants.BRICK_ARC_SIZE - 4);
        double inset = strokeWidth * 0.5; // let diamond touch the square stroke from inside

        for (int row = 0; row < brickData.length; row++) {
            for (int col = 0; col < brickData[row].length; col++) {
                if (brickData[row][col] == 0) {
                    continue;
                }

                double cellX = col * step;
                double cellY = row * step;

                Rectangle outline = new Rectangle(cellX, cellY, brickSize, brickSize);
                outline.setFill(Color.TRANSPARENT);
                outline.setStroke(ghostOutline);
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
                diamond.setStroke(ghostDetail);
                diamond.setStrokeWidth(strokeWidth);
                ghostPane.getChildren().add(diamond);
            }
        }

        double boardOriginX = gamePanel.getLayoutX();
        double boardOriginY = gamePanel.getLayoutY();

        ghostPane.setLayoutX(boardOriginX + brickX * step);
        ghostPane.setLayoutY(
                GameConstants.brickPanelYOffset()
                        + boardOriginY
                        + ghostY * step
        );
    }

    private void setRectangleData(int color, Rectangle rectangle) {
        rectangle.setFill(BlockTextureProvider.getPattern(color));
        rectangle.setArcHeight(GameConstants.BRICK_ARC_SIZE);
        rectangle.setArcWidth(GameConstants.BRICK_ARC_SIZE);
    }
}
```

### src/main/java/com/comp2042/view/BoardVibrationEffect.java
```java
package com.comp2042.view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardVibrationEffect {

    private final List<Node> targets = new ArrayList<>();
    private Timeline vibrationTimeline;

    public BoardVibrationEffect(Node... nodes) {
        if (nodes != null) {
            Arrays.stream(nodes)
                    .filter(node -> node != null)
                    .forEach(targets::add);
        }
    }

    public void vibrate() {
        if (targets.isEmpty()) {
            return;
        }

        if (vibrationTimeline != null && vibrationTimeline.getStatus() == Animation.Status.RUNNING) {
            vibrationTimeline.stop();
            resetTargets();
        }

        vibrationTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, keyValuesForTargets(0)),
                new KeyFrame(Duration.millis(30), keyValuesForTargets(8)),
                new KeyFrame(Duration.millis(60), keyValuesForTargets(-6)),
                new KeyFrame(Duration.millis(90), keyValuesForTargets(5)),
                new KeyFrame(Duration.millis(120), keyValuesForTargets(-3)),
                new KeyFrame(Duration.millis(150), keyValuesForTargets(0))
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

### src/main/java/com/comp2042/view/BombManager.java
```java
package com.comp2042.view;

import com.comp2042.model.Board;
import com.comp2042.util.GameConstants;
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

public final class BombManager {

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

    private final double gridStep = GameConstants.brickStep();

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
        this.bombCount = bombCount;
        this.isGameOver = isGameOver;
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
            bombToolbar.setOpacity(0.5);
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
            bombToolbar.setOpacity(bombCount.get() > 0 ? 1.0 : 0.4);
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
                GameConstants.gridContentWidth(),
                GameConstants.gridContentHeight()
        );

        if (gamePanel != null) {
            double offsetX = GameConstants.gridCenterOffsetX();
            double offsetY = GameConstants.gridCenterOffsetY();
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

                if (cellX >= 0 && cellX < GameConstants.BOARD_WIDTH &&
                        cellY >= 0 && cellY < GameConstants.visibleRows()) {

                    Rectangle highlight = new Rectangle(
                            cellX * gridStep,
                            cellY * gridStep,
                            GameConstants.BRICK_SIZE,
                            GameConstants.BRICK_SIZE
                    );
                    highlight.setFill(Color.rgb(255, 100, 50, 0.4));
                    highlight.setStroke(Color.rgb(255, 140, 0, 0.8));
                    highlight.setStrokeWidth(2);
                    highlight.setArcWidth(GameConstants.BRICK_ARC_SIZE);
                    highlight.setArcHeight(GameConstants.BRICK_ARC_SIZE);
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

        double offsetX = GameConstants.gridCenterOffsetX();
        double offsetY = GameConstants.gridCenterOffsetY();
        double adjustedX = local.getX() - offsetX;
        double adjustedY = local.getY() - offsetY;

        int gridX = (int) (adjustedX / gridStep);
        int gridY = (int) (adjustedY / gridStep);

        if (gridX >= 0 && gridX < GameConstants.BOARD_WIDTH &&
                gridY >= 0 && gridY < GameConstants.visibleRows()) {
            return new int[]{gridX, gridY};
        }
        return null;
    }

    private void placeBombAt(int gridX, int gridY) {
        if (board == null || bombCount.get() <= 0) {
            return;
        }

        int actualY = gridY + GameConstants.HIDDEN_BUFFER_ROWS;
        board.explodeBomb(gridX, actualY);

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

        double opacity = (count > 0) ? 1.0 : 0.4;

        if (bombToolbar != null) {
            bombToolbar.setOpacity(opacity);
        }
        if (bombEmoji != null) {
            bombEmoji.setOpacity(opacity);
        }
    }
}
```

### src/main/java/com/comp2042/view/ChinaStageManager.java
```java
package com.comp2042.view;

import com.comp2042.data.ChinaStageDescriptionProvider;
import com.comp2042.util.GameConstants;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public final class ChinaStageManager {

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

        hideDescriptionBox();
    }

    public void enableExploreMode() {
        if (stages.isEmpty()) {
            enabled = false;
            hideDescriptionBox();
            return;
        }
        enabled = true;
        currentStageIndex = 0;
        applyStage(currentStageIndex);
    }

    public void disableExploreMode() {
        enabled = false;
        hideDescriptionBox();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void handleScoreChanged(int newScore) {
        if (!enabled || stages.isEmpty()) {
            return;
        }

        int targetIndex = Math.min(
                newScore / GameConstants.POINTS_PER_CHINA_STAGE,
                stages.size() - 1
        );

        if (targetIndex > currentStageIndex) {
            applyStage(targetIndex);
        }

        checkCompletion(newScore);
    }

    private void applyStage(int stageIndex) {
        if (!enabled || stages.isEmpty()) {
            return;
        }

        int safeIndex = Math.min(stageIndex, stages.size() - 1);
        currentStageIndex = safeIndex;

        ChinaStageDescriptionProvider.ChinaStage stage = stages.get(safeIndex);

        if (backgroundApplier != null) {
            backgroundApplier.accept(stage.getBackgroundResource());
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
                GameConstants.MIN_GAME_TICK_MS,
                GameConstants.GAME_TICK_MS - (safeIndex * GameConstants.CHINA_STAGE_SPEED_STEP)
        );
        if (gameTickUpdater != null) {
            gameTickUpdater.accept(newTick);
        }
    }

    private void checkCompletion(int score) {
        if (!enabled || stages.isEmpty()) {
            return;
        }

        boolean atFinalStage = currentStageIndex >= stages.size() - 1;
        int completionScore = GameConstants.POINTS_PER_CHINA_STAGE * stages.size();

        if (atFinalStage && score >= completionScore && onJourneyCompleted != null) {
            onJourneyCompleted.run();
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

### src/main/java/com/comp2042/view/GameInputHandler.java
```java
package com.comp2042.view;

import com.comp2042.event.EventSource;
import com.comp2042.event.EventType;
import com.comp2042.event.MoveEvent;
import javafx.beans.property.BooleanProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.function.Consumer;

public final class GameInputHandler {

    private final BooleanProperty isPause;
    private final BooleanProperty isGameOver;

    private final Consumer<MoveEvent> leftHandler;
    private final Consumer<MoveEvent> rightHandler;
    private final Consumer<MoveEvent> rotateHandler;
    private final Consumer<MoveEvent> downHandler;
    private final Consumer<MoveEvent> hardDropHandler;

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

### src/main/java/com/comp2042/view/GameLayoutManager.java
```java
package com.comp2042.view;

import com.comp2042.util.GameConstants;
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

public final class GameLayoutManager {

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

        this.rootPane = rootPane;
        this.gameBoard = gameBoard;
        this.gamePanel = gamePanel;
        this.gridLinesPane = gridLinesPane;
        this.sidePanel = sidePanel;
        this.timerBox = timerBox;
        this.nextBricksContainer = nextBricksContainer;
        this.nextBricksList = nextBricksList;
        this.notificationGroup = notificationGroup;
        this.boardRenderer = boardRenderer;
        this.bombToolbar = bombToolbar;
        this.chinaDescriptionBox = chinaDescriptionBox;
    }

    public void applyInitialLayout() {
        double boardWidth = GameConstants.boardPixelWidth();
        double boardHeight = GameConstants.boardPixelHeight();

        if (gamePanel != null) {
            gamePanel.setHgap(GameConstants.GRID_GAP);
            gamePanel.setVgap(GameConstants.GRID_GAP);
            gamePanel.setPrefSize(boardWidth, boardHeight);
            gamePanel.setMinSize(boardWidth, boardHeight);
            gamePanel.setMaxSize(boardWidth, boardHeight);
        }

        if (gridLinesPane != null) {
            gridLinesPane.setPrefSize(boardWidth, boardHeight);
            gridLinesPane.setMinSize(boardWidth, boardHeight);
            gridLinesPane.setMaxSize(boardWidth, boardHeight);
        }

        if (gameBoard != null) {
            double visualBuffer = 4.0;
            double bufferedWidth = GameConstants.boardAreaWidth() + (visualBuffer * 2);
            double bufferedHeight = GameConstants.boardAreaHeight() + (visualBuffer * 2);
            gameBoard.setPrefSize(bufferedWidth, bufferedHeight);
            gameBoard.setMinSize(bufferedWidth, bufferedHeight);
            gameBoard.setMaxSize(bufferedWidth, bufferedHeight);
            gameBoard.setPadding(new Insets(
                    GameConstants.BOARD_FRAME_THICKNESS,
                    GameConstants.BOARD_FRAME_THICKNESS,
                    GameConstants.BOARD_FRAME_THICKNESS + GameConstants.BOTTOM_PADDING,
                    GameConstants.BOARD_FRAME_THICKNESS
            ));
        }

        if (sidePanel != null) {
            sidePanel.setSpacing(GameConstants.SIDE_PANEL_SPACING);
            sidePanel.setPrefWidth(GameConstants.SIDE_PANEL_WIDTH);
            sidePanel.setPadding(new Insets(GameConstants.SIDE_PANEL_PADDING));
        }

        if (timerBox != null) {
            timerBox.setSpacing(GameConstants.SIDE_PANEL_SPACING / 2.0);
            timerBox.setPrefWidth(GameConstants.SIDE_PANEL_WIDTH);
            timerBox.setPadding(new Insets(GameConstants.SIDE_PANEL_PADDING));
        }

        if (nextBricksContainer != null) {
            nextBricksContainer.setSpacing(GameConstants.NEXT_PREVIEW_SPACING);
        }

        if (nextBricksList != null) {
            nextBricksList.setSpacing(GameConstants.NEXT_PREVIEW_SPACING);
        }

        if (notificationGroup != null) {
            notificationGroup.setLayoutY(GameConstants.notificationPanelY());
        }

        if (bombToolbar != null) {
            bombToolbar.setPadding(new Insets(8));
        }

        if (chinaDescriptionBox != null) {
            double visualBuffer = 4.0;
            double bufferedWidth = GameConstants.boardAreaWidth() + (visualBuffer * 2);
            double bufferedHeight = GameConstants.boardAreaHeight() + (visualBuffer * 2);
            chinaDescriptionBox.setPrefSize(bufferedWidth, bufferedHeight);
            chinaDescriptionBox.setMinSize(bufferedWidth, bufferedHeight);
            chinaDescriptionBox.setMaxSize(bufferedWidth, bufferedHeight);
        }

        if (rootPane != null) {
            rootPane.setPrefWidth(GameConstants.initialWindowWidth());
            rootPane.setPrefHeight(GameConstants.initialWindowHeight());
        }

        if (boardRenderer != null) {
            boardRenderer.redrawGridLines();
        }
    }

    public void positionContent(double availableWidth) {
        double safeWidth = Math.max(availableWidth, GameConstants.initialWindowWidth());
        double boardAreaWidth = GameConstants.boardAreaWidth();

        double centeredBoardLeft = (safeWidth - boardAreaWidth) / 2.0;
        double boardLeft;

        if (safeWidth < GameConstants.minimumCenteredWindowWidth()) {
            double centeredContent = (safeWidth - GameConstants.contentWidth()) / 2.0;
            boardLeft = Math.max(GameConstants.BOARD_LEFT_PADDING, centeredContent);
        } else {
            boardLeft = centeredBoardLeft;
        }

        double boardTop = GameConstants.BOARD_TOP_PADDING;

        if (gamePanel != null) {
            gamePanel.setLayoutX(boardLeft);
            gamePanel.setLayoutY(boardTop);
        }

        if (gridLinesPane != null) {
            gridLinesPane.setLayoutX(boardLeft);
            gridLinesPane.setLayoutY(boardTop);
        }

        double visualBuffer = 4.0;
        if (gameBoard != null) {
            gameBoard.setLayoutX(boardLeft - GameConstants.BOARD_FRAME_THICKNESS - visualBuffer);
            gameBoard.setLayoutY(boardTop - GameConstants.BOARD_FRAME_THICKNESS - visualBuffer);
        }

        double sidePanelLeft = boardLeft + boardAreaWidth + GameConstants.PANEL_GAP;
        if (sidePanel != null) {
            sidePanel.setLayoutX(sidePanelLeft);
            sidePanel.setLayoutY(boardTop);
        }

        if (timerBox != null) {
            double timerLeft = Math.max(
                    GameConstants.SIDE_PANEL_PADDING,
                    boardLeft - GameConstants.SIDE_PANEL_WIDTH - GameConstants.PANEL_GAP
            );
            timerBox.setLayoutX(timerLeft);
            timerBox.setLayoutY(boardTop);
        }

        if (notificationGroup != null) {
            notificationGroup.setLayoutX(sidePanelLeft);
        }

        if (bombToolbar != null) {
            double bombToolbarY = boardTop + GameConstants.boardPixelHeight() + 35;
            double bombToolbarX = boardLeft + (GameConstants.boardPixelWidth() - 50) / 2.0;
            bombToolbar.setLayoutX(bombToolbarX);
            bombToolbar.setLayoutY(bombToolbarY);
        }

        if (chinaDescriptionBox != null) {
            double bufferedWidth = chinaDescriptionBox.getPrefWidth();
            double descLeft = Math.max(
                    GameConstants.SIDE_PANEL_PADDING,
                    boardLeft - bufferedWidth - GameConstants.PANEL_GAP
            );
            double descTop = boardTop - GameConstants.BOARD_FRAME_THICKNESS - visualBuffer;
            chinaDescriptionBox.setLayoutX(descLeft);
            chinaDescriptionBox.setLayoutY(descTop);
        }
    }

    public void applyBackgroundImage(String resourcePath) {
        if (rootPane == null || resourcePath == null || resourcePath.isBlank()) {
            return;
        }

        var resourceUrl = getClass().getClassLoader().getResource(resourcePath);
        if (resourceUrl == null) {
            System.err.println("Missing background resource: " + resourcePath);
            return;
        }

        String url = resourceUrl.toExternalForm();

        Image image = new Image(url, true);
        BackgroundSize size = new BackgroundSize(
                1.0, 1.0, true, true, false, true
        );
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                size
        );
        rootPane.setBackground(new Background(backgroundImage));
        rootPane.setStyle(
                "-fx-background-image: url('" + url + "');"
                        + "-fx-background-size: cover;"
                        + "-fx-background-repeat: no-repeat;"
                        + "-fx-background-position: center center;"
                        + "-fx-background-color: transparent;"
        );
    }

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

        if (rootPane != null) {
            this.endOverlay.prefWidthProperty().bind(rootPane.widthProperty());
            this.endOverlay.prefHeightProperty().bind(rootPane.heightProperty());
        }

        if (this.endBackgroundVideo != null) {
            BackgroundVideoManager.attach(this.endBackgroundVideo, this.endOverlay);
        }
    }

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

        double width = GameConstants.initialWindowWidth();
        if (rootPane != null && rootPane.getWidth() > 0) {
            width = rootPane.getWidth();
        }

        endOverlay.setTranslateX(-width);
        TranslateTransition slideIn = new TranslateTransition(Duration.millis(520), endOverlay);
        slideIn.setFromX(-width);
        slideIn.setToX(0);
        slideIn.setInterpolator(Interpolator.EASE_OUT);
        slideIn.play();
    }

    public void hideEndOverlay() {
        if (endOverlay != null) {
            endOverlay.setVisible(false);
            endOverlay.setManaged(false);
        }
    }
}
```

### src/main/java/com/comp2042/view/GameLoop.java
```java
package com.comp2042.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.util.Duration;

public class GameLoop {

    private final Timeline timeline;

    public GameLoop(int tickMillis, Runnable onTick) {
        this.timeline = new Timeline(
                new KeyFrame(Duration.millis(tickMillis), e -> onTick.run())
        );
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void start() {
        timeline.play();
    }

    public void pause() {
        timeline.pause();
    }

    public void stop() {
        timeline.stop();
    }

    public boolean isRunning() {
        return timeline.getStatus() == Animation.Status.RUNNING;
    }
}
```

### src/main/java/com/comp2042/view/GameNotificationManager.java
```java
package com.comp2042.view;

import com.comp2042.data.DownData;
import com.comp2042.util.GameConstants;
import javafx.beans.property.IntegerProperty;
import javafx.scene.Group;

public final class GameNotificationManager {

    private final Group notificationGroup;
    private final IntegerProperty bombCount;

    private int lastBombMilestone = 0;

    public GameNotificationManager(Group notificationGroup,
                                   IntegerProperty bombCount) {
        this.notificationGroup = notificationGroup;
        this.bombCount = bombCount;
    }

    public void handleScoreChanged(int newScore) {
        int milestonesReached = newScore / GameConstants.POINTS_PER_BOMB;
        int newBombs = milestonesReached - lastBombMilestone;

        if (newBombs > 0) {
            bombCount.set(bombCount.get() + newBombs);
            lastBombMilestone = milestonesReached;
            showBombNotification(newBombs);
        }
    }

    public void handleDownMovement(DownData downData) {
        if (downData == null || downData.getClearRow() == null) {
            return;
        }
        if (downData.getClearRow().getLinesRemoved() <= 0) {
            return;
        }
        int bonus = downData.getClearRow().getScoreBonus();
        if (bonus > 0) {
            showScoreNotification(bonus);
        }
    }

    private void showBombNotification(int bombsAwarded) {
        NotificationPanel panel = new NotificationPanel("+" + bombsAwarded + " 💣");
        notificationGroup.getChildren().add(panel);
        panel.showScore(notificationGroup.getChildren());
    }

    private void showScoreNotification(int scoreBonus) {
        NotificationPanel panel = new NotificationPanel("+" + scoreBonus);
        notificationGroup.getChildren().add(panel);
        panel.showScore(notificationGroup.getChildren());
    }
}
```

### src/main/java/com/comp2042/view/GameOverPanel.java
```java
package com.comp2042.view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GameOverPanel extends BorderPane {

    public GameOverPanel() {
        final Label gameOverLabel = new Label("GAME OVER");
        gameOverLabel.getStyleClass().add("gameOverStyle");
        setCenter(gameOverLabel);
    }

}
```

### src/main/java/com/comp2042/view/GameScreenController.java
```java
package com.comp2042.view;

import com.comp2042.data.ViewData;
import com.comp2042.event.InputEventListener;
import com.comp2042.model.Board;
import com.comp2042.model.brick.BrickFactory;
import com.comp2042.util.GameConstants;
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

public class GameScreenController implements Initializable {

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

    private InputEventListener eventListener;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Font.loadFont(
                getClass().getClassLoader().getResource("digital.ttf").toExternalForm(),
                38
        );

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

            double initialWidth = Math.max(rootPane.getWidth(), GameConstants.initialWindowWidth());
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

    public void bind(Board board) {
        this.board = board;

        if (bombManager != null) {
            bombManager.setBoard(board);
        }
        if (sessionManager != null) {
            sessionManager.bindBoard(board);
        }
    }

    public void setEventListener(InputEventListener eventListener) {
        this.eventListener = eventListener;
        if (sessionManager != null) {
            sessionManager.setEventListener(eventListener);
        }
    }

    public void setNavigationHandlers(Runnable backToHomeHandler, Runnable restartHandler) {
        this.backToHomeHandler = backToHomeHandler;
        this.restartHandler = restartHandler;
    }

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

    public void gameOver() {
        if (sessionManager != null) {
            sessionManager.handleGameEnd("Game Over", "The bricks reached the ceiling.");
        }
    }

    public void configureExploreChinaMode() {
        if (chinaStageManager == null || sessionManager == null) {
            return;
        }

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

        chinaStageManager.enableExploreMode();
    }

    public void configureTimeAttack(int minutes) {
        if (timeAttackManager == null) {
            return;
        }

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
        BrickFactory.setPlusEnabled(minutes == 5);

        if (sessionManager != null && sessionManager.isRunning()) {
            timeAttackManager.start();
        }
    }

    private void applyTimeAttackBackground(int minutes) {
        String resourcePath = null;
        if (minutes == 1) {
            resourcePath = "Time stages/1.jpg";
        } else if (minutes == 3) {
            resourcePath = "Time stages/3.jpg";
        } else if (minutes == 5) {
            resourcePath = "Time stages/5.jpg";
        }

        if (resourcePath != null && layoutManager != null) {
            layoutManager.applyBackgroundImage(resourcePath);
        }
    }
}
```

### src/main/java/com/comp2042/view/GameSessionManager.java
```java
package com.comp2042.view;

import com.comp2042.data.DownData;
import com.comp2042.data.ViewData;
import com.comp2042.event.EventSource;
import com.comp2042.event.EventType;
import com.comp2042.event.InputEventListener;
import com.comp2042.event.MoveEvent;
import com.comp2042.model.Board;
import com.comp2042.util.GameConstants;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

public final class GameSessionManager {

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
    private Board board;

    private int currentTickMillis = GameConstants.GAME_TICK_MS;
    private boolean endScreenShown = false;

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

        this.pauseProperty = pauseProperty;
        this.gameOverProperty = gameOverProperty;
        this.gamePanel = gamePanel;
        this.boardRenderer = boardRenderer;
        this.nextBricksRenderer = nextBricksRenderer;
        this.vibrationEffect = vibrationEffect;
        this.timeAttackManager = timeAttackManager;
        this.layoutManager = layoutManager;
        this.notificationManager = notificationManager;
        this.gameOverPanel = gameOverPanel;
    }

    public void setEventListener(InputEventListener listener) {
        this.eventListener = listener;
    }

    public void bindBoard(Board board) {
        this.board = board;

        board.boardMatrixProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                boardRenderer.refreshBackground(newVal);
            }
        });

        board.isGameOverProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && newVal) {
                handleGameEnd("Game Over", "The bricks reached the ceiling.");
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

    public void startGame(int[][] boardMatrix, ViewData viewData) {
        boardRenderer.initialiseBoard(boardMatrix, viewData);
        nextBricksRenderer.renderNextBricks(viewData.getNextBricksData());
        ensureGameLoopInitialised();
        gameLoop.start();

        if (timeAttackManager != null && timeAttackManager.isEnabled()) {
            timeAttackManager.start();
        }
    }

    public void onMoveLeft(MoveEvent event) {
        if (pauseProperty.get() || eventListener == null) {
            return;
        }
        ViewData viewData = eventListener.onLeftEvent(event);
        updateActiveBrick(viewData);
        gamePanel.requestFocus();
    }

    public void onMoveRight(MoveEvent event) {
        if (pauseProperty.get() || eventListener == null) {
            return;
        }
        ViewData viewData = eventListener.onRightEvent(event);
        updateActiveBrick(viewData);
        gamePanel.requestFocus();
    }

    public void onRotate(MoveEvent event) {
        if (pauseProperty.get() || eventListener == null) {
            return;
        }
        ViewData viewData = eventListener.onRotateEvent(event);
        updateActiveBrick(viewData);
        gamePanel.requestFocus();
    }

    public void onMoveDown(MoveEvent event) {
        if (pauseProperty.get() || eventListener == null) {
            return;
        }
        DownData downData = eventListener.onDownEvent(event);
        handleDownMovement(downData, false);
        gamePanel.requestFocus();
    }

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

    public void togglePause(ToggleButton pauseButton) {
        if (gameOverProperty.get()) {
            return;
        }

        boolean paused = !pauseProperty.get();
        pauseProperty.set(paused);

        if (paused) {
            if (gameLoop != null) {
                gameLoop.pause();
            }
            if (timeAttackManager != null) {
                timeAttackManager.pause();
            }
            if (pauseButton != null) {
                pauseButton.setText("Resume");
            }
        } else {
            if (gameLoop != null) {
                gameLoop.start();
            }
            if (timeAttackManager != null) {
                timeAttackManager.resume();
            }
            if (pauseButton != null) {
                pauseButton.setText("Pause");
            }
        }

        gamePanel.requestFocus();
    }

    public void exitGame() {
        if (gameLoop != null) {
            gameLoop.pause();
        }
        if (timeAttackManager != null) {
            timeAttackManager.pause();
        }
        pauseProperty.set(true);
        if (layoutManager != null) {
            layoutManager.showEndScreen("Exit Game", "Choose what to do next.");
        }
    }

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

    public void pauseForBombDrag() {
        if (gameLoop != null && !pauseProperty.get()) {
            gameLoop.pause();
        }
        if (timeAttackManager != null && timeAttackManager.isEnabled() && !pauseProperty.get()) {
            timeAttackManager.pause();
        }
    }

    public void resumeAfterBombDrag() {
        if (gameLoop != null && !pauseProperty.get()) {
            gameLoop.start();
        }
        if (timeAttackManager != null && timeAttackManager.isEnabled() && !pauseProperty.get()) {
            timeAttackManager.resume();
        }
        gamePanel.requestFocus();
    }

    public void updateGameLoopSpeed(int newTickMillis) {
        int clamped = Math.max(GameConstants.MIN_GAME_TICK_MS, newTickMillis);
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
}
```

### src/main/java/com/comp2042/view/HomeController.java
```java
package com.comp2042.view;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;

import java.util.function.Consumer;

public class HomeController {

    @FXML
    private StackPane homeRoot;

    @FXML
    private MediaView backgroundVideo;

    private Consumer<HomeSelection.Mode> selectionHandler;

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

### src/main/java/com/comp2042/view/HomeSelection.java
```java
package com.comp2042.view;

/**
 * Simple record describing the user's home screen selection.
 */
public record HomeSelection(Mode mode, String option) {
    public enum Mode {
        COUNTRY_EXPLORE,
        TIME_RACING
    }
}
```

### src/main/java/com/comp2042/view/ModeSelectionController.java
```java
package com.comp2042.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;

import java.util.function.Consumer;

public class ModeSelectionController {

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
        Font.loadFont(
                getClass().getClassLoader().getResource("digital.ttf").toExternalForm(),
                38
        );
    }

    public void configure(HomeSelection.Mode mode, Consumer<HomeSelection> selectionHandler, Runnable backHandler) {
        this.mode = mode;
        this.selectionHandler = selectionHandler;
        this.backHandler = backHandler;

        BackgroundMusicManager.playMainMusic();
        setupTimeRacing();
    }

    private void setupTimeRacing() {
        optionOneTitle.setText("1 Minute Sprint");
        optionTwoTitle.setText("3 Minute Rush");
        optionThreeTitle.setText("5 Minute Marathon");

        badgeOne.setText("1M");
        badgeTwo.setText("3M");
        badgeThree.setText("5M");

        optionOneSubtitle.setText("Ultra fast, pure reaction");
        optionTwoSubtitle.setText("Balance speed and control");
        optionThreeSubtitle.setText("Endurance and consistency");
    }

    @FXML
    private void handleOptionOne() {
        fireSelection(optionOneTitle.getText());
    }

    @FXML
    private void handleOptionTwo() {
        fireSelection(optionTwoTitle.getText());
    }

    @FXML
    private void handleOptionThree() {
        fireSelection(optionThreeTitle.getText());
    }

    @FXML
    private void handleBack() {
        if (backHandler != null) {
            backHandler.run();
        }
    }

    private void fireSelection(String option) {
        if (selectionHandler != null && mode != null) {
            selectionHandler.accept(new HomeSelection(mode, option));
        }
    }
}
```

### src/main/java/com/comp2042/view/NextBricksRenderer.java
```java
package com.comp2042.view;

import com.comp2042.util.BlockTextureProvider;
import com.comp2042.util.GameConstants;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class NextBricksRenderer {

    private final VBox nextBricksList;
    private final List<GridPane> nextPreviewGrids = new ArrayList<>();

    public NextBricksRenderer(VBox nextBricksList) {
        this.nextBricksList = nextBricksList;
    }

    public void initialisePanels() {
        if (nextBricksList == null) {
            return;
        }

        nextBricksList.getChildren().clear();
        nextPreviewGrids.clear();

        for (int i = 0; i < GameConstants.NEXT_PREVIEW_COUNT; i++) {
            GridPane previewGrid = new GridPane();
            previewGrid.setHgap(GameConstants.NEXT_BRICK_GAP);
            previewGrid.setVgap(GameConstants.NEXT_BRICK_GAP);
            previewGrid.setAlignment(Pos.CENTER);
            nextPreviewGrids.add(previewGrid);
            nextBricksList.getChildren().add(previewGrid);
        }
    }

    public void renderNextBricks(List<int[][]> nextBricksData) {
        if (nextBricksList == null) {
            return;
        }
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
        Rectangle rectangle = new Rectangle(GameConstants.NEXT_BRICK_SIZE, GameConstants.NEXT_BRICK_SIZE);
        rectangle.setFill(BlockTextureProvider.getPattern(colorIndex));
        rectangle.setArcHeight(GameConstants.BRICK_ARC_SIZE);
        rectangle.setArcWidth(GameConstants.BRICK_ARC_SIZE);
        return rectangle;
    }
}
```

### src/main/java/com/comp2042/view/NotificationManager.java
```java
package com.comp2042.view;

import javafx.scene.Group;

public final class NotificationManager {

    private final Group notificationGroup;

    public NotificationManager(Group notificationGroup) {
        this.notificationGroup = notificationGroup;
    }

    public void showScoreNotification(int scoreBonus) {
        if (notificationGroup == null || scoreBonus <= 0) {
            return;
        }
        NotificationPanel panel = new NotificationPanel("+" + scoreBonus);
        notificationGroup.getChildren().add(panel);
        panel.showScore(notificationGroup.getChildren());
    }

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

### src/main/java/com/comp2042/view/NotificationPanel.java
```java
package com.comp2042.view;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class NotificationPanel extends BorderPane {

    public NotificationPanel(String text) {
        setMinHeight(200);
        setMinWidth(220);
        final Label score = new Label(text);
        score.getStyleClass().add("bonusStyle");
        final Effect glow = new Glow(0.6);
        score.setEffect(glow);
        score.setTextFill(Color.WHITE);
        setCenter(score);

    }

    public void showScore(ObservableList<Node> list) {
        FadeTransition ft = new FadeTransition(Duration.millis(2000), this);
        TranslateTransition tt = new TranslateTransition(Duration.millis(2500), this);
        tt.setToY(this.getLayoutY() - 40);
        ft.setFromValue(1);
        ft.setToValue(0);
        ParallelTransition transition = new ParallelTransition(tt, ft);
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                list.remove(NotificationPanel.this);
            }
        });
        transition.play();
    }
}
```

### src/main/java/com/comp2042/view/TimeAttackManager.java
```java
package com.comp2042.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.util.Duration;

public final class TimeAttackManager {

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
        this.pauseProperty = pauseProperty;
        this.gameOverProperty = gameOverProperty;

        disableTimeAttack();
    }

    public void bindScoreProperty(IntegerProperty scoreProperty) {
        this.boundScoreProperty = scoreProperty;
        updateBestScoreLabel();
    }

    public void setOnTimeOver(Runnable callback) {
        this.onTimeOverCallback = callback;
    }

    public boolean isEnabled() {
        return enabled;
    }

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
            timerTitleLabel.setText(minutes + " MIN TIME ATTACK");
        }

        updateTimerLabel();
        updateBestScoreLabel();
        recreateTimeline();
    }

    public void start() {
        if (!enabled || timeline == null || gameOverProperty.get()) {
            return;
        }
        timeline.playFromStart();
    }

    public void pause() {
        if (timeline != null) {
            timeline.pause();
        }
    }

    public void resume() {
        if (!enabled || timeline == null || gameOverProperty.get()) {
            return;
        }
        if (!pauseProperty.get()) {
            timeline.play();
        }
    }

    public void stop() {
        if (timeline != null) {
            timeline.stop();
        }
    }

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
            timerTitleLabel.setText("CLASSIC MODE");
        }
        if (timerValueLabel != null) {
            timerValueLabel.setText("--:--");
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
            case 1:
                if (currentScore > bestScore1Min) {
                    bestScore1Min = currentScore;
                }
                break;
            case 3:
                if (currentScore > bestScore3Min) {
                    bestScore3Min = currentScore;
                }
                break;
            case 5:
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
            case 1:
                label = "BEST 1 MIN";
                value = bestScore1Min;
                break;
            case 3:
                label = "BEST 3 MIN";
                value = bestScore3Min;
                break;
            case 5:
                label = "BEST 5 MIN";
                value = bestScore5Min;
                break;
            default:
                label = "BEST SCORE";
                value = 0;
        }

        bestScoreTitleLabel.setText(label);
        bestScoreValueLabel.setText(String.valueOf(value));
    }
}
```

### src/main/resources/audio/explorechina.mp3
[binary file, 18787487 bytes]

### src/main/resources/audio/mainmusic.mp3
[binary file, 1972694 bytes]

### src/main/resources/audio/timeracing.mp3
[binary file, 7951117 bytes]

### src/main/resources/background_image.png
[binary file, 373 bytes]

### src/main/resources/China/1.jpg
[binary file, 4995304 bytes]

### src/main/resources/China/10.jpg
[binary file, 488736 bytes]

### src/main/resources/China/11.jpg
[binary file, 486100 bytes]

### src/main/resources/China/12.jpg
[binary file, 749066 bytes]

### src/main/resources/China/13.jpg
[binary file, 245488 bytes]

### src/main/resources/China/14.jpg
[binary file, 557881 bytes]

### src/main/resources/China/15.jpg
[binary file, 490744 bytes]

### src/main/resources/China/16.jpg
[binary file, 867797 bytes]

### src/main/resources/China/17.jpg
[binary file, 292233 bytes]

### src/main/resources/China/18.jpg
[binary file, 454870 bytes]

### src/main/resources/China/19.jpg
[binary file, 3103539 bytes]

### src/main/resources/China/2.jpg
[binary file, 324136 bytes]

### src/main/resources/China/20.jpg
[binary file, 319170 bytes]

### src/main/resources/China/21.jpg
[binary file, 2122743 bytes]

### src/main/resources/China/22.jpg
[binary file, 1717997 bytes]

### src/main/resources/China/23.jpg
[binary file, 372765 bytes]

### src/main/resources/China/24.jpg
[binary file, 313651 bytes]

### src/main/resources/China/25.jpg
[binary file, 281853 bytes]

### src/main/resources/China/26.jpg
[binary file, 230399 bytes]

### src/main/resources/China/27.jpg
[binary file, 583817 bytes]

### src/main/resources/China/28.jpg
[binary file, 387372 bytes]

### src/main/resources/China/29.jpg
[binary file, 581116 bytes]

### src/main/resources/China/3.jpg
[binary file, 461523 bytes]

### src/main/resources/China/30.jpg
[binary file, 550332 bytes]

### src/main/resources/China/4.jpg
[binary file, 6388454 bytes]

### src/main/resources/China/5.jpg
[binary file, 3253170 bytes]

### src/main/resources/China/6.jpg
[binary file, 990912 bytes]

### src/main/resources/China/7.jpg
[binary file, 1561968 bytes]

### src/main/resources/China/8.jpg
[binary file, 584738 bytes]

### src/main/resources/China/9.jpg
[binary file, 1223903 bytes]

### src/main/resources/digital.ttf
[binary file, 23200 bytes]

### src/main/resources/gameLayout.fxml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<?import com.comp2042.view.GameOverPanel?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.control.ToggleButton?>
<?import javafx.scene.Group?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.media.MediaView?>
<?import javafx.scene.text.Text?>
<?import java.net.URL?>

<StackPane fx:id="rootPane"
           fx:controller="com.comp2042.view.GameScreenController"
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

### src/main/resources/home_layout.fxml
```xml
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
           fx:controller="com.comp2042.view.HomeController"
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

### src/main/resources/homebackground.png
[binary file, 290 bytes]

### src/main/resources/images/bomb.png
[binary file, 66028 bytes]

### src/main/resources/images/IBrick.png
[binary file, 25826 bytes]

### src/main/resources/images/JBrick.png
[binary file, 25376 bytes]

### src/main/resources/images/LBrick.png
[binary file, 26863 bytes]

### src/main/resources/images/mainpage.mp4
[binary file, 39759758 bytes]

### src/main/resources/images/OBrick.png
[binary file, 26867 bytes]

### src/main/resources/images/PlusBrick.png
[binary file, 26108 bytes]

### src/main/resources/images/SBrick.png
[binary file, 25323 bytes]

### src/main/resources/images/TBrick.png
[binary file, 25963 bytes]

### src/main/resources/images/ZBrick.png
[binary file, 26525 bytes]

### src/main/resources/selection_layout.fxml
```xml
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
           fx:controller="com.comp2042.view.ModeSelectionController"
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

### src/main/resources/Time stages/1.jpg
[binary file, 412392 bytes]

### src/main/resources/Time stages/3.jpg
[binary file, 3970655 bytes]

### src/main/resources/Time stages/5.jpg
[binary file, 82584 bytes]

### src/main/resources/window_style.css
```css
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

### src/test/java/com/comp2042/controller/GameControllerTest.java
```java
package com.comp2042.controller;

import com.comp2042.data.ClearRow;
import com.comp2042.data.DownData;
import com.comp2042.data.ViewData;
import com.comp2042.event.EventSource;
import com.comp2042.event.EventType;
import com.comp2042.event.MoveEvent;
import com.comp2042.model.Board;
import com.comp2042.model.Score;
import com.comp2042.util.GameConstants;
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
            viewDataToReturn = new ViewData(currentBrick, 0, 0, 10, Collections.singletonList(nextBrick));
            clearRowToReturn = new ClearRow(0, new int[][]{{0}}, 0);
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

        @Override
        public void explodeBomb(int centerX, int centerY) {
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
        assertEquals(initialScore + GameConstants.MANUAL_DOWN_SCORE,
                board.getScore().scoreProperty().get(),
                "Manual down from USER should increase score");
        assertNull(result.getClearRow());
        assertSame(board.viewDataToReturn, result.getViewData());
    }

    @Test
    void onDownEvent_BrickStopsAndClearsRows_AddsScoreMergesAndCreatesNewBrick() {
        FakeBoard board = new FakeBoard();
        GameController controller = new GameController(board);

        board.nextMoveDownResult = false;
        board.clearRowToReturn = new ClearRow(2, new int[][]{{0}}, 100);

        int initialScore = board.getScore().scoreProperty().get();
        int initialCreateCalls = board.createNewBrickCalls;

        DownData result = controller.onDownEvent(
                new MoveEvent(EventType.DOWN, EventSource.THREAD));

        assertEquals(1, board.moveDownCalls);
        assertEquals(1, board.mergeCalls, "mergeBrickToBackground should be called when piece stops");
        assertEquals(1, board.clearRowsCalls, "clearRows should be called when piece stops");
        assertEquals(initialCreateCalls + 1, board.createNewBrickCalls,
                "createNewBrick should be called after merging");
        assertEquals(initialScore + 100,
                board.getScore().scoreProperty().get(),
                "Score should increase by ClearRow.getScoreBonus()");
        assertNotNull(result.getClearRow());
        assertEquals(2, result.getClearRow().getLinesRemoved());
        assertSame(board.viewDataToReturn, result.getViewData());
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

### src/test/java/com/comp2042/model/ActivePieceTest.java
```java
package com.comp2042.model;

import com.comp2042.model.brick.Brick;
import com.comp2042.util.GameConstants;
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

        assertEquals(GameConstants.SPAWN_X, activePiece.getX());
        assertEquals(GameConstants.SPAWN_Y, activePiece.getY());

        int[][] shape = activePiece.getShape();
        assertEquals(2, shape.length);
        assertEquals(2, shape[0].length);
        assertEquals(1, shape[0][0]);
    }

    @Test
    void move_OnEmptyBoard_UpdatesPosition() {
        int[][] board = new int[GameConstants.BOARD_HEIGHT][GameConstants.BOARD_WIDTH];

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
        int[][] board = new int[GameConstants.BOARD_HEIGHT][GameConstants.BOARD_WIDTH];

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
        int[][] board = new int[GameConstants.BOARD_HEIGHT][GameConstants.BOARD_WIDTH];

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
        int[][] board = new int[GameConstants.BOARD_HEIGHT][GameConstants.BOARD_WIDTH];

        board[GameConstants.SPAWN_Y][GameConstants.SPAWN_X + 1] = 9;

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

### src/test/java/com/comp2042/model/brick/BrickFactoryTest.java
```java
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

### src/test/java/com/comp2042/model/brick/RandomBrickGeneratorTest.java
```java
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

### src/test/java/com/comp2042/model/BrickRotatorTest.java
```java
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

### src/test/java/com/comp2042/model/ScoreTest.java
```java
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

### src/test/java/com/comp2042/model/SimpleBoardTest.java
```java
package com.comp2042.model;

import com.comp2042.data.ClearRow;
import com.comp2042.data.ViewData;
import com.comp2042.model.brick.Brick;
import com.comp2042.model.brick.BrickFactory;
import com.comp2042.model.brick.BrickGenerator;
import com.comp2042.util.GameConstants;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleBoardTest {

    @Test
    void createNewBrick_WhenSpawnBlocked_SetsGameOver() {
        SimpleBoard board = new SimpleBoard(GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT);

        int[][] matrix = board.getBoardMatrix();
        for (int y = GameConstants.SPAWN_Y; y < GameConstants.SPAWN_Y + 4 && y < matrix.length; y++) {
            for (int x = 0; x < matrix[y].length; x++) {
                matrix[y][x] = 1;
            }
        }

        boolean gameOver = board.createNewBrick();

        assertTrue(gameOver);
        assertTrue(board.isGameOverProperty().get());
    }


    @Test
    void moveBrickLeftRightDown_UpdatesViewDataPosition() {
        SimpleBoard boardLeft = new SimpleBoard(GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT);
        boardLeft.createNewBrick();
        ViewData initialLeft = boardLeft.getViewData();

        boolean movedLeft = boardLeft.moveBrickLeft();
        ViewData afterLeft = boardLeft.getViewData();

        assertTrue(movedLeft);
        assertEquals(initialLeft.getXPosition() - 1, afterLeft.getXPosition());
        assertEquals(initialLeft.getYPosition(), afterLeft.getYPosition());

        SimpleBoard boardRight = new SimpleBoard(GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT);
        boardRight.createNewBrick();
        ViewData initialRight = boardRight.getViewData();

        boolean movedRight = boardRight.moveBrickRight();
        ViewData afterRight = boardRight.getViewData();

        assertTrue(movedRight);
        assertEquals(initialRight.getXPosition() + 1, afterRight.getXPosition());
        assertEquals(initialRight.getYPosition(), afterRight.getYPosition());

        SimpleBoard boardDown = new SimpleBoard(GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT);
        boardDown.createNewBrick();
        ViewData initialDown = boardDown.getViewData();

        boolean movedDown = boardDown.moveBrickDown();
        ViewData afterDown = boardDown.getViewData();

        assertTrue(movedDown);
        assertEquals(initialDown.getXPosition(), afterDown.getXPosition());
        assertEquals(initialDown.getYPosition() + 1, afterDown.getYPosition());
    }

    @Test
    void clearRows_RemovesFullRowsAndUpdatesMatrixProperty() {
        int width = 4;
        int height = 4;
        SimpleBoard board = new SimpleBoard(width, height);

        int[][] matrix = board.getBoardMatrix();
        for (int x = 0; x < width; x++) {
            matrix[height - 1][x] = 1;
        }

        ClearRow clearRow = board.clearRows();

        assertEquals(1, clearRow.getLinesRemoved());

        int[][] newMatrix = board.getBoardMatrix();
        for (int x = 0; x < width; x++) {
            assertEquals(0, newMatrix[height - 1][x]);
        }

        assertSame(newMatrix, board.boardMatrixProperty().get());
    }

    @Test
    void clearRows_SeparatedRows_RemovesBothAndUpdatesMatrixProperty() {
        SimpleBoard board = new SimpleBoard(4, 4);
        int[][] matrix = board.getBoardMatrix();

        for (int x = 0; x < 4; x++) {
            matrix[1][x] = 1;
        }

        matrix[2][1] = 1;

        for (int x = 0; x < 4; x++) {
            matrix[3][x] = 1;
        }

        ClearRow result = board.clearRows();

        assertEquals(2, result.getLinesRemoved());

        int[][] newMatrix = board.getBoardMatrix();

        assertArrayEquals(new int[]{0, 0, 0, 0}, newMatrix[0]);
        assertArrayEquals(new int[]{0, 0, 0, 0}, newMatrix[1]);
        assertArrayEquals(new int[]{0, 0, 0, 0}, newMatrix[2]);
        assertArrayEquals(new int[]{0, 1, 0, 0}, newMatrix[3]);
    }

    @Test
    void nextPreviews_UpdateWhenNewBricksSpawned() {
        List<Brick> bricks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            bricks.add(BrickFactory.createBrick(i % BrickFactory.getBrickCount()));
        }
        BrickGenerator generator = new FixedBrickGenerator(bricks);
        SimpleBoard board = new SimpleBoard(GameConstants.BOARD_WIDTH, GameConstants.BOARD_HEIGHT, generator);

        board.createNewBrick();
        List<int[][]> firstPreview = board.getViewData().getNextBricksData();

        assertEquals(GameConstants.NEXT_PREVIEW_COUNT, firstPreview.size());
        assertMatrixEquals(bricks.get(1).getShapeMatrix().get(0), firstPreview.get(0));
        assertMatrixEquals(bricks.get(2).getShapeMatrix().get(0), firstPreview.get(1));

        board.mergeBrickToBackground();
        clearBoard(board.getBoardMatrix());
        board.createNewBrick();
        List<int[][]> secondPreview = board.getViewData().getNextBricksData();

        assertMatrixEquals(bricks.get(2).getShapeMatrix().get(0), secondPreview.get(0));
        assertMatrixEquals(bricks.get(3).getShapeMatrix().get(0), secondPreview.get(1));
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

### src/test/java/com/comp2042/util/MatrixOperationsTest.java
```java
package com.comp2042.util;

import com.comp2042.data.ClearRow;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatrixOperationsTest {

    @Test
    void intersect_NoCollision_ReturnsFalse() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        int[][] shape = {
                {1, 1},
                {1, 1}
        };

        boolean result = MatrixOperations.intersect(board, shape, 1, 1);

        assertFalse(result);
    }

    @Test
    void intersect_WithCollision_ReturnsTrue() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        int[][] shape = {
                {1, 1},
                {1, 1}
        };

        boolean result = MatrixOperations.intersect(board, shape, 1, 1);

        assertTrue(result);
    }

    @Test
    void intersect_OutsideBoard_ReturnsTrue() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        int[][] shape = {
                {1, 1},
                {1, 1}
        };

        boolean result = MatrixOperations.intersect(board, shape, -1, 0);

        assertTrue(result);
    }

    @Test
    void copy_ReturnsDeepCopyWithSameValues() {
        int[][] original = {
                {1, 2},
                {3, 4}
        };

        int[][] copy = MatrixOperations.copy(original);

        assertArrayEquals(original, copy);
        assertNotSame(original, copy);

        for (int i = 0; i < original.length; i++) {
            assertNotSame(original[i], copy[i]);
        }
    }

    @Test
    void copy_ModifyingCopyDoesNotAffectOriginal() {
        int[][] original = {
                {1, 2},
                {3, 4}
        };

        int[][] copy = MatrixOperations.copy(original);
        copy[0][0] = 99;

        assertEquals(1, original[0][0]);
        assertEquals(99, copy[0][0]);
    }

    @Test
    void merge_PlacesShapeOnEmptyBoard() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        int[][] shape = {
                {1, 1},
                {1, 1}
        };

        int[][] result = MatrixOperations.merge(board, shape, 1, 1);

        int[][] expected = {
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };

        assertMatrixEquals(expected, result);
    }

    @Test
    void merge_OnlyNonZeroCellsAreWritten() {
        int[][] board = {
                {0, 2, 0, 0},
                {0, 2, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        int[][] shape = {
                {0, 3},
                {3, 3}
        };

        int[][] result = MatrixOperations.merge(board, shape, 1, 1);

        int[][] expected = {
                {0, 2, 0, 0},
                {0, 2, 3, 0},
                {0, 3, 3, 0},
                {0, 0, 0, 0}
        };

        assertMatrixEquals(expected, result);
    }

    @Test
    void checkRemoving_NoFullRows_ReturnsOriginalAndZeroScore() {
        int[][] board = {
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1}
        };

        ClearRow clearRow = MatrixOperations.checkRemoving(board);

        assertMatrixEquals(board, clearRow.getNewMatrix());
        assertEquals(0, clearRow.getLinesRemoved());
        assertEquals(0, clearRow.getScoreBonus());
    }

    @Test
    void checkRemoving_SingleFullRow_RemovedAndShifted() {
        int[][] board = {
                {0, 0, 0, 0},
                {1, 0, 0, 1},
                {1, 1, 1, 1}, // full
                {2, 0, 2, 0}
        };

        ClearRow clearRow = MatrixOperations.checkRemoving(board);

        int[][] expected = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1},
                {2, 0, 2, 0}
        };

        assertMatrixEquals(expected, clearRow.getNewMatrix());
        assertEquals(1, clearRow.getLinesRemoved());
        assertEquals(GameConstants.SCORE_PER_LINE, clearRow.getScoreBonus());
    }

    @Test
    void checkRemoving_TwoFullRows_CascadeAndScoreSquared() {
        int[][] board = {
                {0, 0, 0, 0},
                {1, 1, 1, 1}, 
                {2, 2, 2, 2}, 
                {3, 0, 0, 3}
        };

        ClearRow clearRow = MatrixOperations.checkRemoving(board);

        int[][] expected = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {3, 0, 0, 3}
        };

        assertMatrixEquals(expected, clearRow.getNewMatrix());
        assertEquals(2, clearRow.getLinesRemoved());
        assertEquals(GameConstants.SCORE_PER_LINE * 2 * 2, clearRow.getScoreBonus());
    }

    @Test
    void deepCopyList_CreatesIndependentCopies() {
        int[][] a = {
                {1, 2},
                {3, 4}
        };
        int[][] b = {
                {5, 6},
                {7, 8}
        };

        List<int[][]> original = new ArrayList<>();
        original.add(a);
        original.add(b);

        List<int[][]> copy = MatrixOperations.deepCopyList(original);

        assertEquals(2, copy.size());
        assertMatrixEquals(a, copy.get(0));
        assertMatrixEquals(b, copy.get(1));

        assertNotSame(original.get(0), copy.get(0));
        assertNotSame(original.get(1), copy.get(1));
    }


    @Test
    void explodeBomb_Clears3x3AndDropsOnlyAboveInSameColumns() {
        int[][] board = {
                {0, 0, 1, 0, 0}, 
                {0, 0, 2, 0, 0}, 
                {0, 0, 3, 0, 0},
                {0, 0, 4, 0, 0},
                {0, 0, 5, 0, 0}, 
                {0, 0, 6, 0, 0}  
        };

        int[][] exploded = MatrixOperations.explodeBomb(board, 2, 3);

        int[][] expected = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 2, 0, 0},
                {0, 0, 6, 0, 0}
        };

        assertMatrixEquals(expected, exploded);
    }

    @Test
    void explodeBomb_DoesNotMoveColumnsOutsideBombArea() {
        int[][] board = {
                {9, 0, 1, 0, 8},
                {9, 0, 2, 0, 8},
                {9, 0, 3, 0, 8},
                {9, 0, 4, 0, 8},
                {9, 0, 5, 0, 8},
                {9, 0, 6, 0, 8}
        };

        int[][] exploded = MatrixOperations.explodeBomb(board, 2, 3);

        // Column 0 and 4 must be identical to original
        for (int row = 0; row < board.length; row++) {
            assertEquals(board[row][0], exploded[row][0], "Column 0 changed unexpectedly");
            assertEquals(board[row][4], exploded[row][4], "Column 4 changed unexpectedly");
        }
    }

    @Test
    void explodeBomb_HandlesBordersSafely() {
        int[][] board = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] exploded = MatrixOperations.explodeBomb(board, 0, 0);

        assertEquals(0, exploded[0][0]);
        assertEquals(0, exploded[0][1]);
        assertEquals(0, exploded[1][0]);
        assertEquals(0, exploded[1][1]);

        assertEquals(3, exploded.length);
        assertEquals(3, exploded[0].length);
    }


    private void assertMatrixEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length, "Height mismatch");
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row], "Row " + row + " differs");
        }
    }
}
```

