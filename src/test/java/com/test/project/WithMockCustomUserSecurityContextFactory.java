package com.test.project;

import com.test.project.entity.UserProfile;
import com.test.project.security.enums.RoleName;
import com.test.project.security.model.Role;
import com.test.project.security.model.User;
import liquibase.pro.packaged.U;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Collections;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        User  principal =
                new User();
        UserProfile userProfile= new UserProfile();
        userProfile.setId(1L);
        userProfile.setFirstname("Artyom");
        principal.setProfile(userProfile);
        principal.setUsername(customUser.username());
        principal.setPassword(customUser.password());
        Role role = new Role();
        role.setRoleName(RoleName.ROLE_ADMIN);
        principal.setRoles(Collections.singletonList(role));
        Authentication auth =
                new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}
