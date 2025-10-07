package com.rentcode.rent.controller;

import com.rentcode.rent.entity.Accommodation;
import com.rentcode.rent.service.PGService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pg")
public class PGController {
    private final PGService pgService;

    public PGController(PGService pgService) { this.pgService = pgService; }

    @GetMapping("/all")
    public List<Accommodation> all() { return pgService.listAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> get(@PathVariable Long id) {
        Accommodation a = pgService.getById(id);
        if (a == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(a);
    }

    @PostMapping("/{id}/availability")
    public ResponseEntity<Void> setAvailability(@PathVariable Long id, @RequestParam int available) {
        pgService.updateAvailability(id, available);
        return ResponseEntity.ok().build();
    }
}
