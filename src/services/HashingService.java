package services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * This is a utility class providing functionalities for hashing and verifying
 * strings.
 *
 * The class includes public methods for:
 * - Hashing a string with a salt, using the `secureHash` method.
 * - Hashing a string without a salt, using the `deterministicHash` method.
 * - Verifying a string against a given hash using the `verify` method.
 *
 * @version 1.0
 *
 * @author Rasmus Kortsen
 *         - Email: Rasmus.kortsen1@gmail.com
 *         - Github: https://github.com/rasm685p
 */

public class HashingService {

	public static String secureHash(String text) {
		return hash(text, generateSalt());
	}

	public static String deterministicHash(String text) {
		return hash(text, hash(text, null));
	}

	private static String hash(String text, String salt) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			if (salt != null) {
				md.update(salt.getBytes());
			}

			byte[] hashedString = md.digest(text.getBytes());
			String encodedString = Base64.getEncoder().encodeToString(hashedString);

			return (salt != null) ? encodedString + salt : encodedString;
		} catch (NoSuchAlgorithmException | NullPointerException e) {
			throw new RuntimeException("Error during hashing", e);
		}
	}

	public static boolean verify(String text, String hash) {
		try {
			if (deterministicHash(text).equals(hash)) {
				return true;
			}
			String salt = hash.substring(hash.length() - 24);
			return hash(text, salt).equals(hash);
		} catch (Exception e) {
			throw new IllegalArgumentException("Provided hash seems to be invalid");
		}
	}

	private static String generateSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}
}