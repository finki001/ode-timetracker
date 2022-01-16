package fhtw.timetracker.fxml.controller;

import fhtw.timetracker.NavigationService;
import fhtw.timetracker.model.RecordDTO;
import fhtw.timetracker.model.TaskDTO;
import fhtw.timetracker.network.NetworkService;
import fhtw.timetracker.service.StateService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DetailsController {
    private final NavigationService navigationService = new NavigationService();
    private final NetworkService networkService = new NetworkService();

    @FXML
    private Button btn_cancel;

    @FXML
    private MenuItem btn_close1;

    @FXML
    private MenuItem btn_close2;

    @FXML
    private MenuItem btn_overview;

    @FXML
    private BorderPane layoutRoot;

    // label to write on TODO

    @FXML
    private Label label_datum;

    @FXML
    private Label label_start;

    @FXML
    private Label label_ende;

    @FXML
    private Label label_aufgabe;

    @FXML
    private Label label_notizen;

    private RecordDTO record;

    @FXML
    void initialize() {
        networkService.findAllRecordsForUserId(StateService.getInstance().getUserId(), (success, records, error) -> {
            if (success) {
                if(-1 != StateService.getInstance().getRecordId()) {
                    for (RecordDTO record : records) {
                        if (record.getId() == StateService.getInstance().getRecordId()) {
                            this.record = record;
                        }
                    }

                    Platform.runLater(() -> {
                        LocalDateTime startTime = this.record.getStartTime();
                        LocalDateTime endTime = this.record.getEndTime();
                        label_datum.setText(startTime.getDayOfMonth() + "." + startTime.getMonthValue() + "." + startTime.getYear());
                        label_start.setText(startTime.getHour() + ":" + startTime.getMinute());
                        label_ende.setText(endTime.getHour() + ":" + endTime.getMinute());
                        label_aufgabe.setText(this.record.getTask().getName());
                        label_notizen.setText(this.record.getNotes());
                    });
                } else {
                    navigationService.showOverview(layoutRoot.getScene());
                }
            }
        });
    }

    @FXML
    void cancelButtonClicked(ActionEvent event) {
        navigationService.showOverview(layoutRoot.getScene());
    }

    @FXML
    void closeApp(ActionEvent event) {
        navigationService.closeApp();
    }

    @FXML
    void logout(ActionEvent event) {
        navigationService.logout(btn_cancel.getScene());
    }

    @FXML
    void showOverview(ActionEvent event) {
        navigationService.showOverview(layoutRoot.getScene());
    }

}
