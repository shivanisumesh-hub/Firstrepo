package com.rentcode.rent.service;

import com.rentcode.rent.entity.Book;
import com.rentcode.rent.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) { this.repo = repo; }

    public List<Book> listAll() { return repo.findAll(); }
    public Book getById(Long id) { return repo.findById(id).orElse(null); }
    public void save(Book b) { repo.save(b); }
    public List<Book> searchByTitle(String title) { return repo.findByTitleContainingIgnoreCase(title); }
}
