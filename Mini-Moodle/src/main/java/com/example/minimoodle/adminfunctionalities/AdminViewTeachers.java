package com.example.minimoodle.adminfunctionalities;

import java.io.IOException;

import com.example.minimoodle.AdminDashboardController;
import com.example.servicecodes.TeacherInfo;
import com.example.utils.Client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminViewTeachers {

    @FXML
    private TableView<TeacherTableRow> adminDashboardTeachersTableView;

    @FXML
    private TableColumn<TeacherTableRow, String> adminDashboardTeacherNameColumn;

    @FXML
    private TableColumn<TeacherTableRow, String> adminDashboardTeacherEmailColumn;

    @FXML
    private TableColumn<TeacherTableRow, String> adminDashboardTeacherManagedCourseColumn;

    @FXML
    private Button adminTeacherGoBackButton;

    @FXML
    private Button adminDashboardAddTeacherButton;

    @FXML
    private Label adminNameRibbonLabel;

    private ObservableList<TeacherTableRow> teacherList = FXCollections.observableArrayList();

    private String currentAdminId;

    public void setCurrentAdminId(String adminId) {
        this.currentAdminId = adminId;
    }

    String getCurrentAdminId() {
        return currentAdminId;
    }

    public static class TeacherTableRow {
        private int id;
        private String name;
        private String email;
        private String managedCourse;

        public TeacherTableRow(int id, String name, String email, String managedCourse) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.managedCourse = managedCourse;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getManagedCourse() { return managedCourse; }
    }

    @FXML
    public void initialize(String adminId) {
        setCurrentAdminId(adminId);
        if (adminNameRibbonLabel != null) {
            adminNameRibbonLabel.setText("Admin");
        }
        adminDashboardTeacherNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        adminDashboardTeacherEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        adminDashboardTeacherManagedCourseColumn.setCellValueFactory(new PropertyValueFactory<>("managedCourse"));
        loadData();
    }

    private void loadData() {
        teacherList.clear();
        var teachers = Client.getAllTeachers();
        for (TeacherInfo teacher : teachers) {
            String managedCourse = !teacher.getCourse().equals("default") ? teacher.getCourse() : "";
            TeacherTableRow row = new TeacherTableRow(teacher.getTeacherId(), teacher.getName(), teacher.getEmail(), managedCourse);
            teacherList.add(row);
            System.out.println(teacher);
        }
        adminDashboardTeachersTableView.setItems(teacherList);
    }

    @FXML
    private void onGoBackClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/minimoodle/admin-dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) adminTeacherGoBackButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard: " + getCurrentAdminId());

            AdminDashboardController adminDashboardController = loader.getController();
            adminDashboardController.setCurrentAdminId(currentAdminId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onAddTeacherClicked(ActionEvent event) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Add Teacher");
        dialog.setHeaderText("Enter teacher details:");

        ButtonType addButtonType = new ButtonType("Add", ButtonType.OK.getButtonData());
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        TextField passwordField = new TextField();
        passwordField.setPromptText("Password");
        TextField managedCourseField = new TextField();
        managedCourseField.setPromptText("Managed Course");

        javafx.scene.control.Label nameLabel = new javafx.scene.control.Label("Name:");
        javafx.scene.control.Label emailLabel = new javafx.scene.control.Label("Email:");
        javafx.scene.control.Label usernameLabel = new javafx.scene.control.Label("Username:");
        javafx.scene.control.Label passwordLabel = new javafx.scene.control.Label("Password:");

        javafx.scene.layout.VBox content = new javafx.scene.layout.VBox(10);
        content.getChildren().addAll(nameLabel, nameField, emailLabel, emailField, usernameLabel, usernameField, passwordLabel, passwordField);
        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();
                if (!name.isEmpty() && !email.isEmpty()) {
                    if (!Client.addTeacher(username, password ,name, email)) {
                        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Failed to add teacher");
                        alert.setContentText("Please check the details and try again.");
                        alert.showAndWait();
                    } else {
                        loadData();
                    }
                }
            }
            return null;
        });

        dialog.showAndWait();
    }
}
