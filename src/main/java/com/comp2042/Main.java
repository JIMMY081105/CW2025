package com.comp2042;

import com.comp2042.controller.GameController;
import com.comp2042.model.Board;
import com.comp2042.model.SimpleBoard;
import com.comp2042.util.GameConstants;
import com.comp2042.view.HomeController;
import com.comp2042.view.HomeSelection;
import com.comp2042.view.GuiController;
import com.comp2042.view.ModeSelectionController;
import com.comp2042.view.BackgroundVideoManager;
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

    // Use different scales for different screens
    private static final double HOME_UI_SCALE = 1.3;
    private static final double SELECTION_UI_SCALE = 1.3;
    private static final double GAME_UI_SCALE = 1.0; // <-- game stays at natural size

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
        GuiController guiController = fxmlLoader.getController();

        applyScale(root, GAME_UI_SCALE); 
        setSceneAndMaximize(primaryStage, root);

        GameController gameController = new GameController(board);
        guiController.setEventListener(gameController);
        guiController.bind(board);
        guiController.bindScore(board.scoreProperty());

        applySelectionToGame(selection, guiController);
    }

    private void applySelectionToGame(HomeSelection selection, GuiController guiController) {
        if (selection == null) {
            return;
        }

        if (selection.mode() == HomeSelection.Mode.COUNTRY_EXPLORE) {
            String imagePath = switch (selection.option()) {
                case "China" -> "China/1.jpg";
                case "America" -> "background_image.png";
                case "Turkey" -> "background_image.png";
                default -> "background_image.png";
            };
            guiController.applyBackgroundImage(imagePath);
        }

        if (selection.mode() == HomeSelection.Mode.TIME_RACING) {
            guiController.showModeLabel("Time Racing: " + selection.option());
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
