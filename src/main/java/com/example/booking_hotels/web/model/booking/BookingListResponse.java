package com.example.booking_hotels.web.model.booking;

import com.example.booking_hotels.web.model.hotel.HotelResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingListResponse {
    private List<BookingResponse> bookings;
}
