package com.test.project.service;

import com.test.project.api.repository.HashtagRepository;
import com.test.project.api.service.HashtagService;
import com.test.project.dto.HashtagWithPostsDto;
import com.test.project.dto.PostDto;
import com.test.project.entity.Hashtag;
import com.test.project.entity.Post;
import com.test.project.exceptions.ResourceNotFoundException;
import com.test.project.util.HashtagFinder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl  implements HashtagService {

    private final HashtagRepository hashtagRepository;
    private final ModelMapper mapper;

    @Override
    public Page<HashtagWithPostsDto> getAll(Pageable pageable) {
        return hashtagRepository.findAll(pageable)
                .map(entity->mapper.map(entity, HashtagWithPostsDto.class));
    }
    @Override
    public Page<HashtagWithPostsDto> getAllTop(Pageable pageable) {
        return hashtagRepository.getAllTop(pageable)
                .map(entity->mapper.map(entity, HashtagWithPostsDto.class));
    }

    @Override
    @Transactional
    public Set<HashtagWithPostsDto> createUniqueHashtags(PostDto postDto){
        Post post = mapper.map(postDto,Post.class);
        List<Hashtag> hashtags = HashtagFinder.findHashtag(post.getText());
        Set<Hashtag> hashtagsToSet = new HashSet<>();
        for (Hashtag hashtag : hashtags) {
            if (!hashtagRepository.findHashtagValues().contains(hashtag.getValue())) {
                Hashtag hashtagToAdd = hashtagRepository.save(hashtag);
                hashtagsToSet.add(hashtagToAdd);
            } else {
                hashtagsToSet.add(hashtagRepository.findHashtagByValue(hashtag.getValue()));
            }
        }
        return hashtagsToSet.stream()
                .map(entity->mapper.map(entity, HashtagWithPostsDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(Long id) {
        hashtagRepository.deleteById(id);
    }

    @Override
    public HashtagWithPostsDto read(Long id) {
        Hashtag hashtag = hashtagRepository.findById(id)
                .orElseThrow((()->new ResourceNotFoundException("Invite object with id:" +id+" not found")));
        return mapper.map(hashtag,HashtagWithPostsDto.class);
    }
}
