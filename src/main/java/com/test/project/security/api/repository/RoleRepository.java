package com.test.project.security.api.repository;

import com.test.project.security.enums.RoleName;
import com.test.project.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByRoleName(RoleName roleName);
}
