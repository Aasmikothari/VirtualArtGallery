package com.vag.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    public static String getPropertyString(String fileName) {
        Properties props = new Properties();
        try (InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + fileName);
                return null;
            }
            props.load(input);

            String host = props.getProperty("host");
            String port = props.getProperty("port");
            String dbname = props.getProperty("dbname");
            String username = props.getProperty("username");
            String password = props.getProperty("password");

            return "jdbc:mysql://" + host + ":" + port + "/" + dbname +
                   "?user=" + username + "&password=" + password;

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
