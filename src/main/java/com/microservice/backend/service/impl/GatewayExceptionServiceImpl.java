package com.microservice.backend.service.impl;

import com.microservice.backend.entity.GatewayException;
import com.microservice.backend.repository.GatewayExceptionRepository;
import com.microservice.backend.service.GatewayExceptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date df = null;
        Date dt = null;
        try {
             df = s.parse(dataFrom);
             dt = s.parse(dataTo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return GatewayExceptionRepository.findByTime(df,dt);
    }
}
