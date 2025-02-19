package ca.dal.cs.csci3130.a3.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtility {
    protected static String makeHexString(byte[] messageDigest) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) hexString.append(Integer.toHexString(0xFF & b));
        return hexString.toString();
    }

    public static String makeHash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte[] messageDigest = digest.digest();
            return makeHexString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
