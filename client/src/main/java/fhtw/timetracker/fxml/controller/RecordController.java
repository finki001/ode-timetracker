package fhtw.timetracker.fxml.controller;

import fhtw.timetracker.NavigationService;
import fhtw.timetracker.model.RecordDTO;
import fhtw.timetracker.model.TaskDTO;
import fhtw.timetracker.model.UserDTO;
import fhtw.timetracker.network.NetworkService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    @FXML
    private BorderPane layoutRoot;

    @FXML
    private Button btn_cancel;

    @FXML
    private MenuItem btn_close;

    @FXML
    private MenuItem btn_close1;

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
    private ChoiceBox<String> choiceBoxTasks;

    private List<TaskDTO> tasks;

    @FXML
    void initialize() {
        networkService.findAllTasks((success, tasks, error) -> {
            if (success) {
                Platform.runLater(() -> {
                    this.tasks = tasks;
                    choiceBoxTasks.setItems(FXCollections.observableArrayList(tasks.stream().map(TaskDTO::getName).collect(Collectors.toList())));
                });
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
    void saveClicked(ActionEvent event) {
        RecordDTO record = new RecordDTO();
        LocalDate date = datePicker.getValue();
        String startTimeString = txt_startTime.getText();
        String endTimeString = txt_endTime.getText();

        int selectedTaskIndex = choiceBoxTasks.getSelectionModel().getSelectedIndex();
        TaskDTO selectedTask = this.tasks.get(selectedTaskIndex);
        if (selectedTask == null) {
            System.out.println("Select a Task.");
            return;
        }

        if (date == null) {
            System.out.println("Please select a Date");
            return;
        }

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalTime startTime;
        try {
            startTime = LocalTime.parse(startTimeString, timeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing startTime: " + e.getMessage());
            return;
        }

        LocalTime endTime;
        try {
            endTime = LocalTime.parse(endTimeString, timeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing endTime: " + e.getMessage());
            return;
        }

        record.setStartTime(LocalDateTime.of(date, startTime));
        record.setEndTime(LocalDateTime.of(date, endTime));
        record.setUser(new UserDTO(NetworkService.userId));
        record.setNotes(txt_description.getText().trim());

        record.setTask(new TaskDTO(selectedTask.getId()));
        networkService.createRecord(record, (success, response, error) -> {
            if (success) {
                navigationService.showOverview(layoutRoot.getScene());
            } else {
                System.out.println("Error creating Record: " + error.getMessage());
            }
        });
    }

    @FXML
    void showOverview() {
        navigationService.showOverview(layoutRoot.getScene());
    }
}
