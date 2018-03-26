import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import assignment1.ReferenceCounter.counts;
import assignment1.DirParser;
import java.util.Map;
import static org.junit.Assert.assertEquals;


/**
 * Run test on parseJar function
 * 
 * @author Nathanael Carrigan
 */
public class jarParserTest {
	DirParser dp;			// make global for test reference
	
	// Setup test situation for test
	@Before
	public void setup() throws IOException {
		dp = new DirParser(AllTests.BASEDIR2 + "/jarParsingTest/");
		dp.parseBaseDirectory();
	}
	
	// Test that parseJar is seeing all .java files
	@Test
	public void countFiles() {
		Map<String, counts> c = dp.getCounts();
		assertEquals(5, c.size());
	}
	
	// Test that correct count is being returned for java.lang.String
	@Test
	public void testString() {
		counts c = dp.getCounts().get("java.lang.String");
		assertEquals(0, c.Declarations);
		assertEquals(5, c.References);
	}
	
	// Test that correct count is being returned for Tree
	@Test
	public void testTree() {
		counts c = dp.getCounts().get("Tree");
		assertEquals(1, c.Declarations);
		assertEquals(0, c.References);
	}
	
	// Test that correct count is being returned for Controller
	@Test
	public void testController() {
		counts c = dp.getCounts().get("Controller");
		assertEquals(1, c.Declarations);
		assertEquals(0, c.References);
	}
	
	// Test that correct count is being returned for Tuple
	@Test
	public void testTuple() {
		counts c = dp.getCounts().get("Tuple");
		assertEquals(1, c.Declarations);
		assertEquals(0, c.References);
	}
	
	// Test that correct count is being returned for Constraints
	@Test
	public void testConstraints() {
		counts c = dp.getCounts().get("Constraints");
		assertEquals(1, c.Declarations);
		assertEquals(0, c.References);
	}
}
