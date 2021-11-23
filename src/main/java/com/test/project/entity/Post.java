package com.test.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
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

}
