package com.ibnu.brent.service.impl;

import com.ibnu.brent.constant.ERole;
import com.ibnu.brent.dto.request.AuthRequest;
import com.ibnu.brent.dto.response.LoginResponse;
import com.ibnu.brent.dto.response.RegisterResponse;
import com.ibnu.brent.entity.AppUser;
import com.ibnu.brent.entity.Role;
import com.ibnu.brent.entity.UserCredential;
import com.ibnu.brent.repository.UserCredentialRepository;
import com.ibnu.brent.security.JwtUtil;
import com.ibnu.brent.service.AuthService;
import com.ibnu.brent.service.RoleService;
import com.ibnu.brent.util.ValidationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserCredentialRepository userCredentialRepository;
    private final RoleService roleService;

    private final JwtUtil jwtUtil;
    private final ValidationUtil validationUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse register(AuthRequest authRequest, ERole userRole) {
        try {
            validationUtil.validate(authRequest);
            Role role = Role.builder()
                    .name(userRole)
                    .build();
            Role savedRole = roleService.getOrSave(role);

            UserCredential userCredential = UserCredential.builder()
                    .username(authRequest.getUsername().toLowerCase())
                    .password(passwordEncoder.encode(authRequest.getPassword()))
                    .role(savedRole)
                    .build();
            userCredentialRepository.save(userCredential);

            return RegisterResponse.builder()
                    .username(userCredential.getUsername())
                    .role(userCredential.getRole().getName().toString())
                    .build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exist");
        }
    }

    @Override
    public LoginResponse login(AuthRequest authRequest) {
        validationUtil.validate(authRequest);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getUsername().toLowerCase(),
                authRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .token(token)
                .role(appUser.getRole().name())
                .build();
    }
}
