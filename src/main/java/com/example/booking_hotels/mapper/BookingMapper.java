package com.example.booking_hotels.mapper;

import com.example.booking_hotels.model.Booking;
import com.example.booking_hotels.dto.booking.BookingListResponse;
import com.example.booking_hotels.dto.booking.BookingRequest;
import com.example.booking_hotels.dto.booking.BookingResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@DecoratedWith(BookingMapperDelegate.class)
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {

    Booking requestToBooking(BookingRequest request, UserDetails userDetails);

    BookingResponse bookingToResponse(Booking booking);

    List<BookingResponse> bookingListToReponseList(List<Booking> bookings);

    default BookingListResponse bookingListToBookingListResponse(List<Booking> bookings) {
        BookingListResponse response = new BookingListResponse();
        response.setBookings(bookingListToReponseList(bookings));
        return response;
    }
}
