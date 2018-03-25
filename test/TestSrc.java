import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import assignment1.DirParser;
import assignment1.ReferenceCounter.counts;

/**
 * Run some tests on our own source code
 * 
 * @author daniel
 *
 */
public class TestSrc {

	DirParser dp;

	@Before
	public void setupClass() throws IOException {
		dp = new DirParser(AllTests.BASEDIR + "/src");
		dp.parseBaseDirectory();
	}

	@Test
	public void testString() {
		counts c = dp.getCounts().get("java.lang.String");
		assertEquals(0, c.Declarations);
		assertEquals(10, c.References);
	}

	@Test
	public void testDirParser() {
		counts c = dp.getCounts().get("assignment1.DirParser");
		assertEquals(1, c.Declarations);
		assertEquals(2, c.References);
	}

	@Test
	public void testReferenceCounter() {
		counts c = dp.getCounts().get("assignment1.ReferenceCounter");
		assertEquals(1, c.Declarations);
		assertEquals(2, c.References);
	}

	@Test
	public void testCounts() {
		counts c = dp.getCounts().get("assignment1.ReferenceCounter$counts");
		assertEquals(1, c.Declarations);
		assertEquals(4, c.References);
	}

}
