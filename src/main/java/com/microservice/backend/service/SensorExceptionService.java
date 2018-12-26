package com.microservice.backend.service;

import com.microservice.backend.entity.Sensor;
import com.microservice.backend.entity.SensorException;

import java.util.List;

public interface SensorExceptionService {
    List<SensorException> findAll();
    List<SensorException> findByTime(String dataFrom, String dataTo, int num);
    void insert(SensorException sensorException);
}
