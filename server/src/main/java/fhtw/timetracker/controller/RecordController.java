package fhtw.timetracker.controller;

import fhtw.timetracker.model.Record;
import fhtw.timetracker.service.RecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
