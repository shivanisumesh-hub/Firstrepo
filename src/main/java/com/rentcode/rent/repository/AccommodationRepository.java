package com.rentcode.rent.repository;

import com.rentcode.rent.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    List<Accommodation> findByCityContainingIgnoreCase(String city);
    List<Accommodation> findByPricePerMonthBetween(double min, double max);
}
