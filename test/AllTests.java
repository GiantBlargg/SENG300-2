import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestEmpty.class, TestSrc.class, TestExample.class, TestException.class, jarParserTest.class,
		TestFull.class })
public class AllTests {
	// Please point to the testFiles dir on your machine
	public static String BASEDIR = "/home/daniel/Projects/java/SENG300/testFiles";
}
