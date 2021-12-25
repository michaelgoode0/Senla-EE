package com.test.project.service;

import com.test.project.api.repository.HashtagRepository;
import com.test.project.api.repository.PostRepository;
import com.test.project.api.service.PostService;
import com.test.project.dto.PostDto;
import com.test.project.entity.Hashtag;
import com.test.project.entity.Post;
import com.test.project.security.api.repository.UserRepository;
import com.test.project.security.model.User;
import com.test.project.util.AuthNameHolder;
import com.test.project.util.HashtagFinder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final HashtagRepository hashtagRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public PostDto create(PostDto postDto) {
        User user = userRepository.loadUserByUsername(AuthNameHolder.authUsername());
        Post post = mapper.map(postDto, Post.class);
        List<Hashtag> hashtags = HashtagFinder.findHashtag(post.getText());
        List<Hashtag> hashtagsToSet = new ArrayList<>();
        for (Hashtag hashtag : hashtags) {
            if (!hashtagRepository.getAllHashtagValues().contains(hashtag.getValue())) {
                Hashtag hashtagToAdd = hashtagRepository.create(hashtag);
                hashtagsToSet.add(hashtagToAdd);
            } else {
                hashtagsToSet.add(hashtagRepository.getHashtag(hashtag.getValue()));
            }
        }
        post.setHashtags(hashtagsToSet);
        post.setProfile(user.getUserProfile());
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
}
