package com.ibnu.brent.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ReturnResponse {
    private String issueId;
    private Date returnDate;
    private Long penalty;
    private String review;
}
