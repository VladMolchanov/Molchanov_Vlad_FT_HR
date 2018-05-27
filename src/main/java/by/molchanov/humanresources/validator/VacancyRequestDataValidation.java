package by.molchanov.humanresources.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class {@link VacancyRequestDataValidation} used for validation of vacancy data.
 *
 * @author MolcanovVladislav
 */
public class VacancyRequestDataValidation {
    private static final String REGEX_TEXT = "^[-,.?!'\"():;\\wА-Яа-я\\s]{1,3000}$";
    private static final String REGEX_VACANCY_NAME = "^[-,.?!'()\\wА-Яа-я\\s]{1,45}$";

    private VacancyRequestDataValidation() {}

    public static boolean isVacancyNameCorrect(String name) {
        Pattern pattern = Pattern.compile(REGEX_VACANCY_NAME);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isTextCorrect(String text) {
        Pattern pattern = Pattern.compile(REGEX_TEXT);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
