package com.example.minimoodle.teacherfunctionalities;

import com.example.minimoodle.TeacherDashboardController;
import com.example.servicecodes.StudentInfo;
import com.example.utils.Client;

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

    private int teacherId;
    private String currentCourseId;

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
        // System.out.println("Teacher ID set to: " + teacherId);
    }

    public void setCurrentCourseId(String courseId) {
        this.currentCourseId = new String(courseId);
        // System.out.println("Current Course ID set to: " + courseId);
    }

    private ObservableList<Student> studentData = FXCollections.observableArrayList();
    private static final String[] VALID_GRADES = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "D", "F"};

    @FXML
    public void initialize(String courseId, int teacherId) {
        System.out.println("initialize called with courseId: " + courseId + " and teacherId: " + teacherId);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        setCurrentCourseId(courseId);
        setTeacherId(teacherId);

        loadStudentData();
        studentTable.setItems(studentData);

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
    }

    private void loadStudentData() {
        if (currentCourseId != null) {
            var students = Client.getEnrolledStudents(currentCourseId);
            // sout(var);
            System.out.println(students);
            for (StudentInfo studentInfo : students) {
                studentData.add(new Student(studentInfo));
                System.out.println(studentInfo);
            }
        }
    }

    private void showGradeDialog(Student student) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(student.getGrade() == null ? "Add Grade" : "Update Grade");
        dialog.setHeaderText("Enter grade for " + student.getName());

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        TextField gradeField = new TextField(student.getGrade() != null ? student.getGrade() : "");
        gradeField.setPromptText("Enter letter grade");

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
                int studentId=Integer.parseInt(student.getId());
                int cId = Integer.parseInt(currentCourseId);
                boolean settingGrade= Client.setStudentGrade(studentId,cId,grade);
                System.out.println(settingGrade);
                if (settingGrade) {
                    showAlert("","Grade saved successfully.");
                }
                else{
                    showAlert("Database Error", "Failed to save grade: ");
                }
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleGoBack(ActionEvent event) {
        System.out.println("Navigating to teacher dashboard...");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/minimoodle/teacher-dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Teacher Dashboard");

            TeacherDashboardController controller = loader.getController();
            controller.initialize(teacherId);
        } catch (java.io.IOException | NullPointerException e) {
            showAlert("Loading Error", "Failed to load the teacher dashboard: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
