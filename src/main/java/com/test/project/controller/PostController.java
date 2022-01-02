package com.test.project.controller;

import com.test.project.api.service.PostService;
import com.test.project.dto.PostDto;
import com.test.project.dto.PostWithAllDto;
import com.test.project.dto.PostWithProfileDto;
import com.test.project.dto.PostWithReactionsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/posts")
@Validated
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> create (@RequestBody @Valid PostDto request){
        PostDto response = postService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostWithAllDto> get (@PathVariable @NotNull(message = "Post id can not be null")
                                                   @Positive(message = "Post id can not be negative") Long id){
        PostWithAllDto response = postService.read(id);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PostWithAllDto> delete (@PathVariable @NotNull(message = "Post id can not be null")
                                                      @Positive(message = "Post id can not be negative") Long id){
        PostWithAllDto response = postService.delete(id);
        return ResponseEntity.ok(response);
    }
    @PutMapping
    public ResponseEntity<PostWithProfileDto> update(@RequestBody @Valid PostDto request){
        PostWithProfileDto response = postService.update(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{postId}/like")
    public ResponseEntity<PostWithReactionsDto> like(@PathVariable @NotNull(message = "Post id can not be null")
                                                         @Positive(message = "Post id can not be negative") Long postId){
        PostWithReactionsDto response = postService.setReaction(postId,true);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{postId}/dislike")
    public ResponseEntity<PostWithReactionsDto> dislike (@PathVariable @NotNull(message = "Post id can not be null")
                                                             @Positive(message = "Post id can not be negative") Long postId){
        PostWithReactionsDto response = postService.setReaction(postId,false);
        return ResponseEntity.ok(response);
    }
}
