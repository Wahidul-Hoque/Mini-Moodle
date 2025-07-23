package com.example.servicecodes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.utils.DatabaseConnection;
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
}
