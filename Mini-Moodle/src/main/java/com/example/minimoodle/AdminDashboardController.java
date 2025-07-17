package com.example.minimoodle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AdminDashboardController {

    @FXML
    private Button adminLogoutButton;

    @FXML
    private Button manageCoursesButton;

    @FXML
    private Button manageStudentsButton;

    @FXML
    private Button manageTeachersButton;

    @FXML
    private Label totalCoursesLabel;

    @FXML
    private Label totalStudentsLabel;

    @FXML
    private Label totalTeachersLabel;

    @FXML
    /**
     * 
     * handle logout
     */
    void handleAdminLogout(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome-page.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) adminLogoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Welcome back");

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
    void handleManageCoursesButton(ActionEvent event) {
        // TODO: redirect to course management page
        // We add a new course and set teachers there
        
    }

    @FXML
    void handleManageStudentsButton(ActionEvent event) {
        
    }

    @FXML
    void handleManageTeachersButton(ActionEvent event) {

    }

    @FXML
    void initialize() {
        // TODO: implement load admin data from the database
        
        // loadAdminData();
    }

}
