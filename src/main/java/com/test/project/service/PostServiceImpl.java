package com.test.project.service;

import com.test.project.api.repository.PostRepository;
import com.test.project.api.service.HashtagService;
import com.test.project.api.service.PostService;
import com.test.project.api.service.ReactionService;
import com.test.project.dto.*;
import com.test.project.entity.Hashtag;
import com.test.project.entity.Post;
import com.test.project.exceptions.ResourceNotFoundException;
import com.test.project.security.api.repository.UserRepository;
import com.test.project.security.model.User;
import com.test.project.util.AuthNameHolder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final HashtagService hashtagService;
    private final UserRepository userRepository;
    private final ReactionService reactionService;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public PostDto create(PostDto postDto) {
        User user = userRepository.findUserByUsername(AuthNameHolder.getAuthUsername());
        Post post = mapper.map(postDto, Post.class);
        Set<Hashtag> uniqueHashtags= hashtagService.createUniqueHashtags(postDto).stream().
                map(entity->mapper.map(entity, Hashtag.class)).collect(Collectors.toSet());
        post.setHashtags(uniqueHashtags);
        post.setProfile(user.getProfile());
        Post response = postRepository.save(post);
        return mapper.map(response, PostDto.class);
    }

    @Override
    @Transactional
    @PreAuthorize("@postServiceImpl.read(#postDto.id).profile.user.username == authentication.name"+ "|| hasRole('ROLE_ADMIN')")
    public PostWithProfileDto update(PostDto postDto) {
        Post post = postRepository.findById(postDto.getId())
                .orElseThrow((()->new ResourceNotFoundException("Post with id:" + postDto.getId() + " not found")));
        Set<HashtagWithPostsDto> hashtags = hashtagService.createUniqueHashtags(mapper.map(post,PostDto.class));
        mapper.map(postDto,post);
        Set<HashtagWithPostsDto> newHashtags = hashtagService.createUniqueHashtags(mapper.map(post,PostDto.class));
        hashtags.removeAll(newHashtags);
        Set<Hashtag> hashtagList = newHashtags.stream()
                .map(entity-> mapper.map(entity,Hashtag.class))
                .collect(Collectors.toSet());
        post.setHashtags(hashtagList);
        Post response = postRepository.save(post);
        hashtags.stream()
                .filter(k->k.getPosts().size()==1)
                .forEach(k->hashtagService.delete(k.getId()));
        return mapper.map(response, PostWithProfileDto.class);
    }

    @Override
    @Transactional
    public PostWithAllDto read(Long id) {
        Post response = postRepository.findById(id)
                .orElseThrow((()->new ResourceNotFoundException("Post with id:" + id + " not found")));
        return mapper.map(response, PostWithAllDto.class);
    }

    @Override
    @Transactional
    @PreAuthorize("@postServiceImpl.read(#id).profile.user.username == authentication.name"+ "|| hasRole('ROLE_ADMIN')")
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PostWithReactionsDto setReaction(Long postId, boolean react){
        Post post = postRepository.findById(postId)
                .orElseThrow((()->new ResourceNotFoundException("Post with id:" + postId + " not found")));
        PostDto postDto = mapper.map(post, PostDto.class);
        reactionService.react(postDto,react);
        return mapper.map(post, PostWithReactionsDto.class);
    }
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<PostWithAllDto> findAll(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(entity->mapper.map(entity, PostWithAllDto.class));
    }
}
