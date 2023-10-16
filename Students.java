import java.io.*;
import java.util.*;

class Students implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String email;
    private String password;

    public Students(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
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

    @Override
    public String toString() {
        return "ID: " + id + ", Email: " + email + ", Password: " + password;
    }
}