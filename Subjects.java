import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

public class Subjects {
    static List allSubjectsMarks = new ArrayList();

    private static final long serialVersionUID = 1L;

    private String courseId;
    private double courseMark;
    private String courseGrade;

    static Random random = new Random();
    static String grade;
    static Map<String, Subjects> courses = new HashMap<>();

    public Subjects(String courseId, double courseMark, String courseGrade) {
        this.courseId = courseId;
        this.courseMark = courseMark;
        this.courseGrade = courseGrade;
    }

    public String getCourseId() {
        return courseId;
    }

    public double getCourseMark() {
        return courseMark;
    }

    public String getCourseGrade() {
        return courseGrade;
    }

    @Override
    public String toString() {
        return "courseId: " + courseId + ", courseMark: " + courseMark;
    }
    
    private static String generateCourseID() {
        int id = random.nextInt(999) + 1;
        return String.format("%03d", id);
    }

    private static double generateMark() {
        return 25 + (100 - 25) * random.nextDouble();
    }

    // ToDo
    private static double passFail(double avgMark) {
        // A student pass/fail a course if the average mark of the subjects is greater or equal to 50
        return avgMark;
    }
    private static String calculateGrade(double mark) {
        if (mark >= 90)
            grade = "A";
        else if (mark >= 80)
            grade = "B";
        else if (mark >= 70)
            grade = "C";
        else if (mark >= 60)
            grade = "D";
        return grade;
    }

    public static void dropsubject(){
        System.out.println("This method imolimentation is yet to be done.");

    }

    public static void showEnrolledSubjects(){
        System.out.println("This method imolimentation is yet to be done.");

    }
    
    public static void enrollSubject() {
        
        String courseId = generateCourseID();
        Double courseMark = generateMark();
        String courseGrade = calculateGrade(courseMark);
        
        Subjects subjects = new Subjects(courseId, courseMark, courseGrade);
    
        courses.put(courseId, subjects);
        allSubjectsMarks.add(courses);
        saveToSubjectFile();
        System.out.println(courses);
        System.out.println(allSubjectsMarks);

    }

    public static void saveToSubjectFile() {
        try {
            // Create a FileOutputStream to write to the file
            FileOutputStream fileOut = new FileOutputStream("subjects.data");

            // Create an ObjectOutputStream to write objects
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // Write an example object (in this case, an ArrayList)
            objectOut.writeObject(courses);

            // Close the ObjectOutputStream
            objectOut.close();

            System.out.println("The file 'students.data' has been created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    public void loadFromSubjectFile() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("subjects.data"));
                courses = (Map<String, Subjects>) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}