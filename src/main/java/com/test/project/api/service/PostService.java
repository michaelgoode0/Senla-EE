package com.test.project.api.service;

import com.test.project.dto.PostDto;

public interface PostService {
    PostDto create(PostDto user);
    PostDto update(PostDto user);
    PostDto read(Long id);
    PostDto delete(Long id);
}
