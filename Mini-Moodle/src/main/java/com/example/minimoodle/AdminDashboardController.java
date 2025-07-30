package com.example.minimoodle;

import com.example.minimoodle.adminfunctionalities.AdminViewCourses;
import com.example.minimoodle.adminfunctionalities.AdminViewStudents;
import com.example.minimoodle.adminfunctionalities.AdminViewTeachers;
import com.example.utils.Client;

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
    private Button adminRefreshButton;
    @FXML
    private Label totalCoursesLabel;
    @FXML
    private Label totalStudentsLabel;
    @FXML
    private Label totalTeachersLabel;
    @FXML
    private Label adminNameRibbonLabel;
    @FXML
    private javafx.scene.control.MenuButton adminSettingsButton;
    @FXML
    private javafx.scene.control.MenuItem adminChangePasswordButton;

    private String currentAdminId;

    public void setCurrentAdminId(String adminId) {
        this.currentAdminId = adminId;
        // Optionally update adminNameRibbonLabel if present
        if (adminNameRibbonLabel != null) {
            adminNameRibbonLabel.setText("Administrator");
        }
    }

    public String getCurrentAdminId() {
        return currentAdminId;
    }

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

    @FXML
    void handleManageCoursesButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-dashboard-course-management.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) manageCoursesButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("All Courses");

            AdminViewCourses adminViewCourses = loader.getController();
            adminViewCourses.setCurrentAdminId(currentAdminId);
            adminViewCourses.initialize(currentAdminId);
        } catch (java.io.IOException | NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Loading Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load the course management page: " + e.getMessage());
            alert.showAndWait();
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void handleManageStudentsButton(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-dashboard-student-management.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) manageStudentsButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Students' Details");

            AdminViewStudents adminViewStudents = loader.getController();
            adminViewStudents.setCurrentAdminId(currentAdminId);
            adminViewStudents.initialize(currentAdminId);
        } catch (java.io.IOException | NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Loading Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load the student management page: " + e.getMessage());
            alert.showAndWait();
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void handleManageTeachersButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin-dashboard-teacher-management.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) manageTeachersButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Teachers' Details");

            AdminViewTeachers adminViewTeachers = loader.getController();
            adminViewTeachers.setCurrentAdminId(currentAdminId);
            adminViewTeachers.initialize(currentAdminId);
        } catch (java.io.IOException | NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Loading Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load the teacher management page: " + e.getMessage());
            alert.showAndWait();
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void handleAdminRefresh(ActionEvent event) {
        int teachersCount = Client.getTotalTeacherCount();
        int studentsCount = Client.getTotalStudentCount();
        int coursesCount = Client.getTotalCourseCount();

        totalCoursesLabel.setText("Total Courses: " + String.valueOf(coursesCount));
        totalStudentsLabel.setText("Total Students: " + String.valueOf(studentsCount));
        totalTeachersLabel.setText("Total Teachers: " + String.valueOf(teachersCount));
        
        if (adminNameRibbonLabel != null && currentAdminId != null) {
            adminNameRibbonLabel.setText("Administrator");
        }
    }

    @FXML
    void initialize() {
        handleAdminRefresh(null);
    }

    @FXML
    void handleAdminChangePassword(ActionEvent event) {
        javafx.scene.control.Dialog<javafx.scene.control.ButtonType> dialog = new javafx.scene.control.Dialog<>();
        dialog.initOwner(adminSettingsButton.getScene().getWindow());
        dialog.setTitle("Change Password");
        dialog.initModality(javafx.stage.Modality.APPLICATION_MODAL);

        javafx.scene.control.PasswordField oldPasswordField = new javafx.scene.control.PasswordField();
        javafx.scene.control.PasswordField newPasswordField = new javafx.scene.control.PasswordField();
        javafx.scene.control.PasswordField confirmPasswordField = new javafx.scene.control.PasswordField();

        oldPasswordField.setPromptText("Old Password");
        newPasswordField.setPromptText("New Password");
        confirmPasswordField.setPromptText("Confirm New Password");

        javafx.scene.layout.VBox dialogContent = new javafx.scene.layout.VBox(10,
                new javafx.scene.control.Label("Old Password:"), oldPasswordField,
                new javafx.scene.control.Label("New Password:"), newPasswordField,
                new javafx.scene.control.Label("Confirm New Password:"), confirmPasswordField);
        dialogContent.setPadding(new javafx.geometry.Insets(20));
        dialog.getDialogPane().setContent(dialogContent);

        javafx.scene.control.ButtonType changeButtonType = new javafx.scene.control.ButtonType("Change", javafx.scene.control.ButtonBar.ButtonData.OK_DONE);
        javafx.scene.control.ButtonType cancelButtonType = new javafx.scene.control.ButtonType("Cancel", javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(changeButtonType, cancelButtonType);

        javafx.scene.control.Button changeButton = (javafx.scene.control.Button) dialog.getDialogPane().lookupButton(changeButtonType);
        changeButton.setDefaultButton(true);
        changeButton.setDisable(true);

        newPasswordField.textProperty().addListener((obs, old, newVal)
                -> changeButton.setDisable(newVal.isEmpty() || !newVal.equals(confirmPasswordField.getText())));
        confirmPasswordField.textProperty().addListener((obs, old, newVal)
                -> changeButton.setDisable(newVal.isEmpty() || !newVal.equals(newPasswordField.getText())));

        dialog.setResultConverter(buttonType -> {
            if (buttonType == changeButtonType) {
                String oldPassword = oldPasswordField.getText();
                String newPassword = newPasswordField.getText();
                String confirmPassword = confirmPasswordField.getText();

                boolean flag = true;
                flag = newPassword.equals(confirmPassword) && Client.changeAdminPassword(1, newPassword);
                if (flag) {
                    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Password changed successfully.");
                    alert.showAndWait();
                } else {
                    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to change password. Please check your old password.");
                    alert.showAndWait();
                }
            }
            return buttonType;
        });

        dialog.showAndWait();
    }
}
