package com.microservice.backend.repository;

import com.microservice.backend.entity.SensorException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorExceptionRepository extends JpaRepository<SensorException,Long> {
    SensorException findById(long id);
}
