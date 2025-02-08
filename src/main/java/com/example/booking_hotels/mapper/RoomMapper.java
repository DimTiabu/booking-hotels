package com.example.booking_hotels.mapper;

import com.example.booking_hotels.model.Room;
import com.example.booking_hotels.web.model.room.RoomListResponse;
import com.example.booking_hotels.web.model.room.RoomRequest;
import com.example.booking_hotels.web.model.room.RoomResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(RoomMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper {

    Room requestToRoom(RoomRequest request);

    @Mapping(source = "roomId", target = "id")
    Room requestToRoom(Long roomId, RoomRequest request);

    RoomResponse roomToResponse(Room room);

    default RoomListResponse roomListToResponseList(List<Room> rooms) {
        RoomListResponse response = new RoomListResponse();
        response.setRooms(rooms.stream()
                .map(this::roomToResponse)
                .collect(Collectors.toList()));
        return response;
    }
}
