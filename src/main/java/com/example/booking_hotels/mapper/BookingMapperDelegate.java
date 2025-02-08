package com.example.booking_hotels.mapper;

import com.example.booking_hotels.model.Booking;
import com.example.booking_hotels.model.Room;
import com.example.booking_hotels.model.User;
import com.example.booking_hotels.service.RoomService;
import com.example.booking_hotels.service.UserService;
import com.example.booking_hotels.web.model.booking.BookingRequest;
import com.example.booking_hotels.web.model.booking.BookingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class BookingMapperDelegate implements BookingMapper {

    @Autowired
    private UserService databaseUserService;
    @Autowired
    private RoomService databaseRoomService;

    @Override
    public Booking requestToBooking(BookingRequest request, UserDetails userDetails) {
        Room room = databaseRoomService.findById(request.getRoomId());
        User user = databaseUserService.findByName(userDetails.getUsername());

        return Booking.builder()
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .user(user)
                .room(room)
                .build();
    }

    @Override
    public BookingResponse bookingToResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .checkInDate(booking.getCheckInDate())
                .checkOutDate(booking.getCheckOutDate())
                .roomId(booking.getRoom().getId())
                .userId(booking.getUser().getId())
                .build();
    }

    @Override
    public List<BookingResponse> bookingListToReponseList(List<Booking> bookings) {
        return bookings.stream()
                .map(this::bookingToResponse)
                .toList();
    }
}
