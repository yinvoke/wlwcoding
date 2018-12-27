package com.microservice.backend.service;

import com.microservice.backend.entity.GatewayException;
import com.microservice.backend.entity.Sensor;
import com.microservice.backend.entity.SensorException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SensorExceptionService {
    List<SensorException> findAll();
    Page<SensorException> findAll(Pageable pageable);
    List<SensorException> findByTime(String dataFrom, String dataTo, int num);
    void insert(SensorException sensorException);
}
