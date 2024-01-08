package com.ibnu.brent.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class IssueRequest {
    private String issueId;
    private Long subTotal;
    private Date issueDate;
    private Date returnDate;
    private String adminId;
    private String customerId;
    private List<IssueDetailRequest> books;
}
