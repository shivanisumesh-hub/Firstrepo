package com.rentcode.rent.controller;

import com.rentcode.rent.entity.Accommodation;
import com.rentcode.rent.entity.RentalRecord;
import com.rentcode.rent.entity.Room;
import com.rentcode.rent.service.PGService;
import com.rentcode.rent.repository.RentalRecordRepository;
import com.rentcode.rent.repository.RoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {

    private final PGService pgService;
    private final RoomRepository roomRepository;
    private final RentalRecordRepository rentalRecordRepository;

    public BookController(PGService pgService, RoomRepository roomRepository,
                          RentalRecordRepository rentalRecordRepository) {
        this.pgService = pgService;
        this.roomRepository = roomRepository;
        this.rentalRecordRepository = rentalRecordRepository;
    }

    @PostMapping
    public String bookRoom(@RequestParam Long pgId,
                           @RequestParam String roomNumber,
                           @RequestParam String startDate,
                           @RequestParam String endDate,
                           Model model) {

        Accommodation pg = pgService.getById(pgId);
        if (pg == null || pg.getAvailableRooms() <= 0) {
            model.addAttribute("error", "PG not available or invalid");
            return "transaction";
        }

        Optional<Room> roomOpt = roomRepository.findByAccommodationId(pgId).stream()
                .filter(r -> r.getRoomNumber().equalsIgnoreCase(roomNumber) && !r.isOccupied())
                .findFirst();

        if (roomOpt.isEmpty()) {
            model.addAttribute("error", "Room not available");
            return "transaction";
        }

        // Mark room as occupied
        Room room = roomOpt.get();
        room.setOccupied(true);
        roomRepository.save(room);

        // Decrement available rooms in accommodation
        pg.decrementAvailable();
        pgService.updateAvailability(pgId, pg.getAvailableRooms());

        // Create rental record
        RentalRecord record = new RentalRecord();
        record.setType("PG");
        record.setItemId(room.getId());
        record.setStudentId(1L); // demo student ID; replace with logged-in ID
        record.setStartDate(LocalDate.parse(startDate));
        record.setDueDate(LocalDate.parse(endDate));
        record.setReturned(false);
        rentalRecordRepository.save(record);

        model.addAttribute("success", "Room booked successfully!");
        model.addAttribute("accommodations", pgService.listAll());
        return "dashboard";
    }
}
