package com.example.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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

    public static int sendAdminLoginRequest(String username, String password) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataIn = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream())) {

            // Send the action (ADMIN_LOGIN)
            dataOut.writeUTF("ADMIN_LOGIN");

            // Send the username and password
            dataOut.writeUTF(username);
            dataOut.writeUTF(password);

            return dataIn.readInt();  // Return the adminId (or -1 if invalid)
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return -1;  // Error case
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

    public static void main(String[] args) {
        System.out.println(getCourseIdForTeacher(2));
    }


}