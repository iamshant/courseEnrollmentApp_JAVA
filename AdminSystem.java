import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AdminSystem {
    
    private Map<String, Students> studentsAdmin = new HashMap<>();  // Change to Map
    
    public void adminMenu() throws IOException{
        String chooseStudentMenu = "";
        
        while(!chooseStudentMenu.equals("x")){
            System.out.print(Main.BLUE + "\tAdmin System (c/g/p/r/s/x): " + Main.RESET);
            Scanner userInput = new Scanner(System.in);
            chooseStudentMenu = userInput.nextLine();

            switch (chooseStudentMenu) {
                case "c":
                    eraseDataFile();
                    break;
                case "g":
                    groupStudents();
                    break;
                case "p":
                    partitionStudents();
                    break;
                case "r":
                    removeStudent();
                    break;
                case "s":
                    showStudents();
                    break;
                case "x":
                    Main.main(null);
                    break;
                default:
                    System.out.println("\tInvalid choice!");
            }
        }
    }

    public void Name(){
        System.out.println("ADMIN");
    }

    public void admin(){
        Utils utils = new Utils();
        utils.printAllData();
    }
    StudentSystem studentSystem = new StudentSystem();


    // Erase the students.data file
    public void eraseDataFile() throws IOException {
        File file = new File("students.data");

        String doubleCheck = "N";
        System.out.println(Main.YELLOW + "\tClearing students database" + Main.RESET);
        System.out.print(Main.RED + "\tAre you sure you want to clear the database (Y)es/(N)o: " + Main.RESET);

        Scanner userInput = new Scanner(System.in);
        doubleCheck = userInput.nextLine();

        if (file.exists() && doubleCheck.equals("Y")) {
            if (file.delete()) {
                System.out.println(Main.YELLOW + "\tStudents data cleared" + Main.RESET);
            }
        } else adminMenu();
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String fileName) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            Object object = in.readObject();
            if (object != null) {
                studentsAdmin = (Map<String, Students>) object;
            } else {
                studentsAdmin = new HashMap<>();
            }
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        try {
            // Create a FileOutputStream to write to the file
            FileOutputStream fileOut = new FileOutputStream("students.data");

            // Create an ObjectOutputStream to write objects
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Write an example object (in this case, an ArrayList)
            objectOut.writeObject(studentsAdmin);

            // Close the ObjectOutputStream
            objectOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    // Utils utils = new Utils();
    // utils.printAllData();

    public void removeStudent(){

        loadFromFile("students.data");

        System.out.print("\tRemove by ID: ");

        Scanner userInput = new Scanner(System.in);
        String chooseStudentId = userInput.nextLine();

        StudentCourseSystem studentCourseSystem = new StudentCourseSystem();

        // Create an iterator over the map's entry set
        java.util.Iterator<Map.Entry<String, Students>> iterator = studentsAdmin.entrySet().iterator();

        // Iterate through the entries
        // System.out.println(studentsAdmin.size());
        int i = 0;
        while (iterator.hasNext()) {
            Map.Entry<String, Students> entry = iterator.next();
            String key = entry.getKey();
            String studentId = entry.getValue().getId();

            // Condition for removing a key (for example, remove keys with values greater than 30)
            if (chooseStudentId.equals(studentId)) {
                // studentsAdmin.remove(entry.getKey());
                iterator.remove();
                saveToFile(); // Remove the entry from the map
                System.out.println(String.format("\tRemoving Student %s Account", entry.getValue().getId()));
                break;
            }
            i++;
            if (i==studentsAdmin.size()) System.out.println("\tStudent does not exists");
        }
        


        // Print the modified map
        // System.out.println(map);
    }


    public void partitionStudents(){
        List<String> groupPass = new ArrayList<>();
        List<String> groupFail = new ArrayList<>();

        loadFromFile("students.data");

        StudentCourseSystem studentCourseSystem = new StudentCourseSystem();

        System.out.println(Main.YELLOW + "\tPASS/FAIL Partition" + Main.RESET);

        for (Map.Entry<String, Students> entry : studentsAdmin.entrySet()) {
            int mark = StudentCourseSystem.averageMark(entry.getValue());
            String grade = studentCourseSystem.assignGrade(mark);
            
            String firstName = entry.getValue().getEmail().split("[. @]+", 3)[0];
            String lastName = entry.getValue().getEmail().split("[. @]+", 3)[1];
            
            // ToDo: Deciding between integer and floating mark
            String groupString = String.format("%s %s :: %s --> GRADE: %s - MARK: %d",
                                                    firstName, lastName, entry.getValue().getId(), grade,  mark);
            
            if (mark >= 50) groupPass.add(groupString);
            else groupFail.add(groupString);
        }
            System.out.println("\tFAIL --> " + groupFail);
            System.out.println("\tPASS --> " + groupPass);
    }
    
    // Group students by grade
    public void groupStudents() {
        List<String> groupZ = new ArrayList<>();
        List<String> groupP = new ArrayList<>();
        List<String> groupC = new ArrayList<>();
        List<String> groupD = new ArrayList<>();
        List<String> groupHD = new ArrayList<>();

        Set<String> gradeNames = new HashSet<>();

        loadFromFile("students.data");


        StudentCourseSystem studentCourseSystem = new StudentCourseSystem();

        System.out.println(Main.YELLOW + "\tGrade Grouping" + Main.RESET);

        for (Map.Entry<String, Students> entry : studentsAdmin.entrySet()) {
            int mark = StudentCourseSystem.averageMark(entry.getValue());
            String grade = studentCourseSystem.assignGrade(mark);
            
            String firstName = entry.getValue().getEmail().split("[. @]+", 3)[0];
            String lastName = entry.getValue().getEmail().split("[. @]+", 3)[1];
            
            // ToDo: Deciding between integer and floating mark
            String groupString = String.format("%s %s :: %s --> GRADE: %s - MARK: %d",
                                                    firstName, lastName, entry.getValue().getId(), grade,  mark);
            
            if (grade == "Z") {groupZ.add(groupString); gradeNames.add("Z");}
            else if (grade == "P") {groupP.add(groupString); gradeNames.add("P");}
            else if (grade == "C") {groupC.add(groupString); gradeNames.add("C");}
            else if (grade == "D") {groupD.add(groupString); gradeNames.add("D");}
            else if (grade == "HD") {groupHD.add(groupString); gradeNames.add("HD");}
        }

            if (gradeNames.contains("Z")) System.out.println("\tZ --> " + groupZ);
            if (gradeNames.contains("P")) System.out.println("\tP --> " + groupP);
            if (gradeNames.contains("C")) System.out.println("\tC --> " + groupC);
            if (gradeNames.contains("D")) System.out.println("\tD --> " + groupD);
            if (gradeNames.contains("HD")) System.out.println("\tHD --> " + groupHD);
        
    }

    public void showStudents() {
        loadFromFile("students.data");
        // System.out.println(studentsAdmin);
        System.out.println(Main.YELLOW + "\tStudent List" + Main.RESET);
        for (Map.Entry<String, Students> entry : studentsAdmin.entrySet()) {
            // ToDo: Make the firstName and LastName capitala letter, and email is case sensitive now
            String firstName = entry.getValue().getEmail().split("[. @]+", 3)[0];
            String lastName = entry.getValue().getEmail().split("[. @]+", 3)[1];
            
            System.out.println(String.format("\t%s %s :: %s --> Email: %s",
                                                    firstName, 
                                                    lastName, 
                                                    entry.getValue().getId(), 
                                                    entry.getValue().getEmail()));
            

        }

    }   
}
