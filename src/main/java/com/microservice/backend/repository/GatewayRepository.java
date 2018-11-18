package com.microservice.backend.repository;

import com.microservice.backend.entity.Gateway;
import com.microservice.backend.entity.SensorClassify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GatewayRepository extends JpaRepository<Gateway, Long> {
    Gateway findByIp(String ip);
    Gateway findById(long id);

    @Override
    @Query("select gateway from Gateway gateway where gateway.status=1")
    List<Gateway> findAll();

    @Query(value = "SELECT * FROM sensor_classify classify WHERE classify.status=1 AND classify.id IN (SELECT  s.classify_id FROM sensor s where s.gate_id=?1 )  ",nativeQuery = true)
    List<SensorClassify> findClassifyById(long id);
}
