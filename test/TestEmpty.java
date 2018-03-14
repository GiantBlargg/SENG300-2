import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

import assignment1.DirParser;

public class TestEmpty {

	private static String CLASSTYPE = "NoClass";

	/*****************************************************************************
	 * 
	 * Test for a directory with no declarations or references to the classType.
	 * 
	 *****************************************************************************/
	@Test
	public void testEmpty() {
		DirParser counter = new DirParser(AllTests.BASEDIR, CLASSTYPE);
		try {
			counter.parseBaseDirectory();
		} catch (IOException e) {
			fail("IOException");
		}
		assertEquals("NoClass. Declarations found: 0; references found: 0.", counter.getCount());
	}

	@Test
	public void testNonExistant() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		DirParser.main(new String[] { AllTests.BASEDIR + "/nonexistant", "" });
		assertEquals(". Declarations found: 0; references found: 0.\n", outContent.toString());
		System.setOut(System.out);
	}

}
