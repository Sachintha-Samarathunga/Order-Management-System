package utils;

import java.io.*;
import java.util.Properties;

public class PropertyUtils {
    private static final String FILE_PATH = "src/test/resources/testdata.properties";
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = new FileInputStream(FILE_PATH)) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Properties file not found, a new one will be created.");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
        try (OutputStream output = new FileOutputStream(FILE_PATH)) {
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setIntProperty(String key, int value) {
        properties.setProperty(key, String.valueOf(value));
        try (OutputStream output = new FileOutputStream(FILE_PATH)) {
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}