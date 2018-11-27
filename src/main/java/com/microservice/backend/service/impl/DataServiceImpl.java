package com.microservice.backend.service.impl;

import com.microservice.backend.entity.Data;
import com.microservice.backend.repository.DataRepository;
import com.microservice.backend.service.DataService;
import org.hibernate.annotations.Source;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
