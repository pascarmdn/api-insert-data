package com.example.api_insert_data.repository;

import com.example.api_insert_data.entity.AlertMsgSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertMsgSourceRepository extends JpaRepository<AlertMsgSource, Long> {
}
