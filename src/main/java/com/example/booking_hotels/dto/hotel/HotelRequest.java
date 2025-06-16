package com.example.booking_hotels.dto.hotel;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelRequest {

    @NotEmpty(message = "Укажите название отеля")
    private String name;
    @NotEmpty(message = "Укажите заголовок объявления")
    private String title;
    @NotEmpty(message = "Укажите город, в котором расположен отель")
    private String city;
    @NotEmpty(message = "Укажите адрес отеля")
    private String address;
    @NotNull(message = "Укажите расстояние от центра города")
    private double distanceFromCenter;
}
