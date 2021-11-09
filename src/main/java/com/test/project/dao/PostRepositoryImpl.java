package com.test.project.dao;

import com.test.project.api.repository.PostRepository;
import com.test.project.entity.Post;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryImpl implements PostRepository {
    private final List<Post> posts= new ArrayList<>();


    public Post getById(Long id){
        return posts.stream().filter(s->s.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Post create(Post post) {
        posts.add(post);
        return post;
    }

    @Override
    public Post update(Post post) {
        for (Post i : posts) {
            if (i.getId().equals(post.getId())) {
                int index = posts.indexOf(i);
                posts.set(index, post);
                return post;
            }
        }
        return null;
    }

    @Override
    public Post read(Long id) {
        return getById(id);
    }

    @Override
    public Post delete(Long id) {
        Optional<Post> post= Optional.of(getById(id));
        return post.orElse(null);
    }
}
