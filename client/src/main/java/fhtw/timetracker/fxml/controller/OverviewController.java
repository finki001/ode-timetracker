package fhtw.timetracker.fxml.controller;

import fhtw.timetracker.NavigationService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

public class OverviewController {

    private final NavigationService navigationService = new NavigationService();

    @FXML
    private Button btn_back;

    @FXML
    private MenuItem btn_close;

    @FXML
    private MenuItem btn_create_record;

    @FXML
    private MenuItem btn_logout;

    @FXML
    private Label lbl_timeCalc;

    @FXML
    void close() {
        navigationService.closeApp();
    }

    @FXML
    void createRecord() {
        navigationService.showCreateRecord(btn_back.getScene());
    }

    @FXML
    void logout() {
        navigationService.logout(btn_back.getScene());
    }
}
