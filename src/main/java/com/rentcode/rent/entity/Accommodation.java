package com.rentcode.rent.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(length = 1000)
    private String address;
    private double latitude;
    private double longitude;
    private double pricePerMonth;
    private String contactNumber;
    private String city;
    private int totalRooms;
    private int availableRooms;

    @OneToMany(mappedBy = "accommodation", cascade = CascadeType.ALL)
    private List<Room> rooms;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public double getPricePerMonth() { return pricePerMonth; }
    public void setPricePerMonth(double pricePerMonth) { this.pricePerMonth = pricePerMonth; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public int getTotalRooms() { return totalRooms; }
    public void setTotalRooms(int totalRooms) { this.totalRooms = totalRooms; }
    public int getAvailableRooms() { return availableRooms; }
    public void setAvailableRooms(int availableRooms) { this.availableRooms = availableRooms; }
    public List<Room> getRooms() { return rooms; }
    public void setRooms(List<Room> rooms) { this.rooms = rooms; }

    public synchronized void decrementAvailable() {
        if (availableRooms > 0) availableRooms--;
    }

    public synchronized void incrementAvailable() {
        if (availableRooms < totalRooms) availableRooms++;
    }
}
