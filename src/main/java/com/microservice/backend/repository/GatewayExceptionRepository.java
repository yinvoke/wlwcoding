package com.microservice.backend.repository;

import com.microservice.backend.entity.GatewayException;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GatewayExceptionRepository extends JpaRepository<GatewayException,Long>{
    GatewayException findById(long id);
}
