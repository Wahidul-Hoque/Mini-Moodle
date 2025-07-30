package com.example.servicecodes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.utils.DatabaseConnection;
import com.example.utils.PasswordUtils;

public class TeacherLoginService {

    public static int validateTeacherLogin(String enteredId, String enteredPassword) {
        String sql = "SELECT * FROM teacher WHERE username = ?";
        int teacherId = -1;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, enteredId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("password_hash");
                String enteredPasswordHash = PasswordUtils.hashPassword(enteredPassword);
                if (enteredPasswordHash.equals(storedHash)) {
                    teacherId = rs.getInt("id");
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
