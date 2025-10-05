package com.rentcode.rent.entity;

import jakarta.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;
    private boolean isOccupied;
    private double rent;

    @ManyToOne
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public boolean isOccupied() { return isOccupied; }
    public void setOccupied(boolean occupied) { isOccupied = occupied; }
    public double getRent() { return rent; }
    public void setRent(double rent) { this.rent = rent; }
    public Accommodation getAccommodation() { return accommodation; }
    public void setAccommodation(Accommodation accommodation) { this.accommodation = accommodation; }
}
