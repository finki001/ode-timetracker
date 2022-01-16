package fhtw.timetracker.fxml.controller;

import fhtw.timetracker.NavigationService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RecordController {

    private final NavigationService navigationService = new NavigationService();

    @FXML
    private Button btn_cancel;

    @FXML
    private MenuItem btn_close;

    @FXML
    private Button btn_logout;

    @FXML
    private Button btn_save;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label lbl_timeCalc;

    @FXML
    private TextArea txt_description;

    @FXML
    private TextField txt_endTime;

    @FXML
    private TextField txt_startTime;

    @FXML
    private TextField txt_title;

    @FXML
    void logout(ActionEvent event) {
        navigationService.logout(btn_logout.getScene());
    }

    @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
}
