package com.test.project.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthNameHolder {

    public static String getAuthUsername (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
    public static Authentication getAuth (){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
