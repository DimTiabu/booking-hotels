package com.example.booking_hotels.web.controller;

import com.example.booking_hotels.mapper.HotelMapper;
import com.example.booking_hotels.model.Hotel;
import com.example.booking_hotels.service.HotelService;
import com.example.booking_hotels.web.model.hotel.HotelFilter;
import com.example.booking_hotels.web.model.hotel.HotelListResponse;
import com.example.booking_hotels.web.model.hotel.HotelRequest;
import com.example.booking_hotels.web.model.hotel.HotelResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotel")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService databaseHotelService;
    private final HotelMapper hotelMapper;

    @GetMapping("/filter")
    @PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<HotelListResponse> filterBy(HotelFilter filter) {
        return ResponseEntity.ok(
                hotelMapper.hotelListToResponseList(
                        databaseHotelService.findAll(filter)));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<HotelListResponse> findAll() {
        return ResponseEntity.ok(
                hotelMapper.hotelListToResponseList(
                        databaseHotelService.findAll()));
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN', 'ROLE_USER')")
    public HotelResponse findById(@PathVariable long id) {
        return hotelMapper.hotelToResponse(
                databaseHotelService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN')")
    public ResponseEntity<HotelResponse> create(@RequestBody @Valid HotelRequest request) {

        Hotel newHotel = databaseHotelService.save(
                hotelMapper.requestToHotel(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hotelMapper.hotelToResponse(newHotel));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN')")
    public ResponseEntity<HotelResponse> update(
            @PathVariable("id") Long hotelId,
            @RequestBody HotelRequest request) {

        Hotel updatedHotel = databaseHotelService.update(
                hotelMapper.requestToHotel(hotelId, request));

        return ResponseEntity.ok(
                hotelMapper.hotelToResponse(updatedHotel));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        databaseHotelService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/rate/{id}")
    @PreAuthorize("hasAnyAuthority( 'ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<HotelResponse> updateRating(
            @PathVariable Long id,
            @RequestParam double newMark) {
        Hotel hotel =  databaseHotelService.updateRating(id, newMark);

        return ResponseEntity.ok(
                hotelMapper.hotelToResponse(hotel));
    }



}
