import static org.junit.Assert.*;

import models.Permit;

import org.junit.Test;

import play.test.UnitTest;


public class TestConnect extends UnitTest {
	@Test
	public void test() {
		//Permit permit = new Permit();
		Permit.findAll();
	}

}
