package com.example.utils;

import java.io.*;
import java.net.*;
import java.util.Scanner;

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
            return dataIn.readInt();  // Return the teacherId (or -1 if invalid)
        } catch (IOException e) {
            System.err.println("Error communicating with server: " + e.getMessage());
            return -1;  // Error case
        }
    }


}