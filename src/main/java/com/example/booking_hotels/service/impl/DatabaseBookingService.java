package com.example.booking_hotels.service.impl;

import com.example.booking_hotels.exception.BookingException;
import com.example.booking_hotels.model.Booking;
import com.example.booking_hotels.model.Room;
import com.example.booking_hotels.repository.impl.BookingRepository;
import com.example.booking_hotels.service.BookingService;
import com.example.booking_hotels.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DatabaseBookingService implements BookingService {

    private final BookingRepository bookingRepository;
    private final RoomService roomService;

    @Override
    public Booking bookRoom(Booking booking) {

        Room room = booking.getRoom();
        LocalDate checkInDate = booking.getCheckInDate();
        LocalDate checkOutDate = booking.getCheckOutDate();

        List<LocalDate> unavailableDates = new ArrayList<>();
        List<LocalDate> datesForBooking = new ArrayList<>();
        for (LocalDate date = checkInDate; date.isBefore(checkOutDate); date = date.plusDays(1)) {
            if (room.getUnavailableDates().contains(date)) {
                unavailableDates.add(date);
                break;
            } else {
                datesForBooking.add(date);
            }
        }

        if (unavailableDates.isEmpty()) {
            room.getUnavailableDates().addAll(datesForBooking);
            roomService.update(room);
            return bookingRepository.save(booking);
        } else {
            throw new BookingException(
                    "Невозможно забронировать комнату на эти даты: " +
                    unavailableDates.stream()
                            .map(LocalDate::toString)
                            .collect(Collectors.joining(", ")));
        }
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
