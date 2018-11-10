package com.microservice.backend.repository;

import com.microservice.backend.entity.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatewayRepository extends JpaRepository<Gateway, Long> {
    Gateway findByIp(String ip);
    Gateway findById(long id);
}
