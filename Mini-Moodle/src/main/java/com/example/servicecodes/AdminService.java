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

    public static List<TeacherInfo> getAllTeachers() {
        List<TeacherInfo> teacherList = new ArrayList<>();
        String sql = "SELECT t.id, t.name, t.email, c.title as course_title " +
                "FROM teacher t " +
                "INNER JOIN course c ON t.id = c.teacher_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int teacherId = rs.getInt("id");
                String teacherName = rs.getString("name");
                String teacherEmail = rs.getString("email");
                String courseTitle = rs.getString("course_title");

                TeacherInfo teacher = new TeacherInfo(teacherId, teacherName, teacherEmail, courseTitle);
                teacherList.add(teacher);
            }
        } catch (SQLException e) {
            System.out.println("Error in getting teacher info: " + e.getMessage());
        }
        return teacherList;
    }

    public static boolean addTeacher(String username, String password, String name, String email) {
        String hashedPassword = PasswordUtils.hashPassword(password);
        String sql = "INSERT INTO teacher (username, password_hash, name, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, name);
            stmt.setString(4, email);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Teacher added successfully!");
                return true;
            } else {
                System.out.println("Failed to add teacher.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error adding teacher: " + e.getMessage());
            return false;
        }
    }

    private static int getTeacherIdByUsername(String teacherName) {
        String sql = "SELECT id FROM teacher WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, teacherName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Error finding teacher by name: " + e.getMessage());
        }
        return -1;
    }

    public static boolean addCourse(String title, String description, String teacherUsername) {
        String sql = "INSERT INTO course (title, description, teacher_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int teacherId = getTeacherIdByUsername(teacherUsername);
            if (teacherId == -1) {
                System.out.println("Teacher not found");
                return false;
            }
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setInt(3, teacherId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Course added successfully!");
                return true;
            } else {
                System.out.println("Failed to add course.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error adding course: " + e.getMessage());
            return false;
        }
    }

    public static boolean changeAdminPassword(int adminId, String newPassword) {
        String hashedPassword = PasswordUtils.hashPassword(newPassword);
        String sql = "UPDATE admin SET password_hash = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, hashedPassword);
            stmt.setInt(2, adminId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Password successfully updated for teacher ID: " + adminId);
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
