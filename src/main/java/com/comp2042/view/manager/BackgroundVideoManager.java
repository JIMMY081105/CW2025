package com.comp2042.view.manager;

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
