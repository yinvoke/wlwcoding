package com.microservice.backend.repository;

import com.microservice.backend.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Sensor findById(long id);
}
