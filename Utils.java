import java.util.Random;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class Utils {

    public static boolean isValidEmailAddrRegex(String emailValidationRegex, String emailAddrToValidate) {
        return Pattern.matches(emailValidationRegex, emailAddrToValidate);
    }

    String SHANT = "Zannatul Naim Shanta";
    static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@university\\.com$";
    static final String PASSWORD_REGEX = "^[A-Z]\\w{5}\\d{3}$";

    public void Name() {
        System.out.println("This is Utils");
    }

    // reading data from database
    private static final String FILE_NAME = "students.data";

    public void printAllData() {
        Map<String, Students> students = new HashMap<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (Map<String, Students>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(students);

        // if (students != null) {
        //     // Now you can work with the data in the 'students' map
        //     for (Map.Entry<String, Students> entry : students.entrySet()) {
        //         System.out.println("Email ID: " + entry.getKey() + ", " + entry.getValue());
        //     }
        //     System.out.println("\n\n");
        //     for (Students student : students.values()) {
        //         System.out.println(student.getEmail());
        //     }
        // }
    }

}