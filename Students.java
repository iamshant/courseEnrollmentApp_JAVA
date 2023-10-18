import java.io.*;
import java.util.*;

class Students implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String email;
    private String password;
    private Subjects subjects;

    public Students(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
        // this.subjects;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    
    // public Subjects getSubjects() {
    //     return subjects;
    // }
    
    @Override
    public String toString() {
        return "Email ID: " + id + ", Email: " + email + ", Password: " + password;
    }
}