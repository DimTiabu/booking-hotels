package com.example.booking_hotels.repository;

import com.example.booking_hotels.model.Room;
import com.example.booking_hotels.web.model.room.RoomFilter;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface RoomSpecification {

    static Specification<Room> withFilter(RoomFilter roomFilter) {
        return Specification
                .where(byId(roomFilter.getId()))
                .and(byName(roomFilter.getName()))
                .and(byMinPrice(roomFilter.getMinPrice()))
                .and(byMaxPrice(roomFilter.getMaxPrice()))
                .and(byMaxOccupancy(roomFilter.getMaxOccupancy()))
                .and(byHotelId(roomFilter.getHotelId()))
                .and(byAvailability(roomFilter.getCheckInDate(), roomFilter.getCheckOutDate()));
    }

    static Specification<Room> byId(Long id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("id"), id);
        };
    }

    static Specification<Room> byName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(root.get("name"), "%" + name + "%");
        };
    }

    static Specification<Room> byMinPrice(BigDecimal minPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null) {
                return null;
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
        };
    }

    static Specification<Room> byMaxPrice(BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (maxPrice == null) {
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }

    static Specification<Room> byMaxOccupancy(Integer maxOccupancy) {
        return (root, query, criteriaBuilder) -> {
            if (maxOccupancy == null || maxOccupancy <= 0) {
                return null;
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("maxOccupancy"), maxOccupancy);
        };
    }

    static Specification<Room> byHotelId(Long hotelId) {
        return (root, query, criteriaBuilder) -> {
            if (hotelId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("hotel").get("id"), hotelId);
        };
    }

    static Specification<Room> byAvailability(LocalDate checkInDate, LocalDate checkOutDate) {
        return (root, query, criteriaBuilder) -> {
            if (checkInDate == null || checkOutDate == null) {
                return null;
            }

            return criteriaBuilder.and(criteriaBuilder.not(
                    root.join("unavailableDates").in(checkInDate.datesUntil(checkOutDate))));
        };
    }
}
