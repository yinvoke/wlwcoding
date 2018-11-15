package com.microservice.backend.service;

import com.microservice.backend.entity.SensorClassify;

public interface SensorClassifyService {
    void insert(SensorClassify sensorClassify);
    SensorClassify findById(long id);
}
