package com.ibnu.brent.dto.response;

import com.ibnu.brent.dto.request.IssueDetailRequest;
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
public class IssueResponse {
    private Long subTotal;
    private Date issueDate;
    private Date returnDate;
    private String adminId;
    private String customerId;
    private List<IssueDetailRequest> books;
}
