package com.example.servicecodes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.utils.DatabaseConnection;
import com.example.utils.PasswordUtils;

public class StudentLoginService {

    public static int validateStudentLogin(String enteredId, String enteredPassword) {
        String sql = "SELECT * FROM student WHERE username = ?";
        int studentId = -1;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, enteredId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("password_hash");  // Get the stored password hash
                String enteredPasswordHash = PasswordUtils.hashPassword(enteredPassword);
                if (enteredPasswordHash.equals(storedHash)) {
                    studentId = rs.getInt("id");  // Login successful, return teacher ID
                    System.out.println("Login successful for student: " + enteredId);
                } else {
                    System.out.println("Invalid ID or password.");
                }
            } else {
                System.out.println("No student found with username: " + enteredId);
            }
        } catch (SQLException e) {
            System.out.println("Error in validating student login: " + e.getMessage());
        }
        return studentId;
    }
}
