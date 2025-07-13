package com.example.minimoodle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TeacherDashboardController {

    @FXML
    private Button approveRequestButton;

    @FXML
    private Label courseCodeLabel;

    @FXML
    private Label courseNameLabel;

    @FXML
    private TableView<?> enrollmentTable;

    @FXML
    private TextField gradeInput;

    @FXML
    private TableView<?> gradeTable;

    @FXML
    private TableView<?> manageStudentTable;

    @FXML
    private Button rejectRequestButton;

    @FXML
    private Button removeStudentButton;

    @FXML
    private Label studentCountLabel;

    @FXML
    private TableView<?> studentTable;

    @FXML
    private Button submitGradeButton;

    @FXML
    void approveEnrollment(ActionEvent event) {
        // Logic to approve enrollment request
        System.out.println("Enrollment approved.");

        // TODO: Update the database
        // and refresh the enrollment table
    }

    @FXML
    void rejectEnrollment(ActionEvent event) {
        // Logic to approve enrollment request
        System.out.println("Enrollment rejected.");

        // TODO: Update the database
        // and refresh the enrollment table
    }

    @FXML
    void removeStudent(ActionEvent event) {
        // Logic to remove a student from the course
        System.out.println("Student removed from the course.");

        // TODO: Update the database
        // and refresh the student table
    }

    @FXML
    void submitGrade(ActionEvent event) {
        // Logic to submit the grade for a student
        String grade = gradeInput.getText();
        System.out.println("Grade submitted: " + grade);

        // TODO: Update the database
        // and refresh the grade table
    }

}
