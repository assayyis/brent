package com.ibnu.brent.controller;

import com.ibnu.brent.dto.request.IssueDetailRequest;
import com.ibnu.brent.dto.request.IssueRequest;
import com.ibnu.brent.dto.response.IssueResponse;
import com.ibnu.brent.dto.response.TotalRevenueResponse;
import com.ibnu.brent.entity.Issue;
import com.ibnu.brent.mapper.IssueMapper;
import com.ibnu.brent.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/issues")
public class IssueController {
    private final IssueService issueService;

    @GetMapping
    public List<IssueResponse> getAll() {
        return issueService.findAll()
                .stream()
                .map(IssueMapper::mapToIssueResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id_issue}")
    public IssueResponse getById(@PathVariable String id_issue) {
        return IssueMapper.mapToIssueResponse(issueService.findById(id_issue));
    }

    @PostMapping
    public IssueResponse issueRent(@RequestBody IssueRequest issueRequest) {
        return IssueMapper.mapToIssueResponse(issueService.create(issueRequest));
    }

    @GetMapping("/revenue")
    public TotalRevenueResponse getTotalRevenue() {
        return TotalRevenueResponse.builder()
                .totalRevenue(issueService.getTotalRevenue())
                .build();
    }
}
