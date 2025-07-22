package com.example.utils;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

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
            String action = dataIn.readUTF();

            if ("LOGIN".equals(action)) {
                String username = dataIn.readUTF();
                String password = dataIn.readUTF();
                int teacherId = TeacherLoginService.validateTeacherLogin(username, password);
                dataOut.writeInt(teacherId);
            }
            else if("ADMIN_LOGIN".equals(action)){
                String username = dataIn.readUTF();
                String password = dataIn.readUTF();
                boolean adminId = AdminLoginService.validateAdminLogin(username, password);
                dataOut.writeBoolean(adminId);
            }
            else if("STUDENT_LOGIN".equals(action)){
                String username = dataIn.readUTF();
                String password = dataIn.readUTF();
                int studentId = StudentLoginService.validateStudentLogin(username, password);
                dataOut.writeInt(studentId);
            }
            else if("REGISTER_STUDENT".equals(action)){
                String username = dataIn.readUTF();
                String name = dataIn.readUTF();
                String password = dataIn.readUTF();
                String email = dataIn.readUTF();
                boolean registrationSuccessful = StudentRegisterService.registerStudent(username, name, password, email);
                dataOut.writeBoolean(registrationSuccessful);
            }
            else if( "GET_COURSE_ID".equals(action)){
                int teacherId = dataIn.readInt();
                String courseId = CourseService.getCourseIdForTeacher(teacherId);
                dataOut.writeUTF(courseId);
            }
            else if( "GET_COURSE_NAME".equals(action)){
                String courseId = dataIn.readUTF();
                String courseName= CourseService.getCourseName(courseId);
                dataOut.writeUTF(courseName);
            }
            else if( "GET_COURSE_DESCRIPTION".equals(action)){
                String courseId = dataIn.readUTF();
                String courseDescription= CourseService.getCourseDescription(courseId);
                dataOut.writeUTF(courseDescription);
            }
            else if("SET_STUDENT_GRADE".equals(action)){
                System.out.println(action);
                int studentId = dataIn.readInt();
                int courseId = dataIn.readInt();
                String grade = dataIn.readUTF();
                boolean result = CourseService.setStudentGrade(studentId, courseId, grade);
                System.out.println("Server received grade update request, result: " + result);
                dataOut.writeBoolean(result);
            }

            else if( "GET_TEACHER_NAME".equals(action)){
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
            else if ("GET_APPROVED_STUDENT_COUNT".equals(action)){
                String courseId = dataIn.readUTF();
                int count = CourseService.getEnrolledStudentCount(Integer.parseInt(courseId));
                dataOut.writeInt(count);
            }
            else if ("GET_PENDING_STUDENTS".equals(action)){
                String courseId = dataIn.readUTF();
                List<StudentInfo> students = CourseService.getPendingStudents(Integer.parseInt(courseId));
                dataOut.writeInt(students.size());
                for (StudentInfo student : students) {
                    dataOut.writeInt(student.getId());
                    dataOut.writeUTF(student.getName());
                    dataOut.writeUTF(student.getEmail());
                    dataOut.writeUTF(student.getGrade());
                }
            }
            else if("GET_STUDENT_NAME".equals(action)) {
                int studentId = dataIn.readInt();
                String studentName = CourseService.getStudentName(studentId);
                dataOut.writeUTF(studentName);
            }
            else if("GET_ENROLLED_COURSES".equals(action)) {
                int studentId = dataIn.readInt();
                List<CourseInfo> Courses = studentService.getEnrolledCoursesForStudent(studentId);
                dataOut.writeInt(Courses.size());
                for (CourseInfo course : Courses) {
                    dataOut.writeInt(course.getCourseId());
                    dataOut.writeUTF(course.getCourseTitle());
                    dataOut.writeUTF(course.getGrade());
                }
            }

        } catch (Exception  e) {
            System.err.println("Error handling client request: " + e.getMessage());
        }
    }
}