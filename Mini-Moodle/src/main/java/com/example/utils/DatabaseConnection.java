package com.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:E:/Mini-Moodle/Mini-Moodle/resources/moodle.db";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL);
    }

    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
