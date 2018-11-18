package com.microservice.backend.repository;

import com.microservice.backend.entity.SensorClassify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SensorClassifyRepository extends JpaRepository<SensorClassify,Long> {
    SensorClassify findById(long id);

    @Query(value = "SELECT * FROM sensor_classify classify WHERE classify.status=1 AND classify.id IN (SELECT  s.classify_id FROM sensor s where s.gate_id=?1 )  ",nativeQuery = true)
    List<SensorClassify> findSensorClassifiesByGateway(long id);

    @Override
    @Query("SELECT classify from SensorClassify classify where classify.status=1")
    List<SensorClassify> findAll();
}
