package com.example.booking_hotels.repository.impl;

import com.example.booking_hotels.model.Booking;
import com.example.booking_hotels.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}