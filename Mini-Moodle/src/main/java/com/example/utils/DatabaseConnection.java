package com.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:moodle.db";  // Path to your SQLite database
    private static Connection connection = null;

    // Method to connect to the database
    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL);
        }
        return connection;
    }

    // Method to close the connection
    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
