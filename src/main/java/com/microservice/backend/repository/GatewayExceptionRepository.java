package com.microservice.backend.repository;

import com.microservice.backend.entity.GatewayException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface GatewayExceptionRepository extends JpaRepository<GatewayException,Long>{
    @Override
    @Query("SELECT ge from GatewayException ge")
    List<GatewayException> findAll();

    @Query(value = "SELECT * from gateway_exception ge where ge.time>?1 and ge.time<?2",nativeQuery = true)
    List<GatewayException> findByTime(Date dataFrom, Date dataTo);
}
