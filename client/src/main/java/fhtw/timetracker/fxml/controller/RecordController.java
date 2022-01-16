package fhtw.timetracker.fxml.controller;

import fhtw.timetracker.NavigationService;
import fhtw.timetracker.model.RecordDTO;
import fhtw.timetracker.model.TaskDTO;
import fhtw.timetracker.model.UserDTO;
import fhtw.timetracker.network.NetworkService;
import fhtw.timetracker.service.AlertService;
import fhtw.timetracker.service.StateService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

public class RecordController {

    private final NavigationService navigationService = new NavigationService();
    private final NetworkService networkService = new NetworkService();
    private final AlertService alertService = new AlertService();

    @FXML
    private BorderPane layoutRoot;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea txt_description;

    @FXML
    private TextField txt_endTime;

    @FXML
    private TextField txt_startTime;

    @FXML
    private ChoiceBox<String> choiceBoxTasks;

    private List<TaskDTO> tasks;

    @FXML
    void initialize() {
        networkService.findAllTasks((success, tasks, error) -> {
            if (success) {
                this.tasks = tasks;
                choiceBoxTasks.setItems(FXCollections.observableArrayList(tasks.stream().map(TaskDTO::getName).collect(Collectors.toList())));
            }
        });
    }

    @FXML
    void closeApp() {
        navigationService.closeApp();
    }

    @FXML
    void logout() {
        navigationService.logout(layoutRoot.getScene());
    }

    @FXML
    void saveClicked() {
        LocalDate date = datePicker.getValue();
        String startTimeString = txt_startTime.getText();
        String endTimeString = txt_endTime.getText();

        int selectedTaskIndex = choiceBoxTasks.getSelectionModel().getSelectedIndex();
        TaskDTO selectedTask = selectedTaskIndex > -1 ? this.tasks.get(selectedTaskIndex) : null;

        if (date == null) {
            alertService.showAlert("Kein Datum ausgewählt", "Wählen Sie das Datum aus");
            return;
        }

        if (selectedTask == null) {
            alertService.showAlert("Keine Ausgabe ausgewählt", "Wählen Sie eine Aufgabe aus");
            return;
        }

        // parse start/end time
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m");
        LocalTime startTime;
        try {
            startTime = LocalTime.parse(startTimeString, timeFormatter);
        } catch (DateTimeParseException e) {
            alertService.showAlert("Ungültige Startzeit", "Geben Sie eine gültige Startzeit ein (Beispiel 9:15)");
            return;
        }

        LocalTime endTime;
        try {
            endTime = LocalTime.parse(endTimeString, timeFormatter);
        } catch (DateTimeParseException e) {
            alertService.showAlert("Ungültige Endzeit", "Geben Sie eine gültige Endzeit ein (Beispiel 9:15)");
            return;
        }

        RecordDTO record = new RecordDTO();

        record.setStartTime(LocalDateTime.of(date, startTime));
        record.setEndTime(LocalDateTime.of(date, endTime));
        record.setUser(new UserDTO(StateService.getInstance().getUserId()));
        record.setNotes(txt_description.getText().trim());

        record.setTask(new TaskDTO(selectedTask.getId()));
        networkService.createRecord(record, (success, response, error) -> {
            if (success) {
                navigationService.showOverview(layoutRoot.getScene());
            } else {
                alertService.showAlert("Fehler beim Erstellen der Aufzeichnung", "Die Aufzeichnung wurde nicht gespeichert, folgender Fehler ist aufgetreten: " + (error != null ? error.getMessage() : "unbekannt"));
            }
        });
    }

    @FXML
    void showOverview() {
        navigationService.showOverview(layoutRoot.getScene());
    }

    @FXML
    void cancelButtonClicked() {
        navigationService.showOverview(layoutRoot.getScene());
    }

    @FXML
    void createTask() {
        navigationService.showCreateTask(layoutRoot.getScene());
    }

    @FXML
    void showTaskList() {
        navigationService.showTaskList(layoutRoot.getScene());
    }
}
