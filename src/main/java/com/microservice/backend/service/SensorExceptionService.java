package com.microservice.backend.service;

import com.microservice.backend.entity.SensorException;

import java.util.List;

public interface SensorExceptionService {
    List<SensorException> findAll();
}
