package com.example.servicecodes;

public class StudentInfo{
    private int id;
    private String name;
    private String email;
    private String grade;
    private String username;
    public StudentInfo(int id,String name,String email,String grade){
        this.id = id;
        this.name = name;
        this.email = email;
        this.grade = grade;
        this.username = "user";
    }
    public StudentInfo(int id,String name,String email){
        this.id = id;
        this.name = name;
        this.email = email;
        this.grade = "default";
        this.username = "user";
    }
    public StudentInfo(int id,String name ,String email,String grade,String username){
        this.id = id;
        this.name = name;
        this.email = email;
        this.grade = grade;
        this.username = username;
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
    public String getUsername() {return username;}
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Email: " + email + ", Grade: " + grade+",  Username: " + username;
    }
}