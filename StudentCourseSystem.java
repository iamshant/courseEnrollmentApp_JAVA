import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class StudentCourseSystem {
    public void studentCourseMenu(Students students){
        StudentSystem studentSystem = new StudentSystem();
        String choice = "";
        while(!choice.equals("x")) {
            System.out.print("\t\tStudent Course Menu (c/e/r/s/x):");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine();
            switch (choice) {
                case "c":
                    System.out.println("\t\tUpdating Password");
                    System.out.print("\t\tNew Password: ");
                    String newPassword = scanner.nextLine();
                    String confirmPassword = null;
                    while (null == confirmPassword || !newPassword.equals(confirmPassword)) {
                        System.out.println("\t\tConfirm Password: ");
                        confirmPassword = scanner.nextLine();
                    }
                    students.setPassword(newPassword);
                    studentSystem.saveToFile();
                    break;
                case "e":
                    if (null == students.getSubjectList() || students.getSubjectList().size() < 4) {
                        enrollSubject(students);
                    } else {
                        System.out.println("\t\tAlready enroll 4 subjects.");
                    }
                    studentSystem.saveToFile();
                    break;
                case "r":
                    students.getSubjectList().remove(0);
                    System.out.println("\t\tYou are now enrolled in " + students.getSubjectList().size() + " out of 4 subjects");
                    studentSystem.saveToFile();
                    break;
                case "s":
                    for(Subject subject: students.getSubjectList()){
                        System.out.println("\t\t[ Subject::" + subject.getId()
                                + " -- mark = " + subject.getMark()
                                + " -- grade = " + subject.getGrade() + " ]");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void enrollSubject(Students students) {
        String id = generateID();
        double mark = generateMark();
        String grade = assignGrade(mark);
        Subject subject = new Subject(id, grade, mark);
        if(students.getSubjectList() == null) {
            students.setSubjectList(new ArrayList());
        }
        students.getSubjectList().add(subject);
        System.out.println("\t\tEnrolling in Subject-" + id);
        System.out.println("\t\tYou now enrolled in " + students.getSubjectList().size() + " out of 4 subjects");
    }
    private String generateID() {
        int id = Subjects.random.nextInt(999) + 1;
        return String.format("%03d", id);
    }
 
    private static double generateMark() {
            Random random = new Random();
        return 25 + (100 - 25) * random.nextDouble();
    }

    private String assignGrade(double mark) {
        if (mark >= 50) return "P";
        else return "F";
    }
}
