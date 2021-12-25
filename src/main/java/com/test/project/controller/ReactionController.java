package com.test.project.controller;

import com.test.project.api.service.PostService;
import com.test.project.api.service.ReactionService;
import com.test.project.dto.PostDto;
import com.test.project.dto.ReactionDto;
import com.test.project.entity.Reaction;
import com.test.project.security.dto.UserDto;
import com.test.project.security.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/reaction")
public class ReactionController {

    private final ReactionService reactionService;

    @GetMapping("/{postId}/like")
    public ResponseEntity<ReactionDto> like(@PathVariable Long postId){
        ReactionDto response = reactionService.like(postId,true);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{postId}/dislike")
    public ResponseEntity<ReactionDto> dislike (@PathVariable Long postId){
        ReactionDto response = reactionService.like(postId,false);
        return ResponseEntity.ok(response);
    }
}
