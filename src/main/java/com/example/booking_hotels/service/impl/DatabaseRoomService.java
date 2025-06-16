package com.example.booking_hotels.service.impl;

import com.example.booking_hotels.exception.EntityNotFoundException;
import com.example.booking_hotels.model.Room;
import com.example.booking_hotels.repository.RoomSpecification;
import com.example.booking_hotels.repository.impl.RoomRepository;
import com.example.booking_hotels.service.RoomService;
import com.example.booking_hotels.utils.BeanUtils;
import com.example.booking_hotels.dto.room.RoomFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseRoomService implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<Room> findAll(RoomFilter filter) {
        return roomRepository.findAll(
                RoomSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(),
                        filter.getPageSize())).getContent();
    }
    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Комната с ID " + id + " не найдена"));
    }

    @Override
    public Room save(Room room) {

        return roomRepository.save(room);
    }

    @Override
    public Room update(Room room) {
        Room existedRoom = findById(room.getId());
        BeanUtils.copyNonNullProperties(room, existedRoom);

        return roomRepository.save(room);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        roomRepository.deleteById(id);
    }
}
