import java.util.Scanner;

public class StudentCourseSystem {
    // System.out.println("Stdent After login:");
    // System.out.println(student);

    public void StudenCourseMen(){
        Students student;

        Scanner scanner = new Scanner(System.in);
            String choice;

            do {
                System.out.println("The Student Course System");
                System.out.println("(c) change password");
                System.out.println("(x) exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextLine();

                switch (choice) {
                    case "c":
                        changePassword(student);
                        break;
                    case "x":
                        saveStudentsData();
                        System.out.println("Exiting student menu...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (!choice.equals("X"));
        }
    }
    

    // public void changePassword(String email) {

    //     System.out.println("Type your previous password: ");
    //     StudentSystem system = new StudentSystem();
    //     system.loadFromFile();
    //     Students student = students.get(email);
    //     String password = student.getPassword();
    //     // String email = student.getEmail();
    //     String id = student.getId();
    //     Scanner userInput = new Scanner(System.in);
    //     String previousPassword = userInput.nextLine();

    //     if (student.getPassword().equals(previousPassword)) {
    //         System.out.println("Type your New password: ");
    //         Scanner userInput1 = new Scanner(System.in);
    //         String newPassword = userInput1.nextLine();
    //         System.out.println(newPassword);

    //         System.out.println("Re Type your New password: ");
    //         Scanner userInput2 = new Scanner(System.in);
    //         String reNewPassword = userInput2.nextLine();
    //         System.out.println(reNewPassword);

    //         if (newPassword.equals(reNewPassword) && password.matches(Utils.PASSWORD_REGEX)) {
    //             // Students student1 = new Students(id, email, password);
    //             // students.put(id, student);
    //             // students.put(email, student);
    //             student.setPassword(newPassword);
    //             System.out.println("Password Updated");
    //             saveToFile();
    //             System.out.println("Stdent After changing password:");
    //             System.out.println(student);
    //         } else {
    //             System.out.println("Both new passwords didn't match.");

    //         }

    //     } else {
    //         System.out.println("Wrong Previous Password.");
    //     }
    }

}





