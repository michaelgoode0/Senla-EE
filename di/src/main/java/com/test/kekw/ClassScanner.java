package com.test.kekw;

import com.test.annotations.Component;
import com.test.annotations.Value;

import org.reflections.Reflections;

import java.util.Set;

public class ClassScanner {

    public Set<Class<?>> reflectionsScan(Class<?> clazz) {
        Reflections reflections = new Reflections(clazz);
        return reflections.getTypesAnnotatedWith(Component.class);
    }
}
