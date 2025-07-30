package com.example.servicecodes;

public class Notification {

    private String courseName;
    private String message;
    private String timestamp;

    public Notification(String courseName, String message, String timestamp) {
        this.courseName = courseName;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Course: " + courseName + ", Message: " + message + ", Time: " + timestamp;
    }
}
