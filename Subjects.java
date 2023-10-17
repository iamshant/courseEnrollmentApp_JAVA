import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Subjects {
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
    private static String generateCourseID() {
        int id = random.nextInt(999) + 1;
        return String.format("%03d", id);
    }

    private static double generateMark() {
        return 25 + (100-25) * random.nextDouble();
    }

    private static String calculateGrade( double mark) {
        if (mark >= 90) grade = "A";
        else if (mark >= 80) grade = "B";
        else if (mark >= 70) grade = "C";
        else if (mark >= 60) grade = "D";
        return grade;
    }


    public static void enrollSubject(){
        // StudentSystem studentSystem = new StudentSystem();
        String courseId = generateCourseID();
        Double courseMark = generateMark();
        String courseGrade = calculateGrade(courseMark);

        Subjects subjects = new Subjects(courseId, courseMark, courseGrade);
        courses.put(courseId, subjects);
        System.out.println(courses);

    }
}
