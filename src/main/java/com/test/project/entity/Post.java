package com.test.project.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

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
    @JoinColumn(name = "users_profiles_id")
    private UserProfile profile;
    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY)
    private List<PostComment> postComments;
    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY)
    private List<Reaction> reactions;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "posts_hashtags", joinColumns = {
            @JoinColumn(name = "post_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "hashtag_id", referencedColumnName = "id")
            })
    private List<Hashtag> hashtags;

}
