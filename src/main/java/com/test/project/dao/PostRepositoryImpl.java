package com.test.project.dao;

import com.test.project.api.repository.PostRepository;
import com.test.project.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository {
    private final List<Post> posts= new ArrayList<>();

    public List<Post> getPosts() {
        return posts;
    }

    public Post getById(Long id){
        for(Post post: getPosts()){
            if(post.getId().equals(id)){
                return post;
            }
        }
        return null;
    }

    @Override
    public Post create(Post post) {
        getPosts().add(post);
        return post;
    }

    @Override
    public Post update(Post post) {
        for (Post i : getPosts()) {
            if (i.getId().equals(post.getId())) {
                int index = getPosts().indexOf(i);
                getPosts().set(index, post);
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
        Post post= getById(id);
        if(post==null) {
            return null;
        }
        getPosts().remove(post);
        return post;

    }
}
