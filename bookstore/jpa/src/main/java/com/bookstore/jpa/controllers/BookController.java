package com.bookstore.jpa.controllers;

import com.bookstore.jpa.dtos.BookRecordDto;
import com.bookstore.jpa.models.BookModel;
import com.bookstore.jpa.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookstore/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @PostMapping
    public ResponseEntity<BookModel> saveBook(@RequestBody BookRecordDto bookRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookRecordDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookModel> updateBook(@PathVariable UUID id, @RequestBody BookRecordDto bookRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(id, bookRecordDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully.");
    }


    @GetMapping("/count-by-publisher")
    public ResponseEntity<List<Object[]>> countBooksByPublisher() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.countBooksByPublisher());
    }


    @GetMapping("/count-by-author")
    public ResponseEntity<List<Object[]>> countBooksByAuthor() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.countBooksByAuthor());
    }

    @GetMapping("/sum-prices")
    public ResponseEntity<Double> getSumOfBookPrices() {
        Double totalPrice = bookService.sumBookPrices();
        return ResponseEntity.status(HttpStatus.OK).body(totalPrice);
    }
}
