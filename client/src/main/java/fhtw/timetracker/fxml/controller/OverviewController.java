package fhtw.timetracker.fxml.controller;

import fhtw.timetracker.NavigationService;
import fhtw.timetracker.model.RecordDTO;
import fhtw.timetracker.model.TaskDTO;
import fhtw.timetracker.model.UserDTO;
import fhtw.timetracker.network.NetworkService;
import fhtw.timetracker.service.AlertService;
import fhtw.timetracker.service.StateService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

import java.util.List;
import java.util.stream.Collectors;

public class OverviewController {

    private final NavigationService navigationService = new NavigationService();
    private final NetworkService networkService = new NetworkService();
    private final AlertService alertService = new AlertService();

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
    private ListView recordsListView;

    private List<RecordDTO> records;

    @FXML
    void initialize() {
        networkService.findAllRecordsForUserId(StateService.getInstance().getUserId(), (success, records, error) -> {
            if (success) {
                Platform.runLater(() -> {
                    this.records = records;
                    recordsListView.setItems(FXCollections.observableArrayList(records.stream().map(RecordDTO::getNotes).collect(Collectors.toList())));
                });
            }
        });
    }

    @FXML
    void close() {
        navigationService.closeApp();
    }

    @FXML
    void createRecord() {
        navigationService.showCreateRecord(btn_back.getScene());
    }

    @FXML
    void showRecord() {
        int selectedRecordIndex = recordsListView.getSelectionModel().getSelectedIndex();
        RecordDTO selectedRecord = selectedRecordIndex > -1 ? this.records.get(selectedRecordIndex) : null;

        if (selectedRecord == null) {
            alertService.showAlert("Keine Aufnahme ausgewählt", "Wählen Sie eine Aufnahme aus");
            return;
        }

        StateService.getInstance().setRecordId(selectedRecord.getId());
        navigationService.showDetails(btn_back.getScene());
    }

    @FXML
    void logout() {
        navigationService.logout(btn_back.getScene());
    }
}
