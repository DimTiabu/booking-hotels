package com.example.booking_hotels.model.kafka;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "room_bookings")
public class RoomBookingEntity {
    @Id
    private String id;
    private String userId;
    private String roomId;
    private String checkInDate;
    private String checkOutDate;
}
