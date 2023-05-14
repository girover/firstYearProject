package hashingUtilities;

/**
 * This is a utility class providing functionalities for hashing and verifying strings. 
 *
 * It includes methods for hashing a string with a salt, verifying a string against a given hash, 
 * and generating a secure salt for use in the hashing process.
 *
 * @version 1.0
 *
 * @author Rasmus Kortsen
 *       - Email: Rasmus.kortsen1@gmail.com
 *       - Github: https://github.com/rasm685p
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class HashingUtilities {
    public static String hash(String text) {
        return hash(text, generateSalt());
    }

    public static boolean verify(String text, String hash) {
        if (hash.length() < 24) {
            throw new IllegalArgumentException("Hash length is less than 24 characters");
        }
        String salt = hash.substring(hash.length() - 24);

        return hash(text, salt).equals(hash);
    }

    private static String hash(String text, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] hashedString = md.digest(text.getBytes());
            return Base64.getEncoder().encodeToString(hashedString) + salt;
        } catch (NoSuchAlgorithmException | NullPointerException e) {
            throw new RuntimeException("Error during hashing", e);
        }
    }

    private static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}