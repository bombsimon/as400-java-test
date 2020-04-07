package com.bombsimon.as400test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBProperties {
    private static DBProperties globalProperties;
    private Properties prop = new Properties();

    private DBProperties() {
        InputStream inStream = getClass().getClassLoader().getResourceAsStream("connection.properties");

        assert inStream != null;

        try {
            prop.load(inStream);
        } catch (IOException e) {
            System.out.println("Could not read the properties");
            e.printStackTrace();

            System.exit(1);
        }
    }

    public static DBProperties getInstance() {
        if (globalProperties == null) {
            globalProperties = new DBProperties();
        }

        return globalProperties;
    }

    public String getValue(String key) {
        return prop.getProperty(key, null);
    }

    public int getIntValue(String key) {
        return Integer.parseInt(prop.getProperty(key, null));
    }
}
