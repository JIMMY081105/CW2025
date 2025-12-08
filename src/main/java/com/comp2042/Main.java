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
