package fhtw.timetracker.fxml.controller;

import fhtw.timetracker.network.NetworkService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TimeTrackerClientController {

    private int buttonClickCounter;

    @FXML
    private Button button;

    private final NetworkService networkService = new NetworkService();

    @FXML
    void buttonClicked() {
        buttonClickCounter++;
        networkService.loadAllUsers();
        button.setText("I was clicked " + buttonClickCounter + " times");
    }
}
