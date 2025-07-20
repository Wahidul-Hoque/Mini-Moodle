package com.example.minimoodle;

import java.io.IOException;

import com.example.utils.Client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class StudentDashboardController {

    @FXML
    private Label studentDashboardLabel;

    @FXML
    private Label enrolledCoursesLabel;

    // Uncomment and add FXML for completedCoursesLabel and studentGradeLabel if needed
    // @FXML
    // private Label completedCoursesLabel;
    // @FXML
    // private Label studentGradeLabel;

    @FXML
    private Button viewCoursesButton;

    @FXML
    private Button viewGradesButton;

    @FXML
    private Button studentRefreshButton;

    @FXML
    private Button studentLogoutButton;

    private int studentId;

    public void initialize(int studentId) {
        this.studentId = studentId;
        loadStudentDashboardData();
    }

    // Loads student dashboard data from the database
    private void loadStudentDashboardData() {
        // TODO: Fetch student name, enrolled courses, grades, etc. from database using studentId
        // Example: SELECT name FROM students WHERE id = ?
        // Example: SELECT COUNT(*) FROM enrollments WHERE student_id = ?
        // Example: SELECT grade FROM grades WHERE student_id = ?
        // Set label texts accordingly
        studentDashboardLabel.setText("Welcome, Student " + studentId); // Replace with actual name
        enrolledCoursesLabel.setText("Enrolled Courses: [Count]"); // Replace with actual count
        // completedCoursesLabel.setText("Completed Courses: [Count]");
        // studentGradeLabel.setText("Current Grade: [Grade]");

        String studentName = Client.getStudentName(studentId);
        studentDashboardLabel.setText("Welcome, " + studentName);
        // not implemented yet
        // enrolledCoursesLabel.setText("Enrolled Courses: " + Client.getEnrolledCoursesCount(studentId));
    }

    @FXML
    private void handleViewCoursesButton() {
        // TODO: Fetch and display list of enrolled courses for this student from database
        // Example: SELECT * FROM courses WHERE student_id = ?
        
    }

    @FXML
    private void handleViewGradesButton() {
        // TODO: Fetch and display grades for this student from database
        // Example: SELECT * FROM grades WHERE student_id = ?
    }

    @FXML
    private void handleStudentRefresh() {
        // Reload dashboard data
        loadStudentDashboardData();
    }

    @FXML
    private void handleStudentLogout() {
        // TODO: Implement logout logic (e.g., return to login or welcome page)
        System.out.println("Student " + studentId + " logged out.");
        // Optionally, clear session data or redirect to login page 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome-page.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = (Stage) studentLogoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

}
