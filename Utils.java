import java.util.regex.Pattern;

public class Utils{
    
  public static boolean isValidEmailAddrRegex(String emailValidationRegex, String emailAddrToValidate) {
    return Pattern.matches(emailValidationRegex, emailAddrToValidate);
  }
  String SHANT = "Zannatul Naim Shanta";
  static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@university\\.com$";
  static final String PASSWORD_REGEX = "^[A-Z]\\w{5}\\d{3}$";
  
  public void Name(){
        System.out.println("This is Utils");
  }

  
}