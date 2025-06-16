package com.example.booking_hotels.service;

import com.example.booking_hotels.model.Hotel;
import com.example.booking_hotels.dto.hotel.HotelFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {
    List<Hotel> findAll(HotelFilter filter);

    List<Hotel> findAll();

    Hotel findById(Long id);

    Hotel save(Hotel hotel);

    Hotel update(Hotel hotel);

    void deleteById(Long id);

    Hotel updateRating(Long hotelId, double newMark);
}
