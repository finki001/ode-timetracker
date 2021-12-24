package fhtw.timetracker.service;

import fhtw.timetracker.model.Record;
import fhtw.timetracker.model.RecordDTO;
import fhtw.timetracker.model.Task;
import fhtw.timetracker.model.User;
import fhtw.timetracker.repository.RecordRepository;
import fhtw.timetracker.repository.TaskRepository;
import fhtw.timetracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordService {
    private final RecordRepository recordRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public RecordService(RecordRepository recordRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.recordRepository = recordRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public List<Record> findAll() {
        return recordRepository.findAll();
    }

    public Record createRecord(RecordDTO recordDTO) throws Exception {
        Record record = new Record();
        record.setStartTime(recordDTO.getStartTime());
        record.setEndTime(recordDTO.getEndTime());
        record.setNotes(recordDTO.getNotes());
        Optional<User> user = userRepository.findById(recordDTO.getUser().getId());
        if (user.isEmpty()) {
            throw new Exception("user not present");
        }
        Optional<Task> task = taskRepository.findById(recordDTO.getTask().getId());
        if (task.isEmpty()) {
            throw new Exception("task not present");
        }
        record.setUser(user.get());
        record.setTask(task.get());
        return recordRepository.save(record);
    }

    public List<Record> findByUserId(int userId) {
        return recordRepository.findByUserId(userId);
    }
}
