package fhtw.timetracker.repository;

import fhtw.timetracker.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Integer> {
}
