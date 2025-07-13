package com.example.minimoodle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminLoginController {

    @FXML
    private Button adminLoginButton;

    @FXML
    private TextField adminLoginIdBox;

    @FXML
    private PasswordField adminLoginPasswordBox;

    @FXML
    private CheckBox adminLoginPasswordCheckBox;

    @FXML
    private TextField adminLoginPasswordVisibleBox;

    @FXML
    void processAdminLogin(ActionEvent event) {

    }

    private boolean showPassword = false;

    @FXML
    void toggleAdminLoginPasswordVisibility(ActionEvent event) {
        showPassword = !showPassword;
        if (showPassword) {
            adminLoginPasswordVisibleBox.setText(adminLoginPasswordBox.getText());
            adminLoginPasswordVisibleBox.setVisible(true);
            adminLoginPasswordVisibleBox.setManaged(true);
            adminLoginPasswordBox.setVisible(false);
            adminLoginPasswordBox.setManaged(false);
        } else {
            adminLoginPasswordBox.setText(adminLoginPasswordVisibleBox.getText());
            adminLoginPasswordBox.setVisible(true);
            adminLoginPasswordBox.setManaged(true);
            adminLoginPasswordVisibleBox.setVisible(false);
            adminLoginPasswordVisibleBox.setManaged(false);
        }
    }

    @FXML
    public void processAdminLogin() {
        String enteredId = adminLoginIdBox.getText();
        String enteredPassword = adminLoginPasswordBox.getText();

        System.out.println("Admin ID: " + enteredId);
        System.out.println("Admin Password: " + enteredPassword);

        // TODO: Implement the logic to handle admin login
        // We need to pull data from the database to verify the admin credentials


    }

    @FXML
    private void initialize() {
        adminLoginPasswordVisibleBox.setManaged(false);
        adminLoginPasswordVisibleBox.setVisible(false);

        adminLoginPasswordVisibleBox.textProperty().bindBidirectional(adminLoginPasswordBox.textProperty());
    }

}
