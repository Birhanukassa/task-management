import java.util.List;
import java.util.ArrayList; 
import java.io.*; 
import java.nio.file.Files;


class CompanyEmployeesProgram {
  public static void main(String[] args) {
    ArrayList<CompanyEmployee> employees = readEmployees("employees.csv");
  }
  
  public static ArrayList<CompanyEmployee> readEmployees(String csvFile) {
    // Create an empty ArrayList of CompanyEmployee objects
    ArrayList<CompanyEmployee> employeeList = new ArrayList<>();

    // File object for accessing the CSV file
    File inputDataFile = new File(csvFile);
    List<String> lines = new ArrayList<>();
    // Because the following statements can through exceptions, they are in a try block
    try {
      lines = Files.readAllLines(inputDataFile.toPath());
      for(String line : lines) {
        String[] employeeData = line.split(",");
        int id = Integer.parseInt(employeeData[0]);
        int salary = Integer.parseInt(employeeData[1]);
        // Last name & first name don't need conversion to another datatype. 
        String lastName = employeeData[2];
        String firstName = employeeData[3];
        // Now construct a CompanyEmployee object for each employee
        CompanyEmployee empl = new CompanyEmployee(lastName, firstName, id, salary);
        // Add CompanyEmployee object to ArrayList
        employeeList.add(empl);
      }
    }
    catch(FileNotFoundException ex) {
      System.out.println("File not found: " + ex.getMessage());
    }
    catch(IOException ex) {
      System.out.println("I/O error: " + ex.getMessage());
    }
    catch(NumberFormatException ex) {
      System.out.println("Number Format Error: " + ex.getMessage());
    }
    return employeeList;
  }

}
