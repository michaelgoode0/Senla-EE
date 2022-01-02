package com.test.project.security.dto;

import com.test.project.security.enums.RoleName;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
public class RoleDto {
    private Long id;
    private RoleName roleName;
}
