package com.test.project.controller;

import com.test.project.api.service.PostCommentService;
import com.test.project.dto.PostCommentDto;
import com.test.project.dto.PostCommentWithAllDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/comments")
public class PostCommentController {

    private final PostCommentService postCommentService;

    @PostMapping("/{postId}")
    public ResponseEntity<PostCommentDto> create (@RequestBody @Valid PostCommentDto request,
                                                  @PathVariable  @NotNull(message = "Post id can not be null")
                                                  @Positive(message = "Post id can not be negative") Long postId){
        PostCommentDto response = postCommentService.create(request, postId);
        return ResponseEntity.ok(response);
    }
    @PutMapping
    public ResponseEntity<PostCommentDto> update(@RequestBody @Valid PostCommentDto request){
        PostCommentDto response = postCommentService.update(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostCommentWithAllDto> read (@PathVariable  @NotNull(message = "Post comment id can not be null")
                                                           @Positive(message = "Post comment id can not be negative") Long id){
        PostCommentWithAllDto response = postCommentService.read(id);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PostCommentWithAllDto> delete (@PathVariable @NotNull(message = "Post comment id can not be null")
                                                             @Positive(message = "Post comment id can not be negative") Long id){
        PostCommentWithAllDto response = postCommentService.delete(id);
        return ResponseEntity.ok(response);
    }

}
