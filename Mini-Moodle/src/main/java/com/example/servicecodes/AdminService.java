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

}
