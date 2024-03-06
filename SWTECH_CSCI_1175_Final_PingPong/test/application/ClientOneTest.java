package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClientOneTest {
	int x = 1;
	int xClone = 1;
	int y = 2;
	@Test
	void testDupeCheckerFalse() {
		ClientOne client = new ClientOne();
		assertFalse(client.dupeChecker(x,y));
	}
	
	@Test
	void testDupeCheckerTrue() {
		ClientOne client = new ClientOne();
		assertTrue(client.dupeChecker(xClone, x));
	}

}
