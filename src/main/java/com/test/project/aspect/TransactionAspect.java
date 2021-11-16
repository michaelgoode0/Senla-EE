package com.test.project.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Aspect
@Component
@RequiredArgsConstructor
public class TransactionAspect {

    private final Logger logger= LoggerFactory.getLogger(TransactionAspect.class);

    private final Connection connection;

    @Before(value = "@annotation(com.test.project.annotation.Transaction)")
    public void beforeMethod(){
        logger.debug("before method set auto commit false");
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("SQL exception" + e.getMessage());
        }
    }
    @AfterReturning(value = "@annotation(com.test.project.annotation.Transaction)")
    public void commitResult(){
        logger.debug("commit result");
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error("SQL exception" + e.getMessage());
        }
    }
    @AfterThrowing(value = "@annotation(com.test.project.annotation.Transaction)",throwing = "e")
    public void rollBackAdvice(RuntimeException e){
        logger.warn("roll back");
        try {
            connection.rollback();
        } catch (SQLException ex) {
            logger.error("SQL exception" + ex.getMessage());
        }
    }
    @After(value = "@annotation(com.test.project.annotation.Transaction)")
    public void afterMethod() {
        logger.debug("After method");
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("SQL exception" + e.getMessage());
        }
    }

}
