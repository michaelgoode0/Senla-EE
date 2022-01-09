package com.test.project.api.service;

import com.test.project.dto.PostDto;
import com.test.project.dto.ReactionDto;
import com.test.project.dto.ReactionWithProfileDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReactionService {
   void react(PostDto postDto, boolean reaction);
   List<ReactionDto> getAll();
   Page<ReactionWithProfileDto> findAllByPost(Pageable pageable, Long id);
}
