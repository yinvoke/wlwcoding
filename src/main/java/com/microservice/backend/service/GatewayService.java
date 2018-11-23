package com.microservice.backend.service;

import com.microservice.backend.entity.Gateway;
import com.microservice.backend.entity.SensorClassify;

import java.util.List;

public interface GatewayService {
    void inset(Gateway gateway);
    Gateway findById(long id);
    List<Gateway> findAll();
    void update(Gateway gateway);
    List<SensorClassify> findClassifyById(long id);
}
