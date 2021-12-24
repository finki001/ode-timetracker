package fhtw.timetracker.controller;

import fhtw.timetracker.model.Record;
import fhtw.timetracker.model.RecordDTO;
import fhtw.timetracker.service.RecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/records")
    public List<Record> findAllRecords() {
        return recordService.findAll();
    }

    @GetMapping("/records/{userId}")
    public List<Record> findAllRecords(@PathVariable int userId) {
        return recordService.findByUserId(userId);
    }

    @PostMapping("/records")
    public Record addRecord(@RequestBody RecordDTO record) throws Exception {
        return recordService.createRecord(record);
    }
}
