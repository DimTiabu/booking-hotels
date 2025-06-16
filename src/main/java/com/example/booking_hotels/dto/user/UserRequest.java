package com.example.booking_hotels.dto.user;

import com.example.booking_hotels.model.UserRole;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class UserRequest {
    @NotEmpty(message = "Имя пользователя не может быть пустым")
    private String name;

    @NotEmpty(message = "Пароль не может быть пустым")
    private String password;

    @NotEmpty(message = "Укажите электронную почту")
    private String email;

    @NotEmpty(message = "Укажите роль пользователя")
    private List<UserRole> roles;
}
