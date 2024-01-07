package com.ibnu.brent.service;

import com.ibnu.brent.constant.ERole;
import com.ibnu.brent.dto.request.AuthRequest;
import com.ibnu.brent.dto.response.LoginResponse;
import com.ibnu.brent.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(AuthRequest authRequest, ERole userRole);
    LoginResponse login(AuthRequest authRequest);
}
