package com.rentcode.rent.controller;

import com.rentcode.rent.service.BookService;
import com.rentcode.rent.service.PGService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final PGService pgService;
    private final BookService bookService;

    public MainController(PGService pgService, BookService bookService) {
        this.pgService = pgService;
        this.bookService = bookService;
    }

    @GetMapping({"/", "/home"})
    public String home() {
        return "RentToGo"; // only shows short homepage with Explore/Login/Contact
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("accommodations", pgService.listAll());
        model.addAttribute("books", bookService.listAll());
        return "dashboard"; // shows PG listings and books after login
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/explore")
    public String explore(Model model) {
        model.addAttribute("accommodations", pgService.listAll());
        return "explore"; // dynamic explore page after login
    }

    @GetMapping("/transaction")
    public String transaction(Model model) {
        model.addAttribute("accommodations", pgService.listAll());
        return "transaction"; // transaction page after login
    }
}
