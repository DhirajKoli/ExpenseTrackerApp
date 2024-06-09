package com.example.Tracker.repository;

import com.example.Tracker.model.Record;
import com.example.Tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findByUser(User user);
}
