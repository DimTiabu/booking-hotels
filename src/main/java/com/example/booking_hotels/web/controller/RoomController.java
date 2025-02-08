package com.example.booking_hotels.web.controller;

import com.example.booking_hotels.mapper.RoomMapper;
import com.example.booking_hotels.model.Room;
import com.example.booking_hotels.service.RoomService;
import com.example.booking_hotels.web.model.room.RoomFilter;
import com.example.booking_hotels.web.model.room.RoomListResponse;
import com.example.booking_hotels.web.model.room.RoomRequest;
import com.example.booking_hotels.web.model.room.RoomResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService databaseRoomService;
    private final RoomMapper roomMapper;

    @GetMapping
    @PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<RoomListResponse> findAllWithFilter(RoomFilter filter) {
        return ResponseEntity.ok(
                roomMapper.roomListToResponseList(
                        databaseRoomService.findAll(filter)));
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN', 'ROLE_USER')")
    public RoomResponse findById(@PathVariable long id) {
        return roomMapper.roomToResponse(
                databaseRoomService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN')")
    public ResponseEntity<RoomResponse> create(@RequestBody @Valid RoomRequest request) {

        Room newRoom = databaseRoomService.save(
                roomMapper.requestToRoom(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomMapper.roomToResponse(newRoom));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN')")
    public ResponseEntity<RoomResponse> update(
            @PathVariable("id") Long roomId,
            @RequestBody RoomRequest request) {

        Room updatedRoom = databaseRoomService.update(
                roomMapper.requestToRoom(roomId, request));

        return ResponseEntity.ok(
                roomMapper.roomToResponse(updatedRoom));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        databaseRoomService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
