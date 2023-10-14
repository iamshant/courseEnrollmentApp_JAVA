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

      // Students students = new Students( "123456", "john.doe@university.com", "Abcdef123");
      // System.out.println(students.toString());

      Utils utils = new Utils();
      utils.printAllData();


  // System.out.println(Utils.EMAIL_REGEX);
  


  }

}