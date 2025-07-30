package com.example.minimoodle;

import com.example.minimoodle.teacherfunctionalities.ApplyingStudentsPageController;
import com.example.minimoodle.teacherfunctionalities.EnrolledStudentsPageController;
import com.example.minimoodle.teacherfunctionalities.TeacherGradingPageController;
import com.example.utils.Client;

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
    private Label teacherNameRibbonLabel;
    @FXML
    private Label courseIdLabel;

    @FXML
    private Label courseNameLabel;

    @FXML
    private Label enrolledStudentsLabel;

    @FXML
    private MenuItem teacherChangePasswordButton;

    @FXML
    private MenuItem teacherViewProfileButton;

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

    @FXML
    private Button teacherSendNotificationButton;

    private int teacherId;

    public int getTeacherId() {
        return teacherId;
    }

    public int setTeacherId(int teacherId) {
        this.teacherId = teacherId;
        return this.teacherId;
    }

    public void initialize(int teacherId) {
        this.teacherId = teacherId;
        loadCourseData();
    }

    private String teacherCourseID;

    private void loadCourseData() {
        System.out.println(getTeacherId());
        String courseId = Client.getCourseIdForTeacher(getTeacherId());
        String courseName = Client.getCourseName(courseId);
        String teacherName = Client.getTeacherName(getTeacherId());
        String CourseDescription = Client.getCourseDescription(courseId);
        int studentCount = Client.getEnrolledStudentCount(courseId);

        teacherCourseID = courseId;

        teacherIdLabel.setText("Welcome, " + teacherName);
        courseIdLabel.setText("Course ID: " + courseName);
        courseNameLabel.setText("Course Name: " + CourseDescription);
        enrolledStudentsLabel.setText("Enrolled Students: " + studentCount);

        // Optionally update teacherNameRibbonLabel if present
        if (teacherNameRibbonLabel != null) {
            teacherNameRibbonLabel.setText(Client.getTeacherUsername(getTeacherId()));
        }
    }

    @FXML
    void handleTeacherViewProfile(ActionEvent event) {
        String teacherName = Client.getTeacherName(getTeacherId());
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Teacher Profile");
        alert.setHeaderText("Teacher Profile");
        alert.setContentText("Name: " + teacherName + "\nTeacher Username: " + Client.getTeacherUsername(getTeacherId()) + "\nEmail: " + Client.getTeacherEmail(getTeacherId()));
        alert.showAndWait();
    }

    @FXML
    void handleTeacherDashboardRefresh(ActionEvent event) {
        loadCourseData();
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("teacher-dashboard-applying-students.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) teacherDashboardApplicantsButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Teacher Applicants Page");

            ApplyingStudentsPageController controller = loader.getController();
            controller.initialize(teacherId, teacherCourseID);

        } catch (java.io.IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Loading Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load the applicants page: " + e.getMessage());
            alert.showAndWait();

            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void handleTeacherViewEnrolledStudentsTable(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("teacher-dashboard-enrolled-students.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) teacherDashboardEnrolledStudentsButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Enrolled Students");

            EnrolledStudentsPageController controller = loader.getController();
            controller.initialize(teacherId, teacherCourseID);

        } catch (java.io.IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Loading Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to load the enrolled students page: " + e.getMessage());
            alert.showAndWait();

            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void handleTeacherGrading(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("teacher-grading-page.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) teacherDashboardGradingButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Teacher Grading Page");

            TeacherGradingPageController controller = loader.getController();
            controller.initialize(teacherCourseID, teacherId);

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
    void handleTeacherRefresh(ActionEvent event) {
        loadCourseData();
    }

    @FXML
    void handleTeacherPasswordChange(ActionEvent event) {
        Dialog<ButtonType> dialog = new javafx.scene.control.Dialog<>();
        dialog.initOwner(teacherSettingsButton.getScene().getWindow());
        dialog.setTitle("Change Password");

        dialog.initModality(Modality.APPLICATION_MODAL);

        PasswordField oldPasswordField = new PasswordField();
        PasswordField newPasswordField = new PasswordField();
        PasswordField confirmPasswordField = new PasswordField();

        oldPasswordField.setPromptText("Old Password");
        newPasswordField.setPromptText("New Password");
        confirmPasswordField.setPromptText("Confirm New Password");

        VBox dialogContent = new VBox(10,
                new Label("Old Password:"), oldPasswordField,
                new Label("New Password:"), newPasswordField,
                new Label("Confirm New Password:"), confirmPasswordField);

        dialogContent.setPadding(new Insets(20));

        dialog.getDialogPane().setContent(dialogContent);

        ButtonType changeButtonType = new ButtonType("Change", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(changeButtonType, cancelButtonType);

        Button changeButton = (Button) dialog.getDialogPane().lookupButton(changeButtonType);
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
                flag = newPassword.equals(confirmPassword) && Client.changeTeacherPassword(teacherId, newPassword);
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
                    alert.setContentText("Failed to change password. Please check your old password or server connection.");
                    alert.showAndWait();
                }
            }
            return buttonType;
        });

        dialog.showAndWait();
    }

    @FXML
    void handleTeacherSendNotification(ActionEvent event) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(teacherSendNotificationButton.getScene().getWindow());
        dialog.setTitle("Send Notification");

        dialog.initModality(Modality.APPLICATION_MODAL);
        
        dialog.setWidth(450);
        dialog.setHeight(300);
        dialog.setResizable(true);

        javafx.scene.control.TextArea notificationTextArea = new javafx.scene.control.TextArea();
        notificationTextArea.setPromptText("Enter your notification message here...\nPress Enter for new lines.");
        notificationTextArea.setWrapText(true);
        notificationTextArea.setPrefHeight(180);
        notificationTextArea.setPrefWidth(380);
        
        // Enable newline characters and prevent tab traversal
        notificationTextArea.setStyle("-fx-focus-traversable: false;");

        Label instructionLabel = new Label("Compose your notification message:");
        instructionLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        VBox dialogContent = new VBox(15, instructionLabel, notificationTextArea);
        dialogContent.setPadding(new Insets(20));

        dialog.getDialogPane().setContent(dialogContent);

        ButtonType sendButtonType = new ButtonType("Send", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(sendButtonType, cancelButtonType);

        Button sendButton = (Button) dialog.getDialogPane().lookupButton(sendButtonType);
        sendButton.setDefaultButton(true);
        sendButton.setDisable(true);

        notificationTextArea.textProperty().addListener((_, _, newVal)
                -> sendButton.setDisable(newVal.isEmpty()));

        dialog.setResultConverter(buttonType -> {
            if (buttonType == sendButtonType) {
                String message = notificationTextArea.getText();
                boolean success = Client.sendNotification(Integer.parseInt(teacherCourseID), message);
                if (success) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Notification sent successfully.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to send notification. Please check your server connection.");
                    alert.showAndWait();
                }
            }
            return buttonType;
        });

        dialog.showAndWait();
    
    }


}
