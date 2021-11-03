package com.test.tools;

import com.test.annotations.Autowired;
import com.test.annotations.Component;
import com.test.annotations.Value;
import com.test.exceptions.CustomException;
import com.test.exceptions.InjectionException;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationContext {

    private final Map<Class<?>, Object> context;
    private final Map<Class<?>, Class<?>> classInterfaceMap;
    private ObjectFactory objectFactory;

    public ApplicationContext() {
        this.context = new HashMap<>();
        this.classInterfaceMap = new HashMap<>();
    }

    public void setFactory(ObjectFactory factory) {
        this.objectFactory = factory;
    }

    public void createContext(Set<Class<?>> classes) throws IllegalAccessException, CustomException {
        for (Class<?> clazz : classes) {
            if (!clazz.isAnnotationPresent(Component.class)) {
                continue;
            }

            Class<?>[] interfaces = clazz.getInterfaces();

            if (interfaces.length == 0) {
                classInterfaceMap.put(clazz, clazz);
                continue;
            }

            for (Class<?> interfaceKey : interfaces) {
                if (!classInterfaceMap.containsValue(interfaceKey)) {
                    classInterfaceMap.put(clazz, interfaceKey);
                }
            }
        }
        fillContext();
    }

    private void fillContext() throws IllegalAccessException, CustomException {
        for (Class<?> clazz : classInterfaceMap.keySet()) {
            Object bean = objectFactory.createBean(clazz);
            context.put(clazz, bean);
            injectDependencies(clazz, bean);
        }
    }

    private void injectDependencies(Class<?> clazz,Object bean) throws IllegalAccessException, CustomException {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            handleValueAnnotation(field,bean);
            handleAutowiredAnnotation(field,bean);
        }
    }

    private void handleValueAnnotation(Field field, Object bean) throws CustomException {
        if (field.isAnnotationPresent(Value.class)) {
            Value res = field.getAnnotation(Value.class);
            String value = res.value();
            Configurations configurations = Configurations.getInstance();
            try {
                String someValue = configurations.getProperties(value);
                field.setAccessible(true);
                field.set(bean, someValue);
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void handleAutowiredAnnotation(Field field,Object bean) throws IllegalAccessException, CustomException {
        if (field.isAnnotationPresent(Autowired.class)) {
            Object instance = this.getBean(field.getType());
            field.setAccessible(true);
            field.set(bean, instance);

            injectDependencies(instance.getClass(), instance);
        }
    }

    public <T> T getBean(Class<?> type){
        Set<Map.Entry<Class<?>, Class<?>>> classSet = classInterfaceMap.entrySet().stream()
                .filter(entry -> type.equals(entry.getValue()) ||  type.equals(entry.getKey()))
                .collect(Collectors.toSet());

        if (classSet.size() != 1) {
            throw new InjectionException("Message");
        }

        Class<?> clazz = classSet.stream()
                .findFirst()
                .get()
                .getKey();

        if (context.containsKey(clazz)) {
            return (T) context.get(clazz);
        }

        Object bean = objectFactory.createBean(clazz);
        context.put(clazz, bean);
        return (T) bean;
    }
}
