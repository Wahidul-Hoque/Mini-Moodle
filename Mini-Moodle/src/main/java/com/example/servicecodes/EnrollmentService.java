package com.example.servicecodes;

import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Import DatabaseConnection
import com.example.utils.DatabaseConnection;

public class EnrollmentService {

    // Method for a student to request enrollment in a course
    public static void requestEnrollment(int studentId, int courseId) {
        String sql = "INSERT INTO enrollment (student_id, course_id, status) VALUES (?, ?, 'pending')";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Set the student ID and course ID
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            
            // Execute the query
            stmt.executeUpdate();
            System.out.println("Enrollment request sent successfully.");
            
        } catch (SQLException e) {
            System.out.println("Error in enrolling student: " + e.getMessage());
        }
    }

    // Method for teacher to approve a student's enrollment request
    public static void approveEnrollment(int studentId, int courseId) {
        String sql = "UPDATE enrollment SET status = 'approved' WHERE student_id = ? AND course_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Set the student ID and course ID
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            
            // Execute the update
            stmt.executeUpdate();
            System.out.println("Enrollment approved successfully.");
            
        } catch (SQLException e) {
            System.out.println("Error in approving enrollment: " + e.getMessage());
        }
    }

    // Method for teacher to reject a student's enrollment request
    public static void rejectEnrollment(int studentId, int courseId) {
        String sql = "UPDATE enrollment SET status = 'rejected' WHERE student_id = ? AND course_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Set the student ID and course ID
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            
            // Execute the update
            stmt.executeUpdate();
            System.out.println("Enrollment rejected successfully.");
            
        } catch (SQLException e) {
            System.out.println("Error in rejecting enrollment: " + e.getMessage());
        }
    }

    // Method for teacher to view pending enrollment requests for a specific course
    public static void viewPendingEnrollments(int courseId) {
        String sql = "SELECT s.username, e.status FROM enrollment e " +
                     "JOIN student s ON e.student_id = s.id " +
                     "WHERE e.course_id = ? AND e.status = 'pending'";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                String studentUsername = rs.getString("username");
                String status = rs.getString("status");
                System.out.println("Student: " + studentUsername + ", Status: " + status);
            }
            
        } catch (SQLException e) {
            System.out.println("Error in fetching pending enrollments: " + e.getMessage());
        }
    }
}
