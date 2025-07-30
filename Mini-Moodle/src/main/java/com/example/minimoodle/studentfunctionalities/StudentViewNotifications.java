package com.example.minimoodle.studentfunctionalities;

import java.util.List;

import com.example.servicecodes.Notification;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StudentViewNotifications {

    @FXML
    private Label studentNameRibbonLabel;
    
    @FXML
    private Button goBackButton;
    
    @FXML
    private TableView<Notification> notificationsTable;
    
    @FXML
    private TableColumn<Notification, String> timestampColumn;
    
    @FXML
    private TableColumn<Notification, String> courseIdColumn;
    
    @FXML
    private TableColumn<Notification, String> messageColumn;

    private ObservableList<Notification> notificationsList = FXCollections.observableArrayList();

    private int studentId;
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }       
    public int getStudentId() {
        return studentId;
    }

    @FXML
    public void initialize(int studentId) {

        this.studentId = studentId;

        setupTableColumns();
        
        loadDummyNotifications();
        
        studentNameRibbonLabel.setText(com.example.utils.Client.getStudentUsername(studentId));
    }

    private void setupTableColumns() {
        
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        courseIdColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        
        timestampColumn.setStyle("-fx-alignment: CENTER;");
        courseIdColumn.setStyle("-fx-alignment: CENTER;");
        
        messageColumn.setCellFactory(_ -> {
            TableCell<Notification, String> cell = new TableCell<Notification, String>() {
                private Text text = new Text();
                
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setGraphic(null);
                    } else {
                        text.setText(item);
                        text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(25));
                        text.setStyle("-fx-fill: black; -fx-font-size: 12px;");
                        setGraphic(text);
                    }
                }
            };
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            return cell;
        });
        
        messageColumn.setResizable(true);
        
        notificationsTable.setItems(notificationsList);
        
        notificationsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        notificationsTable.setRowFactory(_ -> {
            TableRow<Notification> row = new TableRow<>();
            row.setPrefHeight(Control.USE_COMPUTED_SIZE);
            return row;
        });
    }

    private void loadDummyNotifications() {
        try {
            List<Notification> notifications = com.example.utils.Client.getNotifications(studentId);
            notificationsList.addAll(notifications);
        } catch (Exception e) {
            System.err.println("Failed to load notifications: " + e.getMessage());
        }
    }

    @FXML
    private void handleGoBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/minimoodle/student-dashboard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) goBackButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Dashboard - " + com.example.utils.Client.getStudentName(studentId));

            com.example.minimoodle.StudentDashboardController controller = loader.getController();
            controller.setStudentId(studentId);
            controller.initialize(studentId);

        } catch (Exception e) {
            System.err.println("Error navigating back: " + e.getMessage());
        }
    }

    public void refreshNotifications() {
        notificationsList.clear();
        loadDummyNotifications();
    }

    public void setStudentName(String studentName) {
        studentNameRibbonLabel.setText(studentName);
    }
}
