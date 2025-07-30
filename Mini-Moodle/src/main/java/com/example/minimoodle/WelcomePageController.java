package com.example.minimoodle;

import com.example.utils.Client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class WelcomePageController {

    @FXML
    private ToggleGroup roleGroup;

    @FXML
    private RadioButton welcomePageAdminRadioButton;

    @FXML
    private RadioButton welcomePageTeacherRadioButton;

    @FXML
    private RadioButton welcomePageStudentRadioButton;

    @FXML
    private Button welcomePageContinueButton;

    @FXML
    private Button connectToServer;

    @FXML
    private void handleWelcomePageContinueButton() {
        RadioButton selectedRole = (RadioButton) roleGroup.getSelectedToggle();

        if (selectedRole == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a role");
            alert.showAndWait();
            return;
        }

        System.out.println(selectedRole.getText() + " selected");

        String role = selectedRole.getText();
        String fxmlFile;
        if (role.equals("Admin")) {
            fxmlFile = "admin-login.fxml";
        } else if (role.equals("Teacher")) {
            fxmlFile = "teacher-login.fxml";
        } else if (role.equals("Student")) {
            fxmlFile = "student-login.fxml";
        } else {
            fxmlFile = "welcome-page.fxml";
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Stage stage = (Stage) welcomePageContinueButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(role + " login");
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
    public void handleConnectToServer() {
        javafx.scene.control.Dialog<String> dialog = new javafx.scene.control.Dialog<>();
        dialog.setTitle("Connect to Server");
        dialog.setHeaderText("Enter the server IP address:");

        javafx.scene.control.ButtonType connectButtonType = new javafx.scene.control.ButtonType("Connect", javafx.scene.control.ButtonBar.ButtonData.OK_DONE);
        javafx.scene.control.ButtonType cancelButtonType = new javafx.scene.control.ButtonType("Cancel", javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(connectButtonType, cancelButtonType);

        javafx.scene.control.TextField ipField = new javafx.scene.control.TextField();
        ipField.setPromptText("127.0.0.1");

        javafx.scene.layout.GridPane grid = new javafx.scene.layout.GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new javafx.scene.control.Label("IP Address:"), 0, 0);
        grid.add(ipField, 1, 0);

        dialog.getDialogPane().setContent(grid);

        javafx.application.Platform.runLater(ipField::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == connectButtonType) {
                return ipField.getText();
            }
            return "127.0.0.1";
        });

        java.util.Optional<String> result = dialog.showAndWait();

        result.ifPresent(ip -> {
            new Thread(() -> {
                boolean reachable = false;
                try (java.net.Socket testSocket = new java.net.Socket()) {
                    testSocket.connect(new java.net.InetSocketAddress(ip, 12345), 10000); // 2s timeout
                    reachable = true;
                } catch (Exception e) {
                    reachable = false;
                }
                final boolean finalReachable = reachable;
                javafx.application.Platform.runLater(() -> {
                    if (finalReachable) {
                        new Client(ip.equals("") ? "127.0.0.1" : ip);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Connection Success");
                        alert.setHeaderText(null);
                        alert.setContentText("Connected to server at: " + (ip.equals("") ? "127.0.0.1" : ip));
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Connection Failed");
                        alert.setHeaderText(null);
                        alert.setContentText("Could not connect to server at: " + (ip.equals("") ? "127.0.0.1" : ip));
                        alert.showAndWait();
                    }
                });
            }).start();
        });
    }
}
