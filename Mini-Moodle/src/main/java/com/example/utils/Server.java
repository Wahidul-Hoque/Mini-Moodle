package com.example.utils;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import com.example.servicecodes.*;

public class Server {
    private static final int PORT = 12345;
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started, waiting for clients...");

            // Continuously accept client connections
            while (true) {
                Socket clientSocket = serverSocket.accept();  // Accept client connection
                new ClientHandler(clientSocket).start();  // Handle client in a new thread
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }
}
class ClientHandler extends Thread {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
                InputStream input = clientSocket.getInputStream();
                OutputStream output = clientSocket.getOutputStream();
                DataInputStream dataIn = new DataInputStream(input);
                DataOutputStream dataOut = new DataOutputStream(output)
        ) {
            // Read request type (e.g., LOGIN)
            String action = dataIn.readUTF();

            // Process different actions (e.g., login)
            if ("LOGIN".equals(action)) {
                String username = dataIn.readUTF();
                String password = dataIn.readUTF();

                // Authenticate using the database
                int teacherId = TeacherLoginService.validateTeacherLogin(username, password);

                // Send response back to client (teacherId or -1 if invalid)
                dataOut.writeInt(teacherId);
            }
            else if ("ADMIN_LOGIN".equals(action)) {
                String username = dataIn.readUTF();
                String password = dataIn.readUTF();

                boolean adminId = AdminLoginService.validateAdminLogin(username, password);
                dataOut.writeBoolean(adminId);
            }
            else if ("STUDENT_LOGIN".equals(action)) {
                String username = dataIn.readUTF();
                String password = dataIn.readUTF();

                int studentId = StudentLoginService.validateStudentLogin(username, password);
                dataOut.writeInt(studentId);
            }
            else if( "GET_COURSE_ID".equals(action)) {
                int teacherId = dataIn.readInt();
                String courseId = CourseService.getCourseIdForTeacher(teacherId);
                dataOut.writeUTF(courseId);
            }
            else if( "GET_COURSE_NAME".equals(action)) {
                String courseId = dataIn.readUTF();
                String courseName= CourseService.getCourseName(courseId);
                dataOut.writeUTF(courseName);
            }
            else if( "GET_TEACHER_NAME".equals(action)) {
                int teacherId = dataIn.readInt();
                String name = CourseService.getTeacherName(teacherId);
                dataOut.writeUTF(name);
            }
            else if ("GET_APPROVED_STUDENTS".equals(action)){
                String courseId = dataIn.readUTF();
                List<StudentInfo> students = CourseService.getEnrolledStudents(Integer.parseInt(courseId));
                dataOut.writeInt(students.size());
                for (StudentInfo student : students) {
                    dataOut.writeInt(student.getId());
                    dataOut.writeUTF(student.getName());
                    dataOut.writeUTF(student.getEmail());
                    dataOut.writeUTF(student.getGrade());
                }
            }

        } catch (Exception  e) {
            System.err.println("Error handling client request: " + e.getMessage());
        }
    }
}