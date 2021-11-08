package com.test.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.project.dto.UserProfileDto;
import com.test.project.service.UserProfileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileController {

    @Autowired
    private final UserProfileServiceImpl userProfileService;
    @Autowired
    private final ObjectMapper objectMapper;


    public String create(String requestJson) {
        try {
            UserProfileDto dto = objectMapper.readValue(requestJson, UserProfileDto.class);
            UserProfileDto response = userProfileService.create(dto);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String update(String requestJson){
        try {
            UserProfileDto dto = objectMapper.readValue(requestJson, UserProfileDto.class);
            UserProfileDto response = userProfileService.update(dto);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public String read(Long id){
        try {
            UserProfileDto response = userProfileService.read(id);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public String delete(Long id){
        try {
            UserProfileDto response = userProfileService.delete(id);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
