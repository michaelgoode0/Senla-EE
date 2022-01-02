package com.test.project.api.service;

import com.test.project.dto.PostDto;
import com.test.project.dto.ReactionDto;
import com.test.project.dto.ReactionWithAllDto;

import java.util.List;

public interface ReactionService {
   void react(PostDto postDto, boolean reaction);
   List<ReactionDto> getAll();
}
