package proj_2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.jar.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import proj_2.ReferenceCounter.counts;
import proj_2.ReferenceCounter.*;

public class DirParser {

	private String dirpath;
	private String type;
	private int refcount = 0;
	private int deccount = 0;
	private File dir;
	Map<String, counts> c = new HashMap<String, counts>();

	public DirParser(String path, String classType) {
		dirpath = path;
		type = classType;
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
		rf.count(c);
	}

	/**
	 * Takes in a File object and converts it into a char[] that gets passed
	 * to the ReferenceCounter.count function for processing
	 * 
	 * @param jar The FILE object to process
	 * @throws IOException
	 */
	public void parseJar(File jar) throws IOException {
		JarFile jf = new JarFile(jar);		// create a JarFile object
		Enumeration<JarEntry> enumJF = jf.entries(); 	// gets the zip file entries 
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
				System.out.println(Arrays.toString(fileCArr));
				ReferenceCounter rf = new ReferenceCounter(dirpath, fileCArr);
				reader.close();		// close bufferedReader
				isr.close();		// close InputStreamReader
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