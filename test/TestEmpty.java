import static org.junit.Assert.assertEquals;

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
	public void testEmpty() throws IOException {
		DirParser counter = new DirParser(AllTests.BASEDIR);
		counter.parseBaseDirectory();
		assertEquals(null, counter.getCounts().get("NoClass"));
	}

	@Test
	public void testNonExistant() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		DirParser.main(new String[] { AllTests.BASEDIR + "/nonexistant" });
		assertEquals("\n", outContent.toString());
		System.setOut(System.out);
	}

}
