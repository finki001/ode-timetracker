package fhtw.timetracker.util;

import fhtw.timetracker.model.Record;
import fhtw.timetracker.model.RecordDTO;
import fhtw.timetracker.model.Task;
import fhtw.timetracker.model.TaskDTO;
import fhtw.timetracker.model.User;
import fhtw.timetracker.model.UserDTO;

public class DTOMapper {

    public static TaskDTO convertTaskToTaskDTO(final Task task) {
        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());

        return taskDTO;
    }

    public static RecordDTO convertRecordToRecordDTO(final Record record) {
        RecordDTO recordDTO = new RecordDTO();
        recordDTO.setId(record.getId());
        recordDTO.setStartTime(record.getStartTime());
        recordDTO.setEndTime(record.getEndTime());
        recordDTO.setNotes(record.getNotes());
        recordDTO.setUser(convertUsertoUserDTO(record.getUser()));
        recordDTO.setTask(convertTaskToTaskDTO(record.getTask()));

        return recordDTO;
    }

    public static UserDTO convertUsertoUserDTO(final User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}
