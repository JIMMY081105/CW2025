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
