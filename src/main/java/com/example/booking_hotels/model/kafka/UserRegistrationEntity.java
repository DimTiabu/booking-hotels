package com.example.booking_hotels.model.kafka;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "user_registrations")
public class UserRegistrationEntity {
    @Id
    private String id;
    private Long userId;
}