package assignment1;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import assignment1.ReferenceCounter.counts;

public class DirParser {

	private String dirpath;
	private File dir;
	Map<String, counts> classes = new HashMap<String, counts>();

	public DirParser(String path) {
		dirpath = path;
		dir = new File(path);
	}

	public void parseBaseDirectory() throws IOException {
		parseDirectory(dir);
	}

	public void parseFile(File file) throws IOException {
		FileReader fr = new FileReader(file);
		char a[] = new char[(int) file.length()]; // Will contain all characters within the file.
		fr.read(a); // Reads the characters to the Array.
		fr.close();
		ReferenceCounter rf = new ReferenceCounter(dirpath, a);
		rf.count(classes);
	}

	public void parseJar(File jar) {

	}

	public void parseDirectory(File dir) throws IOException {
		if (dir.exists()) {
			File[] files = dir.listFiles();
			for (File file : files) { // Iterates through all the files in the base directory
				if (file.isFile()) {
					String name = file.getName();
					int i = name.lastIndexOf('.');
					switch (name.substring(i)) {
					case ".java":
						parseFile(file);
						break;
					case ".jar":
						parseJar(file);
						break;
					}
				} else if (file.isDirectory()) {
					parseDirectory(file);
				}
			}
		}
	}

	@Override
	public String toString() {
		String out = "";
		for (String key : classes.keySet()) {
			counts c = classes.get(key);
			out += key + ". Declarations found: " + c.Declarations + "; references found: " + c.References + ".\n";
		}
		return out;
	}

	public static void main(String args[]) {
		DirParser dp = new DirParser(args[0]);
		try {
			dp.parseBaseDirectory();
			System.out.println(dp);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
