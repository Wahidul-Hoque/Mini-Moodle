package com.example.minimoodle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import com.example.servicecodes.TeacherLoginService;

public class TeacherLoginController {
    @FXML
    private TextField teacherLoginIdBox;

    @FXML
    private PasswordField teacherLoginPasswordBox;

    @FXML
    private TextField teacherLoginPasswordVisibleBox;

    @FXML
    private CheckBox teacherLoginPasswordCheckBox;

    private boolean showPassword = false;

    private String enteredId;
    private String enteredPassword;

    @FXML
    //
    // initialize() method is called by the JavaFX framework to initialize the controller
    // This method sets up the initial state of the password visibility box.
    // It binds the text property of the visible password box to the password field.
    // 
    private void initialize() {
        teacherLoginPasswordVisibleBox.setManaged(false);
        teacherLoginPasswordVisibleBox.setVisible(false);

        teacherLoginPasswordVisibleBox.textProperty().bindBidirectional(teacherLoginPasswordBox.textProperty());
    }

    @FXML
    //
    // When Show Password Button is clicked, toggle the visibility of the password field
    // What does this do?
    // setVisible() method controls whether the password field is visible or not.
    // setManaged() method controls whether the password field is managed by the layout.
    //
    private void toggleTeacherLoginPasswordVisibility() {
        showPassword = !showPassword;
        if (showPassword) {
            teacherLoginPasswordVisibleBox.setText(teacherLoginPasswordBox.getText());
            teacherLoginPasswordVisibleBox.setVisible(true);
            teacherLoginPasswordVisibleBox.setManaged(true);
            teacherLoginPasswordBox.setVisible(false);
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
    // When the login button is clicked, we retrieve the entered ID and password
    // and print them
    public void processTeacherLogin() {
        enteredId = teacherLoginIdBox.getText();
        enteredPassword = showPassword ? teacherLoginPasswordVisibleBox.getText() : teacherLoginPasswordBox.getText();

        // For demonstration purposes, we print the ID and password to the console
        System.out.println("Entered ID: " + enteredId);
        System.out.println("Entered Password: " + enteredPassword);

        
        // TODO: Implement the logic to handle teacher login
        // We need to pull data from the database to verify the admin credentials
        boolean isValidLogin = TeacherLoginService.validateTeacherLogin(enteredId, enteredPassword);

        if (isValidLogin) {
            // Proceed to the next screen or show a success message
            showAlert("Login Successful", "Welcome, Teacher " + enteredId, Alert.AlertType.INFORMATION);
        }
        else {
            // Show an error message if the credentials are invalid
            showAlert("Login Failed", "Invalid username or password. Please try again.", Alert.AlertType.ERROR);
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
