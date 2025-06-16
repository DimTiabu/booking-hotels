package com.example.booking_hotels.service.impl;

import com.example.booking_hotels.exception.EntityNotFoundException;
import com.example.booking_hotels.model.Hotel;
import com.example.booking_hotels.repository.HotelSpecification;
import com.example.booking_hotels.repository.impl.HotelRepository;
import com.example.booking_hotels.service.HotelService;
import com.example.booking_hotels.utils.BeanUtils;
import com.example.booking_hotels.dto.hotel.HotelFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseHotelService implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public List<Hotel> findAll(HotelFilter filter) {
        return hotelRepository.findAll(
                HotelSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(),
                        filter.getPageSize())).getContent();
    }
    @Override
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel findById(Long id) {
        return hotelRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Отель с ID " + id + " не найден"));
    }

    @Override
    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel update(Hotel hotel) {
        Hotel existedHotel = findById(hotel.getId());
        BeanUtils.copyNonNullProperties(hotel, existedHotel);

        return hotelRepository.save(hotel);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        hotelRepository.deleteById(id);
    }

    @Override
    public Hotel updateRating(Long hotelId, double newMark) {
        Hotel hotel = findById(hotelId);

        if (newMark < 1 || newMark > 5) {
            throw new IllegalArgumentException("Оценка должна быть в диапазоне от 1 до 5");
        }

        double rating = hotel.getRating();
        int numberOfRatings = hotel.getRatingCount();

        double totalRating = rating * numberOfRatings;
        totalRating = totalRating + newMark;

        numberOfRatings += 1;

        double newRating = Math.round((totalRating / numberOfRatings) * 10.0) / 10.0;

        hotel.setRating(newRating);
        hotel.setRatingCount(numberOfRatings);

        return hotelRepository.save(hotel);
    }

}
