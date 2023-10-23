
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;


public class StudentCourseSystem {
    public void studentCourseMenu(Students students){
        StudentSystem studentSystem = new StudentSystem();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Student Course Menu (c/e/r/s/x):");
        String choice = scanner.nextLine();
            switch (choice) {
                case "c":
                    System.out.println("Updating Password");
                    System.out.print("New Password: ");
                    String newPassword = scanner.nextLine();
                    String confirmPassword = null;
                    while(null == confirmPassword || !newPassword.equals(confirmPassword)) {
                        System.out.println("Confirm Password: ");
                        confirmPassword = scanner.nextLine();
                    }
                    //TODO check password with the password policy
                    students.setPassword(newPassword);
                    studentSystem.saveToFile();
                    break;
                case "e":
                    System.out.println("before: " + students);
                    if (students.getSubjectList().size() < 4) {
                        enrollSubject(students);
                    } else {
                        System.out.println("Already enroll 4 subjects.");
                    }
                    studentSystem.saveToFile();
                    System.out.println("after: " + students);
                    break;
                case "r":
                    students.getSubjectList().remove(0);
                    System.out.println("You are now enrolled in " + students.getSubjectList().size() + " out of 4 subjects");
                    studentSystem.saveToFile();
                    break;
                case "s":
                    break;
                case "x":
                    System.out.println("Exiting student menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    public void enrollSubject(Students students) {
        String id = "12345"; //TODO random ID
        int mark = 40; //TODO random mark
        String grade = "Z"; //TODO assign grade by random mark
        Subject subject = new Subject(id, grade, mark);
        if( students.getSubjectList() == null) {
            students.setSubjectList(new ArrayList());
        }
        students.getSubjectList().add(subject);
    }
}
