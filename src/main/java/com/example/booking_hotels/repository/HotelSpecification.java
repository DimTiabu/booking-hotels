package com.example.booking_hotels.repository;

import com.example.booking_hotels.model.Hotel;
import com.example.booking_hotels.web.model.hotel.HotelFilter;
import org.springframework.data.jpa.domain.Specification;

public interface HotelSpecification {
    static Specification<Hotel> withFilter(HotelFilter hotelFilter) {
        return Specification
                .where(byId(hotelFilter.getId()))
                .and(byName(hotelFilter.getName()))
                .and(byTitle(hotelFilter.getTitle()))
                .and(byCity(hotelFilter.getCity()))
                .and(byAddress(hotelFilter.getAddress()))
                .and(byDistanceFromCenter(hotelFilter.getDistanceFromCenter()))
                .and(byRating(hotelFilter.getRating()))
                .and(byRatingCount(hotelFilter.getRatingCount()));
    }

    static Specification<Hotel> byId(Long id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return null;
            }
            return criteriaBuilder.equal(
                    root.get("id"), id);
        };
    }

    static Specification<Hotel> byName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null) {
                return null;
            }
            return criteriaBuilder.equal(
                    root.get("name"), name);
        };
    }

    static Specification<Hotel> byTitle(String title) {
        return (root, query, criteriaBuilder) -> {
            if (title == null) {
                return null;
            }
            return criteriaBuilder.like(
                    root.get("title"), "%" + title + "%");
        };
    }

    static Specification<Hotel> byCity(String city) {
        return (root, query, criteriaBuilder) -> {
            if (city == null) {
                return null;
            }
            return criteriaBuilder.equal(
                    root.get("city"), city);
        };
    }

    static Specification<Hotel> byAddress(String address) {
        return (root, query, criteriaBuilder) -> {
            if (address == null) {
                return null;
            }
            return criteriaBuilder.like(
                    root.get("address"), "%" + address + "%");
        };
    }

    static Specification<Hotel> byDistanceFromCenter(double distanceFromCenter) {
        return (root, query, criteriaBuilder) -> {
            if (distanceFromCenter <= 0) { // Или другой порог значений, например \0 как дефолтное значение.
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(
                    root.get("distanceFromCenter"), distanceFromCenter);
        };
    }

    static Specification<Hotel> byRating(double rating) {
        return (root, query, criteriaBuilder) -> {
            if (rating <= 0) { // Убедитесь, что есть подходящее значение по умолчанию.
                return null;
            }
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get("rating"), rating);
        };
    }

    static Specification<Hotel> byRatingCount(int ratingCount) {
        return (root, query, criteriaBuilder) -> {
            if (ratingCount <= 0) { // Убедитесь, что значение подходит для фильтрации.
                return null;
            }
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get("ratingCount"), ratingCount);
        };
    }
}
