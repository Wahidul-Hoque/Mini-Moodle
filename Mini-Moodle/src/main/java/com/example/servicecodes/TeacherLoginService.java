package com.example.servicecodes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.utils.DatabaseConnection;

public class TeacherLoginService {

    // Method to validate teacher login credentials
    public static int validateTeacherLogin(String enteredId, String enteredPassword) {
        String sql = "SELECT * FROM teacher WHERE username = ? AND password = ?";  // Query to check if teacher exists
        int teacherId = -1;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the parameters for the query
            stmt.setString(1, enteredId);
            stmt.setString(2, enteredPassword);

            // Execute the query and get the result
            ResultSet rs = stmt.executeQuery();

            // If a result is found, the teacher is registered and the credentials match
            if (rs.next()) {
                teacherId = rs.getInt("id");
                System.out.println("Login successful for teacher: " + enteredId);
            } else {
                System.out.println("Invalid ID or password.");
            }

        } catch (SQLException e) {
            System.out.println("Error in validating teacher login: " + e.getMessage());

        }
        return teacherId;
    }
}
