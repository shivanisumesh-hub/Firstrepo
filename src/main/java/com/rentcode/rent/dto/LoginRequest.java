package com.rentcode.rent.dto;

public class LoginRequest {
    private String role;
    private String identifier;
    private String password;

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getIdentifier() { return identifier; }
    public void setIdentifier(String identifier) { this.identifier = identifier; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
