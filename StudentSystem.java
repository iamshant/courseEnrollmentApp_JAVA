import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class StudentSystem {

    private static final long serialVersionUID = 1L;
    public static Map<String, Students> students = new HashMap<>();
    private Random random = new Random();
    private static final String STUDENTS_DATA_FILE = "students.data";

    public void studentMenu() throws IOException {
        String chooseStudentMenu;
            System.out.print(Main.BLUE + "\tStudent System (l/r/x): " + Main.RESET);
            Scanner userInput = new Scanner(System.in);
            chooseStudentMenu = userInput.nextLine();
            switch (chooseStudentMenu) {
                case "l":
                    StudentLogin();
                    break;
                case "r":
                    StudentRegister();
                    break;
                case "x":
                    Main.main(null);
                    break;
                default:
                    System.out.println(Main.RED + "\tInvalid choice. Choose l to Login, r to Register, x for going back to the System Menu." + Main.RESET);
                    studentMenu();
                    break;
            }  
    }


    private String generateID() {
        int id = random.nextInt(999999) + 1;
        return String.format("%06d", id);
    }


    public void StudentRegister() throws IOException {
        StudentSystem system = new StudentSystem();

        system.loadFromFile(STUDENTS_DATA_FILE);
        System.out.println(Main.GREEN + "\tStudent Sign Up" + Main.RESET);

        boolean registered = false;
        
        while(!registered){
            System.out.print("\tEmail: ");
            Scanner studentEmailInput = new Scanner(System.in);
            String studentEmail = studentEmailInput.nextLine().toLowerCase();

            System.out.print("\tPassword: ");
            Scanner studentPasswordInput = new Scanner(System.in);
            String studentPassword = studentPasswordInput.nextLine();

            // // Register a new student
            // system.register(studentEmail, studentPassword);
            // studentMenu();


            if (studentEmail.matches(Utils.EMAIL_REGEX) && studentPassword.matches(Utils.PASSWORD_REGEX)) {

                System.out.println(Main.YELLOW + "\temail and password formats acceptable" + Main.RESET);
                
                String firstName = studentEmail.split("[. @]+", 3)[0];
                firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
                String lastName = studentEmail.split("[. @]+", 3)[1];
                lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
                String name =  firstName + " " + lastName;
                

                registered = students.get(studentEmail) != null;
                
                if (registered) {
                    System.out.println(Main.RED + "\tStudent " + name + " already exists" + Main.RESET);
                    studentMenu();
                } else {
                    String id;
                    do {
                        id = generateID();
                    } while (students.containsKey(id));
                    
                    Students student = new Students(id, studentEmail, studentPassword);
                    students.put(studentEmail, student);            
                    // System.out.println(students);
                    saveToFile();
                    System.out.println("\tName: " + name);
                    System.out.println("\tEnrolling Student " + name);
                    studentMenu();                    
                }
            } else {
                System.out.println(Main.RED + "\tIncorrect email or password format." + Main.RESET);
            }
        }
    }

    public void saveToFile() {
        try {
            // Create a FileOutputStream to write to the file
            FileOutputStream fileOut = new FileOutputStream("students.data");

            // Create an ObjectOutputStream to write objects
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Write an example object (in this case, an ArrayList)
            objectOut.writeObject(students);

            // Close the ObjectOutputStream
            objectOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    @SuppressWarnings("unchecked")
    public void loadFromFile(String fileName) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            Object object = in.readObject();
            if (object != null) {
                students = (Map<String, Students>) object;
            } else {
                students = new HashMap<>();
            }
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            // e.printStackTrace();
        }
    }


    public boolean validateStudentIsExistAndIsCorrectPassword(String email, String password) {
        Students student = students.get(email);
        return student != null && student.getPassword().equals(password);
    }

    public void printAllData() {
        System.out.println(students);
        System.out.println("\n");
        System.out.println("\n");
        for (Map.Entry<String, Students> entry : students.entrySet()) {
          System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
    }

    public void StudentLogin() throws IOException {

        StudentSystem system = new StudentSystem();
        system.loadFromFile(STUDENTS_DATA_FILE);

        boolean loggedIn = false;

       System.out.println(Main.GREEN + "\tStudent Sign In" + Main.RESET );

        while(!loggedIn) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\tEmail: ");
            String studentEmail = scanner.nextLine().toLowerCase();;
            System.out.print("\tPassword: ");
            String studentPassword = scanner.nextLine();
            if (studentEmail.matches(Utils.EMAIL_REGEX) && studentPassword.matches(Utils.PASSWORD_REGEX)) {
                System.out.println(Main.YELLOW + "\temail and password formats acceptable" + Main.RESET );
                loggedIn = system.validateStudentIsExistAndIsCorrectPassword(studentEmail, studentPassword);
                if (loggedIn) {
                    Students student = students.get(studentEmail);
                    StudentCourseSystem studentCourseSystem = new StudentCourseSystem();
                    studentCourseSystem.studentCourseMenu(student);
                } else {
                    System.out.println(Main.RED + "\tStudent does not exist" + Main.RESET);
                    studentMenu();
                }
            } else {
                System.out.println(Main.RED + "\tIncorrect email or password format" + Main.RESET);
            }
        }
    }

    
}