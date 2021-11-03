package com.test.tools;

import com.test.exceptions.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurations {

    private Logger logger= LoggerFactory.getLogger(this.getClass());
    private static Configurations instance;
    private Properties properties = new Properties();

    private Configurations() throws CustomException {
        if (instance != null) {
            throw new CustomException("exception");
        }
        loadConfiguration();
    }
    public static Configurations getInstance() throws CustomException {
        if(instance==null){
            instance = new Configurations();
        }
        return instance;
    }

    private void loadConfiguration() throws CustomException {
        try(InputStream inputStream = new FileInputStream("./main/src/main/resources/application.properties")) {
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            logger.error("File not found "+e.getMessage());
            throw new CustomException("message",e);

        } catch (IOException e) {
            logger.error("Exception I/O stream "+e.getMessage());
            throw new CustomException( "message",e);
        }
    }

    public String getProperties(String key) {
        return properties.getProperty(key);
    }
}
