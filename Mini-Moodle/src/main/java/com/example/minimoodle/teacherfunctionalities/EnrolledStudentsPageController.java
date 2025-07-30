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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EnrolledStudentsPageController {

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, String> emailColumn;

    @FXML
    private Button goBackButton;

    @FXML
    private Label teacherNameRibbonLabel;

    private int teacherId;

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    private String currentCourseId; 

    public void setCurrentCourseId(String currentCourseId) {
        this.currentCourseId = currentCourseId;
    }


    private ObservableList<Student> studentList = FXCollections.observableArrayList();

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

        fetchEnrolledStudents();
        studentTable.setItems(studentList);
    }

    private void fetchEnrolledStudents() {

        if (currentCourseId != null) {
            var students = Client.getEnrolledStudents(currentCourseId);
            for (StudentInfo studentInfo : students) {
                studentList.add(new Student(studentInfo));
            }
        }
    }

    @FXML
    public void handleGoBack(ActionEvent event) {
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
