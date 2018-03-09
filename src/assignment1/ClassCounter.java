package assignment1;

import java.io.File;
import java.io.FileReader;

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
    
    public void parseDirectory() {
        if(dir.exists()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println("File " + file.getName());
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
        cc.parseDirectory();
    }
    
}
