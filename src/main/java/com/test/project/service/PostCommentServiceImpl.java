package com.test.project.service;

import com.test.project.api.repository.PostCommentRepository;
import com.test.project.api.service.PostCommentService;
import com.test.project.api.service.PostService;
import com.test.project.dto.*;
import com.test.project.entity.Post;
import com.test.project.entity.PostComment;
import com.test.project.security.api.service.UserService;
import com.test.project.security.dto.UserDto;
import com.test.project.security.dto.UserWithAllDto;
import com.test.project.security.model.User;
import com.test.project.util.AuthNameHolder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostCommentServiceImpl implements PostCommentService {

    private final PostCommentRepository postCommentRepository;
    private final UserService userService;
    private final PostService postService;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public PostCommentDto create(PostCommentDto postCommentDto, Long postId) {
        UserWithAllDto userDto = userService.loadByUsername(AuthNameHolder.getAuthUsername());
        User user = mapper.map(userDto,User.class);
        PostComment postComment = mapper.map(postCommentDto, PostComment.class);
        PostWithAllDto postDto = postService.read(postId);
        Post post = mapper.map(postDto,Post.class);
        postComment.setPost(post);
        postComment.setProfile(user.getProfile());
        PostComment response = postCommentRepository.save(postComment);
        return mapper.map(response, PostCommentDto.class);
    }

    @Override
    @Transactional
    @PreAuthorize("@postCommentServiceImpl.read(#postCommentDto.id).profile.user.username == authentication.name")
    public PostCommentDto update(PostCommentDto postCommentDto) {
        PostComment postComment = postCommentRepository.findById(postCommentDto.getId()).orElse(null);
        mapper.map(postCommentDto,postComment);
        PostComment response = postCommentRepository.save(postComment);
        return mapper.map(response, PostCommentDto.class);
    }

    @Override
    @Transactional
    @PreAuthorize("@postCommentServiceImpl.read(#id).profile.user.username == authentication.name")
    public PostCommentWithAllDto delete(Long id) {
        PostCommentWithAllDto response = read(id);
        postCommentRepository.deleteById(id);
        return mapper.map(response, PostCommentWithAllDto.class);
    }

    @Override
    @Transactional
    public PostCommentWithAllDto read(Long id) {
        PostComment response = postCommentRepository.findById(id).orElse(null);
        return mapper.map(response, PostCommentWithAllDto.class);
    }

}
