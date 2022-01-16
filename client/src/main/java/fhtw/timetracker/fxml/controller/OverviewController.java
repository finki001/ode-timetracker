package fhtw.timetracker.fxml.controller;

import fhtw.timetracker.NavigationService;
import fhtw.timetracker.model.RecordDTO;
import fhtw.timetracker.network.NetworkService;
import fhtw.timetracker.service.AlertService;
import fhtw.timetracker.service.StateService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class OverviewController {

    private final NavigationService navigationService = new NavigationService();
    private final NetworkService networkService = new NetworkService();
    private final AlertService alertService = new AlertService();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    @FXML
    private Button btn_back;
    @FXML
    private ListView<String> recordsListView;
    private List<RecordDTO> records;

    @FXML
    void initialize() {
        networkService.findAllRecordsForUserId(StateService.getInstance().getUserId(), (success, records, error) -> {
            if (success) {
                this.records = records;
                recordsListView.setItems(FXCollections.observableArrayList(records.stream().map(record ->
                        dateFormatter.format(record.getStartTime()) + ", " + timeFormatter.format(record.getStartTime()) + " - " + timeFormatter.format(record.getEndTime()) + (record.getNotes().isBlank() ? "" : ", Notiz: " + record.getNotes().trim())).collect(Collectors.toList()))
                );
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

    @FXML
    void createTask() {
        navigationService.showCreateTask(recordsListView.getScene());
    }

    @FXML
    void showTaskList() {
        navigationService.showTaskList(recordsListView.getScene());
    }
}
