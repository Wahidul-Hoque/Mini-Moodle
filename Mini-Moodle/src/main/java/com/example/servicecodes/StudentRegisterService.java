package com.example.servicecodes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.utils.DatabaseConnection;
import com.example.utils.PasswordUtils;

public class StudentRegisterService {

    public static boolean registerStudent(String username, String name, String password, String email) {
        String hashedPassword = PasswordUtils.hashPassword(password);

        String sql = "INSERT INTO student (username, password_hash, name, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, name);
            stmt.setString(4, email);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student registered successfully!");
                return true;
            } else {
                System.out.println("Student registration failed.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error registering student: " + e.getMessage());
            return false;
        }
    }
}
