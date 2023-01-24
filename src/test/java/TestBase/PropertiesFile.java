package TestBase;

import java.io.*;
import java.util.Properties;

public class PropertiesFile {

    public static Properties prop = new Properties();
    static String path = System.getProperty("user.dir");

    public static void getProperties() throws IOException {
        InputStream input = new FileInputStream(path + "\\src\\test\\resources\\ConfigVariables\\config.properties");
        prop.load(input);
        String browser = prop.getProperty("browser");
        TestSetup.browserName = prop.getProperty("browser");
    }

    public static void setProperties(Object text) throws IOException {
        OutputStream output = new FileOutputStream(path + "\\src\\test\\resources\\ConfigVariables\\config.properties");
        prop.store(output, null);
    }
}
