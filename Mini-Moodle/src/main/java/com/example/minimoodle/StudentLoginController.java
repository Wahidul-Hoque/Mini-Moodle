package com.example.minimoodle;

import com.example.utils.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class StudentLoginController {

    @FXML
    private TextField studentLoginIdBox;

    @FXML
    private PasswordField studentLoginPasswordBox;

    @FXML
    private TextField studentLoginPasswordVisibleBox;

    @FXML
    private CheckBox studentLoginPasswordCheckBox;

    @FXML
    private Button studentLoginButton;

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
        if(studentId >0) {
            //valid student.Kanon: now do the same work as teacher login fxml
        }

    }

}
