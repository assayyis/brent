package com.ibnu.brent.service.impl;

import com.ibnu.brent.dto.request.ReturnRequest;
import com.ibnu.brent.entity.Issue;
import com.ibnu.brent.entity.Return;
import com.ibnu.brent.entity.Review;
import com.ibnu.brent.repository.ReturnRepository;
import com.ibnu.brent.repository.ReviewRepository;
import com.ibnu.brent.service.IssueService;
import com.ibnu.brent.service.ReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class ReturnServiceImpl implements ReturnService {
    private final ReturnRepository returnRepository;
    private final ReviewRepository reviewRepository;
    private final IssueService issueService;

    public Return create(ReturnRequest returnRequest) {
        Issue issue = issueService.findById(returnRequest.getIssueId());

        Long daysLate = ChronoUnit.DAYS.between(LocalDate.parse(issue.getReturnDate().toString()),LocalDate.now());
        Long penalty = daysLate < 0 ? 0 : daysLate * 10000;

        Return issueReturn = Return.builder()
                .issue(issue)
                .returnDate(Date.valueOf(LocalDate.now()))
                .penalty(penalty)
                .build();
        Return savedReturn = returnRepository.save(issueReturn);

        Review review = Review.builder()
                .review(returnRequest.getReview())
                .issueReturn(savedReturn)
                .build();
        Review savedReview = reviewRepository.save(review);

        issueReturn.setReview(savedReview);
        return returnRepository.save(issueReturn);
    }
}
