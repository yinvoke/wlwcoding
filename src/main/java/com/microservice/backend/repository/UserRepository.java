package com.microservice.backend.repository;

import com.microservice.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String Username);
    User findByUsernameAndPassword(String Username,String Password);
    User findById(long id);
}

