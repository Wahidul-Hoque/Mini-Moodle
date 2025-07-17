package com.example.minimoodle.teacherfunctionalities;

import com.example.servicecodes.CourseService;

import javafx.beans.property.SimpleStringProperty;

/**
 * To Wahid:
 * I didn't wanna change much of your code so I implemented my own class for this functionality.
 */

public class StudentInfo {

    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty email;
    private final SimpleStringProperty grade;

    public StudentInfo(String id, String name, String email, String grade) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.grade = new SimpleStringProperty(grade);
    }

    public StudentInfo(CourseService.StudentInfo studentInfo) {
        this.id = new SimpleStringProperty(Integer.toString(studentInfo.getId()));
        this.name = new SimpleStringProperty(studentInfo.getName());
        this.email = new SimpleStringProperty(studentInfo.getEmail());
        this.grade = new SimpleStringProperty(studentInfo.getGrade());
    }

    public String getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getGrade() {
        return grade.get();
    }

    public void setGrade(String grade) {
        this.grade.set(grade);
    }
}
