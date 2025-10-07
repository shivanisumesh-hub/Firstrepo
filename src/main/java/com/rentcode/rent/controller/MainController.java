package com.rentcode.rent.controller;

import com.rentcode.rent.entity.Owner;
import com.rentcode.rent.entity.Student;
import com.rentcode.rent.service.BookService;
import com.rentcode.rent.service.PGService;
import com.rentcode.rent.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    private final PGService pgService;
    private final BookService bookService;
    private final UserService userService;

    public MainController(PGService pgService, BookService bookService, UserService userService) {
        this.pgService = pgService;
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping({"/", "/home"})
    public String home() {
        return "RentToGo"; // short homepage with Explore / Login / Contact
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<?> accommodations = pgService.listAll();
        model.addAttribute("accommodations", accommodations);
        model.addAttribute("books", bookService.listAll());

        // Resolve display name from authentication principal
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String principal = (auth != null && auth.getPrincipal() != null) ? auth.getPrincipal().toString() : null;
        String displayName = "User";

        if (principal != null) {
            // principal is expected to be admissionNo for students or phoneNumber for owners
            Student st = userService.findStudentByAdmission(principal);
            if (st != null) {
                displayName = (st.getName() != null && !st.getName().isBlank()) ? st.getName() : st.getAdmissionNo();
            } else {
                Owner o = userService.findOwnerByPhone(principal);
                if (o != null) {
                    displayName = (o.getName() != null && !o.getName().isBlank()) ? o.getName() : o.getPhoneNumber();
                }
            }
        }

        model.addAttribute("currentUserName", displayName);
        return "dashboard";
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
