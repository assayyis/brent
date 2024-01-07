package com.ibnu.brent.service.impl;

import com.ibnu.brent.entity.AppUser;
import com.ibnu.brent.entity.UserCredential;
import com.ibnu.brent.repository.UserCredentialRepository;
import com.ibnu.brent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserCredentialRepository userCredentialRepository;

    @Override
    public AppUser loadUserByUserId(String id) {
        UserCredential userCredential = userCredentialRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credential"));
        return new AppUser(
                userCredential.getId(),
                userCredential.getUsername(),
                userCredential.getPassword(),
                userCredential.getRole().getName());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential userCredential = userCredentialRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credential"));
        return new AppUser(
                userCredential.getId(),
                userCredential.getUsername(),
                userCredential.getPassword(),
                userCredential.getRole().getName());
    }
}
