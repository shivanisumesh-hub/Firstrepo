package com.rentcode.rent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RentApplication {
    public static void main(String[] args) {
        SpringApplication.run(RentApplication.class, args);
        System.out.println("==============================================");
        System.out.println("  RentToGo - Rental Management System Started");
        System.out.println("  Access at: http://localhost:8080");
        System.out.println("==============================================");
    }
}
