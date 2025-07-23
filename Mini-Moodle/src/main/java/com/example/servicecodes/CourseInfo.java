package com.example.servicecodes;

public class CourseInfo {
    private int courseId;
    private String courseTitle;
    private String courseDescription;
    private String grade;

    public CourseInfo(int courseId, String courseTitle,String courseDescription, String grade) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.grade = grade;
    }
    public CourseInfo(int courseId, String courseTitle,String courseDescription) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.grade = "not set";
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }
    public String getCourseDescription() {
        return courseDescription;
    }
    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Course ID: " + courseId + ", Title: " + courseTitle + ", Grade: " + grade;
    }
}

