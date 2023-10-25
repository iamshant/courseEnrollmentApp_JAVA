import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


public class StudentCourseSystem {
    public void studentCourseMenu(Students students) throws IOException{
        StudentSystem studentSystem = new StudentSystem();
        String choice = "";
        while(!choice.equals("x")) {
            System.out.print(Main.BLUE + "\t\tStudent Course Menu (c/e/r/s/x):" + Main.RESET);
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine();
            switch (choice) {
                case "c":
                    System.out.println(Main.YELLOW + "\t\tUpdating Password" + Main.RESET);
                    System.out.print("\t\tNew Password: ");
                    String newPassword = scanner.nextLine();
                    String confirmPassword = null;
                    while (null == confirmPassword || !newPassword.equals(confirmPassword)) {
                        System.out.print("\t\tConfirm Password: ");
                        confirmPassword = scanner.nextLine();
                        if (!newPassword.equals(confirmPassword)){
                            System.out.println(Main.RED + "\t\tPassword does not match - try again" + Main.RESET);
                            confirmPassword = null;
                        }   
                    }
                    students.setPassword(newPassword);
                    studentSystem.saveToFile();
                    break;
                case "e":
                    if (null == students.getSubjectList() || students.getSubjectList().size() < 4) {
                        enrollSubject(students);
                    } else {
                        System.out.println(Main.RED + "\t\tStudents are allowed to enroll in 4 subjects only" + Main.RESET);
                    }
                    studentSystem.saveToFile();
                    break;
                case "r":
                    // String subId = students.getSubjectList().getFirst().getId();
                    System.out.print("\t\tRemove Subject by ID: ");
                    String subId = scanner.nextLine();

                    for (int i =0; i < students.getSubjectList().size(); i++){
                        if (students.getSubjectList().get(i).getId().equals(subId)) {
                            students.getSubjectList().remove(i);
                            break;
                        } 
                    }
                    System.out.println(Main.YELLOW + "\t\tDroping Subject " + subId + Main.RESET);
                    System.out.println(Main.YELLOW + "\t\tYou are now enrolled in " + students.getSubjectList().size() + " out of 4 subjects" + Main.RESET);
                    studentSystem.saveToFile();
                    break;
                case "s":
//                    if (students.getSubjectList().isEmpty()) System.out.println(Main.YELLOW + "\t\tShowing 0 subjects" + Main.RESET);
                    String abc = String.format("\t\tShowing %s subjects",students.getSubjectList().size());
                    System.out.println(Main.YELLOW + abc + Main.RESET);

                    for(Subject subject: students.getSubjectList()){
                        System.out.println("\t\t[ Subject::" + subject.getId()
                                + " -- mark = " + subject.getMark()
                                + " -- grade = " + subject.getGrade() + " ]");
                    }
                    System.out.println("\t\taverageMark -- " + averageMark(students) + ". --> need to delete this later on");
                    break;
                case "x":
                    Main.main(null);
                    break;
                default:
                    break;
            }
        }
    }

    public void enrollSubject(Students students) {
        String id = generateID();
        int mark = generateMark();
        String grade = assignGrade(mark);
        Subject subject = new Subject(id, grade, mark);
        if(students.getSubjectList() == null) {
            students.setSubjectList(new ArrayList());
        }
        students.getSubjectList().add(subject);
        System.out.println(Main.YELLOW + "\t\tEnrolling in Subject-" + id + Main.RESET);
        System.out.println(Main.YELLOW + "\t\tYou now enrolled in " + students.getSubjectList().size() + " out of 4 subjects" + Main.RESET);
    }
    private String generateID() {
        Random random = new Random();
        int id = random.nextInt(999) + 1;
        return String.format("%03d", id);
    }
 
    private static int generateMark() {
            Random random = new Random();
        return (int) (25 + (100 - 25) * random.nextDouble());
    }

    private String assignGrade(double mark) {
        if (mark < 50) return "Z";
        else if ((mark <= 50) && (mark < 65)) return "P";
        else if ((mark <= 65) && (mark < 75)) return "C";
        else if ((mark <= 75) && (mark < 85)) return "D";
        else return "HD";
    }

    public static int averageMark(Students students) {
        int avgMark = 0;
        for(Subject subject: students.getSubjectList()){
            avgMark = avgMark + subject.getMark();
                        
        }
        int numberOfSub = students.getSubjectList().size();
        if (numberOfSub >= 1){
                    return avgMark / numberOfSub;

        }
        else return avgMark;
    }
}
