package com.example.minimoodle;

import java.awt.event.ActionEvent;

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
    private RadioButton welcomePageTeacherRadioButton;

    @FXML
    private RadioButton welcomePageStudentRadioButton;

    @FXML
    private Button welcomePageContinueButton;

    @FXML
    private Button connectToServer;

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
        } else if (role.equals("Teacher")) {
            fxmlFile = "teacher-login.fxml";
        } else if (role.equals("Student")) {
            fxmlFile = "student-login.fxml";
        } else {
            fxmlFile = "welcome-page.fxml";
        }

        try {
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

    @FXML
    private void handleConnectToServer(ActionEvent event) {
        // Create a custom dialog for IP input
        javafx.scene.control.Dialog<String> dialog = new javafx.scene.control.Dialog<>();
        dialog.setTitle("Connect to Server");
        dialog.setHeaderText("Enter the server IP address:");

        // Set the button types
        javafx.scene.control.ButtonType connectButtonType = new javafx.scene.control.ButtonType("Connect", javafx.scene.control.ButtonBar.ButtonData.OK_DONE);
        javafx.scene.control.ButtonType cancelButtonType = new javafx.scene.control.ButtonType("Cancel", javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(connectButtonType, cancelButtonType);

        // Create the IP address input field
        javafx.scene.control.TextField ipField = new javafx.scene.control.TextField();
        ipField.setPromptText("127.0.0.1");

        // Layout for the dialog
        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new javafx.scene.control.Label("IP Address:"), 0, 0);
        grid.add(ipField, 1, 0);

        dialog.getDialogPane().setContent(grid);

        // Request focus on the IP field by default
        javafx.application.Platform.runLater(ipField::requestFocus);

        // Convert the result to the IP address when the Connect button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == connectButtonType) {
                return ipField.getText();
            }
            return null;
        });

        java.util.Optional<String> result = dialog.showAndWait();

        result.ifPresent(ip -> {
            // Here you can handle the IP address (e.g., attempt to connect)
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Connection Attempt");
            alert.setHeaderText(null);
            alert.setContentText("Attempting to connect to server at: " + ip);
            alert.showAndWait();
        });
    }
}
