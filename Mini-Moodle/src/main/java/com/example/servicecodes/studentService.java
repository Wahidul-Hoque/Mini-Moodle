package com.example.servicecodes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.utils.DatabaseConnection;
public class studentService {
    // Method to get enrolled courses for a student using studentId
    public static List<CourseInfo> getEnrolledCoursesForStudent(int studentId) {
        List<CourseInfo> enrolledCourses = new ArrayList<>();
        String sql = "SELECT c.id, c.title, e.grade " +
                "FROM course c " +
                "INNER JOIN enrollment e ON c.id = e.course_id " +
                "WHERE e.student_id = ? AND e.status = 'approved'";  // Retrieve courses with 'approved' status

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);  // Set the studentId parameter in the query
            ResultSet rs = stmt.executeQuery();

            // Process the results
            while (rs.next()) {
                int courseId = rs.getInt("id");
                String courseTitle = rs.getString("title");
                String grade = rs.getString("grade");

                // Create a CourseInfo object for each course the student is enrolled in
                CourseInfo course = new CourseInfo(courseId, courseTitle, grade);
                enrolledCourses.add(course);
            }

        } catch (SQLException e) {
            System.out.println("Error in getting enrolled courses for student: " + e.getMessage());
        }

        return enrolledCourses;
    }
}
