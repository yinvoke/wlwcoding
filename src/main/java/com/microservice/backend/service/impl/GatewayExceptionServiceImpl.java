package com.microservice.backend.service.impl;

import com.microservice.backend.entity.GatewayException;
import com.microservice.backend.repository.GatewayExceptionRepository;
import com.microservice.backend.service.GatewayExceptionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service("GatewayExceptionService")
public class GatewayExceptionServiceImpl implements GatewayExceptionService {
    @Resource
    GatewayExceptionRepository gatewayExceptionRepository;

    @Override
    public List<GatewayException> findAll(){
        return gatewayExceptionRepository.findAll();
    }

    @Override
    public List<GatewayException> findByTime(String dataFrom, String dataTo, int num){
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date df = null;
        Date dt = null;
        try {
             df = s.parse(dataFrom);
             dt = s.parse(dataTo);
        }catch (Exception e){
            e.printStackTrace();
        }
        Page<GatewayException> page = gatewayExceptionRepository.findByTime(df,dt,PageRequest.of(0, num, Sort.by(Sort.Direction.DESC, "time")));
        Iterator<GatewayException> all = page.iterator();
        List<GatewayException> list = new ArrayList<>();
        while(all.hasNext()){
            list.add(all.next());
        }
        return list;
    }

    @Override
    public void insert(GatewayException gatewayException) {
        gatewayExceptionRepository.save(gatewayException);
    }
}
