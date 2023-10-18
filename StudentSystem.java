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

    public void studentMenu() {
        String chooseStudentMenu;
        do {
            System.out.println("The Student System");
            System.out.println("(L) Login");
            System.out.println("(R) Register");
            System.out.println("(X) eXit");
            System.out.print("Enter your choice: ");

            Scanner userInput = new Scanner(System.in);
            chooseStudentMenu = userInput.nextLine();

            switch (chooseStudentMenu) {
                // case "L", "Login":
                case "L":
                    StudentLogin();
                    break;
                // case "A", "Register":
                case "R":
                    StudentRegister();
                    break;
                case "X":
                    saveToFile();
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!chooseStudentMenu.equals("X"));
        
    }

    private static final long serialVersionUID = 1L;
    private static final boolean True = false;
    static Map<String, Students> students = new HashMap<>();
    private Random random = new Random();

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
            // System.out.println(students);
            System.out.println("Registration successful.");
        } else {
            System.out.println("Invalid email or password format.");
        }
    }

    
    public static void saveToFile() {
        try {
            // Create a FileOutputStream to write to the file
            FileOutputStream fileOut = new FileOutputStream("students.data");

            // Create an ObjectOutputStream to write objects
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Write an example object (in this case, an ArrayList)
            objectOut.writeObject(students);

            // Close the ObjectOutputStream
            objectOut.close();

            System.out.println("The file 'students.data' has been created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("students.data"));
            students = (Map<String, Students>) in.readObject();
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

    public boolean login(String email, String password) {
        Students student = students.get(email);
        return student != null && student.getPassword().equals(password);
    }

    public void printAllData() {
        System.out.println(students);
        System.out.println("\n");
        System.out.println("\n");
        // for (Students student : students.values()) {
        //     System.out.println(student);
        // }
        for (Map.Entry<String, Students> entry : students.entrySet()) {
          System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
    }
    


    public void StudentLogin() {

        StudentSystem system = new StudentSystem();
        system.loadFromFile();

        System.out.print("Enter Your University Email to Login: ");
        Scanner studentEmailInput = new Scanner(System.in);
        String studentEmail = studentEmailInput.nextLine();

        System.out.print("Enter Your Password: ");
        Scanner studentPasswordInput = new Scanner(System.in);
        String studentPassword = studentPasswordInput.nextLine();

        boolean loggedIn = system.login(studentEmail, studentPassword);
        System.out.println(loggedIn ? "Login successful" : "Invalid credentials");

        // Try to login with correct credentials
        // loggedIn = system.login("john.doe@university.com", "Abcdef123");
        // System.out.println(loggedIn ? "Login successful" : "Invalid credentials");

        // System.out.println(loggedIn == True);

        if (loggedIn){
            StudentCourseSystem studentCourseSystem = new StudentCourseSystem();
            studentCourseSystem.studentCourseMenu(studentEmail);
            // studentCourseSystem.changePassword(studentEmail);

        }


        // // Try to login with incorrect password
        // loggedIn = system.login("john.doe@university.com", "WrongPassword");
        // System.out.println(loggedIn ? "Login successful" : "Invalid credentials");

        // // Try to login with non-existent email
        // loggedIn = system.login("nonexistent.email@university.com", "SomePassword");
        // System.out.println(loggedIn ? "Login successful" : "Invalid credentials");
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