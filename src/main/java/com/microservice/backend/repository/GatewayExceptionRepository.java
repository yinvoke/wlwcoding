package com.microservice.backend.repository;

import com.microservice.backend.entity.GatewayException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface GatewayExceptionRepository extends JpaRepository<GatewayException,Long>{
    @Override
    @Query("SELECT id,description,status,time,gateway_id from gateway_exception")
    List<GatewayException> findAll();
}
