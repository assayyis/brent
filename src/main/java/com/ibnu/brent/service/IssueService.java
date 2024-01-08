package com.ibnu.brent.service;

import com.ibnu.brent.dto.request.IssueRequest;
import com.ibnu.brent.entity.Issue;

import java.util.List;

public interface IssueService {
    Issue create(IssueRequest issueRequest);
    List<Issue> findAll();
    Issue findById(String id);
    Long getTotalRevenue();
}
