import java.time.LocalDate;

public class User {
  private String userName;
  private String password;
  private LocalDate dateJoined;
  private boolean activeUser;

  public User(String userName, String password) {
    this.userName = userName;
    this.password = password;
    this.dateJoined = LocalDate.now(); 
    this.activeUser = true;           
  }

  public String getUserName() {
    return userName;
  }

  public static void main(String[] args) {

    User firstUser = new User("abc", "123");
    firstUser.userName = "ABC";

  
  

  System.out.println(firstUser.userName);
  // System.out.println("Is active user: " + firstUser.activeUser);
  // System.out.println("Date joined: " + firstUser.dateJoined);
 
  }  // Main method's closing brace

}

