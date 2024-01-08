package com.ibnu.brent.controller;

import com.ibnu.brent.dto.request.ReturnRequest;
import com.ibnu.brent.dto.response.CommonResponse;
import com.ibnu.brent.dto.response.ReturnResponse;
import com.ibnu.brent.mapper.ReturnMapper;
import com.ibnu.brent.service.ReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> returnBook(@RequestBody ReturnRequest returnRequest) {
        var returnResponse = ReturnMapper.mapToReturnResponse(returnService.create(returnRequest));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CommonResponse.<ReturnResponse>builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Returning Book Success")
                        .data(returnResponse)
                        .build());
    }
}
