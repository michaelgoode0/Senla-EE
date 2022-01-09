package com.test.project.service;

import com.test.project.api.repository.PostCommentRepository;
import com.test.project.api.service.PostCommentService;
import com.test.project.dto.PostCommentWithAllDto;
import com.test.project.dto.PostCommentWithPostDto;
import com.test.project.entity.Post;
import com.test.project.entity.PostComment;
import com.test.project.entity.User;
import com.test.project.exceptions.ResourceNotFoundException;
import com.test.project.security.api.service.UserService;
import com.test.project.security.dto.UserWithAllDto;
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
    private final ModelMapper mapper;

    @Override
    @Transactional
    public PostCommentWithPostDto create(PostCommentWithPostDto commentDto) {
        UserWithAllDto userDto = userService.loadByUsername(AuthNameHolder.getAuthUsername());
        User user = mapper.map(userDto,User.class);
        PostComment postComment = mapper.map(commentDto, PostComment.class);
        Post post = mapper.map(commentDto.getPost(),Post.class);
        postComment.setPost(post);
        postComment.setProfile(user.getProfile());
        PostComment response = postCommentRepository.save(postComment);
        return mapper.map(response, PostCommentWithPostDto.class);
    }

    @Override
    @Transactional
    @PreAuthorize("@postCommentServiceImpl.read(#commentDto.id).profile.user.username == authentication.name"+ "|| hasRole('ROLE_ADMIN')")
    public PostCommentWithPostDto update(PostCommentWithPostDto commentDto) {
        PostComment postComment = postCommentRepository.findById(commentDto.getId())
                .orElseThrow((()->new ResourceNotFoundException("Post comment with id:" + commentDto.getId()+" not found")));
        mapper.map(commentDto,postComment);
        PostComment response = postCommentRepository.save(postComment);
        return mapper.map(response, PostCommentWithPostDto.class);
    }

    @Override
    @Transactional
    @PreAuthorize("@postCommentServiceImpl.read(#id).profile.user.username == authentication.name"+ "|| hasRole('ROLE_ADMIN')")
    public void delete(Long id) {
        postCommentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PostCommentWithAllDto read(Long id) {
        PostComment response = postCommentRepository.findById(id)
                .orElseThrow((()->new ResourceNotFoundException("Post comment with id:" + id +" not found")));
        return mapper.map(response, PostCommentWithAllDto.class);
    }

}
