package com.example.servicecodes;

import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.utils.DatabaseConnection;

public class EnrollmentService {
    //for student
    public static boolean requestEnrollment(int studentId, String courseTitle) {
        String sql = "INSERT INTO enrollment (student_id, course_id, status, email, grade) " +
                "SELECT ?, c.id, 'pending', s.email, 'notset' " +
                "FROM student s " +
                "INNER JOIN course c ON c.title = ? " +
                "WHERE s.id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the parameters for the query
            stmt.setInt(1, studentId);  // student_id
            stmt.setString(2, courseTitle);  // courseTitle
            stmt.setInt(3, studentId);  // student_id again to ensure email is correctly linked to the student

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Enrollment request submitted successfully for student ID: " + studentId + " in course: " + courseTitle);
                return true;
            } else {
                System.out.println("Failed to submit enrollment request. Course might not exist or student details are incorrect.");
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


}
