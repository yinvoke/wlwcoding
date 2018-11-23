package com.microservice.backend.repository;

import com.microservice.backend.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Sensor findById(long id);

    @Override
    @Query("select sensor from Sensor sensor where sensor.status=1")
    List<Sensor> findAll();
}
