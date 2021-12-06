package fhtw.timetracker.controller;

import fhtw.timetracker.model.Task;
import fhtw.timetracker.repository.TaskRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }
}
