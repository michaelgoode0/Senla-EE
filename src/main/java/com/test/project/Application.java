package com.test.project;

import com.test.project.config.JdbcConfig;
import com.test.project.controller.PostController;
import com.test.project.controller.UserController;
import com.test.project.controller.UserProfileController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
        UserController userController = context.getBean(UserController.class);
        UserProfileController userProfileController = context.getBean(UserProfileController.class);
        PostController postController = context.getBean(PostController.class);

        String jsonStringUser = "{\"id\":1,\"email\":\"Name1\", \"password\": \"32131\",\"profile\": null }";
        userController.create(jsonStringUser);
        System.out.println(userController.read(1L));
        String updateUser ="{\"id\":1,\"email\":\"Name1@mail.ru\", \"password\": \"kkkkk323\",\"profile\": null }";
        userController.update(updateUser);
        System.out.println(userController.read(1L));

        String jsonStringUser1 = "{\"id\":2,\"email\":\"@mail.ru\", \"password\": \"324fg131\",\"profile\": null}";
        userController.create(jsonStringUser1);
        userController.delete(2L);


        String jsonStringUserProfile = "{\"id\":1,\"name\":\"Daniel\", \"posts\": null }";
        userProfileController.create(jsonStringUserProfile);
        System.out.println(userProfileController.read(1L));
        String updateProfile="{\"id\":1,\"name\":\"Alexey\", \"posts\": null }";
        userProfileController.update(updateProfile);
        System.out.println(userProfileController.read(1L));

        String jsonStringUserProfile1 = "{\"id\":2,\"name\":\"Ilya\", \"posts\": null }";
        userProfileController.create(jsonStringUserProfile1);
        userProfileController.delete(2L);

        String jsonStringPost= "{\"id\":1,\"text\":\"Hello there\"}";
        postController.create(jsonStringPost);
        System.out.println(postController.read(1L));
        String updatePost= "{\"id\":1,\"text\":\"Bye Bye\" }";
        postController.update(updatePost);
        System.out.println(postController.read(1L));


        String jsonStringPost1= "{\"id\":2,\"text\":\"Hello again\" }";
        postController.create(jsonStringPost1);
        postController.delete(2L);
    }
}
