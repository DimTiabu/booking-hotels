package com.example.booking_hotels.service;

import com.example.booking_hotels.model.Room;
import com.example.booking_hotels.web.model.room.RoomFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {
    List<Room> findAll(RoomFilter filter);

    Room findById(Long id);

    Room save(Room client);

    Room update(Room client);

    void deleteById(Long id);

}
