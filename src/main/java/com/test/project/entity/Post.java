package com.test.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "posts")
@NamedEntityGraph(name = "graph.Post.profile",
        attributeNodes = @NamedAttributeNode("profile"))
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "posts_profiles", joinColumns = {
            @JoinColumn(name = "post_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "profile_id", referencedColumnName = "id")
            })
    private UserProfile profile;
    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY)
    private List<PostComment> postComments;
    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY)
    private List<Reaction> reactions;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinTable(name = "posts_hashtags", joinColumns = {
            @JoinColumn(name = "post_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "hashtag_id", referencedColumnName = "id")
            })
    private Set<Hashtag> hashtags;

}
