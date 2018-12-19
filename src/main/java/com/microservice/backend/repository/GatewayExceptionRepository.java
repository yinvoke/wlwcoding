package com.microservice.backend.repository;

import com.microservice.backend.entity.GatewayException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface GatewayExceptionRepository extends JpaRepository<GatewayException,Long>{
    @Override
    @Query("SELECT gateway_exception from GatewayException gateway_exception")
    List<GatewayException> findAll();

    @Query(value = "SELECT gateway_exception from GatewayException gateway_exception where gateway_exception.time>?1 and gateway_exception.time<?2",nativeQuery = true)
    List<GatewayException> findByTime(Date dataFrom, Date dataTo);
}
