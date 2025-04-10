package com.vag.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String connectionString = PropertyUtil.getPropertyString("db.properties");
                connection = DriverManager.getConnection(connectionString);
                System.out.println("Database connected successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
