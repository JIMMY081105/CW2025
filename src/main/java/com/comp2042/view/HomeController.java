package com.comp2042.view;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaView;

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
    private void initialize() {
        initBackgroundVideo();
    }

    private void initBackgroundVideo() {
        BackgroundVideoManager.attach(backgroundVideo, homeRoot);
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
