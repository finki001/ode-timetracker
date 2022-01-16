package fhtw.timetracker.fxml.controller;

import fhtw.timetracker.NavigationService;
import fhtw.timetracker.model.TaskDTO;
import fhtw.timetracker.network.NetworkService;
import fhtw.timetracker.service.AlertService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class TaskListController {

    private final NetworkService networkService = new NetworkService();
    private final AlertService alertService = new AlertService();
    private final NavigationService navigationService = new NavigationService();

    @FXML
    private ListView<TaskDTO> task_list;

    private final List<TaskDTO> taskList = new ArrayList<>();

    @FXML
    public void initialize() {
        task_list.setCellFactory(param -> {
            ListCell<TaskDTO> cell = new ListCell<>() {
                @Override
                protected void updateItem(TaskDTO item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getName());
                    }
                }
            };

            ContextMenu contextMenu = new ContextMenu();

            MenuItem deleteItem = new MenuItem();
            deleteItem.textProperty().bind(Bindings.format("Delete"));
            deleteItem.setOnAction(event -> {
                int indexToDelete = task_list.getSelectionModel().getSelectedIndex();
                if (indexToDelete > -1) {
                    TaskDTO taskToDelete = taskList.get(indexToDelete);
                    networkService.deleteTask(taskToDelete.getId(), (success, response, error) -> {
                        if (success) {
                            reloadTasks();
                        } else {
                            alertService.showAlert("Fehler beim Löschen", "Die Aufgabe konnte nicht gelöscht werden: " + (error != null ? error.getLocalizedMessage() : null));
                        }
                    });
                }
            });
            contextMenu.getItems().add(deleteItem);

            cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
                if (isNowEmpty) {
                    cell.setContextMenu(null);
                } else {
                    cell.setContextMenu(contextMenu);
                }
            });
            return cell;
        });
        reloadTasks();
    }

    private void reloadTasks() {
        networkService.findAllTasks((success, tasks, error) -> {
            if (success) {
                taskList.clear();
                taskList.addAll(tasks);
                task_list.setItems(FXCollections.observableList(tasks));
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
