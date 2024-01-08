package com.ibnu.brent.service;

import com.ibnu.brent.entity.AppUser;
import com.ibnu.brent.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId(String id);

    User getUserByUserId(String id);
}
