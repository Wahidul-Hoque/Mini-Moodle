package com.example.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.example.servicecodes.CourseInfo;
import com.example.servicecodes.StudentInfo;

public class Client {
    private static final String SERVER_ADDRESS = "127.0.0.1";  // Server address (localhost for now)
    private static final int SERVER_PORT = 12345;  // Server port

    // Method to send login request to server
    public static int sendLoginRequest(String username, String password) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            // Send the action (LOGIN)
            dataOut.writeUTF("LOGIN");

            // Send the username and password
            dataOut.writeUTF(username);
            dataOut.writeUTF(password);

            // Get the response (teacherId or -1)
            int loginResponse = dataIn.readInt();
            System.out.println("Login Response: " + loginResponse);
            return loginResponse;

            // Return the teacherId (or -1 if invalid)
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return -1;  // Error case
        }
    }

    public static boolean sendAdminLoginRequest(String username, String password) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            // Send the action (ADMIN_LOGIN)
            dataOut.writeUTF("ADMIN_LOGIN");

            // Send the username and password
            dataOut.writeUTF(username);
            dataOut.writeUTF(password);

            return dataIn.readBoolean();  // Return the adminId (or -1 if invalid)
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return false;  // Error case
        }
    }

    public static int sendStudentLoginRequest(String username, String password) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            dataOut.writeUTF("STUDENT_LOGIN");

            // Send the username and password
            dataOut.writeUTF(username);
            dataOut.writeUTF(password);

            return dataIn.readInt();  // Return the adminId (or -1 if invalid)
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return -1;  // Error case
        }
    }

    public static String getTeacherName(int teacherId) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            // Send the action (GET_COURSE_ID)
            dataOut.writeUTF("GET_TEACHER_NAME");

            // Send teacher ID
            dataOut.writeInt(teacherId);

            // Get the course ID from the server
            return dataIn.readUTF();  // Return course ID
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return null;  // Error case
        }
    }

    public static String getStudentName(int studentId) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            // Send the action (GET_STUDENT_NAME)
            dataOut.writeUTF("GET_STUDENT_NAME");

            // Send student ID
            dataOut.writeInt(studentId);

            // Get the student name from the server
            return dataIn.readUTF();  // Return student name
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return null;  // Error case
        }
    }

            

    public static String getCourseIdForTeacher(int teacherId) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            // Send the action (GET_COURSE_ID)
            dataOut.writeUTF("GET_COURSE_ID");

            // Send teacher ID
            dataOut.writeInt(teacherId);

            // Get the course ID from the server
            return dataIn.readUTF();  // Return course ID
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return null;  // Error case
        }
    }

    public static String getCourseName(String courseId) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            // Send the action (GET_COURSE_NAME)
            dataOut.writeUTF("GET_COURSE_NAME");

            // Send the course ID
            dataOut.writeUTF(courseId);

            // Get the course name from the server
            return dataIn.readUTF();  // Return course name
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return null;  // Error case
        }
    }

    public static List<StudentInfo> getEnrolledStudents(String courseId) {
        List<StudentInfo> students = new ArrayList<>();
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            // Send the action type to get approved students
            dataOut.writeUTF("GET_APPROVED_STUDENTS");

            // Send the courseId to the server
            dataOut.writeUTF(courseId);

            // Receive the number of approved students
            int studentCount = dataIn.readInt();

            // Receive each student's details
            for (int i = 0; i < studentCount; i++) {
                int studentId = dataIn.readInt();
                String studentName = dataIn.readUTF();
                String studentEmail = dataIn.readUTF();
                String studentGrade = dataIn.readUTF();

                students.add(new StudentInfo(studentId, studentName, studentEmail, studentGrade));
            }

        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
        }

        return students;
    }

    public static int getEnrolledStudentCount(String courseId) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            // Send the action type to get approved students
            dataOut.writeUTF("GET_APPROVED_STUDENT_COUNT");

            // Send the courseId to the server
            dataOut.writeUTF(courseId);

            // Receive the count of enrolled students
            return dataIn.readInt();

        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return -1;  // Error case
        }
    }

    public static List<StudentInfo> getPendingStudents(String courseId) {
        List<StudentInfo> students = new ArrayList<>();
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            System.out.println("chilam");

            // Send the action type to get approved students
            dataOut.writeUTF("GET_PENDING_STUDENTS");

            // Send the courseId to the server
            dataOut.writeUTF(courseId);

            // Receive the number of approved students
            int studentCount = dataIn.readInt();

            // Receive each student's details
            for (int i = 0; i < studentCount; i++) {
                int studentId = dataIn.readInt();
                String studentName = dataIn.readUTF();
                String studentEmail = dataIn.readUTF();
                String studentGrade = dataIn.readUTF();

                students.add(new StudentInfo(studentId, studentName, studentEmail, studentGrade));
            }

        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
        }

        return students;
    }

    public static List<CourseInfo> getEnrolledCoursesForStudent(int studentId) {
        List<CourseInfo> enrolledCourses = new ArrayList<>();
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            // Send the action (GET_ENROLLED_COURSES)
            dataOut.writeUTF("GET_ENROLLED_COURSES");

            // Send the student ID
            dataOut.writeInt(studentId);

            // Get the number of courses
            int courseCount = dataIn.readInt();

            // Read the course details (courseId, courseTitle, grade) from the server
            for (int i = 0; i < courseCount; i++) {
                int courseId = dataIn.readInt();
                String courseTitle = dataIn.readUTF();
                String grade = dataIn.readUTF();

                // Create a CourseInfo object for each course
                enrolledCourses.add(new CourseInfo(courseId, courseTitle, grade));
            }

        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
        }

        return enrolledCourses;
    }

    public static void main(String[] args) {
        System.out.println(getCourseIdForTeacher(2));
    }


}