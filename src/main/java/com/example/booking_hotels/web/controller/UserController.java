package com.example.booking_hotels.web.controller;

import com.example.booking_hotels.mapper.UserMapper;
import com.example.booking_hotels.model.User;
import com.example.booking_hotels.model.kafka.UserRegistrationEvent;
import com.example.booking_hotels.service.UserService;
import com.example.booking_hotels.web.model.user.UserListResponse;
import com.example.booking_hotels.web.model.user.UserRequest;
import com.example.booking_hotels.web.model.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService databaseUserService;
    private final UserMapper userMapper;
    private final KafkaTemplate<String, UserRegistrationEvent> kafkaTemplate;

    @GetMapping
    @PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserListResponse> findAll() {
        return ResponseEntity.ok(
                userMapper.userListToResponseList(
                        databaseUserService.findAll()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN', 'ROLE_USER')")
    public UserResponse findById(@PathVariable long id) {
        return userMapper.userToResponse(databaseUserService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest request) {
        User newUser = databaseUserService.save(
                userMapper.requestToUser(request));

        kafkaTemplate.send("user-registrations",
                new UserRegistrationEvent(newUser.getId()));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(newUser));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserResponse> update(
            @PathVariable("id") Long userId,
            @RequestBody UserRequest request) {

        User updatedUser = databaseUserService.update(
                userMapper.requestToUser(userId, request));

        return ResponseEntity.ok(userMapper.userToResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        databaseUserService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
