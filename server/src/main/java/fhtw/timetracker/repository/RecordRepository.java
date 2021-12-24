package fhtw.timetracker.repository;

import fhtw.timetracker.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Integer> {

    List<Record> findByUserId(int userId);
}
