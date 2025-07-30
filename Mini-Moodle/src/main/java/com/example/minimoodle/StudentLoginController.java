package com.example.minimoodle;

import com.example.utils.Client;

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

public class StudentLoginController {

    @FXML
    private Button studentLoginButton;
    @FXML
    private TextField studentLoginIdBox;
    @FXML
    private Button studentLoginBackButton;
    @FXML
    private PasswordField studentLoginPasswordBox;
    @FXML
    private TextField studentLoginPasswordVisibleBox;
    @FXML
    private CheckBox studentLoginPasswordCheckBox;
    @FXML
    private Button studentRegisterButton;

    private boolean showPassword = false;
    private String enteredId;
    private String enteredPassword;

    private int studentId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @FXML
    private void initialize() {
        // Bind password fields for visibility toggle
        studentLoginPasswordVisibleBox.textProperty().bindBidirectional(studentLoginPasswordBox.textProperty());
        studentLoginPasswordVisibleBox.setVisible(false);
        studentLoginPasswordVisibleBox.setManaged(false);
    }

    /**
     * Handles toggling password visibility when the checkbox is clicked.
     */
    @FXML
    private void toggleStudentLoginPasswordVisibility() {
        showPassword = !showPassword;
        studentLoginPasswordBox.setVisible(!showPassword);
        studentLoginPasswordBox.setManaged(!showPassword);
        studentLoginPasswordVisibleBox.setVisible(showPassword);
        studentLoginPasswordVisibleBox.setManaged(showPassword);
    }

    /**
     * Handles the login button click event.
     */
    @FXML
    public void handleStudentLogin() {
        enteredId = studentLoginIdBox.getText();
        enteredPassword = showPassword ? studentLoginPasswordVisibleBox.getText() : studentLoginPasswordBox.getText();

        studentId = Client.sendStudentLoginRequest(enteredId, enteredPassword);
        if (studentId > 0) {
            showAlert("Login Successful", "Welcome Student " + enteredId, Alert.AlertType.INFORMATION);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("student-dashboard.fxml"));
                Parent root = loader.load();
                StudentDashboardController controller = loader.getController();
                controller.initialize(studentId);  // Pass the studentId to the dashboard controller
                Stage stage = (Stage) studentLoginButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Dashboard - " + enteredId);

                StudentDashboardController studentDashboardController = loader.getController();
                studentDashboardController.setStudentId(studentId);
            } catch (java.io.IOException | NullPointerException e) {
                showAlert("Loading Error", "Failed to load the login page: " + e.getMessage(), Alert.AlertType.ERROR);
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Handles the back button click event to return to the welcome page.
     */
    @FXML
    private void handleStudentLoginBack(javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome-page.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) studentLoginBackButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Welcome Page");
        } catch (java.io.IOException | NullPointerException e) {
            showAlert("Navigation Error", "Failed to load the welcome page: " + e.getMessage(), Alert.AlertType.ERROR);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handles the register button click event to go to the registration page.
     * TODO: Implement navigation to student registration page.
     */
    @FXML
    private void handleStudentRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("student-register.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) studentRegisterButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Student Registration");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            showAlert("Error", "Failed to load registration page: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Helper method to display an alert message
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
