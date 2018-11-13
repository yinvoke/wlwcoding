package com.microservice.backend.repository;

import com.microservice.backend.entity.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GatewayRepository extends JpaRepository<Gateway, Long> {
    Gateway findByIp(String ip);
    Gateway findById(long id);

    @Override
    @Query("select gateway from Gateway gateway where gateway.status=1")
    List<Gateway> findAll();
}
