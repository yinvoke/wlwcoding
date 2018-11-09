package com.microservice.backend.service;

import com.microservice.backend.entity.User;
import java.util.List;


public interface UserService {

    void insert(User user);

    void update(User user);

    void delete(long id);

    User findById(long id);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username,String password);
    List<User> findAll();
}
