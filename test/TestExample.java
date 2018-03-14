import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileReader;

import org.junit.Before;
import org.junit.Test;

import assignment1.ReferenceCounter;
import assignment1.ReferenceCounter.counts;

public class TestExample {
	ReferenceCounter rf;

	@Before
	public void setUp() throws Exception {
		File file = new File(AllTests.BASEDIR + "/example/Test.java");
		FileReader fr = new FileReader(file);
		char a[] = new char[(int) file.length()]; // Will contain all characters within the file.
		fr.read(a); // Reads the characters to the Array.
		rf = new ReferenceCounter(AllTests.BASEDIR + "/example/", a);
		fr.close();
	}

	@Test
	public void testTest() {
		counts c = rf.count("Test");
		assertEquals(1, c.Declarations);
		assertEquals(1, c.References);
	}

	@Test
	public void testNest() {
		counts c = rf.count("Test$NestedClass");
		assertEquals(1, c.Declarations);
		assertEquals(2, c.References);
	}

	@Test
	public void testNInterface() {
		counts c = rf.count("Interface");
		assertEquals(1, c.Declarations);
		assertEquals(0, c.References);
	}

}
