package com.example.minimoodle.studentfunctionalities;

import java.util.List;

import com.example.minimoodle.StudentDashboardController;
import com.example.servicecodes.CourseInfo;
import com.example.utils.Client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class StudentViewGrades {

    @FXML
    private TableView<GradeRow> gradesTable;
    @FXML
    private TableColumn<GradeRow, String> courseIdColumn;
    @FXML
    private TableColumn<GradeRow, String> courseNameColumn;
    @FXML
    private TableColumn<GradeRow, String> teacherColumn;
    @FXML
    private TableColumn<GradeRow, String> gradeColumn;
    @FXML
    private Button goBackButton;

    @FXML
    private Label studentNameRibbonLabel;

    private int studentId;

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public static class GradeRow {

        private final String courseId;
        private final String courseName;
        private final String grade;

        public GradeRow(String courseId, String courseName, String grade) {
            this.courseId = courseId;
            this.courseName = courseName;
            this.grade = grade;
        }

        public String getCourseId() {
            return courseId;
        }

        public String getCourseName() {
            return courseName;
        }

        public String getGrade() {
            return "NOT_SET".equalsIgnoreCase(grade) ? "" : grade;
        }
    }

    private final ObservableList<GradeRow> gradeData = FXCollections.observableArrayList();

    @FXML
    public void initialize(int studentId) {
        courseIdColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCourseId()));
        courseNameColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCourseName()));
        gradeColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getGrade()));

        this.studentId = studentId;

        // Set student name in the ribbon
        String studentName = Client.getStudentUsername(studentId);
        if (studentNameRibbonLabel != null) {
            studentNameRibbonLabel.setText(studentName);
        }

        loadGrades();

        gradesTable.setItems(gradeData);
    }

    private void loadGrades() {
        List<CourseInfo> courses = Client.getEnrolledCoursesForStudent(studentId);
        for (CourseInfo course : courses) {
            gradeData.add(new GradeRow(course.getCourseTitle(), course.getCourseDescription(), course.getGrade()));
        }
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/minimoodle/student-dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Student Dashboard");

            StudentDashboardController studentDashboardController = loader.getController();
            studentDashboardController.setStudentId(studentId);
            studentDashboardController.initialize(studentId);
        } catch (Exception e) {
            showAlert("Loading Error", "Failed to load the student dashboard: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
