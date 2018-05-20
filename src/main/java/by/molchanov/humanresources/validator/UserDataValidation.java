package by.molchanov.humanresources.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataValidation {
    private static final String REGEX_EMAIL_SPLITTER = "[@]";
    private static final String REGEX_EMAIL_USERNAME = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*$";
    private static final String REGEX_EMAIL_HOSTNAME = "^([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$";
    private static final String REGEX_USER_NAME = "^[A-ZА-Я][a-zа-я]{1,44}$";
    private static final String REGEX_USER_PASS = "^[\\S]{1,45}$";

    private UserDataValidation() {}

    public static boolean isEmailAddressCorrect(String email) {
        String[] emailParts = email.split(REGEX_EMAIL_SPLITTER);
        return emailParts.length == 2 && (isEmailHostnameCorrect(emailParts[1]) && isEmailUsernameCorrect(emailParts[0]));
    }

    private static boolean isEmailHostnameCorrect(String hostname) {
        if (hostname.length() > 255) {
            return false;
        }
        Pattern pattern = Pattern.compile(REGEX_EMAIL_HOSTNAME);
        Matcher matcher = pattern.matcher(hostname);
        return matcher.matches();
    }

    private static boolean isEmailUsernameCorrect(String username) {
        Pattern pattern = Pattern.compile(REGEX_EMAIL_USERNAME);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static boolean isUserNameCorrect(String namePart) {
        Pattern pattern = Pattern.compile(REGEX_USER_NAME);
        Matcher matcher = pattern.matcher(namePart);
        return matcher.matches();
    }

    public static boolean isUserPasswordCorrect(String password) {
        Pattern pattern = Pattern.compile(REGEX_USER_PASS);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
