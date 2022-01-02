package com.test.project.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "post_comments")
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "comments_posts", joinColumns = {
            @JoinColumn(name = "comment_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "post_id", referencedColumnName = "id")
            })
    private Post post;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "comments_profiles", joinColumns = {
            @JoinColumn(name = "comment_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "profile_id", referencedColumnName = "id")
            })
    private UserProfile profile;

}
