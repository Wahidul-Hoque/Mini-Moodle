package com.example.minimoodle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

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
    private void handleWelcomePageContinueButton()
    {
        // returns which button we selected, I think as a reference?
        RadioButton selectedRole = (RadioButton)roleGroup.getSelectedToggle();

        // If no role is selected, we show an error
        if(selectedRole == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a role");
            alert.showAndWait();
            return;
        }

        String role = selectedRole.getText();
        String fxmlFile;
        if(role.equals("Admin")) fxmlFile = new String("admin-login-page.fxml");
        else fxmlFile = new String("teacher-login-page.fxml");

        //
        // we then try to load the selected login page
        //
        try {

            // loads the corresponding fxml file
            // using FXMLLoader class
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/minimoodle/" + fxmlFile));
//            Parent root = loader.load();
//
//            Stage stage = (Stage) welcomePageContinueButton.getScene().getWindow();
//            stage.setScene(new Scene(root));
//            stage.setTitle(role + " login");
        } catch(Exception e)
        {

        }
    }



}
