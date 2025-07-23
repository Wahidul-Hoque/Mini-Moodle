package com.example.servicecodes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.utils.*;
public class AdminService {
    public static int getTotalCourseCount() {
        int totalCourses = 0;
        String sql = "SELECT COUNT(*) FROM course";  // SQL query to count the total number of courses

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalCourses = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error in getting total course count: " + e.getMessage());
        }
        return totalCourses;
    }

    public static int getTotalStudentCount() {
        int totalStudent = 0;
        String sql = "SELECT COUNT(*) FROM student";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalStudent = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error in getting total student count: " + e.getMessage());
        }
        return totalStudent;
    }

    public static int getTotalTeacherCount() {
        int totalTeacher = 0;
        String sql = "SELECT COUNT(*) FROM teacher";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalTeacher = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error in getting total teacher count: " + e.getMessage());
        }
        return totalTeacher;
    }

    public static List<CourseInfoAdmin> getAllCourses() {
        List<CourseInfoAdmin> courses = new ArrayList<>();
        String sql = "SELECT c.id, c.title, c.description, t.name FROM course c " +
                "JOIN teacher t ON c.teacher_id = t.id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int courseId = rs.getInt("id");
                String courseTitle = rs.getString("title");
                String courseDescription = rs.getString("description");
                String teacherName = rs.getString("name");

                CourseInfoAdmin courseInfo = new CourseInfoAdmin(courseId, courseTitle, courseDescription, teacherName);
                courses.add(courseInfo);
            }

        } catch (SQLException e) {
            System.out.println("Error in getting courses: " + e.getMessage());
        }

        return courses;
    }

    public static List<StudentInfo> getAllStudents() {
        List<StudentInfo> students = new ArrayList<>();
        String sql = "SELECT id, name, email FROM student";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int studentId = rs.getInt("id");
                String studentName = rs.getString("name");
                String studentEmail = rs.getString("email");

                StudentInfo student = new StudentInfo(studentId, studentName, studentEmail);
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("Error in getting all students: " + e.getMessage());
        }
        return students;
    }

}
