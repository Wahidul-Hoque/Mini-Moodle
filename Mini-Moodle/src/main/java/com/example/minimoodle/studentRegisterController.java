package com.example.minimoodle;

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
    @FXML
    private Button studentRegisterCancelButton;
    private boolean showPassword = false;

    @FXML
    public void initialize() {
        studentRegisterPasswordVisibleBox.setManaged(false);
        studentRegisterPasswordVisibleBox.setVisible(false);
        studentRegisterReenterPasswordVisibleBox.setManaged(false);
        studentRegisterReenterPasswordVisibleBox.setVisible(false);
        studentRegisterPasswordBox.textProperty().bindBidirectional(studentRegisterPasswordVisibleBox.textProperty());
        studentRegisterReenterPasswordBox.textProperty().bindBidirectional(studentRegisterReenterPasswordVisibleBox.textProperty());
    }

    @FXML
    private void handleStudentRegisterButton() {
    }

    @FXML
    private void handleStudentRegisterCancel() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("student-login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) studentRegisterCancelButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to load login page.");
            alert.showAndWait();
        }
    }

    @FXML
    private void toggleStudentRegisterPasswordVisibility() {
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
}
