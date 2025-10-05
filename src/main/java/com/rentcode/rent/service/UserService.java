package com.rentcode.rent.service;

import com.rentcode.rent.entity.Owner;
import com.rentcode.rent.entity.Student;
import com.rentcode.rent.repository.OwnerRepository;
import com.rentcode.rent.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final StudentRepository studentRepo;
    private final OwnerRepository ownerRepo;

    public UserService(StudentRepository studentRepo, OwnerRepository ownerRepo) {
        this.studentRepo = studentRepo;
        this.ownerRepo = ownerRepo;
    }

    public Student findStudentByAdmission(String admissionNo) {
        return studentRepo.findByAdmissionNo(admissionNo);
    }

    public Owner findOwnerByPhone(String phone) {
        return ownerRepo.findByPhoneNumber(phone);
    }
}
