import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;


public class StudentSystem {


    public void studentMenu(){
        System.out.print("Student System: (L)ogin, (R)egister, or e(X)it : ");
        Scanner userInput = new Scanner(System.in);
        String chooseStudentMenu = userInput.nextLine();

        switch (chooseStudentMenu) {
        // case "L", "Login":
        case "L":
            StudentLogin();
            break;
        // case "A", "Register":
        case "R":
            StudentRegister();
            break;
        default:
            break;
        }
    }

    
    private static final long serialVersionUID = 1L;
    // private Map<String, Students> students = new HashMap<>();
    Map<String, Students> students = new HashMap<>();
    private Random random = new Random();

    private String generateID() {
        int id = random.nextInt(999999) + 1;
        return String.format("%06d", id);
    }
    
    public void register(String email, String password) {
        if (email.matches(Utils.EMAIL_REGEX) && password.matches(Utils.PASSWORD_REGEX)) {

            String id ;
            do {
                id = generateID();
            } while (students.containsKey(id)); 
            // students.put(new Students(studentId, email, password));
            Students student = new Students(id, email, password);
            // students.put(id, student);
            students.put(email, student);
            saveToFile();
            // System.out.println(students);
            System.out.println("Registration successful.");
        } else {
            System.out.println("Invalid email or password format.");
        }
    }

    

    private void saveToFile() {
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
    
    // Log in by student Email
    
    // public boolean login(String email, String password) {
    //     for (Students student : students) {
    //         // System.out.println(student);
    //         if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    
    // Log in by student ID
    
    // public boolean login(String id, String password) {
    //     Student student = students.get(id);
    //     return student != null && student.getPassword().equals(password);
    // }
    

    // ToDo: Anyone can get the data from here. It's a concern
    public boolean login(String email, String password) {
        Students student = students.get(email);

        System.out.print("Student Course System: Password (C)hange, (E)nroll in a Subject, (R)emove a subject, or e(X)it : ");
            Scanner userInput1 = new Scanner(System.in);
            String chooseStudentCourseMenu = userInput1.nextLine();
            switch (chooseStudentCourseMenu) {
                case "C":
                    // changePassword("abc.def@university.com");
                    changePassword(email);
                    break;
            
                default:
                    break;
            }

        return student != null && student.getPassword().equals(password);
    }

    public void printAllData() {
        for (Students student : students.values()) {
            System.out.println(student);
        }
    }


    public void changePassword(String email){
        
        System.out.println("Type your previous password: ");
        StudentSystem system = new StudentSystem();
        system.loadFromFile();
        Students student = students.get(email);
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

            if (newPassword.matches(Utils.PASSWORD_REGEX)) {
                if (newPassword.equals(reNewPassword)){
                    // Students student1 = new Students(id, email, password);
                    // students.put(id, student);
                    // students.put(email, student);
                    student.setPassword(newPassword);
                    System.out.println("Password Updated");
                    saveToFile();
                } else {
                    System.out.println("Both new passwords didn't match.");
                }  

            } else {
                System.out.println("Password Format is Wrong!!!!!");
            } 
        }   else {
            System.out.println("Wrong Previous Password.");
        }
    } 

    public void StudentLogin(){

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
        
        // // Try to login with correct credentials
        // loggedIn = system.login("john.doe@university.com", "Abcdef123");
        // System.out.println(loggedIn ? "Login successful" : "Invalid credentials");

        // // Try to login with incorrect password
        // loggedIn = system.login("john.doe@university.com", "WrongPassword");
        // System.out.println(loggedIn ? "Login successful" : "Invalid credentials");

        // // Try to login with non-existent email
        // loggedIn = system.login("nonexistent.email@university.com", "SomePassword");
        // System.out.println(loggedIn ? "Login successful" : "Invalid credentials");
    }


    public void StudentRegister(){
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
