package com.test.project;

import com.test.project.controller.UserController;
import com.test.tools.ApplicationContext;
import com.test.tools.DependencyInjector;

public class Application {
    public static void main(String[] args) throws  IllegalAccessException{
        ApplicationContext context = new ApplicationContext();
        DependencyInjector.run(Application.class,context);
        UserController userController = context.getBean(UserController.class);
        System.out.println("Database url: " + userController.getUrl());
    }
}
