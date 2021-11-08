package com.test.project.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.test.project.dao","com.test.project.service","com.test.project.controller"})
public class Config {
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return  new ObjectMapper();
    }

}
