import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import assignment1.DirParser;

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
		assertEquals(0, dp.getCounts().get("java.lang.String").Declarations);
		assertEquals(10, dp.getCounts().get("java.lang.String").References);
	}

	@Test
	public void testDirParser() {
		assertEquals(1, dp.getCounts().get("assignment1.DirParser").Declarations);
		assertEquals(2, dp.getCounts().get("assignment1.DirParser").References);
	}

	@Test
	public void testReferenceCounter() {
		assertEquals(1, dp.getCounts().get("assignment1.ReferenceCounter").Declarations);
		assertEquals(2, dp.getCounts().get("assignment1.ReferenceCounter").References);
	}

	@Test
	public void testCounts() {
		assertEquals(1, dp.getCounts().get("assignment1.ReferenceCounter$counts").Declarations);
		assertEquals(4, dp.getCounts().get("assignment1.ReferenceCounter$counts").References);
	}

}
