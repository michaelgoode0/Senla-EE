package com.test.project.entity;

import com.test.project.security.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users_profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;
    @OneToMany(mappedBy = "profile",fetch = FetchType.LAZY)
    private List<Post> posts;
    @OneToOne(mappedBy = "profile",fetch = FetchType.LAZY)
    private Reaction reaction;
}
