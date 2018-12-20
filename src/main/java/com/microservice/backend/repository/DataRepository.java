package com.microservice.backend.repository;

import com.microservice.backend.entity.Data;
import com.microservice.backend.entity.Sensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;

import static org.hibernate.jpa.QueryHints.HINT_COMMENT;

public interface DataRepository extends JpaRepository<Data,Long> {
    Data findById(long id);

//    @QueryHints(value = { @QueryHint(name = HINT_COMMENT, value = "a query for pageable")})
//    @Query("select d from Data d where d.sensor=:sensor")
    Page<Data> findBySensor(Sensor sensor, Pageable pageable);
}
