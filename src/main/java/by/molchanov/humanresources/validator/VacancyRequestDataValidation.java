package by.molchanov.humanresources.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VacancyRequestDataValidation {
    private static final String REGEX_REQUIREMENT = "^[-,.?!'\"()\\wА-Яа-я\\s]{1,3000}$";
    private static final String REGEX_VACANCY_NAME = "^[-,.?!'\"()\\wА-Яа-я\\s]{1,45}$";

    private VacancyRequestDataValidation() {}

    public static boolean isVacancyNameCorrect(String name) {
        Pattern pattern = Pattern.compile(REGEX_VACANCY_NAME);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isRequirementCorrect(String requirement) {
        Pattern pattern = Pattern.compile(REGEX_REQUIREMENT);
        Matcher matcher = pattern.matcher(requirement);
        return matcher.matches();
    }

    public static boolean isResumeCorrect(String requirement) {
        Pattern pattern = Pattern.compile(REGEX_REQUIREMENT);
        Matcher matcher = pattern.matcher(requirement);
        return matcher.matches();
    }
}
