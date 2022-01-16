package fhtw.timetracker.fxml.controller;

import fhtw.timetracker.NavigationService;
import fhtw.timetracker.model.TaskDTO;
import fhtw.timetracker.network.NetworkService;
import fhtw.timetracker.service.AlertService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.stream.Collectors;

public class TaskListController {

    private final NetworkService networkService = new NetworkService();
    private final AlertService alertService = new AlertService();
    private final NavigationService navigationService = new NavigationService();

    @FXML
    private ListView<String> task_list;

    @FXML
    public void initialize() {
        networkService.findAllTasks((success, tasks, error) -> {
            if (success) {
                task_list.setItems(FXCollections.observableList(tasks.stream().map(TaskDTO::getName).collect(Collectors.toList())));
            } else {
                alertService.showAlert("Fehler beim Laden der Aufgaben", "Die Aufgaben konnten nicht geladen werden: " + (error != null ? error.getLocalizedMessage() : null));
            }
        });
    }

    @FXML
    public void createTask() {
        navigationService.showCreateTask(task_list.getScene());
    }

    @FXML
    public void showOverview() {
        navigationService.showOverview(task_list.getScene());
    }

}
