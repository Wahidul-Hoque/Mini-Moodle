package com.example.minimoodle;

import com.example.utils.Client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    
    private boolean showPassword = false;

    private String enteredId;
    private String enteredPassword;

    @FXML
    private void initialize() {

        studentLoginPasswordVisibleBox.textProperty().bindBidirectional(studentLoginPasswordBox.textProperty());
        studentLoginPasswordVisibleBox.setVisible(false);
        studentLoginPasswordVisibleBox.setManaged(false);
    }

    @FXML
    private void toggleStudentLoginPasswordVisibility() {
        showPassword = !showPassword;
        studentLoginPasswordBox.setVisible(!showPassword);
        studentLoginPasswordBox.setManaged(!showPassword);
        studentLoginPasswordVisibleBox.setVisible(showPassword);
        studentLoginPasswordVisibleBox.setManaged(showPassword);
    }

    @FXML
    public void handleStudentLogin() {
        // to Wahid: Get enteredId and enteredPassword from fields
        // Query database to validate credentials
        // If valid, load dashboard and pass student ID
        // If invalid, show error alert
        // Add error handling for database failures

        enteredId = studentLoginIdBox.getText();
        enteredPassword = showPassword ? studentLoginPasswordVisibleBox.getText() : studentLoginPasswordBox.getText();

        int studentId = Client.sendStudentLoginRequest(enteredId, enteredPassword);
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

            } catch (java.io.IOException | NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Loading Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to load the login page: " + e.getMessage());
                alert.showAndWait();

                System.out.println(e.getMessage());
                e.printStackTrace();
            }

        }

    }
    
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

    // Helper method to display an alert message
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
