package com.test.project.security.dto;

import com.test.project.security.enums.RoleName;
import lombok.Data;

@Data
public class RoleDto {
    private Long id;
    private RoleName roleName;
}
