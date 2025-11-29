package com.comp2042.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.function.Consumer;

public class ModeSelectionController {

    @FXML
    private Label titleLabel;

    @FXML
    private Button optionOne;

    @FXML
    private Button optionTwo;

    @FXML
    private Button optionThree;

    private HomeSelection.Mode mode;
    private Consumer<HomeSelection> selectionHandler;

    public void configure(HomeSelection.Mode mode, Consumer<HomeSelection> selectionHandler) {
        this.mode = mode;
        this.selectionHandler = selectionHandler;
        if (mode == HomeSelection.Mode.COUNTRY_EXPLORE) {
            titleLabel.setText("Choose a Country");
            optionOne.setText("China");
            optionTwo.setText("America");
            optionThree.setText("Turkey");
        } else {
            titleLabel.setText("Choose a Time Limit");
            optionOne.setText("1 min");
            optionTwo.setText("3 min");
            optionThree.setText("5 min");
        }
    }

    @FXML
    private void handleOptionOne() {
        fireSelection(optionOne.getText());
    }

    @FXML
    private void handleOptionTwo() {
        fireSelection(optionTwo.getText());
    }

    @FXML
    private void handleOptionThree() {
        fireSelection(optionThree.getText());
    }

    private void fireSelection(String option) {
        if (selectionHandler != null && mode != null) {
            selectionHandler.accept(new HomeSelection(mode, option));
        }
    }
}
