package com.test.project.aspect;

import com.test.project.exceptions.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionAspect {

    private final Connection connection;

    @Before(value = "@annotation(com.test.project.annotation.Transaction)")
    public void beforeMethod(){
        log.debug("before method set auto commit false");
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            log.error("SQL exception" + e.getMessage(),e);
            throw new GlobalException("SQL exception" + e.getMessage(),e);
        }
    }
    @AfterReturning(value = "@annotation(com.test.project.annotation.Transaction)")
    public void commitResult(){
        log.debug("commit result");
        try {
            connection.commit();
        } catch (SQLException e) {
            log.error("SQL exception" + e.getMessage(),e);
            throw new GlobalException("SQL exception" + e.getMessage(),e);
        }
    }
    @AfterThrowing(value = "@annotation(com.test.project.annotation.Transaction)",throwing = "e")
    public void rollBackAdvice(RuntimeException e){
        log.warn("roll back");
        try {
            connection.rollback();
        } catch (SQLException ex) {
            log.error("SQL exception" + e.getMessage(),e);
            throw new GlobalException("SQL exception" + e.getMessage(),e);
        }
    }
    @After(value = "@annotation(com.test.project.annotation.Transaction)")
    public void afterMethod() {
        log.debug("After method");
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error("SQL exception" + e.getMessage(),e);
            throw new GlobalException("SQL exception" + e.getMessage(),e);
        }
    }

}
