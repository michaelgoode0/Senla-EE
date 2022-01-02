package com.test.project.api.service;

import com.test.project.dto.PostCommentDto;
import com.test.project.dto.PostCommentWithAllDto;


public interface PostCommentService {
    PostCommentDto create(PostCommentDto postCommentDto, Long postId);
    PostCommentDto update(PostCommentDto postCommentDto);
    PostCommentWithAllDto delete(Long id);
    PostCommentWithAllDto read(Long id);
}
