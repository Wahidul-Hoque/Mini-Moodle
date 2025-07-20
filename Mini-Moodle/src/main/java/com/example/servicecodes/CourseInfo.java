package com.example.servicecodes;

public class CourseInfo {
    private int courseId;
    private String courseTitle;
    private String grade;

    public CourseInfo(int courseId, String courseTitle, String grade) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.grade = grade;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseId + ", Title: " + courseTitle + ", Grade: " + grade;
    }
}

