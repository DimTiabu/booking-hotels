package com.example.booking_hotels.repository.kafka;

import com.example.booking_hotels.model.kafka.UserRegistrationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepository extends MongoRepository<UserRegistrationEntity, String> {
}

