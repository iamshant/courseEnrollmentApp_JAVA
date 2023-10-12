import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
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

    private ArrayList<Students> students = new ArrayList<>();

    public void register(String email, String password) {
        if (email.matches(Utils.EMAIL_REGEX) && password.matches(Utils.PASSWORD_REGEX)) {
            students.add(new Students(email, password));
            saveToFile();
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
            students = (ArrayList<Students>) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean login(String email, String password) {
        for (Students student : students) {
            System.out.println(student);
            if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void StudentLogin(){

        StudentSystem system = new StudentSystem();

        // Try to login with correct credentials
        boolean loggedIn = system.login("john.doe@university.com", "Abcdef123");
        System.out.println(loggedIn ? "Login successful" : "Invalid credentials");

        // Try to login with incorrect password
        loggedIn = system.login("john.doe@university.com", "WrongPassword");
        System.out.println(loggedIn ? "Login successful" : "Invalid credentials");

        // Try to login with non-existent email
        loggedIn = system.login("nonexistent.email@university.com", "SomePassword");
        System.out.println(loggedIn ? "Login successful" : "Invalid credentials");
    }
    public void StudentRegister(){
        StudentSystem system = new StudentSystem();

        system.loadFromFile();

        // Register a new student
        system.register("john.doe@university.com", "Abcdef123");

        // Try to register with an invalid email format
        system.register("invalid.email", "Abcdef123");

        // Try to register with an invalid password format
        system.register("jane.doe@university.com", "invalidpassword");

        
    }
}
