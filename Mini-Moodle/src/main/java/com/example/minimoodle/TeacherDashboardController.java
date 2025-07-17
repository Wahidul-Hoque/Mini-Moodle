package com.example.minimoodle;

import com.example.servicecodes.CourseService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TeacherDashboardController {

        @FXML
    private Label courseIdLabel;

    @FXML
    private Label courseNameLabel;

    @FXML
    private Label enrolledStudentsLabel;

    @FXML
    private MenuItem teacherChangePasswordButton;

    @FXML
    private Button teacherDashboardApplicantsButton;

    @FXML
    private Button teacherDashboardEnrolledStudentsButton;

    @FXML
    private Button teacherDashboardGradingButton;

    @FXML
    private Button teacherDashboardLogoutButton;

    @FXML
    private Button teacherDashboardRefreshButton;

    @FXML
    private Label teacherIdLabel;

    @FXML
    private MenuButton teacherSettingsButton;

    private int teacherId;
    private String courseId;

    // This method will be called after the teacher logs in
    public void initialize(int teacherId) {
        this.teacherId = teacherId;
        loadCourseData();
    }

    private void loadCourseData() {
        courseId = CourseService.getCourseIdForTeacher(teacherId);
        String courseName = CourseService.getCourseName(courseId);
        int enrolledStudentsCount = CourseService.getEnrolledStudents(courseId).size();

        // TODO: get teacher name, not teacher ID. This is displaying teacher ID instead of name
        teacherIdLabel.setText("Welcome, teacher " + teacherId);
        courseIdLabel.setText("Course ID: " + courseId);
        courseNameLabel.setText("Course Name: " + courseName);
        enrolledStudentsLabel.setText("Enrolled Students: " + enrolledStudentsCount);
    }

    @FXML
    void handleTeacherDashboardRefresh(ActionEvent event) {
        // TODO: refresh the teacher dashboard
        loadCourseData();
    }

    @FXML
    void handleTeacherGrading(ActionEvent event) {
        // TODO: take me to the grading page

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("teacher-grading-page.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) teacherDashboardGradingButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Teacher Grading Page");

        } catch (java.io.IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Loading Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load the grading page: " + e.getMessage());
            alert.showAndWait();

            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void handleTeacherLogout(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome-page.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) teacherDashboardLogoutButton.getScene().getWindow();
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
    void handleTeacherSeeApplyingStudents(ActionEvent event) {
        // TODO: take to applicants page
    }

    @FXML
    void handleTeacherViewEnrolledStudentsTable(ActionEvent event) {
        // TODO: enrolled students page

    }


    @FXML
    void handleTeacherRefresh(ActionEvent event) {
        loadCourseData();
    }

    @FXML
    void handleTeacherPasswordChange(ActionEvent event) {
        Dialog<ButtonType> dialog = new javafx.scene.control.Dialog<>();
        dialog.initOwner(teacherSettingsButton.getScene().getWindow());
        dialog.setTitle("Change Password");

        // change the modality
        // this feature allows the dialog to block input to other windows
        // until the dialog is closed
        dialog.initModality(Modality.APPLICATION_MODAL);

        PasswordField oldPasswordField = new PasswordField();
        PasswordField newPasswordField = new PasswordField();
        PasswordField confirmPasswordField = new PasswordField();

        oldPasswordField.setPromptText("Old Password");
        newPasswordField.setPromptText("New Password");
        confirmPasswordField.setPromptText("Confirm New Password");

        // Set the button types
        VBox dialogContent = new VBox(10,
                new Label("Old Password:"), oldPasswordField,
                new Label("New Password:"), newPasswordField,
                new Label("Confirm New Password:"), confirmPasswordField);

        // set padding
        dialogContent.setPadding(new Insets(20));

        // Add the buttons to the dialog
        dialog.getDialogPane().setContent(dialogContent);

        // add cancel and set buttons
        ButtonType changeButtonType = new ButtonType("Change", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(changeButtonType, cancelButtonType);

        Button changeButton = (Button) dialog.getDialogPane().lookupButton(changeButtonType);
        changeButton.setDefaultButton(true);
        changeButton.setDisable(true);

        // now we set the logiu for the change button siabled/enabled
        newPasswordField.textProperty().addListener((obs, old, newVal)
                -> changeButton.setDisable(newVal.isEmpty() || !newVal.equals(confirmPasswordField.getText())));
        confirmPasswordField.textProperty().addListener((obs, old, newVal)
                -> changeButton.setDisable(newVal.isEmpty() || !newVal.equals(newPasswordField.getText())));

        dialog.setResultConverter(buttonType -> {
            if (buttonType == changeButtonType) {
                String oldPassword = oldPasswordField.getText();
                String newPassword = newPasswordField.getText();
                String confirmPassword = confirmPasswordField.getText();

                // TODO: implement the password change logic
                // flag boolean is a placeholder for the actual password change logic.
                boolean flag = true;
                if (flag) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Password changed successfully.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
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
