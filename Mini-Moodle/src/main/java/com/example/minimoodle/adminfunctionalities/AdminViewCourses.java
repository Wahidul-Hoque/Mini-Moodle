package com.example.minimoodle.adminfunctionalities;

import java.io.IOException;

import com.example.minimoodle.AdminDashboardController;
import com.example.servicecodes.CourseInfoAdmin;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminViewCourses {

    @FXML
    private TableView<CourseInfoAdmin> adminDashboardCoursesTableView;

    @FXML
    private TableColumn<CourseInfoAdmin, String> adminDashboardCourseTitleColumn;

    @FXML
    private TableColumn<CourseInfoAdmin, String> adminDashboardCourseNameColumn;

    @FXML
    private TableColumn<CourseInfoAdmin, String> adminDashboardCourseTeacherColumn;

    @FXML
    private Button adminCourseGoBackButton;

    @FXML
    private Button adminDashboardAddCourseButton;

    private ObservableList<CourseInfoAdmin> courseList = FXCollections.observableArrayList();

    private String currentAdminId;

    public void setCurrentAdminId(String adminId) {
        this.currentAdminId = adminId;
    }

    String getCurrentAdminId() {
        return currentAdminId;
    }

    @FXML
    public void initialize(String adminId) {
        // Set up table columns and load data
        adminDashboardCourseTitleColumn.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
        adminDashboardCourseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseDescription"));
        adminDashboardCourseTeacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacherName"));

        setCurrentAdminId(adminId);

        loadData();
    }

    private void loadData() {
        var courseList = Client.getAllCourses();
        for(CourseInfoAdmin course : courseList) {
            this.courseList.add(course);
        }
        adminDashboardCoursesTableView.setItems(this.courseList);
    }

    @FXML
    private void onGoBackClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/minimoodle/admin-dashboard.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = (Stage) adminCourseGoBackButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

            stage.setTitle("Dashboard: " + getCurrentAdminId());

            AdminDashboardController adminDashboardController = loader.getController();
            adminDashboardController.setCurrentAdminId(getCurrentAdminId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onAddCourseClicked(ActionEvent event) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Add Course");
        dialog.setHeaderText("Enter course details:");

        ButtonType addButtonType = new ButtonType("Add", ButtonType.OK.getButtonData());
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        TextField titleField = new TextField();
        titleField.setPromptText("Course Title");
        TextField nameField = new TextField();
        nameField.setPromptText("Course Name");
        TextField teacherField = new TextField();
        teacherField.setPromptText("Course Teacher");

        javafx.scene.control.Label titleLabel = new javafx.scene.control.Label("Course Title:");
        javafx.scene.control.Label nameLabel = new javafx.scene.control.Label("Course Name:");
        javafx.scene.control.Label teacherLabel = new javafx.scene.control.Label("Course Teacher:");

        javafx.scene.layout.VBox content = new javafx.scene.layout.VBox(10);
        content.getChildren().addAll(titleLabel, titleField, nameLabel, nameField, teacherLabel, teacherField);
        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                String title = titleField.getText().trim();
                String name = nameField.getText().trim();
                String teacher = teacherField.getText().trim();
                if (!title.isEmpty() && !name.isEmpty() && !teacher.isEmpty()) {

                    if(!Client.addCourse(title, name, teacher))
                    {
                        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Failed to add course");
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
