import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import assignment1.ReferenceCounter;
import assignment1.ReferenceCounter.counts;

public class TestExample {

	private Map<String, counts> classes = new HashMap<String, counts>();

	@Before
	public void setUp() throws Exception {
		File file = new File(AllTests.BASEDIR + "/example/Test.java");
		FileReader fr = new FileReader(file);
		char a[] = new char[(int) file.length()]; // Will contain all characters within the file.
		fr.read(a); // Reads the characters to the Array.
		ReferenceCounter rf = new ReferenceCounter(AllTests.BASEDIR + "/example/", a);
		fr.close();
		rf.count(classes);
	}

	@Test
	public void testTest() {
		counts c = classes.get("Test");
		assertEquals(1, c.Declarations);
	}

	@Test
	public void testNest() {
		counts c = classes.get("Test.NestedClass");
		assertEquals(1, c.Declarations);
		assertEquals(2, c.References);
	}

	@Test
	public void testNInterface() {
		counts c = classes.get("Interface");
		assertEquals(1, c.Declarations);
		assertEquals(0, c.References);
	}

}
