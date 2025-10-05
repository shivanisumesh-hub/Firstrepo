package com.rentcode.rent.service;

import com.rentcode.rent.entity.Accommodation;
import com.rentcode.rent.repository.AccommodationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PGService {
    private final AccommodationRepository accommodationRepository;

    public PGService(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    public List<Accommodation> listAll() {
        return accommodationRepository.findAll();
    }

    public Accommodation getById(Long id) {
        return accommodationRepository.findById(id).orElse(null);
    }

    public List<Accommodation> findByCity(String city) {
        return accommodationRepository.findByCityContainingIgnoreCase(city);
    }

    public void updateAvailability(Long id, int newAvailable) {
        Accommodation a = getById(id);
        if (a != null) {
            a.setAvailableRooms(newAvailable);
            accommodationRepository.save(a);
        }
    }
}
