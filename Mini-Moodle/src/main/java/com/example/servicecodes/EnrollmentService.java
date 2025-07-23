package com.example.servicecodes;

import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.utils.DatabaseConnection;

public class EnrollmentService {
    //for student
    public static boolean requestEnrollment(int studentId, String courseTitle) {
        String sql = "INSERT INTO enrollment (student_id, course_id, status) " +
                "SELECT ?, id, 'pending' FROM course WHERE title = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            stmt.setString(2, courseTitle);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Enrollment request submitted successfully for student ID: " + studentId + " in course: " + courseTitle);
                return true;
            } else {
                System.out.println("Failed to submit enrollment request. Course might not exist.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error processing enrollment request: " + e.getMessage());
            return false;
        }
    }
    //for teacher
    public static boolean approveEnrollment(int studentId, int courseId) {
        String sql = "UPDATE enrollment SET status = 'approved' WHERE student_id = ? AND course_id = ? AND status = 'pending'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Enrollment successfully approved for student ID: " + studentId + " in course ID: " + courseId);
                return true;
            } else {
                System.out.println("Failed to approve enrollment. Check if the student is enrolled and the status is pending.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error approving enrollment: " + e.getMessage());
            return false;
        }
    }

    // Method for teacher
    public static boolean rejectEnrollment(int studentId, int courseId) {
        String sql = "UPDATE enrollment SET status = 'rejected' WHERE student_id = ? AND course_id = ? AND status = 'pending'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Enrollment successfully rejected for student ID: " + studentId + " in course ID: " + courseId);
                return true;
            } else {
                System.out.println("Failed to reject enrollment. Check if the student is enrolled and the status is pending.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error rejecting enrollment: " + e.getMessage());
            return false;
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
