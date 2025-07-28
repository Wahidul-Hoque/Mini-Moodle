package com.example.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.example.servicecodes.*;

public class Client {
    private static String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 12345;
    public Client(String serverAddress) {
        this.SERVER_ADDRESS = serverAddress;
    }
    public static int sendLoginRequest(String username, String password) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            dataOut.writeUTF("LOGIN");
            dataOut.writeUTF(username);
            dataOut.writeUTF(password);

            int loginResponse = dataIn.readInt();
            System.out.println("Login Response: " + loginResponse);
            return loginResponse;

        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return -1;
        }
    }

    public static boolean sendAdminLoginRequest(String username, String password) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            dataOut.writeUTF("ADMIN_LOGIN");
            dataOut.writeUTF(username);
            dataOut.writeUTF(password);

            return dataIn.readBoolean();
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return false;
        }
    }

    public static int sendStudentLoginRequest(String username, String password) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            dataOut.writeUTF("STUDENT_LOGIN");

            dataOut.writeUTF(username);
            dataOut.writeUTF(password);

            return dataIn.readInt();
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return -1;
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

    public static boolean changeTeacherPassword(int teacherId, String newPassword) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            dataOut.writeUTF("CHANGE_PASSWORD_TEACHER");
            dataOut.writeInt(teacherId);
            dataOut.writeUTF(newPassword);

            return dataIn.readBoolean();
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return false;
        }
    }

    public static boolean changeAdminPassword(int teacherId, String newPassword) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            dataOut.writeUTF("CHANGE_PASSWORD_ADMIN");
            dataOut.writeInt(teacherId);
            dataOut.writeUTF(newPassword);

            return dataIn.readBoolean();
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return false;
        }
    }

    public static boolean changeStudentPassword(int teacherId, String newPassword) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            dataOut.writeUTF("CHANGE_PASSWORD_STUDENT");
            dataOut.writeInt(teacherId);
            dataOut.writeUTF(newPassword);

            return dataIn.readBoolean();
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return false;
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

    public static List<CourseInfo> getUnregisteredCoursesForStudent(int studentId) {
        List<CourseInfo> unregCourses = new ArrayList<>();
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            dataOut.writeUTF("GET_UNREGISTERED_COURSES");
            dataOut.writeInt(studentId);

            int courseCount = dataIn.readInt();
            for (int i = 0; i < courseCount; i++) {
                int courseId = dataIn.readInt();
                String courseTitle = dataIn.readUTF();
                String courseDescription = dataIn.readUTF();
                String grade = dataIn.readUTF();
                unregCourses.add(new CourseInfo(courseId, courseTitle,courseDescription, grade));
            }

        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
        }

        return unregCourses;
    }

    public static boolean requestEnrollment(int studentId, String courseTitle) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            dataOut.writeUTF("REQUEST_ENROLLMENT");

            dataOut.writeInt(studentId);
            dataOut.writeUTF(courseTitle);
            return dataIn.readBoolean();
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return false;
        }
    }

    public static boolean approveEnrollment(int studentId, int courseId) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {
            dataOut.writeUTF("APPROVE_ENROLLMENT");
            dataOut.writeInt(studentId);
            dataOut.writeInt(courseId);
            return dataIn.readBoolean();
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return false;
        }
    }

    public static boolean rejectEnrollment(int studentId, int courseId) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {
            dataOut.writeUTF("REJECT_ENROLLMENT");
            dataOut.writeInt(studentId);
            dataOut.writeInt(courseId);
            return dataIn.readBoolean();
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return false;
        }
    }

    public static int getTotalCourseCount() {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {
            dataOut.writeUTF("GET_TOTAL_COURSES");
            return dataIn.readInt();
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return -1;
        }
    }

    public static int getTotalStudentCount() {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {
            dataOut.writeUTF("GET_TOTAL_STUDENTS");
            return dataIn.readInt();
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return -1;
        }
    }

    public static int getTotalTeacherCount() {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {
            dataOut.writeUTF("GET_TOTAL_TEACHERS");
            return dataIn.readInt();
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return -1;
        }
    }

    public static List<CourseInfoAdmin> getAllCourses() {
        List<CourseInfoAdmin> courses = new ArrayList<>();
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            dataOut.writeUTF("GET_ALL_COURSES");
            int numberOfCourses = dataIn.readInt();

            for (int i = 0; i < numberOfCourses; i++) {
                int courseId = dataIn.readInt();
                String courseTitle = dataIn.readUTF();
                String courseDescription = dataIn.readUTF();
                String teacherName = dataIn.readUTF();

                CourseInfoAdmin course = new CourseInfoAdmin(courseId, courseTitle, courseDescription, teacherName);
                courses.add(course);
            }
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
        }
        return courses;
    }

    public static List<StudentInfo> getAllStudents() {
        List<StudentInfo> students = new ArrayList<>();
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            dataOut.writeUTF("GET_ALL_STUDENTS");
            int numberOfStudents = dataIn.readInt();

            for (int i = 0; i < numberOfStudents; i++) {
                int studentId = dataIn.readInt();
                String studentName = dataIn.readUTF();
                String studentEmail = dataIn.readUTF();
                StudentInfo student = new StudentInfo(studentId, studentName, studentEmail);
                students.add(student);
            }
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
        }
        return students;
    }

    public static List<TeacherInfo> getAllTeachers() {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            dataOut.writeUTF("GET_ALL_TEACHERS");

            int teacherCount = dataIn.readInt();

            List<TeacherInfo> teacherList = new ArrayList<>();

            for (int i = 0; i < teacherCount; i++) {
                int teacherId = dataIn.readInt();
                String name = dataIn.readUTF();
                String email = dataIn.readUTF();
                String course = dataIn.readUTF();

                TeacherInfo teacher = new TeacherInfo(teacherId, name, email, course);
                teacherList.add(teacher);
            }
            return teacherList;
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return null;
        }
    }


    public static boolean addTeacher(String username, String password, String name, String email) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            dataOut.writeUTF("ADD_TEACHER");
            dataOut.writeUTF(username);
            dataOut.writeUTF(password);
            dataOut.writeUTF(name);
            dataOut.writeUTF(email);

            return dataIn.readBoolean();
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return false;
        }
    }

    public static boolean addCourse(String title, String description, String teacherUsername) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            dataOut.writeUTF("ADD_COURSE");
            dataOut.writeUTF(title);
            dataOut.writeUTF(description);
            dataOut.writeUTF(teacherUsername);

            return dataIn.readBoolean();
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return false;
        }
    }


    public static void main(String[] args) {
        System.out.println(getCourseIdForTeacher(2));
        //boolean a=addTeacher("f","f","f","f");
        List<TeacherInfo> teachers = getAllTeachers();
        for (TeacherInfo teacher : teachers) {
            System.out.println(teacher);

        }
    }


}