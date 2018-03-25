package proj_2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import proj_2.ReferenceCounter.counts;
import proj_2.ReferenceCounter.*;

public class DirParser {

	private String dirpath;
	private String type;
	private int refcount = 0;
	private int deccount = 0;
	private File dir;
	private FileReader fr;
	private Map<String, counts> c = new HashMap<String, counts>();

	public DirParser(String path, String classType) {
		dirpath = path;
		type = classType;
		dir = new File(path);
	}

	public void parseBaseDirectory() throws IOException {
		if (dir.exists()) {
			File[] files = dir.listFiles();
			for (File file : files) { // Iterates through all the files in the base directory
				if (file.isFile()) {
					fr = new FileReader(file);
					char a[] = new char[(int) file.length()]; // Will contain all characters within the file.
					fr.read(a); // Reads the characters to the Array.
					ReferenceCounter rf = new ReferenceCounter(dirpath, a);
					//c = rf.count();
					c = rf.count();
					System.out.println(c.values().show());
//					System.out.println(c.References);
				}
			}
		}
	}

	public String getCount() {
		return this.type + ". Declarations found: " + this.deccount + "; references found: " + this.refcount + ".";
	}

	public static void main(String args[]) {
		DirParser dp = new DirParser(args[0], args[1]);
		try {
			dp.parseBaseDirectory();
			System.out.println(dp.getCount());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}