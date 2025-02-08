package com.example.booking_hotels.repository.impl;

import com.example.booking_hotels.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findByName(String name);

    boolean existsUserByName(String name);
    boolean existsUserByEmail(String email);

}
