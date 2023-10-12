import java.util.Scanner;
import java.util.regex.Pattern;
// import Students.StudentSystem;

public class Main {
  public static void main(String a[]) {

    System.out.print("University System: (A)dmin, (S)tudent, or e(X)it : ");
    Scanner userInput = new Scanner(System.in);
    String chooseSystem = userInput.nextLine();

    switch (chooseSystem) {
      // case "S", "Student":
      case "S":
          StudentSystem studentSystem = new StudentSystem();
          studentSystem.studentMenu();
          break;

      // This part needs to be modified same as StudentMenu, it will be adminMenu    
      // case "A", "Admin":
      case "A":
        Admins admin = new Admins();
        admin.Name();
        break;
      default:
        break;
    }
  // System.out.println(Utils.EMAIL_REGEX);
  

  // StudentSystem system = new StudentSystem();

  //       studentSystem.loadFromFile();

  //       // Register a new student
  //       studentSystem.register("john.doe@university.com", "Abcdef123");

  //       // Try to register with an invalid email format
  //       studentSystem.register("invalid.email", "Abcdef123");

  //       // Try to register with an invalid password format
  //       studentSystem.register("jane.doe@university.com", "invalidpassword");

  //       // Try to login with correct credentials
  //       boolean loggedIn = studentSystem.login("john.doe@university.com", "Abcdef123");
  //       studentSystem.out.println(loggedIn ? "Login successful" : "Invalid credentials");

  //       // Try to login with incorrect password
  //       loggedIn = studentSystem.login("john.doe@university.com", "WrongPassword");
  //       studentSystem.out.println(loggedIn ? "Login successful" : "Invalid credentials");

  //       // Try to login with non-existent email
  //       loggedIn = studentSystem.login("nonexistent.email@university.com", "SomePassword");
  //       studentSystem.out.println(loggedIn ? "Login successful" : "Invalid credentials");
  }

}