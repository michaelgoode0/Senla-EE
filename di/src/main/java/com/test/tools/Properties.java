package com.test.tools;

import java.io.FileInputStream;
import java.io.IOException;

public class Properties {
    private static Properties properties = null;

    public static Properties getInstance() {
        if (properties == null) {
            properties = new Properties();
        }
        return properties;
    }


    public String getProperty(String propertyName) throws IOException {
        java.util.Properties props = new java.util.Properties();
        try {
            props.load(new FileInputStream("./main/src/main/resources/application.properties"));
        } catch (IOException e) {
            throw new IOException(e.toString());
        }
        String property = props.getProperty(propertyName);
        if (property == null) {
            throw new IOException("Property not found");
        }
        return property;
    }
}

