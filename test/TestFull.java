import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import assignment1.DirParser;

public class TestFull {

	/**
	 * test the whole program
	 */
	@Test
	public void test() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		DirParser.main(new String[] { AllTests.BASEDIR + "/jarParsingTest/test1.jar" });
		assertEquals("Constraints. Declarations found: 1; references found: 1.\n"
				+ "Tree. Declarations found: 1; references found: 0.\n"
				+ "java.lang.String. Declarations found: 0; references found: 5.\n"
				+ "Controller. Declarations found: 1; references found: 0.\n"
				+ "Tuple. Declarations found: 1; references found: 0.\n\n", outContent.toString());
		System.setOut(System.out);
	}

}
