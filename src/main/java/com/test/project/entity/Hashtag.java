package com.test.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "hashtags")
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
    @ManyToMany(mappedBy ="hashtags", fetch = FetchType.LAZY)
    private List<Post> posts;

}
