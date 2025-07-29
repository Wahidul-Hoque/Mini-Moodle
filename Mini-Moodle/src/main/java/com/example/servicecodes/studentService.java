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

    public static List<CourseInfo> getPendingCoursesForStudent(int studentId) {
        List<CourseInfo> pendingCourses = new ArrayList<>();
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
                pendingCourses.add(course);
            }

        } catch (SQLException e) {
            System.out.println("Error in getting enrolled courses for student: " + e.getMessage());
        }

        return pendingCourses;
    }

    public static StudentInfo getStudentDetails(int studentId) {
        StudentInfo studentDetails = null;
        String sql = "SELECT s.id, s.name, s.email, s.username " + "FROM student s " + "WHERE s.id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String studentName = rs.getString("name");
                String studentEmail = rs.getString("email");
                String studentUsername = rs.getString("username");
                String grade = "NOT_SET";
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

    public static List<Notification> getNotifications(int studentId) {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT c.title, n.message, n.timestamp " +
                "FROM notifications n " +
                "JOIN course c ON n.course_id = c.id " +
                "JOIN enrollment e ON e.course_id = c.id " +
                "WHERE e.student_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String courseName = rs.getString("title");
                String message = rs.getString("message");
                String timestamp = rs.getString("timestamp");

                notifications.add(new Notification(courseName, message, timestamp));
            }

        } catch (SQLException e) {
            System.out.println("Error in getting notifications for student: " + e.getMessage());
        }

        return notifications;
    }

}
