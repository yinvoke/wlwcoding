package com.microservice.backend.service.impl;

import com.microservice.backend.entity.GatewayException;
import com.microservice.backend.repository.GatewayExceptionRepository;
import com.microservice.backend.service.GatewayExceptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("GatewayExceptionService")
public class GatewayExceptionServiceImpl implements GatewayExceptionService {
    @Resource
    GatewayExceptionRepository GatewayExceptionRepository;

    @Override
    public List<GatewayException> findAll(){
        return GatewayExceptionRepository.findAll();
    }

    @Override
    public List<GatewayException> findByTime(String dataFrom, String dataTo){
        return GatewayExceptionRepository.findByTime(dataFrom, dataTo);
    }
}
