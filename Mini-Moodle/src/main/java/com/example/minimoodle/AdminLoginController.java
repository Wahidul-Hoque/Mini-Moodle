package com.example.minimoodle;

import com.example.utils.Client; 

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

public class AdminLoginController {

    @FXML
    private Button adminLoginButton;

    @FXML
    private Button adminLoginBackButton;

    @FXML
    private TextField adminLoginIdBox;

    @FXML
    private PasswordField adminLoginPasswordBox;

    @FXML
    private CheckBox adminLoginPasswordCheckBox;

    @FXML
    private TextField adminLoginPasswordVisibleBox;

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
    private void handleAdminLoginBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome-page.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) adminLoginBackButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Welcome Page");
        } catch (java.io.IOException | NullPointerException e) {
            showAlert("Navigation Error", "Failed to load the welcome page: " + e.getMessage(), Alert.AlertType.ERROR);
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    /**
     * Handles the admin login button click event.
     * @param event
     * @return void
     */
    public void processAdminLogin(ActionEvent event) {
        String enteredId = adminLoginIdBox.getText();
        String enteredPassword = adminLoginPasswordBox.getText();

        System.out.println("Admin ID: " + enteredId);
        System.out.println("Admin Password: " + enteredPassword);

        boolean isValidLogin = Client.sendAdminLoginRequest(enteredId, enteredPassword);

        if (isValidLogin) {
            showAlert("Login Successful", "Welcome, Admin " + enteredId, Alert.AlertType.INFORMATION);
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-dashboard.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) adminLoginButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Dashboard - " + enteredId);

                AdminDashboardController controller = loader.getController();
                controller.setCurrentAdminId((enteredId));

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
        else {
            showAlert("Login Failed", "Invalid username or password. Please try again.", Alert.AlertType.ERROR);
        }

    }

    @FXML
    private void initialize() {
        adminLoginPasswordVisibleBox.setManaged(false);
        adminLoginPasswordVisibleBox.setVisible(false);

        adminLoginPasswordVisibleBox.textProperty().bindBidirectional(adminLoginPasswordBox.textProperty());
    }

    
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
