package com.test.project.entity;

import liquibase.pro.packaged.E;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    private UserProfile profile;
    @ManyToMany(mappedBy ="users",fetch = FetchType.LAZY)
    private List<Role> roles;
}
