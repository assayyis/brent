package com.ibnu.brent.dto.request;

import com.ibnu.brent.constant.EBookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookRequest {
    private String bookId;
    private String bookTitle;
    private String bookDescription;
    private String bookImage;
    private String bookAuthor;
    private Long bookPrice;
    private EBookStatus bookStatus;
}
