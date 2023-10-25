import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static String RESET = "\u001B[0m";
    static String BLUE = "\u001B[34m";
    static String CYAN = "\u001B[36m";
    static String RED = "\u001B[31m";
    static String GREEN = "\u001B[32m";
    static String WHITE = "\u001B[37m";
    static String YELLOW = "\u001B[33m";
    static String BLACK = "\u001B[30m";
    static String MAGENTA = "\u001B[35m";


    public static void main(String a[]) throws IOException {



        File file = new File("students.data");

            if (!file.exists()) {
                try {
                    if (file.createNewFile()) {
                        System.out.println("students.data file created for the first times.");
                    } else {
                        System.out.println("Failed to create students.data file.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        
        System.out.print(BLUE + "University System: (A)dmin, (S)tudent, or X : " + RESET);
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
            AdminSystem adminSystem = new AdminSystem();
            adminSystem.adminMenu();
            // Admins admin = new Admins();
            // adminSystem.adminMenu();
            // admin.Name();
            break;
        case "X":
            System.out.println(YELLOW + "Thank You" + RESET);
            break;
        default:
            System.out.println("Invalid Choice!!!");
            main(null);
            break;
        }

        // Utils utils = new Utils();
        // utils.printAllData();

    }

}