package com.test.project.controller;

import com.test.project.api.service.HashtagService;
import com.test.project.dto.HashtagWithPostsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hashtags")
public class HashtagController {

    private final HashtagService hashtagService;

    @GetMapping
    public ResponseEntity<List<HashtagWithPostsDto>> getAll(@RequestParam(required = false, defaultValue = "id") String sort,
                                                            @RequestParam(required = false, defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC,sort));
        Page<HashtagWithPostsDto> result = hashtagService.getAll(pageable);
        return ResponseEntity.ok(result.getContent());
    }
}
