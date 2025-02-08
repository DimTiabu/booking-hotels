package com.example.booking_hotels.web.model.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponse {

    private Long id;
    private String name;
    private String description;
    private Integer number;
    private BigDecimal price;
    private Integer maxOccupancy;
    private List<LocalDate> unavailableDates;
    private String hotelName;
}

