package com.rentcode.rent.entity;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbn;
    private double price;
    private double rentPerWeek;
    private boolean available;

    @ManyToOne
    private Owner owner;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public double getRentPerWeek() { return rentPerWeek; }
    public void setRentPerWeek(double rentPerWeek) { this.rentPerWeek = rentPerWeek; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public Owner getOwner() { return owner; }
    public void setOwner(Owner owner) { this.owner = owner; }
}
