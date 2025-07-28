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

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
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
            else if ("GET_TEACHER_PROFILE".equals(action)) {
                int teacherId = dataIn.readInt();
                TeacherInfo profile = CourseService.getTeacherProfile(teacherId);

                if (profile != null) {
                    dataOut.writeInt(profile.getTeacherId());
                    dataOut.writeUTF(profile.getName());
                    dataOut.writeUTF(profile.getEmail());
                    dataOut.writeUTF(profile.getUsername());
                    dataOut.writeUTF(profile.getCourse());
                } else {
                    dataOut.writeUTF("Error: Teacher not found");
                }
            }
            else if ("GET_STUDENT_DETAILS".equals(action)) {
                int studentId = dataIn.readInt();
                StudentInfo details = studentService.getStudentDetails(studentId);

                if (details != null) {
                    dataOut.writeInt(details.getId());
                    dataOut.writeUTF(details.getName());
                    dataOut.writeUTF(details.getEmail());
                    dataOut.writeUTF(details.getGrade());
                    dataOut.writeUTF(details.getUsername());
                } else {
                    dataOut.writeUTF("Error: Student not found");
                }
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
            else if("CHANGE_PASSWORD_TEACHER".equals(action)){
                int teacherId = dataIn.readInt();
                String newPassword = dataIn.readUTF();
                boolean success = CourseService.changeTeacherPassword(teacherId, newPassword);
                dataOut.writeBoolean(success);
            }
            else if("CHANGE_PASSWORD_ADMIN".equals(action)){
                int teacherId = dataIn.readInt();
                String newPassword = dataIn.readUTF();
                boolean success = AdminService.changeAdminPassword(teacherId, newPassword);
                dataOut.writeBoolean(success);
            }
            else if("CHANGE_PASSWORD_STUDENT".equals(action)){
                int teacherId = dataIn.readInt();
                String newPassword = dataIn.readUTF();
                boolean success = studentService.changeStudentPassword(teacherId, newPassword);
                dataOut.writeBoolean(success);
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
                    dataOut.writeUTF(course.getCourseDescription());
                    dataOut.writeUTF(course.getGrade());
                }
            }
            else if("GET_UNREGISTERED_COURSES".equals(action)) {
                int studentId = dataIn.readInt();
                List<CourseInfo> Courses = studentService.getUnregisteredCoursesForStudent(studentId);
                dataOut.writeInt(Courses.size());
                for (CourseInfo course : Courses) {
                    dataOut.writeInt(course.getCourseId());
                    dataOut.writeUTF(course.getCourseTitle());
                    dataOut.writeUTF(course.getCourseDescription());
                    dataOut.writeUTF(course.getGrade());
                }
            }
            else if("REQUEST_ENROLLMENT".equals(action)){
                int studentId = dataIn.readInt();
                String courseTitle = dataIn.readUTF();
                boolean success = EnrollmentService.requestEnrollment(studentId, courseTitle);
                dataOut.writeBoolean(success);
            }
            else if("APPROVE_ENROLLMENT".equals(action)){
                int studentId = dataIn.readInt();
                int courseId = dataIn.readInt();
                boolean success = EnrollmentService.approveEnrollment(studentId, courseId);
                dataOut.writeBoolean(success);
            }
            else if("REJECT_ENROLLMENT".equals(action)){
                int studentId = dataIn.readInt();
                int courseId = dataIn.readInt();
                boolean success = EnrollmentService.rejectEnrollment(studentId, courseId);
                dataOut.writeBoolean(success);
            }
            else if("GET_TOTAL_COURSES".equals(action)){
                int count= AdminService.getTotalCourseCount();
                dataOut.writeInt(count);
            }
            else if("GET_TOTAL_STUDENTS".equals(action)){
                int count= AdminService.getTotalStudentCount()  ;
                dataOut.writeInt(count);
            }
            else if("GET_TOTAL_TEACHERS".equals(action)){
                int count= AdminService.getTotalTeacherCount();
                dataOut.writeInt(count);
            }
            else if("GET_ALL_COURSES".equals(action)){
                List<CourseInfoAdmin> allCourses = AdminService.getAllCourses();
                dataOut.writeInt(allCourses.size());

                for (CourseInfoAdmin course : allCourses) {
                    dataOut.writeInt(course.getCourseId());
                    dataOut.writeUTF(course.getCourseTitle());
                    dataOut.writeUTF(course.getCourseDescription());
                    dataOut.writeUTF(course.getTeacherName());
                }
            }
            else if("GET_ALL_STUDENTS".equals(action)) {
                List<StudentInfo> students = AdminService.getAllStudents();
                dataOut.writeInt(students.size());

                for (StudentInfo student : students) {
                    dataOut.writeInt(student.getId());
                    dataOut.writeUTF(student.getName());
                    dataOut.writeUTF(student.getEmail());
                }
            }
            else if ("GET_ALL_TEACHERS".equals(action)) {
                List<TeacherInfo> teachers = AdminService.getAllTeachers();
                dataOut.writeInt(teachers.size());

                for (TeacherInfo teacher : teachers) {
                    dataOut.writeInt(teacher.getTeacherId());
                    dataOut.writeUTF(teacher.getName());
                    dataOut.writeUTF(teacher.getEmail());
                    dataOut.writeUTF(teacher.getCourse());
                }
            }
            else if("ADD_TEACHER".equals(action)) {
                String username = dataIn.readUTF();
                String password = dataIn.readUTF();
                String name = dataIn.readUTF();
                String email = dataIn.readUTF();
                boolean isAdded = AdminService.addTeacher(username, password, name, email);
                dataOut.writeBoolean(isAdded);
            }
            else if ("ADD_COURSE".equals(action)) {
                String title = dataIn.readUTF();
                String description = dataIn.readUTF();
                String teacherName = dataIn.readUTF();
                boolean isAdded = AdminService.addCourse(title, description, teacherName);
                dataOut.writeBoolean(isAdded);
            }
        } catch (Exception  e) {
            System.err.println("Error handling client request: " + e.getMessage());
        }
    }
}