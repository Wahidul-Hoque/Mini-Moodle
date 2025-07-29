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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class StudentViewCourses {

    @FXML
    private TableView<CourseRow> courseTable;
    @FXML
    private TableColumn<CourseRow, String> idColumn;
    @FXML
    private TableColumn<CourseRow, String> nameColumn;
    @FXML
    private TableColumn<CourseRow, Void> actionColumn;
    @FXML
    private Button goBackButton;

    @FXML
    private Label studentNameRibbonLabel;

    // Dummy data class for table rows
    public static class CourseRow {

        private String id;
        private String name;

        public CourseRow(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    private int studentId;
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }  
    public int getStudentId() {
        return studentId;
    }

    private ObservableList<CourseRow> courseData = FXCollections.observableArrayList();

    @FXML
    public void initialize(int studentId) {
        idColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getId()));
        nameColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getName()));

        this.studentId = studentId;

        // Set student name in the ribbon
        String studentName = Client.getStudentName(studentId);
        if (studentNameRibbonLabel != null) {
            studentNameRibbonLabel.setText(studentName);
        }

        List<CourseInfo> courses = Client.getUnregisteredCoursesForStudent(studentId);
        for (CourseInfo course : courses) {
            courseData.add(new CourseRow(String.valueOf(course.getCourseTitle()), course.getCourseDescription()));
        }
        
        courseTable.setItems(courseData);

        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button enrollButton = new Button("Request Enrollment");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                // Apply the dashboard button styling
                enrollButton.getStyleClass().clear();
                enrollButton.getStyleClass().add("dashboard-button");
                enrollButton.getStylesheets().add(getClass().getResource("/com/example/minimoodle/styles.css").toExternalForm());
                enrollButton.setOnAction(event -> {
                    CourseRow course = getTableView().getItems().get(getIndex());
                    Client.requestEnrollment(studentId, course.getId());
                    showAlert("Enrollment Requested", "Requested enrollment for course: " + course.getName());
                });
                setGraphic(enrollButton);
            }
        });
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
