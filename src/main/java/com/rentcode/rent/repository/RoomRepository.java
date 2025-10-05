package com.rentcode.rent.repository;

import com.rentcode.rent.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByAccommodationId(Long accommodationId);
}
