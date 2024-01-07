package com.ibnu.brent.service;

import com.ibnu.brent.entity.Role;

public interface RoleService {
    Role getOrSave(Role role);
}
