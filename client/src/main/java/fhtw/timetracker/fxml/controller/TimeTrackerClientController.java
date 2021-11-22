package fhtw.timetracker.fxml.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TimeTrackerClientController {

    private int buttonClickCounter;

    @FXML
    private Button button;

    @FXML
    void buttonClicked() {
        buttonClickCounter++;
        button.setText("I was clicked " + buttonClickCounter + " times");
    }
}
