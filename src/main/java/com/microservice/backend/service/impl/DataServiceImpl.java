package com.microservice.backend.service.impl;

import com.microservice.backend.entity.Data;
import com.microservice.backend.entity.Sensor;
import com.microservice.backend.repository.DataRepository;
import com.microservice.backend.service.DataService;
import org.hibernate.annotations.Source;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("dataService")
public class DataServiceImpl implements DataService {
    @Resource
    DataRepository dataRepository;

    @Override
    public void inserts(List<Data> datas) {
        for (Data data:datas){
            dataRepository.save(data);
        }
    }

    @Override
    public void insert(Data data) {
        dataRepository.save(data);
    }

    @Override
    public List<Data> findNewData(int num,Sensor sensor) {
        Page<Data> page = dataRepository.findBySensor(sensor,PageRequest.of(0, num, Sort.by(Sort.Direction.DESC, "time")));
        Iterator<Data> all = page.iterator();
        List<Data> list = new ArrayList<>();
        while(all.hasNext()){
            list.add(all.next());
        }

        return list;
    }
}
