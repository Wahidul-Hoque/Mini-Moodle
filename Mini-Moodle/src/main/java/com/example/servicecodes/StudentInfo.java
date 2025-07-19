package com.example.servicecodes;

public class StudentInfo{
    private int id;
    private String name;
    private String email;
    private String grade;
    public StudentInfo(int id,String name,String email,String grade){
        this.id = id;
        this.name = name;
        this.email = email;
        this.grade = grade; // Default grade
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getGrade() {
        return grade;
    }
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Email: " + email + ", Grade: " + grade;
    }
}