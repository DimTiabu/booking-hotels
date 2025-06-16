package com.example.booking_hotels.dto.user;

import com.example.booking_hotels.model.UserRole;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private long id;
    private String name;
    private String password;
    private String email;
    private List<UserRole> roles;
}
