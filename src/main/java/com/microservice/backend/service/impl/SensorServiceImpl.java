package com.microservice.backend.service.impl;

import com.microservice.backend.entity.Sensor;
import com.microservice.backend.repository.SensorRepository;
import com.microservice.backend.service.SensorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("sensorService")
public class SensorServiceImpl implements SensorService{
    @Resource
    SensorRepository sensorRepository;

    @Override
    public void insert(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    @Override
    public void update(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    @Override
    public Sensor findById(long id) {
        return sensorRepository.findById(id);
    }
}
