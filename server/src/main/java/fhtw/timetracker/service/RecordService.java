package fhtw.timetracker.service;

import fhtw.timetracker.model.Record;
import fhtw.timetracker.repository.RecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<Record> findAll() {
        return recordRepository.findAll();
    }
}
