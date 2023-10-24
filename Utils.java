import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Utils {
    public static Map<String, Students> students = new HashMap<>();


    public static boolean isValidEmailAddrRegex(String emailValidationRegex, String emailAddrToValidate) {
        return Pattern.matches(emailValidationRegex, emailAddrToValidate);
    }

    // static final String EMAIL_REGEX = "^([a-zA-Z]+\.)([a-zA-Z]+)@university.com$";
    static final String EMAIL_REGEX = "^[a-zA-Z]+\\.[a-zA-Z]+@university\\.com$";

    // static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@university\\.com$";
    // static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[university.com]$";
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

    }



    @SuppressWarnings("unchecked")
    public static List<Students> loadFromFile(String fileName) {
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
            e.printStackTrace();
        }
        return null;
    }


}