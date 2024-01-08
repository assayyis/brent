package com.ibnu.brent.service.impl;

import com.ibnu.brent.dto.request.IssueRequest;
import com.ibnu.brent.entity.Issue;
import com.ibnu.brent.entity.IssueDetail;
import com.ibnu.brent.entity.User;
import com.ibnu.brent.repository.IssueDetailRepository;
import com.ibnu.brent.repository.IssueRepository;
import com.ibnu.brent.service.BookService;
import com.ibnu.brent.service.IssueService;
import com.ibnu.brent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {
    private final UserService userService;
    private final BookService bookService;
    private final IssueRepository issueRepository;
    private final IssueDetailRepository issueDetailRepository;

    @Override
    public Issue create(IssueRequest issueRequest) {
        User admin = userService.getUserByUserId(issueRequest.getAdminId());
        User customer = userService.getUserByUserId(issueRequest.getCustomerId());

        Issue issue = Issue.builder()
                .id(issueRequest.getIssueId())
                .admin(admin)
                .customer(customer)
                .issueDate(Date.valueOf(LocalDate.now()))
                .returnDate(issueRequest.getReturnDate())
                .build();

        Issue savedIssue = issueRepository.save(issue);

        List<IssueDetail> issueDetails = issueRequest.getBooks()
                .stream()
                .map(issueDetailRequest ->
                        IssueDetail.builder()
                                .book(bookService.findById(issueDetailRequest.getBookId()))
                                .issue(savedIssue)
                                .build()
                ).collect(Collectors.toList());

        List<IssueDetail> savedIssueDetails = issueDetailRepository.saveAll(issueDetails);

        Long subTotal = savedIssueDetails
                .stream()
                .mapToLong(issueDetail -> issueDetail.getBook().getPrice())
                .sum();

        savedIssue.setSubTotal(subTotal);
        savedIssue.setIssueDetail(savedIssueDetails);

        return issueRepository.save(savedIssue);
    }

    @Override
    public List<Issue> findAll() {
        return issueRepository.findAll();
    }

    @Override
    public Issue findById(String id) {
        return issueRepository.findById(id);
    }

    @Override
    public Long getTotalRevenue() {
        List<Issue> issues = findAll();

        return issues
                .stream()
                .mapToLong(Issue::getSubTotal)
                .sum();
    }
}
