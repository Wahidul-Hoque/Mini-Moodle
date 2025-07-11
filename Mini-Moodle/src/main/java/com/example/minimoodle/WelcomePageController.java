package com.example.minimoodle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class WelcomePageController {

    //
    // Inject all the UI elements in the welcome-page.fxml file
    // here in the controller
    //
    @FXML
    private ToggleGroup roleGroup;

    @FXML
    private RadioButton welcomePageAdminRadioButton;

    @FXML
    private RadioButton welcomePageUserRadioButton;

    @FXML
    private Button welcomePageContinueButton;

    //
    // handle the continue button and update the current scene accordingly
    //
    @FXML
    private void handleWelcomePageContinueButton() {
        // returns which button we selected, I think as a reference? 
        RadioButton selectedRole = (RadioButton) roleGroup.getSelectedToggle();

        // If no role is selected, we show an error
        if (selectedRole == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a role");
            alert.showAndWait();
            return;
        }

        System.out.println(selectedRole.getText() + " selected");

        String role = selectedRole.getText();
        String fxmlFile;
        if (role.equals("Admin")) {
            fxmlFile = "admin-login.fxml"; 
        }else {
            fxmlFile = "teacher-login.fxml";
        }

        //
        // we then try to load the selected login page
        //
        try {

            // loads the corresponding fxml file
            // using FXMLLoader class
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Stage stage = (Stage) welcomePageContinueButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(role + " login");
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
