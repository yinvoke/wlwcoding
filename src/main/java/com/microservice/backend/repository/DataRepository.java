package com.microservice.backend.repository;

import com.microservice.backend.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data,Long> {
    Data findById(long id);
}
