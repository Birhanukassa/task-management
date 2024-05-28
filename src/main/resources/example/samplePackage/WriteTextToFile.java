import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;


public class WriteTextToFile {
  public static void main(String[] args) {

    ArrayList<String> lines = new ArrayList<>();
    // lines.add("Line 1");
    // lines.add("Line 2");
    // lines.add("line 3");

    File outputFile = new File("output.txt");

    try {
      Files.write(outputFile.toPath(), lines, StandardOpenOption.CREATE);
      
      // System.out.println(lines.get(0));
      // System.out.println(lines.get(1));
      // System.out.println(lines.get(2));
    }
    catch(IOException ex) {
      System.out.println("Error writing to file: " + ex.getMessage());
    }
  }
}

