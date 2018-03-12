package assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
            File[] files = dir.listFiles();
            for (File file : files) {                                   // Iterates through all the files in the base directory
                if (file.isFile()) {
                    System.out.println("File " + file.getName());
                    
                    char a[] = new char[5000];                          // Will contain all characters within the file.
                    fr.read(a);                                         // Reads the characters to the Array.
                    String contents = new String(a);
                    while(contents.indexOf(CLASSTYPE)!=-1) {
                        REFCOUNT++;
                        contents = contents.substring(contents.indexOf(CLASSTYPE)+CLASSTYPE.length(),contents.length()-1);
                    }
                    System.out.println(REFCOUNT);
                  } else if (file.isDirectory()) {
                    System.out.println("Directory " + file.getName());  
                  }
            }
        }
    }
    
    
    public static void parseFile(File file) {
        
    }
    
    public static void main(String args[]) {
        ClassCounter cc = new ClassCounter(args[0],args[1]);
        try {
            cc.parseBaseDirectory();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
