package com.microservice.backend.repository;

import com.microservice.backend.entity.SensorClassify;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorClassifyRepository extends JpaRepository<SensorClassify,Long> {
    SensorClassify findById(long id);
}
