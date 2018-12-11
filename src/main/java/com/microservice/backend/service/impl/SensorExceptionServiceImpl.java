package com.microservice.backend.service.impl;

import com.microservice.backend.entity.GatewayException;
import com.microservice.backend.entity.SensorException;
import com.microservice.backend.repository.GatewayExceptionRepository;
import com.microservice.backend.repository.SensorExceptionRepository;
import com.microservice.backend.service.SensorExceptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("sensorExceptionService")
public class SensorExceptionServiceImpl implements SensorExceptionService {
    @Resource
    SensorExceptionRepository sensorExceptionRepository;

    @Override
    public List<SensorException> findAll(){
        return sensorExceptionRepository.findAll();
    }
}
