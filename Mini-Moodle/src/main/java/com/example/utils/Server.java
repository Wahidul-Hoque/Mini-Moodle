package com.example.utils;
import java.io.*;
import java.net.*;
import java.sql.*;
import com.example.servicecodes.TeacherLoginService;
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
        } catch (Exception  e) {
            System.err.println("Error handling client request: " + e.getMessage());
        }
    }
}
