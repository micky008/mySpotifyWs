package com.msc.myspotify.helper;

import java.security.MessageDigest;

/**
 *
 * @author Michael
 */
public class PasswordHelper {

    public static String convertPassword(String password) {
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            return byteArrayToHexString(crypt.digest());
        } catch (Exception e) {
        }
        return null;
    }

    private static String byteArrayToHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

}
