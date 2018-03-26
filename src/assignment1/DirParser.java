package assignment1;

import java.io.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import assignment1.ReferenceCounter.counts;

public class DirParser {

	private String dirpath;
	private File dir;

	private Map<String, counts> classes = new HashMap<String, counts>();

	public Map<String, counts> getCounts() {
		return classes;
	}

	public DirParser(String path) {
		dirpath = path;
		dir = new File(path);
	}

	public void parseBaseDirectory() throws IOException {

		if (dir.isDirectory()) {
			parseDirectory(dir);
		} else {
			String name = dir.getName();
			int i = name.lastIndexOf('.');
			if (name.substring(i) == ".jar") {
				parseJar(dir);
			}
		}
	}

	public void parseFile(File file) throws IOException {
		FileReader fr = new FileReader(file);
		char a[] = new char[(int) file.length()]; // Will contain all characters within the file.
		fr.read(a); // Reads the characters to the Array.
		fr.close();
		ReferenceCounter rf = new ReferenceCounter(dirpath, a);
		rf.count(classes);
	}

	/**
	 * Takes in a File object and converts it into a char[] that gets passed to the
	 * ReferenceCounter.count function for processing
	 * 
	 * @param jar
	 *            The FILE object to process
	 * @throws IOException
	 */
	public void parseJar(File jar) throws IOException {
		JarFile jf = new JarFile(jar); // create a JarFile object
		Enumeration<JarEntry> enumJF = jf.entries(); // gets the zip file entries
		while (enumJF.hasMoreElements()) {
			// get next file
			JarEntry ent = enumJF.nextElement();
			String name = ent.getName();
			// check if name has the .java suffix
			if (name.contains(".java")) {
				InputStreamReader isr = new InputStreamReader(jf.getInputStream(ent));
				BufferedReader reader = new BufferedReader(isr);
				String line;
				String f = "";
				while ((line = reader.readLine()) != null) {
					f = f + line;
				}
				// convert string to charArray
				char[] fileCArr = f.toCharArray();
				ReferenceCounter rf = new ReferenceCounter(dirpath, fileCArr);
				rf.count(classes);
				reader.close(); // close bufferedReader
				isr.close(); // close InputStreamReader
			}
		}

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
