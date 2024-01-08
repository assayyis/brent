package com.ibnu.brent.controller;

import com.ibnu.brent.constant.EBookStatus;
import com.ibnu.brent.dto.request.BookRequest;
import com.ibnu.brent.dto.response.BookResponse;
import com.ibnu.brent.entity.Book;
import com.ibnu.brent.mapper.BookMapper;
import com.ibnu.brent.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<BookResponse> getAll() {
        return bookService
                .findAll()
                .stream()
                .map(BookMapper::toBookResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id_book}")
    public BookResponse getById(@PathVariable String id_book) {
        return BookMapper.toBookResponse(bookService.findById(id_book));
    }

    @GetMapping(params = {"bookName"})
    public List<BookResponse> getByTitle(@RequestParam(name = "bookName", required = false) String bookName) {
        return bookService
                .findByTitle(bookName)
                .stream()
                .map(BookMapper::toBookResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    public BookResponse add(@RequestBody BookRequest bookRequest) {
        return BookMapper.toBookResponse(bookService.create(BookMapper.toBook(bookRequest)));
    }

    @PutMapping
    public BookResponse update(@RequestBody BookRequest bookRequest) {
        return BookMapper.toBookResponse(bookService.update(BookMapper.toBook(bookRequest)));
    }

    @DeleteMapping("/{id_book}")
    public String delete(@PathVariable String id_book) {
        bookService.delete(id_book);
        return "success";
    }
}
