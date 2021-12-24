package fhtw.timetracker.util;

import fhtw.timetracker.model.Task;
import fhtw.timetracker.model.TaskDTO;

public class DTOMapper {

    public static TaskDTO convertTaskToTaskDTO(final Task task) {
        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());

        return taskDTO;

    }
}
