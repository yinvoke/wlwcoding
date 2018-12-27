package com.microservice.backend.repository;

import com.microservice.backend.entity.GatewayException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface GatewayExceptionRepository extends JpaRepository<GatewayException,Long>, JpaSpecificationExecutor<GatewayException> {
    @Override
    @Query("SELECT ge from GatewayException ge")
    List<GatewayException> findAll();

    Page<GatewayException> findAll(Pageable pageable);

    @Query(value = "SELECT * from gateway_exception ge where ge.time>?1 and ge.time<?2",nativeQuery = true)
    Page<GatewayException> findByTime(Date dataFrom, Date dataTo, Pageable pageable);
}
