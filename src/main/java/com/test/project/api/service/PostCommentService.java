package com.test.project.api.service;

import com.test.project.dto.PostCommentWithAllDto;
import com.test.project.dto.PostCommentWithPostDto;

public interface PostCommentService {
    PostCommentWithPostDto create(PostCommentWithPostDto commentDto);

    PostCommentWithPostDto update(PostCommentWithPostDto commentDto);

    void delete(Long id);

    PostCommentWithAllDto read(Long id);
}
