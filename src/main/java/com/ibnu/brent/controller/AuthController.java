package com.ibnu.brent.controller;

import com.ibnu.brent.constant.ERole;
import com.ibnu.brent.dto.request.AuthRequest;
import com.ibnu.brent.dto.response.LoginResponse;
import com.ibnu.brent.dto.response.RegisterResponse;
import com.ibnu.brent.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public RegisterResponse registerCustomer(@RequestBody AuthRequest authRequest) {
        return authService.register(authRequest, ERole.ROLE_CUSTOMER);
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(CommonResponse.<RegisterResponse>builder()
//                        .statusCode(HttpStatus.CREATED.value())
//                        .message("Success register")
//                        .data(registerResponse)
//                        .build());
    }
    @PostMapping("/register-admin")
    public RegisterResponse registerAdmin(@RequestBody AuthRequest authRequest) {
        return authService.register(authRequest, ERole.ROLE_ADMIN);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }

}
