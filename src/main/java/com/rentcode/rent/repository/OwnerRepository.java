package com.rentcode.rent.repository;

import com.rentcode.rent.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findByPhoneNumber(String phoneNumber);
}
