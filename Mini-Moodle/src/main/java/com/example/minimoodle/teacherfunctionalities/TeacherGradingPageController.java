package com.example.minimoodle.teacherfunctionalities;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TeacherGradingPageController {

    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> idColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> gradeColumn;
    @FXML
    private TableColumn<Student, Void> actionColumn;
    @FXML
    private Button goBackButton;

    private ObservableList<Student> studentData = FXCollections.observableArrayList();
    private static final String[] VALID_GRADES = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "D", "F"};

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button actionButton = new Button("Add Grade");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    return;
                }
                Student student = getTableView().getItems().get(getIndex());
                actionButton.setText(student.getGrade() == null ? "Add Grade" : "Update Grade");
                actionButton.setOnAction(event -> showGradeDialog(student));
                setGraphic(actionButton);
            }
        });

        loadStudentData();
        studentTable.setItems(studentData);
    }

    private void loadStudentData() {
        // Placeholder for SQL data retrieval
        try (Connection conn = getDatabaseConnection()) {
            String query = "SELECT id, name, grade FROM students WHERE course_id = ?"; // Example query
            // Execute query and populate studentData
            // ResultSet rs = conn.prepareStatement(query).executeQuery();
            // while (rs.next()) {
            //     studentData.add(new Student(rs.getString("id"), rs.getString("name"), rs.getString("grade")));
            // }
        } catch (SQLException e) {
            showAlert("Database Error", "Failed to load student data: " + e.getMessage());
        }

        // Temporary dummy data for demonstration
        studentData.add(new Student("S001", "John Doe", null));
        studentData.add(new Student("S002", "Jane Smith", "B+"));
    }

    private Connection getDatabaseConnection() throws SQLException {
        // Placeholder for SQL connection
        return null; // Replace with actual database connection logic
    }

    private void showGradeDialog(Student student) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(student.getGrade() == null ? "Add Grade" : "Update Grade");
        dialog.setHeaderText("Enter grade for " + student.getName());

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        TextField gradeField = new TextField(student.getGrade() != null ? student.getGrade() : "");
        gradeField.setPromptText("Enter letter grade (e.g., A, B+, C)");

        VBox content = new VBox(10);
        content.getChildren().addAll(new Label("Grade:"), gradeField);
        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                return gradeField.getText().trim().toUpperCase();
            }
            return null;
        });

        dialog.showAndWait().ifPresent(grade -> {
            if (isValidGrade(grade)) {
                student.setGrade(grade);
                studentTable.refresh();
                // Placeholder for SQL update
                // try (Connection conn = getDatabaseConnection()) {
                //     String query = "UPDATE students SET grade = ? WHERE id = ?";
                //     PreparedStatement stmt = conn.prepareStatement(query);
                //     stmt.setString(1, grade);
                //     stmt.setString(2, student.getId());
                //     stmt.executeUpdate();
                // } catch (SQLException e) {
                //     showAlert("Database Error", "Failed to save grade: " + e.getMessage());
                // }
            } else {
                showAlert("Invalid Grade", "Please enter a valid letter grade (A, A-, B+, B, B-, C+, C, C-, D+, D, F).");
            }
        });
    }

    private boolean isValidGrade(String grade) {
        if (grade == null || grade.isEmpty()) {
            return false;
        }
        for (String validGrade : VALID_GRADES) {
            if (validGrade.equals(grade)) {
                return true;
            }
        }
        return false;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
        // Placeholder for navigation to teacher dashboard
        System.out.println("Navigating to teacher dashboard...");
        // Replace with actual navigation logic, e.g., loading another FXML

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("teacher-dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Teacher Dashboard");
        } catch (java.io.IOException | NullPointerException e) {
            showAlert("Loading Error", "Failed to load the teacher dashboard: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static class Student {

        private final SimpleStringProperty id;
        private final SimpleStringProperty name;
        private final SimpleStringProperty grade;

        public Student(String id, String name, String grade) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.grade = new SimpleStringProperty(grade);
        }

        public String getId() {
            return id.get();
        }

        public String getName() {
            return name.get();
        }

        public String getGrade() {
            return grade.get();
        }

        public void setGrade(String grade) {
            this.grade.set(grade);
        }
    }
}
