package com.example.servicecodes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.utils.DatabaseConnection;

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
    // TODO: Check for errors : Wahid
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

    // (only approved students)
    public static List<StudentInfo> getEnrolledStudents(int courseId) {
        List<StudentInfo> approvedStudents = new ArrayList<>();
        String sql = "SELECT s.id, s.name, s.email, e.grade " +
                "FROM student s " +
                "INNER JOIN enrollment e ON s.id = e.student_id " +
                "WHERE e.course_id = ? AND e.status = 'approved'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, courseId);  // Set the courseId parameter in the query
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




}

