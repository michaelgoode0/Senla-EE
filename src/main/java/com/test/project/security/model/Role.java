package com.test.project.security.model;

import com.test.project.security.enums.RoleName;
import com.test.project.security.model.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

import static java.lang.String.valueOf;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "permission")
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @ManyToMany(mappedBy ="roles",fetch = FetchType.LAZY)
    private List<User> users;

    @Override
    public String getAuthority() {
        return valueOf(getRoleName());
    }
}
