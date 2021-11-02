package com.test.project;

import com.test.kekw.ApplicationContext;
import com.test.kekw.DependencyInjector;
import com.test.project.api.controller.UserControllerInt;

public class Application {
    public static void main(String[] args) throws  IllegalAccessException{
        ApplicationContext context = new ApplicationContext();
        DependencyInjector.run(Application.class,context);
        UserControllerInt userController = context.getBean(UserControllerInt.class);
        System.out.println("Database url: " + userController.display());
    }
}
