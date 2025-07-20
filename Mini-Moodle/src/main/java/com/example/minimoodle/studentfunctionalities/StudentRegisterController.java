package com.example.minimoodle.studentfunctionalities;

import static com.example.servicecodes.StudentRegisterService.registerStudent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the student registration page. Handles user input, validation,
 * and registration logic.
 */
public class StudentRegisterController {

    @FXML
    private TextField studentRegisterNameBox;

    @FXML
    private TextField studentRegisterEmailBox;

    @FXML
    private TextField studentRegisterUsernameBox;

    @FXML
    private PasswordField studentRegisterPasswordBox;

    @FXML
    private TextField studentRegisterPasswordVisibleBox;

    @FXML
    private PasswordField studentRegisterReenterPasswordBox;

    @FXML
    private TextField studentRegisterReenterPasswordVisibleBox;

    @FXML
    private CheckBox studentRegisterPasswordCheckBox;

    @FXML
    private Button studentRegisterButton;

    private boolean showPassword = false;

    @FXML
    public void initialize() {
        // Initialize any necessary components or listeners
        studentRegisterPasswordVisibleBox.setManaged(false);
        studentRegisterPasswordVisibleBox.setVisible(false);
        studentRegisterReenterPasswordVisibleBox.setManaged(false);
        studentRegisterReenterPasswordVisibleBox.setVisible(false);

        studentRegisterPasswordBox.textProperty().bindBidirectional(studentRegisterPasswordVisibleBox.textProperty());
        studentRegisterReenterPasswordBox.textProperty().bindBidirectional(studentRegisterReenterPasswordVisibleBox.textProperty());

    }

    @FXML
    private void handleStudentRegisterButton() {
        // TODO: Implement field validation
        // TODO: Implement registration logic (call service/database)
        // TODO: Show feedback to user (success/error)
    }

    private boolean areFieldsFilled() {
        return !studentRegisterNameBox.getText().trim().isEmpty()
                && !studentRegisterEmailBox.getText().trim().isEmpty()
                && !studentRegisterUsernameBox.getText().trim().isEmpty()
                && !getPassword().isEmpty()
                && !getReenteredPassword().isEmpty();
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    private boolean doPasswordsMatch() {
        return getPassword().equals(getReenteredPassword());
    }

    private String getPassword() {
        if (studentRegisterPasswordBox.isVisible()) {
            return studentRegisterPasswordBox.getText();
        } else {
            return studentRegisterPasswordVisibleBox.getText();
        }
    }

    private String getReenteredPassword() {
        if (studentRegisterReenterPasswordBox.isVisible()) {
            return studentRegisterReenterPasswordBox.getText();
        } else {
            return studentRegisterReenterPasswordVisibleBox.getText();
        }
    }

    private boolean saveStudentToDatabase(String name, String email, String username, String password) {
        if (registerStudent(name, email, username, password)) {
            showAlert("Registration Successful", "You have been registered successfully!", Alert.AlertType.INFORMATION);
            return true; // Registration successful
        } else {
            showAlert("Registration Failed", "An error occurred while registering. Please try again.", Alert.AlertType.ERROR);
            return false; // Registration failed
        }
    }

    @FXML
    private void toggleStudentRegisterPasswordVisibility(ActionEvent Event) {
        showPassword = !showPassword;

        studentRegisterPasswordBox.setVisible(!showPassword);
        studentRegisterPasswordBox.setManaged(!showPassword);
        studentRegisterReenterPasswordBox.setVisible(!showPassword);
        studentRegisterReenterPasswordBox.setManaged(!showPassword);

        studentRegisterPasswordVisibleBox.setVisible(showPassword);
        studentRegisterPasswordVisibleBox.setManaged(showPassword);
        studentRegisterReenterPasswordVisibleBox.setVisible(showPassword);
        studentRegisterReenterPasswordVisibleBox.setManaged(showPassword);
    }

    @FXML
    private void handleStudentRegisterCancel() {
        try {
            // Load the login page (use absolute resource path)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/minimoodle/student-login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) studentRegisterButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Student Login");
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to load login page.");
            alert.showAndWait();
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



}
