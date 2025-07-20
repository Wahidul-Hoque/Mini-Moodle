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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EnrolledStudentsPageController {

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, String> idColumn;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private Label studentNameLabel;

    @FXML
    private Label studentEmailLabel;

    @FXML
    private Button teacherBlockButton;

    @FXML
    private Button goBackButton;

    private int teacherId;

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }


    private ObservableList<Student> studentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initializing the columns to display student ID and name
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Fetch enrolled students (Placeholder for actual data fetching)
        fetchEnrolledStudents();
        studentTable.setItems(studentList);
        teacherBlockButton.setDisable(true);

        // Set up event handler for when a row is clicked
        studentTable.setRowFactory(tv -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked((MouseEvent event) -> {
                if (!row.isEmpty()) {
                    Student student = row.getItem();
                    displayStudentDetails(student);
                }
            });
            return row;
        });
    }

    private String currentCourseId;

    public void setCurrentCourseId(String courseId) {
        this.currentCourseId = courseId;
    }

    private void fetchEnrolledStudents() {
        // Temporary dummy data for demonstration
        // studentList.add(new Student("S001", "Alice", "alice@gmail.com", "A"));
        // studentList.add(new Student("S002", "Bob", "bob@example.com", "B+"));
        // studentList.add(new Student("S003", "Charlie", "charlie@example.com", "C"));

        if (currentCourseId != null) {
            var students = Client.getEnrolledStudents(currentCourseId);
            for (StudentInfo studentInfo : students) {
                studentList.add(new Student(studentInfo));
            }
        }
        // return students;
    }

    private void displayStudentDetails(Student student) {
        // Display student name and email
        studentNameLabel.setText("Name: " + student.getName());
        studentEmailLabel.setText("Email: " + student.getEmail());
        teacherBlockButton.setDisable(false); // Enable the "Block Student" button
    }

    @FXML
    public void handleGoBack(ActionEvent event) {
        // Handle going back to the previous page (e.g., Teacher Dashboard)
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
    public void handleTeacherBlockStudent(ActionEvent event) {
        // Handle blocking the selected student
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            teacherBlockStudent(selectedStudent);
        }
    }

    private void teacherBlockStudent(Student student) {
        // Placeholder logic for blocking a student
        // Remove student from the list and database if necessary
        showAlert("Blocked Student", "Student " + student.getName() + " has been blocked.");
        studentList.remove(student);
        studentNameLabel.setText("Name: ");
        studentEmailLabel.setText("Email: ");
        teacherBlockButton.setDisable(true);

        // TODO: Update the database to reflect the blocking action
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
