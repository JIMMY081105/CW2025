package com.comp2042.view.manager;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public final class BackgroundMusicManager {

    private static final String MAIN_MUSIC_PATH = "audio/mainmusic.mp3";
    private static final String EXPLORE_CHINA_MUSIC_PATH = "audio/explorechina.mp3";
    private static final String TIME_RACING_MUSIC_PATH = "audio/timeracing.mp3";

    private static MediaPlayer currentPlayer;
    private static String currentTrack;
    private static double volume = 0.6;

    private BackgroundMusicManager() {
    }

    public static void playMainMusic() {
        playLoop(MAIN_MUSIC_PATH);
    }

    public static void playExploreChinaMusic() {
        playLoop(EXPLORE_CHINA_MUSIC_PATH);
    }

    public static void playTimeRacingMusic() {
        playLoop(TIME_RACING_MUSIC_PATH);
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
