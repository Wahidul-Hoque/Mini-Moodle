package com.example.minimoodle.studentfunctionalities;

import java.io.IOException;
import java.util.List;

import com.example.servicecodes.CourseInfo;
import com.example.utils.Client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class StudentViewPendingCourses {

    @FXML
    private Button goBackButton;
    @FXML
    private Label studentNameRibbonLabel;
    @FXML
    private TableView<CourseInfo> courseTable;
    @FXML
    private TableColumn<CourseInfo, Integer> idColumn;
    @FXML
    private TableColumn<CourseInfo, String> titleColumn;
    private int studentId;

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void initialize(int studentId) {
        this.studentId = studentId;
        String studentName = Client.getStudentUsername(studentId);
        if (studentNameRibbonLabel != null) {
            studentNameRibbonLabel.setText(studentName);
        }
        setupTable();
        loadAppliedCourses();
    }

    private void setupTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("courseDescription"));
    }

    private void loadAppliedCourses() {
        try {
            List<CourseInfo> appliedCourses = Client.getPendingCoursesForStudent(studentId);
            courseTable.getItems().setAll(appliedCourses);
        } catch (Exception e) {
            System.out.println("Error loading applied courses: " + e.getMessage());
        }
    }

    @FXML
    private void handleGoBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/minimoodle/student-dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Student Dashboard - " + Client.getStudentUsername(studentId));
            com.example.minimoodle.StudentDashboardController controller = loader.getController();
            controller.setStudentId(studentId);
            controller.initialize(studentId);
        } catch (IOException e) {
            System.out.println("Error navigating back to dashboard: " + e.getMessage());
        }
    }
}
