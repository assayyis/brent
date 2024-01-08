package com.ibnu.brent.mapper;

import com.ibnu.brent.dto.request.IssueDetailRequest;
import com.ibnu.brent.dto.response.IssueResponse;
import com.ibnu.brent.entity.Issue;
import com.ibnu.brent.entity.IssueDetail;

import java.util.stream.Collectors;

public class IssueMapper {

    public static IssueResponse mapToIssueResponse(Issue issue) {
        if (issue == null) return null;
        return IssueResponse.builder()
                .adminId(issue.getAdmin().getId())
                .customerId(issue.getCustomer().getId())
                .subTotal(issue.getSubTotal())
                .issueDate(issue.getIssueDate())
                .returnDate(issue.getReturnDate())
                .books(issue.getIssueDetail()
                        .stream()
                        .map(IssueMapper::mapToIssueDetailRequest)
                        .collect(Collectors.toList()))
                .build();
    }

    public static IssueDetailRequest mapToIssueDetailRequest(IssueDetail issueDetail) {
        if (issueDetail == null) return null;
        return IssueDetailRequest.builder()
                .bookId(issueDetail.getBook().getId())
                .build();
    }
}
