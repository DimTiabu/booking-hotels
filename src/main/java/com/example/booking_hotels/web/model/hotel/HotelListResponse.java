package com.example.booking_hotels.web.model.hotel;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelListResponse {
    private List<HotelResponse> hotels;
}
