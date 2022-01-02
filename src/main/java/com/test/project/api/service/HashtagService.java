package com.test.project.api.service;

import com.test.project.dto.HashtagWithPostsDto;
import com.test.project.dto.PostDto;
import com.test.project.dto.PostWithAllDto;
import com.test.project.entity.Hashtag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

public interface HashtagService  {
    Page<HashtagWithPostsDto> getAll (Pageable pageable);
    Set<HashtagWithPostsDto> createUniqueHashtags(PostDto postDto);
    HashtagWithPostsDto delete(Long id);
    HashtagWithPostsDto read(Long id);
}
