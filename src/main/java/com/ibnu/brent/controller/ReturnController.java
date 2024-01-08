package com.ibnu.brent.controller;

import com.ibnu.brent.dto.request.ReturnRequest;
import com.ibnu.brent.dto.response.ReturnResponse;
import com.ibnu.brent.entity.Return;
import com.ibnu.brent.service.ReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/returns")
public class ReturnController {
    private final ReturnService returnService;

    @PostMapping
    public ReturnResponse returnBook(@RequestBody ReturnRequest returnRequest) {
        Return issueReturn = returnService.create(returnRequest);
        return ReturnResponse.builder()
                .issueId(issueReturn.getIssue().getId())
                .returnDate(issueReturn.getReturnDate())
                .penalty(issueReturn.getPenalty())
                .review(issueReturn.getReview().getReview())
                .build();
    }
}
