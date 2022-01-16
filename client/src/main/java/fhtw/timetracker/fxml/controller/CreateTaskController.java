package fhtw.timetracker.fxml.controller;

import fhtw.timetracker.NavigationService;
import fhtw.timetracker.model.TaskDTO;
import fhtw.timetracker.network.NetworkService;
import fhtw.timetracker.service.AlertService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateTaskController {

    private final NetworkService networkService = new NetworkService();
    private final AlertService alertService = new AlertService();
    private final NavigationService navigationService = new NavigationService();

    @FXML
    private TextField text_task_name;

    @FXML
    void saveTask() {
        TaskDTO task = new TaskDTO();
        task.setName(text_task_name.getText().trim());
        networkService.createTask(task, (success, response, error) -> {
            if (success) {
                navigationService.showTaskList(text_task_name.getScene());
            } else {
                alertService.showAlert("Fehler beim Aufgabe erstellen", "Die Aufgabe konnte nicht erstellt werden, Fehler: " + (error != null ? error.getLocalizedMessage() : null));
            }
        });
    }

    @FXML
    public void initialize() {

    }

    @FXML
    void cancel() {
        navigationService.showTaskList(text_task_name.getScene());
    }
}
