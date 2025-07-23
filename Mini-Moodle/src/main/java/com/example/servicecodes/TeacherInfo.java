package com.example.servicecodes;

public class TeacherInfo {
    private int teacherId;
    private String name;
    private String email;
    private String course;

    public TeacherInfo(int teacherId, String name, String email, String course) {
        this.teacherId = teacherId;
        this.name = name;
        this.email = email;
        this.course = course;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "Teacher ID: " + teacherId + ", Name: " + name + ", Email: " + email + ", Course: " + course;
    }
}

