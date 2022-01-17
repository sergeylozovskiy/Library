package com.library.util;

import java.security.MessageDigest;

public class PasswordEncryption {

    public static String hashPassword(String password) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte bytes : digest) {
                stringBuilder.append(String.format("%02x", bytes & 0xff));
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }

}
