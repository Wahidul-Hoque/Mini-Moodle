package com.example.servicecodes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.utils.DatabaseConnection;
import com.example.utils.PasswordUtils;
public class TeacherLoginService {

    // Method to validate teacher login credentials
    public static int validateTeacherLogin(String enteredId, String enteredPassword) {
        String sql = "SELECT * FROM teacher WHERE username = ?";  // Query to get teacher by username
        int teacherId = -1;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the parameters for the query
            stmt.setString(1, enteredId);

            // Execute the query and get the result
            ResultSet rs = stmt.executeQuery();

            // If a result is found, the teacher is registered
            if (rs.next()) {
                String storedHash = rs.getString("password_hash");  // Get the stored password hash

                // Hash the entered password and compare with the stored hash
                String enteredPasswordHash = PasswordUtils.hashPassword(enteredPassword);
                if (enteredPasswordHash.equals(storedHash)) {
                    teacherId = rs.getInt("id");  // Login successful, return teacher ID
                    System.out.println("Login successful for teacher: " + enteredId);
                } else {
                    System.out.println("Invalid ID or password.");
                }
            } else {
                System.out.println("No teacher found with username: " + enteredId);
            }

        } catch (SQLException e) {
            System.out.println("Error in validating teacher login: " + e.getMessage());
        }
        return teacherId;
    }
}
