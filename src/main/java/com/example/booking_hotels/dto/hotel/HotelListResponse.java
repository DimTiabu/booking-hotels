package com.example.booking_hotels.dto.hotel;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelListResponse {
    private List<HotelResponse> hotels;
}
