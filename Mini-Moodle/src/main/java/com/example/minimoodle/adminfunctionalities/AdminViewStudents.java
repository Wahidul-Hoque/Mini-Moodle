package com.example.minimoodle.adminfunctionalities;

import java.util.List;

import com.example.minimoodle.AdminDashboardController;
import com.example.servicecodes.CourseInfo;
import com.example.servicecodes.StudentInfo;
import com.example.utils.Client;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminViewStudents {

    @FXML
    private TableView<StudentTableRow> adminDashboardStudentsTableView;

    @FXML
    private TableColumn<StudentTableRow, String> adminDashboardStudentNameColumn;

    @FXML
    private TableColumn<StudentTableRow, String> adminDashboardStudentEmailColumn;

    @FXML
    private Button adminDashboardViewStudentsButton;

    @FXML
    private Button adminDashboardGoBackButton;

    @FXML
    private Label adminNameRibbonLabel;

    private String currentAdminId;

    public void setCurrentAdminId(String adminId) {
        this.currentAdminId = adminId;
    }

    String getCurrentAdminId() {
        return currentAdminId;
    }

    public static class StudentTableRow {

        private int id;
        private String name;
        private String email;

        public StudentTableRow(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public int getId() {
            return id;
        }
    }

    @FXML
    public void initialize(String adminId) {

        setCurrentAdminId(adminId);

        // Set admin name in the ribbon
        if (adminNameRibbonLabel != null) {
            adminNameRibbonLabel.setText("Admin");
        }

        adminDashboardStudentNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        adminDashboardStudentEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        ObservableList<StudentTableRow> students = getStudentData();
        adminDashboardStudentsTableView.setItems(students);

        adminDashboardViewStudentsButton.setDisable(true);

        adminDashboardStudentsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            adminDashboardViewStudentsButton.setDisable(newSel == null);
        });
    }

    @FXML
    private void onGoBackClicked(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/minimoodle/admin-dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) adminDashboardGoBackButton.getScene().getWindow();
            stage.setScene(new Scene(root));

            stage.setTitle("Dashboard: " + getCurrentAdminId());

            AdminDashboardController adminDashboardController = loader.getController();
            adminDashboardController.setCurrentAdminId(currentAdminId);
        } catch (Exception e) {
            showErrorDialog("Failed to load the admin dashboard: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void onViewDetailsClicked(ActionEvent event) {
        StudentTableRow selected = adminDashboardStudentsTableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            showEnrolledCoursesDialog(selected);
        } else {
            showErrorDialog("No student selected.");
        }
    }

    private ObservableList<StudentTableRow> getStudentData() {
        ObservableList<StudentTableRow> t = FXCollections.observableArrayList();

        var students = Client.getAllStudents();
        for (StudentInfo student : students) {
            StudentTableRow row = new StudentTableRow(student.getId(), student.getName(), student.getEmail());
            t.add(row);
        }

        return t;
    }

    private void showEnrolledCoursesDialog(StudentTableRow student) {
        List<CourseInfo> courses = Client.getEnrolledCoursesForStudent(student.getId());

        if (courses == null || courses.isEmpty()) {
            showErrorDialog("No courses found for this student.");
            return;
        }

        TableView<CourseInfo> tableView = new TableView<>();
        tableView.setPrefHeight(250);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<CourseInfo, String> idCol = new TableColumn<>("Course Title");
        idCol.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));

        TableColumn<CourseInfo, String> nameCol = new TableColumn<>("Course Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("courseDescription"));

        TableColumn<CourseInfo, String> gradeCol = new TableColumn<>("Obtained Grade");
        gradeCol.setCellValueFactory(data -> {
            String grade = data.getValue().getGrade();
            if (grade == null || grade.equalsIgnoreCase("default")) {
                return new SimpleStringProperty("");
            } else {
                return new SimpleStringProperty(grade);
            }
        });

        tableView.getColumns().addAll(idCol, nameCol, gradeCol);
        tableView.getItems().addAll(courses);

        VBox container = new VBox(tableView);
        container.setPadding(new Insets(10));

        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Enrolled Courses");
        dialog.setHeaderText(student.getName() + " is enrolled in:");

        dialog.getDialogPane().setContent(container);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.getDialogPane().setMinWidth(200);
        dialog.getDialogPane().setMinHeight(300);
        dialog.showAndWait();
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
