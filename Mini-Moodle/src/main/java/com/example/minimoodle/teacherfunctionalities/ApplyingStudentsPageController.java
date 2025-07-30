package com.example.minimoodle.teacherfunctionalities;

import com.example.minimoodle.TeacherDashboardController;
import com.example.servicecodes.StudentInfo;
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
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, String> emailColumn;

    @FXML
    private Button rejectButton;

    @FXML
    private TableView<Student> requestTable;

    @FXML
    private Label teacherNameRibbonLabel;

    private int  teacherId;

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    private String courseId; 

    public void setCurrentCourseId(String courseId) {
        this.courseId = courseId;
    }

    private ObservableList<Student> requestList = FXCollections.observableArrayList();

    @FXML
    public void initialize(int teacherId, String courseId) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        setTeacherId(teacherId);
        setCurrentCourseId(courseId);

        String teacherName = Client.getTeacherUsername(teacherId);
        if (teacherNameRibbonLabel != null) {
            teacherNameRibbonLabel.setText(teacherName);
        }

        fetchApplyingStudents();
        requestTable.setItems(requestList);
        approveButton.setDisable(true);
        rejectButton.setDisable(true);

        requestTable.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    approveButton.setDisable(false);
                    rejectButton.setDisable(false);
                }
            });
            return row;
        });
    }

    private void fetchApplyingStudents() {
        var students = Client.getPendingStudents(courseId);
        System.out.println(students);
        for (StudentInfo studentInfo : students) {
            requestList.add(new Student(studentInfo));
        }
    }

    @FXML
    void handleApproveRequest(ActionEvent event) {
        Student selectedStudent = requestTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            Client.approveEnrollment(Integer.parseInt(selectedStudent.getId()), Integer.parseInt(courseId));
            showAlert("Success", "Enrollment approved for " + selectedStudent.getName());
            requestList.remove(selectedStudent);
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

            TeacherDashboardController controller = loader.getController();
            controller.initialize(teacherId); 
        } catch (java.io.IOException | NullPointerException e) {
            showAlert("Loading Error", "Failed to load the teacher dashboard: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void handleRejectRequest(ActionEvent event) {
        Student selectedStudent = requestTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            Client.rejectEnrollment(Integer.parseInt(selectedStudent.getId()), Integer.parseInt(courseId));
            showAlert("Success", "Enrollment rejected for " + selectedStudent.getName());
            requestList.remove(selectedStudent);
            approveButton.setDisable(true);
            rejectButton.setDisable(true);
        } else {
            showAlert("Error", "Please select a student to reject.");
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
