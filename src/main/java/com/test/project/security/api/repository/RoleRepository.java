package com.test.project.security.api.repository;

import com.test.project.security.enums.RoleName;
import com.test.project.security.model.Role;

public interface RoleRepository {
    Role findRoleByName(RoleName roleName);
}
