package com.test.project.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan({"com.test.project.dao","com.test.project.service","com.test.project.controller","com.test.project.annotation","com.test.project.aspect"})
@EnableAspectJAutoProxy
public class JdbcConfig {

    @Value(value = "${dataBaseUrl}")
    private String dataBaseUrl;
    @Value(value = "${username1}")
    private String username;
    @Value(value = "${password}")
    private String password;
    @Value(value = "${driver}")
    private String driver;

    @Bean
    public Connection connection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(dataBaseUrl,username,password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
