package com.test.project.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "reactions")
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean reaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "reactions_profiles", joinColumns = {
            @JoinColumn(name = "reaction_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "profile_id", referencedColumnName = "id")
            })
    private UserProfile profile;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "reactions_posts", joinColumns = {
            @JoinColumn(name = "reaction_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "post_id", referencedColumnName = "id")
            })
    private Post post;



}
