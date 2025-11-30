package com.comp2042.view;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.function.Consumer;

public class HomeController {

    @FXML
    private StackPane homeRoot;

    @FXML
    private MediaView backgroundVideo;

    private Consumer<HomeSelection.Mode> selectionHandler;
    private MediaPlayer mediaPlayer;

    public void setSelectionHandler(Consumer<HomeSelection.Mode> selectionHandler) {
        this.selectionHandler = selectionHandler;
    }

    @FXML
    private void initialize() {
        initBackgroundVideo();
    }

    private void initBackgroundVideo() {
        if (backgroundVideo == null) {
            return;
        }

        URL videoUrl = getClass().getClassLoader().getResource("images/mainpage.mp4");
        if (videoUrl == null) {
            return;
        }

        Media media = new Media(videoUrl.toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setMute(true);

        backgroundVideo.setMediaPlayer(mediaPlayer);
        backgroundVideo.setPreserveRatio(true);

        if (homeRoot != null) {
            backgroundVideo.fitWidthProperty().bind(homeRoot.widthProperty());
            backgroundVideo.fitHeightProperty().bind(homeRoot.heightProperty());
        }
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
