package com.example.booking_hotels.mapper;

import com.example.booking_hotels.model.Hotel;
import com.example.booking_hotels.dto.hotel.HotelListResponse;
import com.example.booking_hotels.dto.hotel.HotelRequest;
import com.example.booking_hotels.dto.hotel.HotelResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {
    Hotel requestToHotel(HotelRequest request);

    @Mapping(source = "hotelId", target = "id")
    Hotel requestToHotel(Long hotelId, HotelRequest request);

    HotelResponse hotelToResponse(Hotel hotel);

    default HotelListResponse hotelListToResponseList(List<Hotel> hotels) {
        HotelListResponse response = new HotelListResponse();
        response.setHotels(hotels.stream()
                .map(this::hotelToResponse)
                .collect(Collectors.toList()));
        return response;
    }
}
