import static org.junit.Assert.assertEquals;

import java.io.IOException;

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

}
