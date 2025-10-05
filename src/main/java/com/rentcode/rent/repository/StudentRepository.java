package com.rentcode.rent.repository;

import com.rentcode.rent.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByAdmissionNo(String admissionNo);
}
