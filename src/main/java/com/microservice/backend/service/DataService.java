package com.microservice.backend.service;

import com.microservice.backend.entity.Data;

import java.util.List;

public interface DataService {
    void inserts(List<Data> datas);
    void insert(Data data);
}
