package com.microservice.backend.service.impl;

import com.microservice.backend.entity.SensorException;
import com.microservice.backend.repository.SensorExceptionRepository;
import com.microservice.backend.service.SensorExceptionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
    public Page<SensorException> findAll(Pageable pageable){
        return sensorExceptionRepository.findAll(pageable);
    }

    @Override
    public List<SensorException> findByTime(String dataFrom, String dataTo, int num){

        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date df = null;
        Date dt = null;
        try {
            df = s.parse(dataFrom);
            dt = s.parse(dataTo);
        }catch (Exception e){
            e.printStackTrace();
        }
        Page<SensorException> page = sensorExceptionRepository.findByTime(df,dt, PageRequest.of(0, num, Sort.by(Sort.Direction.DESC, "time")));
        Iterator<SensorException> all = page.iterator();
        List<SensorException> list = new ArrayList<>();
        while(all.hasNext()){
            list.add(all.next());
        }
        return list;
    }

    @Override
    public void insert(SensorException sensorException) {
        sensorExceptionRepository.save(sensorException);
    }
}
