package com.example.servicecodes;

public class CourseInfoAdmin {
    private int courseId;
    private String courseTitle;
    private String courseDescription;
    private String teacherName;

    public CourseInfoAdmin(int courseId, String courseTitle,String courseDescription, String teacherName) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.teacherName = teacherName;
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
    public String getTeacherName() {
        return teacherName;
    }
}
