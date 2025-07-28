package com.example.servicecodes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.utils.DatabaseConnection;
import com.example.utils.PasswordUtils;

public class studentService {

    public static List<CourseInfo> getEnrolledCoursesForStudent(int studentId) {
        List<CourseInfo> enrolledCourses = new ArrayList<>();
        String sql = "SELECT c.id, c.title, c.description, e.grade " +
                "FROM course c " +
                "INNER JOIN enrollment e ON c.id = e.course_id " +
                "WHERE e.student_id = ? AND e.status = 'approved'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int courseId = rs.getInt("id");
                String courseTitle = rs.getString("title");
                String courseDescription = rs.getString("description");
                String grade = rs.getString("grade");

                CourseInfo course = new CourseInfo(courseId, courseTitle, courseDescription, grade);
                enrolledCourses.add(course);
            }

        } catch (SQLException e) {
            System.out.println("Error in getting enrolled courses for student: " + e.getMessage());
        }

        return enrolledCourses;
    }

    public static StudentInfo getStudentDetails(int studentId) {
        StudentInfo studentDetails = null;
        String sql = "SELECT s.id AS student_id, s.name AS student_name, s.email AS student_email, s.username AS student_username " +
                "FROM student s " +
                "WHERE s.id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);  // Set the studentId parameter
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String studentName = rs.getString("student_name");
                String studentEmail = rs.getString("student_email");
                String studentUsername = rs.getString("student_username");
                String grade = "notset";

                // Create the StudentDetails object and assign the values
                studentDetails = new StudentInfo(studentId, studentName, studentEmail,grade, studentUsername);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching student details: " + e.getMessage());
        }

        return studentDetails;
    }


    public static List<CourseInfo> getUnregisteredCoursesForStudent(int studentId) {
        List<CourseInfo> unregisteredCourses = new ArrayList<>();
        String sql = "SELECT c.id, c.title, c.description " +
                "FROM course c " +
                "WHERE c.id NOT IN ( " +
                "    SELECT e.course_id " +
                "    FROM enrollment e " +
                "    WHERE e.student_id = ? )";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int courseId = rs.getInt("id");
                String courseTitle = rs.getString("title");
                String courseDescription = rs.getString("description");

                CourseInfo course = new CourseInfo(courseId, courseTitle, courseDescription);
                unregisteredCourses.add(course);
            }

        } catch (SQLException e) {
            System.out.println("Error in getting unregistered courses for student: " + e.getMessage());
        }

        return unregisteredCourses;
    }

    public static boolean changeStudentPassword(int teacherId, String newPassword) {
        String hashedPassword = PasswordUtils.hashPassword(newPassword);
        String sql = "UPDATE student SET password_hash = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, hashedPassword);
            stmt.setInt(2, teacherId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Password successfully updated for teacher ID: " + teacherId);
                return true;
            } else {
                System.out.println("Failed to update password. Check if the teacher ID is valid.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error changing teacher password: " + e.getMessage());
            return false;
        }
    }
}
