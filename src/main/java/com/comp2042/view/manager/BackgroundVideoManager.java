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
