package com.rentcode.rent.controller;

import com.rentcode.rent.dto.LoginRequest;
import com.rentcode.rent.entity.Owner;
import com.rentcode.rent.entity.Student;
import com.rentcode.rent.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/doLogin")
    public String doLogin(LoginRequest request, Model model) {
        if ("student".equals(request.getRole())) {
            Student st = userService.findStudentByAdmission(request.getIdentifier());
            if (st != null && st.getPassword().equals(request.getPassword())) {
                model.addAttribute("student", st);
                return "dashboard";
            }
        } else {
            Owner o = userService.findOwnerByPhone(request.getIdentifier());
            if (o != null && o.getPassword().equals(request.getPassword())) {
                model.addAttribute("owner", o);
                return "dashboard";
            }
        }
        model.addAttribute("error", "Invalid credentials");
        return "login";
    }
}
