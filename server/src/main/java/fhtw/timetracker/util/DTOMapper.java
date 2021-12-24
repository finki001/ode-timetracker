package fhtw.timetracker.util;

import fhtw.timetracker.model.*;

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
        return new UserDTO(
                user.getId(),
                user.getLogin(),
                user.getFirstname(),
                user.getLastname(),
                user.getRole()
        );
    }
}
