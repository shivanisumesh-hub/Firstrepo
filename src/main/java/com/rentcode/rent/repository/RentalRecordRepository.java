package com.rentcode.rent.repository;

import com.rentcode.rent.entity.RentalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRecordRepository extends JpaRepository<RentalRecord, Long> {
}
