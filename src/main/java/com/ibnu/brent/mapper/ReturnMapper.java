package com.ibnu.brent.mapper;

import com.ibnu.brent.dto.response.ReturnResponse;
import com.ibnu.brent.entity.Return;

public class ReturnMapper {

    public static ReturnResponse mapToReturnResponse(Return issueReturn) {
        return ReturnResponse.builder()
                .issueId(issueReturn.getIssue().getId())
                .returnDate(issueReturn.getReturnDate())
                .penalty(issueReturn.getPenalty())
                .review(issueReturn.getReview().getReview())
                .build();
    }
}
