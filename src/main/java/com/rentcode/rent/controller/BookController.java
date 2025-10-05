package com.rentcode.rent.controller;

import com.rentcode.rent.entity.Book;
import com.rentcode.rent.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService s) { this.bookService = s; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", bookService.listAll());
        return "dashboard";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Book b = bookService.getById(id);
        model.addAttribute("book", b);
        return "dashboard";
    }

    @PostMapping("/add")
    public String add(Book book) {
        bookService.save(book);
        return "redirect:/books";
    }
}
