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
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    private final ObjectMapper objectMapper;

    public String create(String requestJson) {
        try {
            PostDto dto = objectMapper.readValue(requestJson, PostDto.class);
            PostDto response = postService.create(dto);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException" + e.getMessage(),e);
            throw new GlobalException("JsonProcessingException" + e.getMessage(),e);
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
            log.error("JsonProcessingException" + e.getMessage(),e);
            throw new GlobalException("JsonProcessingException" + e.getMessage(),e);
        }
    }
    public String read(Long id){
        try {
            PostDto response = postService.read(id);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("JsonProcessingException" + e.getMessage(),e);
            throw new GlobalException("JsonProcessingException" + e.getMessage(),e);
        }
    }
    public String getCriteria(Long id){
        try {
            PostDto response = postService.getPostCriteria(id);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("JsonProcessingException" + e.getMessage(),e);
            throw new GlobalException("JsonProcessingException" + e.getMessage(),e);
        }
    }
    public String getGraph(Long id){
        try {
            PostDto response = postService.getPostGraph(id);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("JsonProcessingException" + e.getMessage(),e);
            throw new GlobalException("JsonProcessingException" + e.getMessage(),e);
        }
    }
    public String getJpql(Long id){
        try {
            PostDto response = postService.getPostJpql(id);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("JsonProcessingException" + e.getMessage(),e);
            throw new GlobalException("JsonProcessingException" + e.getMessage(),e);
        }
    }
    public String delete(Long id){
        try {
            PostDto response = postService.delete(id);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("JsonProcessingException" + e.getMessage(),e);
            throw new GlobalException("JsonProcessingException" + e.getMessage(),e);
        }
    }
    public String mapToJson(PostDto postDto) {
        try {
            return objectMapper.writeValueAsString(postDto);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            log.error("JsonProcessingException" + e.getMessage(),e);
            throw new GlobalException("JsonProcessingException" + e.getMessage(),e);
        }
    }
}
