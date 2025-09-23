package utils;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropReader {

    public static String getProperty(String key) {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
            properties.load(fileInputStream);
            String value = properties.getProperty(key);
            if (StringUtils.isEmpty(value)) {
                throw new RuntimeException("Not able to find the value for the key: " + key);
            }
            return value;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}