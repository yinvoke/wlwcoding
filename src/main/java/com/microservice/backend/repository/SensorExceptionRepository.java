package com.microservice.backend.repository;

import com.microservice.backend.entity.SensorException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SensorExceptionRepository extends JpaRepository<SensorException,Long> {
    @Override
    @Query("SELECT sensor_exception from SensorException sensor_exception where sensor_exception.status=0")
    List<SensorException> findAll();

    @Query(value = "SELECT sensor_exception from SensorException sensor_exception where sensor_exception.status=0 and sensor_exception.time>?1 and sensor_exception.time<?2",nativeQuery = true)
    List<SensorException> findByTime(String dataFrom, String dataTo);
}
