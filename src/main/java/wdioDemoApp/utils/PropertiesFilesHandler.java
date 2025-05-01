package wdioDemoApp.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFilesHandler {
    // This method loads a properties file from the classpath
    public  Properties loadPropertiesFile(String propFileName) {
        Properties prop = new Properties();
        InputStream inputStream;


        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }


}
