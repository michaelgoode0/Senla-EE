package com.test.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.project.api.service.PostService;
import com.test.project.dto.PostDto;
import com.test.project.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostController {

    private Logger logger= LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    private final ObjectMapper objectMapper;

    public String create(String requestJson) {
        try {
            PostDto dto = objectMapper.readValue(requestJson, PostDto.class);
            PostDto response = postService.create(dto);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String update(String requestJson){
        try {
            PostDto dto = objectMapper.readValue(requestJson, PostDto.class);
            PostDto response = postService.update(dto);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error("JsonProcessingException" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public String read(Long id){
        try {
            PostDto response = postService.read(id);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error("JsonProcessingException" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public String delete(Long id){
        try {
            PostDto response = postService.delete(id);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error("JsonProcessingException" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public String mapToJson(PostDto postDto) {
        try {
            return objectMapper.writeValueAsString(postDto);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            logger.error("JsonProcessingException" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
