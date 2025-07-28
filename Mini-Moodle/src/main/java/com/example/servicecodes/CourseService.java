package com.example.servicecodes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.utils.*;

public class CourseService {

    public static String getTeacherName(int teacherId) {
        String teacherName = null;
        String sql = "SELECT name FROM teacher WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, teacherId);

            // Execute the query and get the result
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                teacherName = rs.getString("name");  // Retrieve teacher name
            }

        } catch (SQLException e) {
            System.out.println("Error in getting teacher name: " + e.getMessage());
        }
        return teacherName;
    }

    // Method to get the student name by studentId

    public static String getStudentName(int studentId) {
        String studentName = null;
        String sql = "SELECT name FROM student WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);

            // Execute the query and get the result
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                studentName = rs.getString("name");  // Retrieve student name
            }

        } catch (SQLException e) {
            System.out.println("Error in getting student name: " + e.getMessage());
        }
        return studentName;
    }
    public static TeacherInfo getTeacherDetails(int teacherId) {
        TeacherInfo teacherProfile = null;
        String sql = "SELECT t.id, t.name, t.email, t.username, c.title as course_title " +
                "FROM teacher t " +
                "INNER JOIN course c ON t.id = c.teacher_id " +
                "WHERE t.id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String courseTitle = rs.getString("course_title");

                teacherProfile = new TeacherInfo(teacherId, name, email, courseTitle,username);
            }

        } catch (SQLException e) {
            System.out.println("Error in fetching teacher profile: " + e.getMessage());
        }

        return teacherProfile;
    }


    // Method to get the course ID assigned to a teacher
    public static String getCourseIdForTeacher(int teacherId) {
        String courseId = null;
        String sql = "SELECT id FROM course WHERE teacher_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, teacherId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                courseId = rs.getString("id");  // Retrieve the course ID
            }
        } catch (SQLException e) {
            System.out.println("Error in getting course ID for teacher: " + e.getMessage());
        }
        return courseId;
    }

    public static  String getCourseName(String courseId){
        String courseName = null;
        String sql = "SELECT title FROM course WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                courseName = rs.getString("title");
            }
        } catch (SQLException e) {
            System.out.println("Error in getting course name: " + e.getMessage());
        }
        return courseName;
    }

    public static String getCourseDescription(String courseId){
        String courseDescription = null;
        String sql = "SELECT description FROM course WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                courseDescription = rs.getString("description");
            }
        }catch (SQLException e) {
            System.out.println("Error in getting course description: " + e.getMessage());
        }
        return courseDescription;
    }

    public static boolean setStudentGrade(int studentId, int courseId, String grade) {
        String sql = "UPDATE enrollment SET grade = ? WHERE student_id = ? AND course_id = ? AND status = 'approved'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, grade);
            stmt.setInt(2, studentId);
            stmt.setInt(3, courseId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error setting student grade: " + e.getMessage());
            return false;
        }
    }

    public static boolean changeTeacherPassword(int teacherId, String newPassword) {
        String hashedPassword = PasswordUtils.hashPassword(newPassword);
        String sql = "UPDATE teacher SET password_hash = ? WHERE id = ?";

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

    // (only approved students)
    public static List<StudentInfo> getEnrolledStudents(int courseId) {
        List<StudentInfo> approvedStudents = new ArrayList<>();
        String sql = "SELECT s.id, s.name, s.email, e.grade " +
                "FROM student s " +
                "INNER JOIN enrollment e ON s.id = e.student_id " +
                "WHERE e.course_id = ? AND e.status = 'approved'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();

            // Process the results
            while (rs.next()) {
                int studentId = rs.getInt("id");
                String studentName = rs.getString("name");
                String studentEmail = rs.getString("email");
                String studentGrade = rs.getString("grade");

                // Create a StudentInfo object for each approved student
                StudentInfo student = new StudentInfo(studentId, studentName, studentEmail, studentGrade);
                approvedStudents.add(student);
            }

        } catch (SQLException e) {
            System.out.println("Error in getting approved students: " + e.getMessage());
        }

        return approvedStudents;
    }

    public static int getEnrolledStudentCount(int courseId) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM enrollment WHERE course_id = ? AND status = 'approved'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);  // Retrieve the count of approved students
            }
        } catch (SQLException e) {
            System.out.println("Error in getting enrolled student count: " + e.getMessage());
        }
        return count;
    }

    public static List<StudentInfo> getPendingStudents(int courseId) {
        List<StudentInfo> pendingStudents = new ArrayList<>();
        String sql = "SELECT s.id, s.name, s.email, e.grade " +
                "FROM student s " +
                "INNER JOIN enrollment e ON s.id = e.student_id " +
                "WHERE e.course_id = ? AND e.status = 'pending'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, courseId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int studentId = rs.getInt("id");
                String studentName = rs.getString("name");
                String studentEmail = rs.getString("email");
                String studentGrade = rs.getString("grade");

                StudentInfo student = new StudentInfo(studentId, studentName, studentEmail, studentGrade);
                pendingStudents.add(student);
            }

        } catch (SQLException e) {
            System.out.println("Error in getting pending students: " + e.getMessage());
        }

        return pendingStudents;
    }





}

