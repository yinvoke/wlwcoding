package com.microservice.backend.service;

import com.microservice.backend.entity.GatewayException;

import java.util.List;

public interface GatewayExceptionService {
    List<GatewayException> findAll();
}
