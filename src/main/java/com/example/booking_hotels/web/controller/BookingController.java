package com.example.booking_hotels.web.controller;

import com.example.booking_hotels.mapper.BookingMapper;
import com.example.booking_hotels.model.Booking;
import com.example.booking_hotels.model.kafka.RoomBookingEvent;
import com.example.booking_hotels.service.BookingService;
import com.example.booking_hotels.web.model.booking.BookingListResponse;
import com.example.booking_hotels.web.model.booking.BookingRequest;
import com.example.booking_hotels.web.model.booking.BookingResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService databaseBookingService;
    private final BookingMapper bookingMapper;
    private final KafkaTemplate<String, RoomBookingEvent> kafkaTemplate;

    @GetMapping
    @PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN')")
    public ResponseEntity<BookingListResponse> findAll() {
        return ResponseEntity.ok(
                bookingMapper.bookingListToBookingListResponse(
                        databaseBookingService.getAllBookings()));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<BookingResponse> create(
            @RequestBody @Valid BookingRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        Booking booking = databaseBookingService.bookRoom(
                bookingMapper.requestToBooking(request, userDetails));

        kafkaTemplate.send("room-bookings",
                new RoomBookingEvent(
                        booking.getUser().getId().toString(),
                        booking.getRoom().getId().toString(),
                        booking.getCheckInDate().toString(),
                        booking.getCheckOutDate().toString()));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookingMapper.bookingToResponse(booking));
    }
}
