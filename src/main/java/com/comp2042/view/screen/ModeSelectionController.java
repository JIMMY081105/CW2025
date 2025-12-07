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
