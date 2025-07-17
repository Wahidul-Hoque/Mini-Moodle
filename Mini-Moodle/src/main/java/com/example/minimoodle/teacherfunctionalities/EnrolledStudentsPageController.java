// package com.example.minimoodle.teacherfunctionalities;


// import javafx.fxml.FXML;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.event.ActionEvent;
// import javafx.scene.input.MouseEvent;
// import javafx.scene.control.TableRow;
// import javafx.beans.property.SimpleStringProperty;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;

// public class EnrolledStudentsPageController {

//     @FXML
//     private TableView<Student> studentTable;

//     @FXML
//     private TableColumn<Student, String> idColumn;

//     @FXML
//     private TableColumn<Student, String> nameColumn;

//     @FXML
//     private Label studentNameLabel;

//     @FXML
//     private Label studentEmailLabel;

//     @FXML
//     private Button blockButton;

//     private ObservableList<Student> studentList;

//     @FXML
//     public void initialize() {
//         // Initializing the columns to display student ID and name
//         idColumn.setCellValueFactory(cellData -> cellData.getValue().getId());
//         nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());

//         // Fetch enrolled students (Placeholder for actual data fetching)
//         studentList = fetchEnrolledStudents();
//         studentTable.setItems(studentList);

//         // Set up event handler for when a row is clicked
//         studentTable.setRowFactory(tv -> {
//             TableRow<Student> row = new TableRow<>();
//             row.setOnMouseClicked((MouseEvent event) -> {
//                 if (!row.isEmpty()) {
//                     Student student = row.getItem();
//                     displayStudentDetails(student);
//                 }
//             });
//             return row;
//         });
//     }

//     private ObservableList<Student> fetchEnrolledStudents() {
//         // Temporary dummy data for demonstration
//         ObservableList<Student> students = FXCollections.observableArrayList();
//         students.add(new Student("S001", "Alice", "alice@example.com"));
//         students.add(new Student("S002", "Bob", "bob@example.com"));
//         students.add(new Student("S003", "Charlie", "charlie@example.com"));
//         return students;
//     }

//     private void displayStudentDetails(Student student) {
//         // Display student name and email
//         studentNameLabel.setText("Name: " + student.getName());
//         studentEmailLabel.setText("Email: " + student.getEmail());
//         blockButton.setDisable(false); // Enable the "Block Student" button
//     }

//     @FXML
//     public void handleGoBack(ActionEvent event) {
//         // Handle going back to the previous page (e.g., Teacher Dashboard)
//         System.out.println("Going back to the teacher dashboard...");
//         // Implement logic for navigation to the Teacher Dashboard or previous screen
//     }

//     @FXML
//     public void handleBlockStudent(ActionEvent event) {
//         // Handle blocking the selected student
//         Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
//         if (selectedStudent != null) {
//             blockStudent(selectedStudent);
//         }
//     }

//     private void blockStudent(Student student) {
//         // Placeholder logic for blocking a student
//         // Remove student from the list and database if necessary
//         showAlert("Blocked Student", "Student " + student.getName() + " has been blocked.");
//         studentList.remove(student);
//     }

//     private void showAlert(String title, String message) {
//         Alert alert = new Alert(Alert.AlertType.INFORMATION);
//         alert.setTitle(title);
//         alert.setHeaderText(null);
//         alert.setContentText(message);
//         alert.showAndWait();
//     }

//     public static class Student {

//         private final SimpleStringProperty id;
//         private final SimpleStringProperty name;
//         private final SimpleStringProperty grade;

//         public Student(String id, String name, String grade) {
//             this.id = new SimpleStringProperty(id);
//             this.name = new SimpleStringProperty(name);
//             this.grade = new SimpleStringProperty(grade);
//         }

//         public String getId() {
//             return id.get();
//         }

//         public String getName() {
//             return name.get();
//         }

//         public String getGrade() {
//             return grade.get();
//         }

//         public void setGrade(String grade) {
//             this.grade.set(grade);
//         }
//     }

    
// }

