package fhtw.timetracker.controller;

import fhtw.timetracker.model.Task;
import fhtw.timetracker.model.TaskDTO;
import fhtw.timetracker.repository.TaskRepository;
import fhtw.timetracker.util.DTOMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    public List<TaskDTO> findAllTasks() {
        return taskRepository.findAll().stream().map(DTOMapper::convertTaskToTaskDTO).collect(Collectors.toList());
    }

    @PostMapping("/tasks")
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        Task task = new Task();
        task.setName(taskDTO.getName());

        Task savedTask = taskRepository.save(task);
        return DTOMapper.convertTaskToTaskDTO(savedTask);
    }
}
