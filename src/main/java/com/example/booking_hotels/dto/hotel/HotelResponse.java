package com.example.booking_hotels.dto.hotel;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelResponse {

    private Long id;
    private String name;
    private String title;
    private String city;
    private String address;
    private double distanceFromCenter;
    private double rating;
    private int ratingCount;
}

