package com.rentcode.rent.controller;

import com.rentcode.rent.dto.LoginRequest;
import com.rentcode.rent.entity.Owner;
import com.rentcode.rent.entity.Student;
import com.rentcode.rent.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/doLogin")
    public String doLogin(LoginRequest request, Model model) {
        if (request == null || request.getRole() == null) {
            model.addAttribute("error", "Invalid login request");
            return "login";
        }

        String role = request.getRole().trim().toLowerCase();

        try {
            if ("student".equals(role)) {
                Student st = userService.findStudentByAdmission(request.getIdentifier());
                if (st != null && st.getPassword() != null && st.getPassword().equals(request.getPassword())) {
                    // create authentication token and set into SecurityContext
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    st.getAdmissionNo(), // principal (unique)
                                    null,
                                    List.of(new SimpleGrantedAuthority("ROLE_STUDENT"))
                            );
                    SecurityContextHolder.getContext().setAuthentication(auth);

                    // Add student to model if you want to use it on the dashboard
                    model.addAttribute("student", st);
                    return "redirect:/dashboard";
                }
            } else if ("owner".equals(role)) {
                Owner o = userService.findOwnerByPhone(request.getIdentifier());
                if (o != null && o.getPassword() != null && o.getPassword().equals(request.getPassword())) {
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    o.getPhoneNumber(),
                                    null,
                                    List.of(new SimpleGrantedAuthority("ROLE_OWNER"))
                            );
                    SecurityContextHolder.getContext().setAuthentication(auth);

                    model.addAttribute("owner", o);
                    return "redirect:/dashboard";
                }
            } else {
                model.addAttribute("error", "Unknown role");
                return "login";
            }
        } catch (Exception ex) {
            model.addAttribute("error", "Login error");
            return "login";
        }

        model.addAttribute("error", "Invalid credentials");
        return "login";
    }
}
