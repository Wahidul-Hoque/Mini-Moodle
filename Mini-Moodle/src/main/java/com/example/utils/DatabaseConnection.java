package com.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:E:/Mini-Moodle/Mini-Moodle/resources/moodle.db";  // Path to your SQLite database

    // Method to connect to the database
    public static Connection getConnection() throws SQLException {
        // Open a new connection every time
        return DriverManager.getConnection(URL);
    }

    // Method to close the connection
    public static void closeConnection(Connection connection) throws SQLException {
        // Close the provided connection
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
