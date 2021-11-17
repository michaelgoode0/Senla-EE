package com.test.project.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.project.api.service.UserService;
import com.test.project.dto.UserDto;
import com.test.project.exceptions.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final ObjectMapper objectMapper;

    public String create(String requestJson) {
        try {
            UserDto dto = objectMapper.readValue(requestJson, UserDto.class);
            UserDto response = userService.create(dto);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException" + e.getMessage(),e);
            throw new GlobalException("JsonProcessingException" + e.getMessage(),e);
        }
    }

    public String update(String requestJson){
        try {
            UserDto dto = objectMapper.readValue(requestJson, UserDto.class);
            UserDto response = userService.update(dto);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException" + e.getMessage(),e);
            throw new GlobalException("JsonProcessingException" + e.getMessage(),e);
        }
    }
    public String read(Long id){
        try {
            UserDto response = userService.read(id);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException" + e.getMessage(),e);
            throw new GlobalException("JsonProcessingException" + e.getMessage(),e);
        }
    }
    public String delete(Long id){
        try {
            UserDto response = userService.delete(id);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException" + e.getMessage(),e);
            throw new GlobalException("JsonProcessingException" + e.getMessage(),e);
        }
    }
    public String mapToJson(UserDto userDto) {
        try {
            return objectMapper.writeValueAsString(userDto);
        }catch (JsonProcessingException e){
            log.error("JsonProcessingException" + e.getMessage(),e);
            throw new GlobalException("JsonProcessingException" + e.getMessage(),e);
        }
    }



}
