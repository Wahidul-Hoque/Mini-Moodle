package com.example.minimoodle.teacherfunctionalities;

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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ApplyingStudentsPageController {

    @FXML
    private Button approveButton;

    @FXML
    private Button goBackButton;

    @FXML
    private TableColumn<StudentInfo, String> idColumn;

    @FXML
    private TableColumn<StudentInfo, String> nameColumn;

    @FXML
    private Button rejectButton;

    @FXML
    private TableView<StudentInfo> requestTable;

    @FXML
    private Label studentEmailLabel;

    @FXML
    private Label studentNameLabel;

    private ObservableList<StudentInfo> requestList = FXCollections.observableArrayList();

    private int courseId = 1; // Assuming a course ID for demonstration

    public void setCurrentCourseId(int courseId) {
        this.courseId = courseId;
    }

    @FXML
    public void initialize() {
        // Initializing the columns to display student ID and name
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Fetching applying students (Placeholder for actual data fetching)
        fetchApplyingStudents();
        requestTable.setItems(requestList);
        approveButton.setDisable(true);
        rejectButton.setDisable(true);

        // Set up event handler for when a row is clicked
        requestTable.setRowFactory(tv -> {
            TableRow<StudentInfo> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    StudentInfo student = row.getItem();
                    studentNameLabel.setText("Name: " + student.getName());
                    studentEmailLabel.setText("Email: " + student.getEmail());
                    approveButton.setDisable(false);
                    rejectButton.setDisable(false);
                }
            });
            return row;
        });
    }

    private void fetchApplyingStudents() {
        // Placeholder for fetching applying students from the database or service
        // This should populate requestList with StudentInfo objects

        // for now, dummy data
        requestList.add(new StudentInfo("1", "John Doe", "johndoe@example.com", null));
        requestList.add(new StudentInfo("2", "Jane Smith", "janesmith@example.com", null));

        // TODO: complete data fecthing logic
        // var students = 
    }

    @FXML
    void handleApproveRequest(ActionEvent event) {
        StudentInfo selectedStudent = requestTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            // Call the service to approve the enrollment
            // EnrollmentService.approveEnrollment(Integer.parseInt(selectedStudent.getId()), courseId); // Assuming courseId is 1 for now
            showAlert("Success", "Enrollment approved for " + selectedStudent.getName());
            requestList.remove(selectedStudent);
            studentNameLabel.setText("Name: ");
            studentEmailLabel.setText("Email: ");
            approveButton.setDisable(true);
            rejectButton.setDisable(true);
        } else {
            showAlert("Error", "Please select a student to approve.");
        }
    }

    @FXML
    void handleGoBack(ActionEvent event) {
        System.out.println("Going back to the teacher dashboard...");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/minimoodle/teacher-dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Teacher Dashboard");
        } catch (java.io.IOException | NullPointerException e) {
            showAlert("Loading Error", "Failed to load the teacher dashboard: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void handleRejectRequest(ActionEvent event) {
        StudentInfo selectedStudent = requestTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            // Call the service to approve the enrollment
            // EnrollmentService.rejectEnrollment(Integer.parseInt(selectedStudent.getId()), courseId); // Assuming courseId is 1 for now
            showAlert("Success", "Enrollment rejected for " + selectedStudent.getName());
            requestList.remove(selectedStudent);
            studentNameLabel.setText("Name: ");
            studentEmailLabel.setText("Email: ");
            approveButton.setDisable(true);
            rejectButton.setDisable(true);
        } else {
            showAlert("Error", "Please select a student to approve.");
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
