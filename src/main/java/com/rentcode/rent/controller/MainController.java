package com.rentcode.rent.controller;

import com.rentcode.rent.service.PGService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final PGService pgService;

    public MainController(PGService pgService) {
        this.pgService = pgService;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("accommodations", pgService.listAll());
        return "RentToGo";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("accommodations", pgService.listAll());
        return "dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
