package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import services.HashingService;

class HashingTest extends BaseTestCase {
	
	@Test
	public void secureHashVerifyShouldOnlyReturnTrueIfCorrectPassword() {
		String password1 = "test123";
		String password2 = "test1234";

		String hashed = HashingService.secureHash(password1);
		
		assertTrue(HashingService.verify(password1, hashed));
		assertFalse(HashingService.verify(password2, hashed));
	}
	
	@Test
	public void deterministicHashVerifyShouldOnlyReturnTrueIfCorrectPassword() {
		String password1 = "test123";
		String password2 = "test1234";

		String hashed = HashingService.deterministicHash(password1);
		
		assertTrue(HashingService.verify(password1, hashed));
		assertFalse(HashingService.verify(password2, hashed));
	}
	
	@Test
	public void secureHashShouldNeverReturnTheSameHash() {
		String password1 = "test123";
		
		String hashed1 = HashingService.secureHash(password1);
		String hashed2 = HashingService.secureHash(password1);
		
		assertFalse(hashed1.equals(hashed2));
	}
	
	@Test
	public void deterministicHashShouldReturnTheSameHash() {
		String cpr = "1234567898";
		
		String hashed1 = HashingService.deterministicHash(cpr);
		String hashed2 = HashingService.deterministicHash(cpr);
		
		assertTrue(hashed1.equals(hashed2));
	}
	
	@Test
	public void ShouldThrowExceptionIfHashIsUnexpected() {
		assertThrows(IllegalArgumentException.class, () -> {
            HashingService.verify("test123", "abc");
        });
	}
}