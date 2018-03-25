import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import assignment1.ReferenceCounter;
import assignment1.ReferenceCounter.counts;

public class TestException {

	@Test
	public void test() throws IOException {
		File file = new File(AllTests.BASEDIR + "/garbage/garbage.java");
		FileReader fr = new FileReader(file);
		char a[] = new char[(int) file.length()]; // Will contain all characters within the file.
		fr.read(a); // Reads the characters to the Array.
		ReferenceCounter rf = new ReferenceCounter(AllTests.BASEDIR + "/garbage/", a);
		fr.close();
		Map<String, counts> classes = new HashMap<String, counts>();
		rf.count(classes);
		assertEquals(0, classes.size());
	}
}
