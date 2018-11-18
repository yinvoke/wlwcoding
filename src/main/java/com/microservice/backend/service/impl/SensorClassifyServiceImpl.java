package com.microservice.backend.service.impl;

import com.microservice.backend.entity.SensorClassify;
import com.microservice.backend.repository.SensorClassifyRepository;
import com.microservice.backend.service.SensorClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("sensorClassifyService")
public class SensorClassifyServiceImpl implements SensorClassifyService {
    @Resource
    SensorClassifyRepository sensorClassifyRepository;

    @Override
    public List<SensorClassify> findSensorClassifiesByGateway(long id) {
        return sensorClassifyRepository.findSensorClassifiesByGateway(id);
    }

    @Override
    public List<SensorClassify> findAll() {
        return sensorClassifyRepository.findAll();
    }

    @Override
    public void insert(SensorClassify sensorClassify) {
        sensorClassifyRepository.save(sensorClassify);
    }

    @Override
    public SensorClassify findById(long id) {
        return sensorClassifyRepository.findById(id);
    }
}
