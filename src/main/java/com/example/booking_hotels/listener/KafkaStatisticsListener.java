package com.example.booking_hotels.listener;

import com.example.booking_hotels.model.kafka.RoomBookingEntity;
import com.example.booking_hotels.model.kafka.RoomBookingEvent;
import com.example.booking_hotels.model.kafka.UserRegistrationEntity;
import com.example.booking_hotels.model.kafka.UserRegistrationEvent;
import com.example.booking_hotels.repository.kafka.RoomBookingRepository;
import com.example.booking_hotels.repository.kafka.UserRegistrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaStatisticsListener {

    private final UserRegistrationRepository userRegistrationRepository;
    private final RoomBookingRepository roomBookingRepository;

    @KafkaListener(topics = "user-registrations",
            groupId = "statistics-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void handleUserRegistration(UserRegistrationEvent event) {
        log.info("Received UserRegistrationEvent: {}", event);
        UserRegistrationEntity entity = new UserRegistrationEntity();
        entity.setUserId(event.getUserId());
        userRegistrationRepository.save(entity);
    }

    @KafkaListener(topics = "room-bookings",
            groupId = "statistics-group",
            containerFactory = "kafkaListenerContainerFactory")
    public void handleRoomBooking(RoomBookingEvent event) {
        log.info("Received RoomBookingEvent: {}", event);
        RoomBookingEntity entity = new RoomBookingEntity();
        entity.setUserId(event.getUserId());
        entity.setRoomId(event.getRoomId());
        entity.setCheckInDate(event.getCheckInDate());
        entity.setCheckOutDate(event.getCheckOutDate());
        roomBookingRepository.save(entity);
    }
}