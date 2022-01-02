package com.test.project.entity;

import com.test.project.security.model.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "users_profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String surname;
    private String town;
    private Long phoneNumber;
    @OneToOne(mappedBy = "profile",fetch = FetchType.LAZY)
    private User user;
    @OneToMany(mappedBy = "profile",fetch = FetchType.LAZY)
    private List<Post> posts;
    @OneToMany(mappedBy = "profile",fetch = FetchType.LAZY)
    private List<Reaction> reaction;

}
