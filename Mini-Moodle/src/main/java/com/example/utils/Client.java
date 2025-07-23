package com.example.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.example.servicecodes.CourseInfo;
import com.example.servicecodes.CourseService;
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

    public static boolean sendStudentRegistrationRequest(String username, String name, String password, String email) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {
            dataOut.writeUTF("REGISTER_STUDENT");
            dataOut.writeUTF(username);
            dataOut.writeUTF(name);
            dataOut.writeUTF(password);
            dataOut.writeUTF(email);

            return dataIn.readBoolean();
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return false;
        }
    }

    public static String getTeacherName(int teacherId) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {
            dataOut.writeUTF("GET_TEACHER_NAME");
            dataOut.writeInt(teacherId);
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

            dataOut.writeUTF("GET_STUDENT_NAME");

            dataOut.writeInt(studentId);

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

            dataOut.writeUTF("GET_COURSE_ID");

            dataOut.writeInt(teacherId);

            return dataIn.readUTF();
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return null;  // Error case
        }
    }

    public static String getCourseName(String courseId) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            dataOut.writeUTF("GET_COURSE_NAME");

            dataOut.writeUTF(courseId);
            return dataIn.readUTF();
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return null;
        }
    }

    public static boolean setStudentGrade(int studentId, int courseId, String grade) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {
            dataOut.writeUTF("SET_STUDENT_GRADE");
            dataOut.writeInt(studentId);
            dataOut.writeInt(courseId);
            dataOut.writeUTF(grade);
            boolean answer = dataIn.readBoolean();
            System.out.println("Received response from server: " + answer);
            return answer;

        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return false;
        }
    }

    public static String getCourseDescription(String courseId) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            dataOut.writeUTF("GET_COURSE_DESCRIPTION");

            dataOut.writeUTF(courseId);
            return dataIn.readUTF();
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return null;
        }
    }

    public static List<StudentInfo> getEnrolledStudents(String courseId) {
        List<StudentInfo> students = new ArrayList<>();
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            dataOut.writeUTF("GET_APPROVED_STUDENTS");
            dataOut.writeUTF(courseId);
            int studentCount = dataIn.readInt();
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

            dataOut.writeUTF("GET_APPROVED_STUDENT_COUNT");
            dataOut.writeUTF(courseId);
            return dataIn.readInt();

        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return -1;
        }
    }

    public static List<StudentInfo> getPendingStudents(String courseId) {
        List<StudentInfo> students = new ArrayList<>();
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            dataOut.writeUTF("GET_PENDING_STUDENTS");
            dataOut.writeUTF(courseId);
            int studentCount = dataIn.readInt();
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

            dataOut.writeUTF("GET_ENROLLED_COURSES");
            dataOut.writeInt(studentId);

            int courseCount = dataIn.readInt();
            for (int i = 0; i < courseCount; i++) {
                int courseId = dataIn.readInt();
                String courseTitle = dataIn.readUTF();
                String courseDescription = dataIn.readUTF();
                String grade = dataIn.readUTF();


                enrolledCourses.add(new CourseInfo(courseId, courseTitle,courseDescription, grade));
            }

        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
        }

        return enrolledCourses;
    }

    public static void main(String[] args) {
        System.out.println(getCourseIdForTeacher(2));
        System.out.println(CourseService.setStudentGrade(7,2,"D"));
    }


}