package com.microservice.backend.service.impl;

import com.microservice.backend.entity.Gateway;
import com.microservice.backend.entity.SensorClassify;
import com.microservice.backend.repository.GatewayRepository;
import com.microservice.backend.service.GatewayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("gatewayService")
public class GatewayServiceImpl implements GatewayService{

    @Resource
    private GatewayRepository gatewayRepository;

    @Override
    public void inset(Gateway gateway) {
        gatewayRepository.save(gateway);
    }

    @Override
    public Gateway findById(long id) {
        return gatewayRepository.findById(id);
    }

    @Override
    public List<Gateway> findAll() {
        return gatewayRepository.findAll();
    }

    @Override
    public List<SensorClassify> findClassifyById(long id) {
        return gatewayRepository.findClassifyById(id);
    }
}
