import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
 
public class FileExample {
  public static void main(String[] args) {
    // File object for text file
    File poemFile = new File("poem.txt");
    try {
      // Files.readAllLines() reads entire file & puts lines in the
      // List<String>
      List<String> lines = Files.readAllLines(poemFile.toPath());
      
      System.out.println(lines);
      
      for(String line : lines) {
        System.out.println(line);  
      }
    }
    // If Files.readAllLines() can't find or read file, it throws an 
    // IOException
    catch(IOException ex) {
      System.out.println("Error accessing file: " + ex.getMessage());
    }
  }  
}
 
