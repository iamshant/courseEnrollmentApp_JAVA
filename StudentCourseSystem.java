import java.util.Scanner;

public class StudentCourseSystem {
    public void changePassword(){
        
        System.out.print("Student Course System: Password (C)hange, (E)nroll in a Subject, (R)emove a subject, or e(X)it : ");
        Scanner userInput1 = new Scanner(System.in);
        String chooseStudentCourseMenu = userInput1.nextLine();
        switch (chooseStudentCourseMenu) {
            case "C":
                // changePassword("abc.def@university.com");
                changePassword(student);
;
                break;
        
            default:
                break;
        }

    }
}
