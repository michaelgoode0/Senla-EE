package com.test.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.project.api.service.PostService;
import com.test.project.dto.PostDto;
import com.test.project.exceptions.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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
