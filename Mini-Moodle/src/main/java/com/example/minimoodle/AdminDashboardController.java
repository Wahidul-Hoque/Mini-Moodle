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

    @FXML private Button adminLogoutButton;
    @FXML private Button manageCoursesButton;
    @FXML private Button manageStudentsButton;
    @FXML private Button manageTeachersButton;
    @FXML private Button adminRefreshButton;
    @FXML private Label totalCoursesLabel;
    @FXML private Label totalStudentsLabel;
    @FXML private Label totalTeachersLabel;

    /**
     * Called when the admin clicks the logout button.
     * Navigates back to the welcome page.
     */
    @FXML
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

    /**
     * Called when the admin clicks the manage courses button.
     * TODO: Implement navigation to course management page.
     */
    @FXML
    void handleManageCoursesButton(ActionEvent event) {
        // TODO: redirect to course management page
    }

    /**
     * Called when the admin clicks the manage students button.
     * TODO: Implement navigation to student management page.
     */
    @FXML
    void handleManageStudentsButton(ActionEvent event) {
        // TODO: redirect to student management page
    }

    /**
     * Called when the admin clicks the manage teachers button.
     * TODO: Implement navigation to teacher management page.
     */
    @FXML
    void handleManageTeachersButton(ActionEvent event) {
        // TODO: redirect to teacher management page
    }

    /**
     * Called when the admin clicks the refresh button.
     * TODO: Reload admin dashboard data from the database.
     */
    @FXML
    void handleAdminRefresh(ActionEvent event) {
        // TODO: reload dashboard data (counts, etc.)
    }

    /**
     * Called when the dashboard is initialized.
     * TODO: Load admin data from the database and update labels.
     */
    @FXML
    void initialize() {
        // TODO: implement load admin data from the database
        // loadAdminData();
    }
}
