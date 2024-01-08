package com.ibnu.brent.controller;

import com.ibnu.brent.dto.request.BookRequest;
import com.ibnu.brent.dto.response.BookResponse;
import com.ibnu.brent.dto.response.CommonResponse;
import com.ibnu.brent.mapper.BookMapper;
import com.ibnu.brent.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        var booksResponse = bookService
                .findAll()
                .stream()
                .map(BookMapper::toBookResponse)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<List<BookResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get All Book Success")
                        .data(booksResponse)
                        .build());
    }

    @GetMapping("/{id_book}")
    public ResponseEntity<?> getById(@PathVariable String id_book) {
        var bookResponse = BookMapper.toBookResponse(bookService.findById(id_book));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<BookResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get Book Success")
                        .data(bookResponse)
                        .build());
    }

    @GetMapping(params = {"bookName"})
    public ResponseEntity<?> getByTitle(@RequestParam(name = "bookName", required = false) String bookName) {
        var booksResponse = bookService
                .findByTitle(bookName)
                .stream()
                .map(BookMapper::toBookResponse)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<List<BookResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get Books Success")
                        .data(booksResponse)
                        .build());
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody BookRequest bookRequest) {
        var bookResponse = BookMapper.toBookResponse(bookService.create(BookMapper.toBook(bookRequest)));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CommonResponse.<BookResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Add Book Success")
                        .data(bookResponse)
                        .build());
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody BookRequest bookRequest) {
        var bookResponse = BookMapper.toBookResponse(bookService.update(BookMapper.toBook(bookRequest)));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<BookResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Update Book Success")
                        .data(bookResponse)
                        .build());
    }

    @DeleteMapping("/{id_book}")
    public ResponseEntity<?> delete(@PathVariable String id_book) {
        bookService.delete(id_book);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<String>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Delete Book Success")
                        .data(null)
                        .build());
    }
}
