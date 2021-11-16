package com.test.project.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.project.api.service.UserService;
import com.test.project.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserController {

    private final Logger logger= LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    private final ObjectMapper objectMapper;

    public String create(String requestJson) {
        try {
            UserDto dto = objectMapper.readValue(requestJson, UserDto.class);
            UserDto response = userService.create(dto);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error("JsonProcessingException" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String update(String requestJson){
        try {
            UserDto dto = objectMapper.readValue(requestJson, UserDto.class);
            UserDto response = userService.update(dto);
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
            UserDto response = userService.read(id);
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
            UserDto response = userService.delete(id);
            String responseJson = objectMapper.writeValueAsString(response);
            return objectMapper.readTree(responseJson).toString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error("JsonProcessingException" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public String mapToJson(UserDto userDto) {
        try {
            return objectMapper.writeValueAsString(userDto);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            logger.error("JsonProcessingException" + e.getMessage());
            throw new RuntimeException(e);
        }
    }



}
