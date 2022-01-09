package com.test.project.security.model;

import com.test.project.entity.User;
import com.test.project.security.enums.RoleName;
import lombok.Getter;
import lombok.Setter;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "permission")
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @ManyToMany(mappedBy ="roles",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<User> users;

    @Override
    public String getAuthority() {
        return valueOf(getRoleName());
    }
}
