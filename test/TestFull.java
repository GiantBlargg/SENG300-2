import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import assignment1.DirParser;

public class TestFull {

	private static String CLASSTYPE = "NoClass";

	/*****************************************************************************
	 * 
	 * Test for a directory with no declarations or references to the classType.
	 * 
	 *****************************************************************************/
	@Test
	public void test() {
		DirParser counter = new DirParser(AllTests.BASEDIR, CLASSTYPE);
		try {
			counter.parseBaseDirectory();
		} catch (IOException e) {
			fail("IOException");
		}
		assertEquals("NoClass. Declarations found: 0; references found: 0.", counter.getCount());
	}

}
