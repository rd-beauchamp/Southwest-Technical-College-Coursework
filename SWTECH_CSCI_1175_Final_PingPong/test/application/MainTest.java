package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	void  returnAnIntFromGetBallCoord() {
		int min = 1;
		int max = 8;
		Main main = new Main();
		int randTest = main.getBallCoord();
		assertTrue(randTest >= min && randTest <= max);
	}
	
	

}
