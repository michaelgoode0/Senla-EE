package com.test.tools;


import com.test.exceptions.CustomException;
import com.test.exceptions.InjectionException;

public class DependencyInjector {

    public static void run(Class<?> startClass, ApplicationContext applicationContext) throws IllegalAccessException {
        try {
            ClassScanner classScanner = new ClassScanner();
            ObjectFactory factory = new ObjectFactory();
            applicationContext.setFactory(factory);
            applicationContext.createContext(classScanner.reflectionsScan(startClass));
        } catch (IllegalAccessException | CustomException e) {
            throw new InjectionException("Message", e);
        }
    }
}
