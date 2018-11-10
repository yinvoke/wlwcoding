package com.microservice.backend.service;

import com.microservice.backend.entity.Gateway;

public interface GatewayService {
    void inset(Gateway gateway);
    Gateway findById(long id);
}
