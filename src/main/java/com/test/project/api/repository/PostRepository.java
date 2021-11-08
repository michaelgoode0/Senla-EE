package com.test.project.api.repository;

import com.test.project.entity.Post;

public interface PostRepository {
    Post create(Post user);
    Post update(Post user);
    Post read(Long id);
    Post delete(Long id);
}
