package com.example.minimoodle;

import java.io.IOException;

import com.example.minimoodle.studentfunctionalities.StudentViewCourses;
import com.example.minimoodle.studentfunctionalities.StudentViewGrades;
import com.example.utils.Client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class StudentDashboardController {

    @FXML
    private Label studentDashboardLabel;
    @FXML
    private Label enrolledCoursesLabel;
    @FXML
    private Label studentNameRibbonLabel;
    @FXML
    private Label studentIdLabel;
    @FXML
    private Label currentGradeLabel;
    @FXML
    private Button viewCoursesButton;
    @FXML
    private Button viewGradesButton;
    @FXML
    private Button studentRefreshButton;
    @FXML
    private Button studentLogoutButton;
    @FXML
    private javafx.scene.control.MenuButton studentSettingsButton;
    @FXML
    private javafx.scene.control.MenuItem studentChangePasswordButton;
    @FXML
    private javafx.scene.control.MenuItem studentViewProfileButton;
    @FXML
    private Button notificationsButton;
    private int studentId;

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void initialize(int studentId) {
        this.studentId = studentId;
        loadStudentDashboardData();
    }

    private void loadStudentDashboardData() {
        String studentName = Client.getStudentName(studentId);
        studentDashboardLabel.setText("Welcome, " + studentName);
        enrolledCoursesLabel.setText("Enrolled Courses: " + Client.getEnrolledCoursesForStudent(studentId).size());
        if (studentNameRibbonLabel != null) {
            studentNameRibbonLabel.setText(Client.getStudentUsername(studentId));
        }
        if (studentIdLabel != null) {
            studentIdLabel.setText("Student ID: " + studentId);
        }
        if (currentGradeLabel != null) {
            currentGradeLabel.setText("Current Grade: " + getCurrentGradeForStudent(studentId));
        }
    }

    private String getCurrentGradeForStudent(int studentId) {
        return "N/A";
    }

    @FXML
    private void handleViewCoursesButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("student-dashboard-view-courses.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = (Stage) viewCoursesButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("View Enrolled Courses - Student " + Client.getStudentName(studentId));
            StudentViewCourses controller = loader.getController();
            controller.setStudentId(studentId);
            controller.initialize(studentId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleViewGradesButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("student-dashboard-view-grades.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = (Stage) viewGradesButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("View Grades - Student " + Client.getStudentName(studentId));
            StudentViewGrades controller = loader.getController();
            controller.initialize(studentId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleViewRequestedCoursesButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("student-dashboard-view-applied-courses.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) viewCoursesButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("View Applied Courses - Student " + Client.getStudentName(studentId));
            com.example.minimoodle.studentfunctionalities.StudentViewPendingCourses controller = loader.getController();
            controller.setStudentId(studentId);
            controller.initialize(studentId);
        } catch (IOException e) {
            System.out.println("Error loading applied courses view: " + e.getMessage());
        }
    }

    @FXML
    private void handleStudentRefresh() {
        loadStudentDashboardData();
    }

    @FXML
    private void handleStudentPasswordChange() {
        javafx.scene.control.Dialog<javafx.scene.control.ButtonType> dialog = new javafx.scene.control.Dialog<>();
        dialog.initOwner(studentSettingsButton.getScene().getWindow());
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
        newPasswordField.textProperty().addListener((obs, old, newValue)
                -> changeButton.setDisable(newValue.isEmpty() || !newValue.equals(confirmPasswordField.getText())));
        confirmPasswordField.textProperty().addListener((obs, old, newValue)
                -> changeButton.setDisable(newValue.isEmpty() || !newValue.equals(newPasswordField.getText())));
        dialog.setResultConverter(buttonType -> {
            if (buttonType == changeButtonType) {
                String newPassword = newPasswordField.getText();
                String confirmPassword = confirmPasswordField.getText();
                boolean success = newPassword.equals(confirmPassword) && Client.changeStudentPassword(studentId, newPassword);
                if (success) {
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

    @FXML
    private void handleViewProfile() {
        System.out.println("Student " + studentId + " requested to view profile.");
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Profile");
        alert.setHeaderText("Student Profile");
        alert.setContentText("Name: " + Client.getStudentName(studentId) + "\n");
        alert.setContentText(alert.getContentText() + "Student username: " + Client.getStudentUsername(studentId) + "\n");
        alert.setContentText(alert.getContentText() + "Student Email: " + Client.getStudentEmail(studentId) + "\n");
        alert.showAndWait();
    }

    @FXML
    private void handleStudentLogout() {
        System.out.println("Student " + studentId + " logged out.");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome-page.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = (Stage) studentLogoutButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Welcome - Mini Moodle");
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleViewNotifications() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("student-dashboard-view-notifications.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) notificationsButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Notifications");
            com.example.minimoodle.studentfunctionalities.StudentViewNotifications controller = loader.getController();
            controller.setStudentId(studentId);
            controller.initialize(studentId);
        } catch (java.io.IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Loading Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load notifications: " + e.getMessage());
            alert.showAndWait();
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
