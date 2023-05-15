package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import services.HashingService;

class HashingTest extends BaseTestCase {

	private static final int HASHED_STRING_LENGTH = 68;
	
	@Test
	public void shouldHashProvidedString() {
		String password = "1234";

		String hashed = HashingService.hash(password);
		
		assertTrue(hashed.length() == HASHED_STRING_LENGTH);

		assertTrue(HashingService.verify(password, hashed));
	}

	@Test
	public void shouldReturnFalseWhenVerifyingWithWrongProvidedString() {
		
		String password = "abcdefghijklmnopqrstuvwxyz..";

		String hashed = HashingService.hash(password);

		assertFalse(HashingService.verify("abcdefghijklmnopqrstuvwxyz...", hashed));
	}

}
