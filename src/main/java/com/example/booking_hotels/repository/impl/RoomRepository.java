package com.example.booking_hotels.repository.impl;

import com.example.booking_hotels.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>,
        JpaSpecificationExecutor<Room> {
}

