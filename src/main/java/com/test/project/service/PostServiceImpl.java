package com.test.project.service;

import com.test.project.annotation.Transaction;
import com.test.project.api.service.PostService;
import com.test.project.dto.PostDto;
import com.test.project.entity.Post;
import com.test.project.dao.PostRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepositoryImpl postRepository;
    private final ModelMapper mapper;

    @Override
    @Transaction
    public PostDto create(PostDto postDto) {
        Post post = mapper.map(postDto, Post.class);
        Post response = postRepository.create(post);
        return mapper.map(response, PostDto.class);
    }

    @Override
    @Transaction
    public PostDto update(PostDto postDto) {
        Post post = mapper.map(postDto, Post.class);
        Post response = postRepository.update(post);
        if(response!=null){
            return mapper.map(response,PostDto.class);
        }
        return null;
    }

    @Override
    @Transaction
    public PostDto read(Long id) {
        Post response = postRepository.read(id);
        if(response!=null){
            return mapper.map(response,PostDto.class);
        }
        return null;
    }

    @Override
    @Transaction
    public PostDto delete(Long id) {
        Post response = postRepository.delete(id);
        if(response!=null){
            return mapper.map(response,PostDto.class);
        }
        return null;
    }
}
