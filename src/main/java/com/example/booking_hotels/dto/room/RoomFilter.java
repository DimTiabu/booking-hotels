package com.example.booking_hotels.dto.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomFilter {
    @Builder.Default
    private Integer pageSize = 100;
    @Builder.Default
    private Integer pageNumber = 0;
    private Long id;
    private String name;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer maxOccupancy;
    private Long hotelId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}