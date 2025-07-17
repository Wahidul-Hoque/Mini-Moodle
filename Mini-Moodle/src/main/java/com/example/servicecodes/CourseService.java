package com.example.servicecodes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.utils.DatabaseConnection;

public class CourseService {

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

    //(only approved students)
    public static List<StudentInfo> getEnrolledStudents(String courseId) {

        if(courseId == null || courseId.isEmpty()) {
            System.out.println("Course ID is null or empty.");
            return new ArrayList<>();
        }

        List<StudentInfo> enrolledStudents = new ArrayList<>();
        String sql = "SELECT s.id, s.name, s.email FROM student s INNER JOIN enrollment e ON s.id = e.student_id " +
                "WHERE e.course_id = ? AND e.status = 'approved'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, courseId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int studentId = rs.getInt("id");
                String studentName = rs.getString("name");
                String studentEmail = rs.getString("email");
                StudentInfo student = new StudentInfo(studentId, studentName, studentEmail);
                enrolledStudents.add(student);
            }

        } catch (SQLException e) {
            System.out.println("Error in getting enrolled students: " + e.getMessage());
        }
        return enrolledStudents;
    }

    // StudentInfo class to hold the student details
    public static class StudentInfo{
        private int id;
        private String name;
        private String email;
        private String grade;
        public StudentInfo(int id,String name,String email){
            this.id = id;
            this.name = name;
            this.email = email;
            this.grade = null; // Default grade
        }
        public int getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public String getEmail() {
            return email;
        }
        public String getGrade() {
            return grade;
        }
        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Email: " + email;
        }
    }


}

