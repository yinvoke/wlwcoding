package com.microservice.backend.repository;

import com.microservice.backend.entity.SensorException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SensorExceptionRepository extends JpaRepository<SensorException,Long> {
    @Override
    @Query("SELECT se from SensorException se")
    List<SensorException> findAll();

    @Query(value = "SELECT * from sensor_exception se where se.time>?1 and se.time<?2",nativeQuery = true)
    List<SensorException> findByTime(Date dataFrom, Date dataTo);
}
