package com.example.minimoodle;

import com.example.utils.Client; // Importing the service class for teacher login validation

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

public class TeacherLoginController {

    @FXML
    private TextField teacherLoginIdBox;
    @FXML
    private PasswordField teacherLoginPasswordBox;
    @FXML
    private TextField teacherLoginPasswordVisibleBox;
    @FXML
    private CheckBox teacherLoginPasswordCheckBox;
    @FXML
    private Button teacherLoginButton;
    @FXML
    private Button teacherLoginBackButton;
    private boolean showPassword = false;
    private String enteredId;
    private String enteredPassword;

    @FXML
    private void initialize() {
        teacherLoginPasswordVisibleBox.setManaged(false);
        teacherLoginPasswordVisibleBox.setVisible(false);
        teacherLoginPasswordVisibleBox.textProperty().bindBidirectional(teacherLoginPasswordBox.textProperty());
    }

    @FXML
    private void toggleTeacherLoginPasswordVisibility() {
        showPassword = !showPassword;
        if (showPassword) {
            teacherLoginPasswordVisibleBox.setText(teacherLoginPasswordBox.getText());
            teacherLoginPasswordVisibleBox.setVisible(true);
            teacherLoginPasswordVisibleBox.setManaged(true);
            teacherLoginPasswordBox.setVisible(false);
            teacherLoginBackButton.setOnAction(event -> handleTeacherLoginBack());
            teacherLoginPasswordBox.setManaged(false);
        } else {
            teacherLoginPasswordBox.setText(teacherLoginPasswordVisibleBox.getText());
            teacherLoginPasswordBox.setVisible(true);
            teacherLoginPasswordBox.setManaged(true);
            teacherLoginPasswordVisibleBox.setVisible(false);
            teacherLoginPasswordVisibleBox.setManaged(false);
        }
    }

    @FXML
    private void handleTeacherLoginBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome-page.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) teacherLoginBackButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Welcome Page");
        } catch (java.io.IOException | NullPointerException e) {
            showAlert("Navigation Error", "Failed to load the welcome page: " + e.getMessage(), Alert.AlertType.ERROR);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void processTeacherLogin() {
        enteredId = teacherLoginIdBox.getText();
        enteredPassword = showPassword ? teacherLoginPasswordVisibleBox.getText() : teacherLoginPasswordBox.getText();
        System.out.println("Entered ID: " + enteredId);
        System.out.println("Entered Password: " + enteredPassword);
        int teacherId = Client.sendLoginRequest(enteredId, enteredPassword);
        if (teacherId > 0) {
            showAlert("Login Successful", "Welcome, Teacher " + enteredId, Alert.AlertType.INFORMATION);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("teacher-dashboard.fxml"));
                Parent root = loader.load();
                TeacherDashboardController controller = loader.getController();
                controller.initialize(teacherId);  // Pass the teacherId to the dashboard controller
                Stage stage = (Stage) teacherLoginButton.getScene().getWindow();
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
        } else {
            showAlert("Login Failed", "Invalid username or password. Please try again.", Alert.AlertType.ERROR);
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
