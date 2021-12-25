package com.test.project.controller;

import com.test.project.api.service.PostService;
import com.test.project.dto.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> create (@RequestBody PostDto request){
        PostDto response = postService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> get (@PathVariable Long id){
        PostDto response = postService.read(id);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PostDto> delete (@PathVariable Long id){
        PostDto response = postService.delete(id);
        return ResponseEntity.ok(response);
    }
    @PutMapping
    public ResponseEntity<PostDto> update(@RequestBody PostDto request){
        PostDto response = postService.update(request);
        return ResponseEntity.ok(response);
    }
}
