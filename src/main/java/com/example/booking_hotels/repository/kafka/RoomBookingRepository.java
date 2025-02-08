package com.example.booking_hotels.repository.kafka;

import com.example.booking_hotels.model.kafka.RoomBookingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomBookingRepository extends MongoRepository<RoomBookingEntity, String> {
}

