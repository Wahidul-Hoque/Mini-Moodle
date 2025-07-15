package com.example.minimoodle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import com.example.servicecodes.CourseService;
import com.example.servicecodes.TeacherLoginService;

public class TeacherDashboardController {

    @FXML
    private Label courseIdLabel;

    @FXML
    private Label courseNameLabel;

    @FXML
    private Label enrolledStudentsLabel;

    @FXML
    private Button teacherDashboardApplicantsButton;

    @FXML
    private Button teacherDashboardEnrolledStudentsButton;

    @FXML
    private Button teacherDashboardGradingButton;

    @FXML
    private Button teacherDashboardLogoutButton;

    @FXML
    private Button teacherDashboardRefreshButton;

    private int teacherId;
    private String courseId;

    // This method will be called after the teacher logs in
    public void initialize(int teacherId) {
        this.teacherId = teacherId;
        loadCourseData();
    }

    private void loadCourseData(){
        courseId = CourseService.getCourseIdForTeacher(teacherId);
        String courseName = CourseService.getCourseName(courseId);
        int enrolledStudentsCount= CourseService.getEnrolledStudents(courseId).size();

        courseIdLabel.setText(courseId);
        courseNameLabel.setText(courseName);
        enrolledStudentsLabel.setText("Enrolled Students: " + enrolledStudentsCount);
    }
    @FXML
    void handleTeacherDashboardRefresh(ActionEvent event) {
        // TODO: refresh the teacher dashboard
        loadCourseData();
    }

    @FXML
    void handleTeacherGrading(ActionEvent event) {
        // TODO: take me to the grading page
    }

    @FXML
    void handleTeacherLogout(ActionEvent event) {
        // TODO: LOG OUT and take me to WelcomePage

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome-page.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) teacherDashboardLogoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Welcome back");
            
        } catch (java.io.IOException | NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Loading Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load the login page: " + e.getMessage());
            alert.showAndWait();

            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void handleTeacherSeeApplyingStudents(ActionEvent event) {
        // TODO: take to applicants page
    }

    @FXML
    void handleTeacherViewEnrolledStudentsTable(ActionEvent event) {
        // TODO: enrolled students page

    }

}
