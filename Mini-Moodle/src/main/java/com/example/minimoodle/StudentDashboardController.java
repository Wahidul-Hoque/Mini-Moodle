package com.example.minimoodle;

import java.io.IOException;

import com.example.minimoodle.studentfunctionalities.StudentViewCourses;
import com.example.minimoodle.studentfunctionalities.StudentViewGrades;
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

    @FXML
    private Button viewCoursesButton;

    @FXML
    private Button viewGradesButton;

    @FXML
    private Button studentRefreshButton;

    @FXML
    private Button studentLogoutButton;

    private int studentId;
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public int getStudentId() {
        return studentId;
    }

    public void initialize(int studentId) {
        this.studentId = studentId;
        loadStudentDashboardData();
    }

    private void loadStudentDashboardData() {
        

        String studentName = Client.getStudentName(studentId);
        studentDashboardLabel.setText("Welcome, " + studentName);
        
        enrolledCoursesLabel.setText("Enrolled Courses: " + Client.getEnrolledCoursesForStudent(studentId).size());
    }

    @FXML
    private void handleViewCoursesButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("student-dashboard-view-courses.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = (Stage) viewCoursesButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("View Enrolled Courses - Student " + Client.getStudentName(studentId));

            StudentViewCourses controller = loader.getController();
            controller.setStudentId(studentId);
            controller.initialize(studentId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void handleViewGradesButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("student-dashboard-view-grades.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = (Stage) viewGradesButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("View Grades - Student " + Client.getStudentName(studentId));

            StudentViewGrades controller = loader.getController();
            controller.initialize(studentId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleStudentRefresh() {
        loadStudentDashboardData();
    }

    @FXML
    private void handleStudentLogout() {
        System.out.println("Student " + studentId + " logged out.");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome-page.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = (Stage) studentLogoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Welcome - Mini Moodle");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

}
