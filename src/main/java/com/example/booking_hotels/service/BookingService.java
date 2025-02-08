package com.example.booking_hotels.service;

import com.example.booking_hotels.model.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {

    Booking bookRoom(Booking booking);

    List<Booking> getAllBookings();
}
