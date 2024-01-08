package com.ibnu.brent.controller;

import com.ibnu.brent.dto.request.IssueRequest;
import com.ibnu.brent.dto.response.CommonResponse;
import com.ibnu.brent.dto.response.IssueResponse;
import com.ibnu.brent.dto.response.TotalRevenueResponse;
import com.ibnu.brent.mapper.IssueMapper;
import com.ibnu.brent.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/issues")
public class IssueController {
    private final IssueService issueService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        var issuesResponse = issueService.findAll()
                .stream()
                .map(IssueMapper::mapToIssueResponse)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<List<IssueResponse>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get All Issue Success")
                        .data(issuesResponse)
                        .build());
    }

    @GetMapping("/{id_issue}")
    public ResponseEntity<?> getById(@PathVariable String id_issue) {
        var issueResponse = IssueMapper.mapToIssueResponse(issueService.findById(id_issue));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<IssueResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get Issue Success")
                        .data(issueResponse)
                        .build());
    }

    @PostMapping
    public ResponseEntity<?> issueRent(@RequestBody IssueRequest issueRequest) {
        var issueResponse = IssueMapper.mapToIssueResponse(issueService.create(issueRequest));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CommonResponse.<IssueResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Create Issue Success")
                        .data(issueResponse)
                        .build());
    }

    @GetMapping("/revenue")
    public ResponseEntity<?> getTotalRevenue() {
        var totalRevenueResponse = TotalRevenueResponse.builder()
                .totalRevenue(issueService.getTotalRevenue())
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponse.<TotalRevenueResponse>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Get Total Revenue Success")
                        .data(totalRevenueResponse)
                        .build());
    }
}
