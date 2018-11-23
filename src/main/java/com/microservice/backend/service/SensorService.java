package com.microservice.backend.service;

import com.microservice.backend.entity.Sensor;

public interface SensorService {
    void insert(Sensor sensor);
    Sensor findById(long id);
    void update(Sensor sensor);
}
