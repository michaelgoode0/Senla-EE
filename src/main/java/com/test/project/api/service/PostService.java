package com.test.project.api.service;

import com.test.project.dto.PostDto;
import com.test.project.entity.Post;

public interface PostService {
    PostDto create(PostDto user);
    PostDto update(PostDto user);
    PostDto read(Long id);
    PostDto delete(Long id);
    PostDto getPostGraph(Long id);
    PostDto getPostJpql(Long id);
    PostDto getPostCriteria(Long id);
}
