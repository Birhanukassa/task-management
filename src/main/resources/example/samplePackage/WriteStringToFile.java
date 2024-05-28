import java.io.*;
import java.nio.file.*;

public class WriteStringToFile {
  public static void main(String[] args) {
    File output = new File("output.txt");
    try {
      // Try writing 2 lines to the file in StandardOpenOption.CREATE mode
      Files.writeString(output.toPath(), "Hello, world", StandardOpenOption.CREATE);
      Files.writeString(output.toPath(), "Hello, world", StandardOpenOption.CREATE);
    }
    catch(IOException ex) {
      System.out.println("Error: " + ex.getMessage());
    } 
  }
}
