package assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import assignment1.ReferenceCounter.counts;

public class DirParser {

    private String dirpath;
    private String type;
    private int refcount;
    private int deccount;
    private File dir;
    private FileReader fr;
    
    public DirParser(String path, String classType){
        dirpath = path;
        type = classType;
        dir = new File(path);
    }
    
    public void parseBaseDirectory() throws IOException {
        if(dir.exists()) {
            ReferenceCounter rf = new ReferenceCounter(dirpath);
            File[] files = dir.listFiles();
            for (File file : files) {                                   // Iterates through all the files in the base directory
                if (file.isFile()) {
                    fr = new FileReader(file);
                    char a[] = new char[5000];                          // Will contain all characters within the file.
                    fr.read(a);                                         // Reads the characters to the Array.
                    rf.setSource(a);
                    counts c = rf.count(type);
                    deccount += c.Declarations;
                    refcount += c.References;
                }
            }
        }
    }
    
    public static void main(String args[]) {
        DirParser cc = new DirParser(args[0],args[1]);
        try {
            cc.parseBaseDirectory();
            System.out.println(cc.type + ". Declarations found: " + cc.deccount + "; references found: " + cc.refcount + ".");
           
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
