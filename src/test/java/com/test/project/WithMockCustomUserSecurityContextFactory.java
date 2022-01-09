package com.test.project;

import com.test.project.entity.User;
import com.test.project.entity.UserProfile;
import com.test.project.security.api.repository.UserRepository;
import com.test.project.security.enums.RoleName;
import com.test.project.security.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
    @Autowired
    private UserRepository userRepository;
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        User  principal =
                new User();
        UserProfile userProfile= new UserProfile();
        userProfile.setFirstname("Artyom");
        principal.setProfile(userProfile);
        principal.setUsername(customUser.username());
        principal.setPassword(customUser.password());
        Role role = new Role();
        role.setRoleName(RoleName.ROLE_ADMIN);
        principal.setRoles(Collections.singletonList(role));
        userRepository.save(principal);
        Authentication auth =
                new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}
