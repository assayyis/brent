package com.ibnu.brent.service.impl;

import com.ibnu.brent.entity.Role;
import com.ibnu.brent.repository.RoleRepository;
import com.ibnu.brent.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role getOrSave(Role role) {
        Optional<Role> optionalRole = roleRepository.findByName(role.getName().name());
        return optionalRole.orElseGet(() -> roleRepository.save(role));
    }
}
