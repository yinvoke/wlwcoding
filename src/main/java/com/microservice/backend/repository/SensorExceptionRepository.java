package com.microservice.backend.repository;

import com.microservice.backend.entity.SensorException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SensorExceptionRepository extends JpaRepository<SensorException,Long> {
    @Override
    @Query("SELECT id,description,status,time,sensor_id from sensor_exception")
    List<SensorException> findAll();
}
