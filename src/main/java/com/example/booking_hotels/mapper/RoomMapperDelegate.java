package com.example.booking_hotels.mapper;

import com.example.booking_hotels.model.Hotel;
import com.example.booking_hotels.model.Room;
import com.example.booking_hotels.service.HotelService;
import com.example.booking_hotels.dto.room.RoomRequest;
import com.example.booking_hotels.dto.room.RoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class RoomMapperDelegate implements RoomMapper {

    @Autowired
    private HotelService databaseHotelService;

    @Override
    public Room requestToRoom(RoomRequest request) {
        Hotel hotel = databaseHotelService.findById(request.getHotelId());
        return Room.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .maxOccupancy(request.getMaxOccupancy())
                .number(request.getNumber())
                .hotel(hotel)
                .build();
    }

    @Override
   public Room requestToRoom(Long roomId, RoomRequest request) {
        Room room = requestToRoom(request);
        room.setId(roomId);
        return room;
    }


    @Override
    public RoomResponse roomToResponse(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .description(room.getDescription())
                .number(room.getNumber())
                .hotelName(room.getHotel().getName())
                .maxOccupancy(room.getMaxOccupancy())
                .name(room.getName())
                .price(room.getPrice())
                .unavailableDates(room.getUnavailableDates())
                .build();
    }
}
