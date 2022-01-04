package com.test.project.entity;

import com.test.project.security.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
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
    @OneToMany(mappedBy = "profile",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Post> posts;
    @OneToMany(mappedBy = "profile",fetch = FetchType.LAZY)
    private List<Reaction> reaction;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "friends", joinColumns = {
            @JoinColumn(name = "from_user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "to_user_id", referencedColumnName = "id")
            })
    private List<UserProfile> friends;
}
