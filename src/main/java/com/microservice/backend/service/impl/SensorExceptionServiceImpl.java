package com.microservice.backend.service.impl;

import com.microservice.backend.entity.GatewayException;
import com.microservice.backend.entity.SensorException;
import com.microservice.backend.repository.GatewayExceptionRepository;
import com.microservice.backend.repository.SensorExceptionRepository;
import com.microservice.backend.service.SensorExceptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("sensorExceptionService")
public class SensorExceptionServiceImpl implements SensorExceptionService {
    @Resource
    SensorExceptionRepository sensorExceptionRepository;

    @Override
    public List<SensorException> findAll(){
        return sensorExceptionRepository.findAll();
    }

    @Override
    public List<SensorException> findByTime(String dataFrom, String dataTo){

        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date df = null;
        Date dt = null;
        try {
            df = s.parse(dataFrom);
            dt = s.parse(dataTo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sensorExceptionRepository.findByTime(df,dt);
    }

    @Override
    public void insert(SensorException sensorException) {
        sensorExceptionRepository.save(sensorException);
    }
}
