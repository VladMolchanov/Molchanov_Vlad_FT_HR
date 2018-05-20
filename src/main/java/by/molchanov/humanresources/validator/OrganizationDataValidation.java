package by.molchanov.humanresources.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class {@link OrganizationDataValidation} used for validation of organization data.
 *
 * @author MolcanovVladislav
 */
public class OrganizationDataValidation {
    private static final String REGEX_ORG_NAME = "^[-\\wА-Яа-я\\s]{1,45}$";
    private static final String REGEX_WEB_SITE = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]{1,245}[-a-zA-Z0-9+&@#/%=~_|]$";
    private static final String REGEX_DESCRIPTION = "^[-,.?!'\"\\wА-Яа-я\\s]{1,3000}$";

    private OrganizationDataValidation() {}

    public static boolean isOrgNameCorrect(String name) {
        Pattern pattern = Pattern.compile(REGEX_ORG_NAME);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isWEBSiteCorrect(String website) {
        Pattern pattern = Pattern.compile(REGEX_WEB_SITE);
        Matcher matcher = pattern.matcher(website);
        return matcher.matches();
    }

    public static boolean isDescriptionCorrect(String description) {
        Pattern pattern = Pattern.compile(REGEX_DESCRIPTION);
        Matcher matcher = pattern.matcher(description);
        return matcher.matches();
    }
}
