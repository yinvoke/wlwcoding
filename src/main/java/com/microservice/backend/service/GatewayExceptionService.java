package com.microservice.backend.service;

import com.microservice.backend.entity.GatewayException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GatewayExceptionService {
    List<GatewayException> findAll();
    Page<GatewayException> findAll(Pageable pageable);
    List<GatewayException> findByTime(String dataFrom, String dataTo, int num);
    void insert(GatewayException gatewayException);
}
