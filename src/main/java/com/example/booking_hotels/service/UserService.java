package com.example.booking_hotels.service;

import com.example.booking_hotels.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> findAll();

    User findById(Long id);

    User findByName(String username);

    User save(User client);

    User update(User client);

    void deleteById(Long id);

}
