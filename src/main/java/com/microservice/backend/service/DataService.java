package com.microservice.backend.service;

import com.microservice.backend.entity.Data;
import com.microservice.backend.entity.Sensor;

import java.util.List;

public interface DataService {
    void inserts(List<Data> datas);
    void insert(Data data);
    List<Data> findNewData(int num,Sensor sensor);
}
