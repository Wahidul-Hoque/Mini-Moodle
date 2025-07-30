package com.example.servicecodes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.utils.DatabaseConnection;
import com.example.utils.PasswordUtils;

public class AdminLoginService {

    public static boolean validateAdminLogin(String enteredId, String enteredPassword) {
        String sql = "SELECT * FROM admin WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, enteredId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedHash = rs.getString("password_hash");

                String enteredPasswordHash = PasswordUtils.hashPassword(enteredPassword);
                if (enteredPasswordHash.equals(storedHash)) {
                    System.out.println("Login successful for admin: " + enteredId);
                    return true;
                } else {
                    System.out.println("Invalid ID or password.");
                    return false;
                }
            } else {
                System.out.println("Invalid ID or password.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error in validating admin login: " + e.getMessage());
            return false;
        }
    }
}
