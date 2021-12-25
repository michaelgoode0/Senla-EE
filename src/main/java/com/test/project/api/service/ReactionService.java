package com.test.project.api.service;

import com.test.project.dto.PostDto;
import com.test.project.dto.ReactionDto;
import com.test.project.dto.UserProfileDto;
import com.test.project.entity.Reaction;

public interface ReactionService {
   ReactionDto like(Long postId, boolean reaction);
}
