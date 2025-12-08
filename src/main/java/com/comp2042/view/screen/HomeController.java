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
