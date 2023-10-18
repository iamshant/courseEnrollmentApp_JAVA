import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class StudentCourseSystem {
    // System.out.println("Stdent After login:");
    // System.out.println(student);


    static Map<String, StudentCourseSystem> courses = new HashMap<>();



    public void studentCourseMenu(String email){
        // String email = StudentSystem ;

        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("The Student Course System");
            System.out.println("(C) change password");
            System.out.println("(E) Enroll subject");
            System.out.println("(D) Drop subject");
            System.out.println("(S) Show Enrolled Subjects");
            System.out.println("(X) exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "c", "C":
                    changePassword(email);
                    break;
                // case "X":
                //     saveStudentsData();
                //     System.out.println("Exiting student menu...");
                //     break;
                case "e", "E":
                    Subjects.enrollSubject();
                    break;
                case "d", "D":
                    Subjects.dropsubject();
                    break;
                case "s", "S":
                    Subjects.showEnrolledSubjects();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (!choice.equals("X"));
    }




    public void changePassword(String email) {

        System.out.println("Type your previous password: ");
        StudentSystem system = new StudentSystem();
        system.loadFromFile();
        Students student = system.students.get(email);
        String password = student.getPassword();
        // String email = student.getEmail();
        String id = student.getId();
        Scanner userInput = new Scanner(System.in);
        String previousPassword = userInput.nextLine();

        if (student.getPassword().equals(previousPassword)) {
            System.out.println("Type your New password: ");
            Scanner userInput1 = new Scanner(System.in);
            String newPassword = userInput1.nextLine();
            System.out.println(newPassword);

            System.out.println("Re Type your New password: ");
            Scanner userInput2 = new Scanner(System.in);
            String reNewPassword = userInput2.nextLine();
            System.out.println(reNewPassword);

            if (newPassword.equals(reNewPassword) && password.matches(Utils.PASSWORD_REGEX)) {
                student.setPassword(newPassword);
                System.out.println("Password Updated");

                StudentSystem.saveToFile();
                System.out.println("Student.data After changing password:");
                System.out.println(student);
            } else {
                System.out.println("Both new passwords didn't match.");

            }

        } else {
            System.out.println("Wrong Previous Password.");
        }
     }

}