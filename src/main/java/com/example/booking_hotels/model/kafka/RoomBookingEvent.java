package com.example.booking_hotels.model.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingEvent {
    private String userId;
    private String roomId;
    private String checkInDate;
    private String checkOutDate;
}