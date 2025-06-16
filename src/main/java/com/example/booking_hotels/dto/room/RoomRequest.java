package com.example.booking_hotels.dto.room;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomRequest {

    @NotEmpty(message = "Укажите название комнаты")
    private String name;

    @NotEmpty(message = "Добавьте описание комнаты")
    private String description;

    @NotNull(message = "Укажите номер комнаты")
    private Integer number;

    @NotNull(message = "Укажите цену")
    private BigDecimal price;

    @NotNull(message = "Укажите максимальное количество людей, " +
            "которое можно разместить")
    private Integer maxOccupancy;

    @NotNull(message = "Укажите id отеля")
    private Long hotelId;
}
