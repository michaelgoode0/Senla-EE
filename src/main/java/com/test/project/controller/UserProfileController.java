package com.test.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.project.api.service.UserProfileService;
import com.test.project.dto.UserProfileDto;
import com.test.project.exceptions.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class UserProfileController {

    private Logger logger= LoggerFactory.getLogger(UserProfileController.class);
    private final UserProfileService userProfileService;

    private final ObjectMapper objectMapper;


    public String create(String requestJson) {
        try {
            UserProfileDto dto = objectMapper.readValue(requestJson, UserProfileDto.class);
            UserProfileDto response = userProfileService.create(dto);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException" + e.getMessage(),e);
            throw new GlobalException("JsonProcessingException" + e.getMessage(),e);
        }
    }

    public String update(String requestJson){
        try {
            UserProfileDto dto = objectMapper.readValue(requestJson, UserProfileDto.class);
            UserProfileDto response = userProfileService.update(dto);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException" + e.getMessage(),e);
            throw new GlobalException("JsonProcessingException" + e.getMessage(),e);
        }
    }
    public String read(Long id){
        try {
            UserProfileDto response = userProfileService.read(id);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException" + e.getMessage(),e);
            throw new GlobalException("JsonProcessingException" + e.getMessage(),e);
        }
    }
    public String delete(Long id){
        try {
            UserProfileDto response = userProfileService.delete(id);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException" + e.getMessage(),e);
            throw new GlobalException("JsonProcessingException" + e.getMessage(),e);
        }
    }


}
