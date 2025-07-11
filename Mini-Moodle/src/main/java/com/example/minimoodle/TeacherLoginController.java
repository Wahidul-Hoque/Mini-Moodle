package com.example.minimoodle;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.awt.*;

public class TeacherLoginController {
    @FXML
    private TextField teacherLoginIdBox;

    @FXML
    private PasswordField teacherLoginPasswordBox;

    @FXML
    private CheckBox teacherLoginPasswordCheckBox;

    @FXML
    private Button teacherLoginButton;

    private boolean showPassword = false;

    private String enteredId;
    private String enteredPassword;

    @FXML
    private void toggleTeacherLoginPasswordVisibility()
    {
        if(showPassword) showPassword = false;
        else showPassword = true;
    }


}
