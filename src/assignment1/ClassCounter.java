package assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import assignment1.ReferenceCounter.counts;

public class ClassCounter {

    private String BASEDIR;
    private String CLASSTYPE;
    private int REFCOUNT;
    private int DECCOUNT;
    private File dir;
    private FileReader fr;
    
    public ClassCounter(String classType, String path){
        BASEDIR = path;
        CLASSTYPE = classType;
        dir = new File(path);
    }
    
    public void parseBaseDirectory() throws IOException {
        if(dir.exists()) {
            ReferenceCounter rf = new ReferenceCounter(BASEDIR);
            File[] files = dir.listFiles();
            for (File file : files) {                                   // Iterates through all the files in the base directory
                if (file.isFile()) {
                    fr = new FileReader(file);
                    char a[] = new char[5000];                          // Will contain all characters within the file.
                    fr.read(a);                                         // Reads the characters to the Array.
                    rf.setSource(a);
                    counts c = rf.count(CLASSTYPE);
                    DECCOUNT += c.Declarations;
                    REFCOUNT += c.References;
                }
            }
        }
    }
    
    public static void main(String args[]) {
        ClassCounter cc = new ClassCounter(args[0],args[1]);
        try {
            cc.parseBaseDirectory();
            System.out.println(cc.CLASSTYPE + ". Declarations found: " + cc.DECCOUNT + "; references found: " + cc.REFCOUNT + ".");
           
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
