package com.example.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public static boolean validatePassword(String enteredPassword, String storedHash) {
        String enteredPasswordHash = hashPassword(enteredPassword);
        return enteredPasswordHash.equals(storedHash);
    }

    public static void main(String[] args) {
        String teacherPassword = "4";
        System.out.println("Teacher Hashed Password: " + hashPassword(teacherPassword));
    }
}
