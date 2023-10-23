import java.util.Scanner;

public class Main {
  public static void main(String a[]) {

    System.out.print("University System: (A)dmin, (S)tudent, or e(X)it : ");
    Scanner userInput = new Scanner(System.in);
    String chooseSystem = userInput.nextLine();
    // Students student = new Students(chooseSystem, chooseSystem, chooseSystem)
    switch (chooseSystem) {
      // case "S", "Student":
      case "S":
          StudentSystem studentSystem = new StudentSystem();
          studentSystem.studentMenu();
          break;

      // This part needs to be modified same as StudentMenu, it will be adminMenu    
      // case "A", "Admin":
      case "A":
        AdminSystem adminSystem = new AdminSystem();
        // Admins admin = new Admins();
        adminSystem.adminMenu();
        // admin.Name();
        break;
      default:
        break;
    }

      // Students students = new Students( "123456", "john.doe@university.com", "Abcdef123");
      // System.out.println(students.toString());

      Utils utils = new Utils();
      utils.printAllData();

      System.out.println("Course/n");



  // System.out.println(Utils.EMAIL_REGEX);



  }

}