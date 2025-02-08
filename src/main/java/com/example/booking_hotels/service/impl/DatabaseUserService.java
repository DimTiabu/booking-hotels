package com.example.booking_hotels.service.impl;

import com.example.booking_hotels.exception.EntityNotFoundException;
import com.example.booking_hotels.model.User;
import com.example.booking_hotels.repository.impl.UserRepository;
import com.example.booking_hotels.service.UserService;
import com.example.booking_hotels.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseUserService implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Пользователь с ID " + id + " не найден"));
    }

    @Override
    public User findByName(String username) {
        return userRepository.findByName(username).orElseThrow(
                () -> new EntityNotFoundException(
                        "Пользователь с именем " + username + " не найден"));
    }

    @Override
    public User save(User user) {
        String name = user.getName();
        String email = user.getEmail();
        if (existsUserByName(name)) {
            throw new EntityNotFoundException(
                    "Пользователь с именем " + name + " уже существует");
        }
        if (existsUserByEmail(email)) {
            throw new EntityNotFoundException(
                    "Пользователь с электронной почтой" + email + " уже существует");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User existedUser = findById(user.getId());
        BeanUtils.copyNonNullProperties(user, existedUser);

        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private boolean existsUserByName(String name){
        return userRepository.existsUserByName(name);
    }

    private boolean existsUserByEmail(String email){
        return userRepository.existsUserByEmail(email);
    }

}
