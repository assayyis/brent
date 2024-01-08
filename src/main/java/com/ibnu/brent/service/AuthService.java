package com.ibnu.brent.service;

import com.ibnu.brent.constant.ERole;
import com.ibnu.brent.dto.request.LoginRequest;
import com.ibnu.brent.dto.request.RegisterRequest;
import com.ibnu.brent.dto.response.LoginResponse;
import com.ibnu.brent.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest authRequest, ERole userRole);
    LoginResponse login(LoginRequest loginRequest);
}
