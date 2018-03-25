package assignment1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import assignment1.ReferenceCounter.counts;

public class DirParser {

	private String dirpath;
	private String type;
	private int refcount = 0;
	private int deccount = 0;
	private File dir;
	private FileReader fr;

	public DirParser(String path, String classType) {
		dirpath = path;
		type = classType;
		dir = new File(path);
	}

	public void parseBaseDirectory() throws IOException {
		if (dir.exists()) {
			ReferenceCounter rf = new ReferenceCounter(dirpath);
			File[] files = dir.listFiles();
			for (File file : files) { // Iterates through all the files in the base directory
				if (file.isFile()) {
					fr = new FileReader(file);
					char a[] = new char[(int) file.length()]; // Will contain all characters within the file.
					fr.read(a); // Reads the characters to the Array.
					rf.setSource(a);
					counts c = rf.count(type);
					deccount += c.Declarations;
					refcount += c.References;
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
