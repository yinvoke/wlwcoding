package com.microservice.backend.service;

import com.microservice.backend.entity.SensorClassify;

import java.util.List;

public interface SensorClassifyService {
    void insert(SensorClassify sensorClassify);
    SensorClassify findById(long id);
    List<SensorClassify> findSensorClassifiesByGateway(long id);
    List<SensorClassify> findAll();
}
