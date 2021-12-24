package fhtw.timetracker.controller;

import fhtw.timetracker.model.RecordDTO;
import fhtw.timetracker.service.RecordService;
import fhtw.timetracker.util.DTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/records")
    public List<RecordDTO> findAllRecords() {
        return recordService.findAll().stream().map(DTOMapper::convertRecordToRecordDTO).collect(Collectors.toList());
    }

    @GetMapping("/records/{userId}")
    public List<RecordDTO> findAllRecords(@PathVariable int userId) {
        return recordService.findByUserId(userId).stream().map(DTOMapper::convertRecordToRecordDTO).collect(Collectors.toList());
    }

    @PostMapping("/records")
    public RecordDTO createRecord(@RequestBody RecordDTO record) throws Exception {
        return DTOMapper.convertRecordToRecordDTO(recordService.createRecord(record));
    }

    @DeleteMapping("/records/{recordId}")
    public boolean deleteRecord(@PathVariable int recordId) {
        return recordService.deleteRecord(recordId);
    }
}
