package com.ibnu.brent.mapper;

import com.ibnu.brent.constant.EBookStatus;
import com.ibnu.brent.dto.request.BookRequest;
import com.ibnu.brent.dto.response.BookResponse;
import com.ibnu.brent.entity.Book;

public class BookMapper {

    public static BookResponse toBookResponse(Book book) {
        return BookResponse.builder()
            .bookTitle(book.getTitle())
            .bookDescription(book.getDescription())
            .bookImage(book.getImage())
            .bookPrice(book.getPrice())
            .bookAuthor(book.getAuthor())
            .bookStatus(book.getStatus())
            .build();
    }

    public static Book toBook(BookRequest bookRequest) {
        return Book.builder()
                .id(bookRequest.getBookId())
                .title(bookRequest.getBookTitle())
                .description(bookRequest.getBookTitle())
                .image(bookRequest.getBookTitle())
                .price(bookRequest.getBookPrice())
                .author(bookRequest.getBookAuthor())
                .status(bookRequest.getBookStatus() != null ? bookRequest.getBookStatus() : EBookStatus.AVAILABLE)
                .build();
    }
}
