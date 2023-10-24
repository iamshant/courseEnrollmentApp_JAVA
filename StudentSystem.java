import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class StudentSystem {

    private static final long serialVersionUID = 1L;
    public static Map<String, Students> students = new HashMap<>();
    private Random random = new Random();

    public void studentMenu() {
        String chooseStudentMenu;
        do {
            System.out.print("\tStudent System (l/r/x): ");
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
                    saveToFile();
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("\tInvalid choice. Please try again.");
            }
        } while (!chooseStudentMenu.equals("X"));
        
    }

    private static final boolean True = false;
//    private Random random = new Random();

    private String generateID() {
        int id = random.nextInt(999999) + 1;
        return String.format("%06d", id);
    }

    
    public void register(String email, String password) {
        if (email.matches(Utils.EMAIL_REGEX) && password.matches(Utils.PASSWORD_REGEX)) {
            String id;
            do {
                id = generateID();
            } while (students.containsKey(id));
            
            Students student = new Students(id, email, password);
            students.put(email, student);            
            System.out.println(students);
            saveToFile();
            System.out.println("Registration successful.");
        } else {
            System.out.println("Invalid email or password format.");
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
    public void loadFromFile() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("students.data"));
            Object object = in.readObject();
            if (object != null) {
                students = (Map<String, Students>) object;
            } else {
                students = new HashMap<>();
            }
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    // Log in by student ID

    // public boolean login(String id, String password) {
    // Student student = students.get(id);
    // return student != null && student.getPassword().equals(password);
    // }

    // ToDo: Anyone can get the data from here. It's a concern

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

    public void StudentLogin() {

        StudentSystem system = new StudentSystem();
        system.loadFromFile();

        boolean loggedIn = false;
        while(!loggedIn) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("\tEmail: ");
            String studentEmail = scanner.nextLine();
            System.out.print("\tPassword: ");
            String studentPassword = scanner.nextLine();
            if (studentEmail.matches(Utils.EMAIL_REGEX) && studentPassword.matches(Utils.PASSWORD_REGEX)) {
                System.out.println("\temail and password formats acceptable");
                loggedIn = system.validateStudentIsExistAndIsCorrectPassword(studentEmail, studentPassword);
                if (loggedIn) {
                    Students student = students.get(studentEmail);
                    StudentCourseSystem studentCourseSystem = new StudentCourseSystem();
                    studentCourseSystem.studentCourseMenu(student);
                } else {
                    System.out.println("\tStudent does not exist");
                }
            } else {
                System.out.println("\tIncorrect email or password format");
            }
        }
    }

    public void StudentRegister() {
        StudentSystem system = new StudentSystem();

        system.loadFromFile();

        System.out.print("Enter Your University Email to Register: ");
        Scanner studentEmailInput = new Scanner(System.in);
        String studentEmail = studentEmailInput.nextLine();

        System.out.print("Enter Your Password: ");
        Scanner studentPasswordInput = new Scanner(System.in);
        String studentPassword = studentPasswordInput.nextLine();

        // Register a new student
        system.register(studentEmail, studentPassword);

        // system.register("abc.def@university.com", "Abcdef123");

        // // Register a new student
        // system.register("john.doe@university.com", "Abcdef123");

        // // Register a new student
        // system.register("aaaaa.doe@university.com", "Abcdef123");

        // // Try to register with an invalid email format
        // system.register("invalid.email", "Abcdef123");

        // // Try to register with an invalid password format
        // system.register("jane.doe@university.com", "invalidpassword");

    }
}