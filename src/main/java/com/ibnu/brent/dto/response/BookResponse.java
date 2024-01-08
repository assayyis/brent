package com.ibnu.brent.dto.response;

import com.ibnu.brent.constant.EBookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookResponse {
    private String bookTitle;
    private String bookDescription;
    private String bookImage;
    private String bookAuthor;
    private Long bookPrice;
    private EBookStatus bookStatus;
}
