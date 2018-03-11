package assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ClassCounter {

    private static String BASEDIR;
    private static String CLASSTYPE;
    private static int COUNT;
    private static File dir;
    private static FileReader fr;
    
    public ClassCounter(String classType, String path){
        BASEDIR = path;
        CLASSTYPE = classType;
        dir = new File(path);
    }
    
    public void parseDirectory() throws IOException {
        if(dir.exists()) {
            File[] files = dir.listFiles();
            for (File file : files) {                                   // Iterates through all the files in the base directory
                if (file.isFile()) {
                    System.out.println("File " + file.getName());
                    fr = new FileReader(file);
                    char a[] = new char[5000];                          // Will contain all characters within the file.
                    fr.read(a);                                         // Reads the characters to the Array.
                    System.out.println(new String(a));
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
            cc.parseDirectory();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
