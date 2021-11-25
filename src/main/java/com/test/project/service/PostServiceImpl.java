package com.test.project.service;

import com.test.project.api.repository.PostRepository;
import com.test.project.api.service.PostService;
import com.test.project.dto.PostDto;
import com.test.project.entity.Post;
import com.test.project.dao.PostRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper mapper;


    @Override
    @Transactional
    public PostDto create(PostDto postDto) {
        Post post = mapper.map(postDto, Post.class);
        Post response = postRepository.create(post);
        return mapper.map(response, PostDto.class);
    }

    @Override
    @Transactional
    public PostDto update(PostDto postDto) {
        Post post = mapper.map(postDto, Post.class);
        Post response = postRepository.update(post);
        if(response!=null){
            return mapper.map(response,PostDto.class);
        }
        return null;
    }

    @Override
    @Transactional
    public PostDto read(Long id) {
        Post response = postRepository.read(id);
        if(response!=null){
            return mapper.map(response,PostDto.class);
        }
        return null;
    }

    @Override
    @Transactional
    public PostDto delete(Long id) {
        Post response = postRepository.delete(id);
        if(response!=null){
            return mapper.map(response,PostDto.class);
        }
        return null;
    }

    @Override
    public PostDto getPostCriteria(Long id) {
        Post response = postRepository.getPostCriteria(id);
        if(response!=null){
            return mapper.map(response,PostDto.class);
        }
        return null;
    }
    @Override
    public PostDto getPostGraph(Long id) {
        Post response = postRepository.getPostGraph(id);
        if(response!=null){
            return mapper.map(response,PostDto.class);
        }
        return null;
    }
    @Override
    public PostDto getPostJpql(Long id) {
        Post response = postRepository.getPostJpql(id);
        if(response!=null){
            return mapper.map(response,PostDto.class);
        }
        return null;
    }
}
