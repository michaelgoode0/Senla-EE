package com.test.project.api.service;

import com.test.project.dto.HashtagWithPostsDto;
import com.test.project.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface HashtagService  {
    Page<HashtagWithPostsDto> getAll (Pageable pageable);
    Page<HashtagWithPostsDto> getAllTop(Pageable pageable);
    Set<HashtagWithPostsDto> createUniqueHashtags(PostDto postDto);
    void delete(Long id);
    HashtagWithPostsDto read(Long id);
}
