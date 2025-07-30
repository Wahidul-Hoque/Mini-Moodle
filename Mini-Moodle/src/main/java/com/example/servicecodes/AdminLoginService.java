package com.example.servicecodes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.utils.DatabaseConnection;
import com.example.utils.PasswordUtils;

public class AdminLoginService {

    // Method to validate teacher login credentials
    public static boolean validateAdminLogin(String enteredId, String enteredPassword) {
        String sql = "SELECT * FROM admin WHERE username = ?";  // Query to check if admin exists

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the parameters for the query
            stmt.setString(1, enteredId);

            // Execute the query and get the result
            ResultSet rs = stmt.executeQuery();

            // If a result is found, the teacher is registered and the credentials match
            if (rs.next()) {
                String storedHash = rs.getString("password_hash");  // Get the stored password hash

                // Hash the entered password and compare with the stored hash
                String enteredPasswordHash = PasswordUtils.hashPassword(enteredPassword);
                if (enteredPasswordHash.equals(storedHash)) {
                    System.out.println("Login successful for admin: " + enteredId);
                    return true;  // Login successful
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
