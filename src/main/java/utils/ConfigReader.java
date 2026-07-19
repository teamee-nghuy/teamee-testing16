package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties PROPERTIES = loadProperties();

    public static Properties loadProperties(){
        Properties props = new Properties();

        try(InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if(inputStream == null){
                throw new RuntimeException("File not found");
            }
            props.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Error reading properties" + e);
        }
        return props;
    }

    //define function get value
    public static String get(String key) {
        String val = PROPERTIES.getProperty(key);

        if(val == null){
            throw new RuntimeException("Key not found " + key);
        }

        return val.trim();
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
