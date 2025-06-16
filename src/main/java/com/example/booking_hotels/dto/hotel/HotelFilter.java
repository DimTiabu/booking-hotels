package com.example.booking_hotels.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelFilter {
    @Builder.Default
    private Integer pageSize = 100;
    @Builder.Default
    private Integer pageNumber = 0;
    private Long id;
    private String name;
    private String title;
    private String city;
    private String address;
    private double distanceFromCenter;
    private double rating;
    private int ratingCount;
}
