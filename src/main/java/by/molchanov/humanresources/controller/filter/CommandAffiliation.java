package by.molchanov.humanresources.controller.filter;

import java.util.HashSet;

/**
 * Class {@link CommandAffiliation} checks whether user has rights to call certain command.
 *
 * @author Molchanov Vladislav
 */
public class CommandAffiliation {
    private static final CommandAffiliation COMMAND_AFFILIATION = new CommandAffiliation();
    private HashSet<String> adminCommands;
    private HashSet<String> aspirantCommands;
    private HashSet<String> guestCommands;
    private HashSet<String> directorCommands;

    private static final String ROLE_ADMIN = "admin";
    private static final String ROLE_DIRECTOR = "director";
    private static final String ROLE_ASPIRANT = "aspirant";

    private CommandAffiliation() {
        String vacFilterCommand = "vacancy_filter";
        String contentCommand = "fill_content";
        String establishRussianLocaleCommand = "est_russian_locale";
        String establishBelorussianLocaleCommand = "est_belorussian_locale";
        String establishEnglishLocaleCommand = "est_english_locale";
        String logOutCommand = "log_out";
        String editUser = "edit_user";
        guestCommands = new HashSet<>();
        adminCommands = new HashSet<>();
        aspirantCommands = new HashSet<>();
        directorCommands = new HashSet<>();
        guestCommands.add("authentication");
        guestCommands.add("user_registration");
        guestCommands.add(vacFilterCommand);
        guestCommands.add(contentCommand);
        guestCommands.add(establishRussianLocaleCommand);
        guestCommands.add(establishEnglishLocaleCommand);
        guestCommands.add(establishBelorussianLocaleCommand);
        aspirantCommands.add(vacFilterCommand);
        aspirantCommands.add(contentCommand);
        aspirantCommands.add(establishEnglishLocaleCommand);
        aspirantCommands.add(establishBelorussianLocaleCommand);
        aspirantCommands.add("org_registration");
        aspirantCommands.add(logOutCommand);
        aspirantCommands.add("request_registration");
        aspirantCommands.add(establishRussianLocaleCommand);
        aspirantCommands.add(editUser);
        directorCommands.add(vacFilterCommand);
        directorCommands.add(contentCommand);
        directorCommands.add(establishRussianLocaleCommand);
        directorCommands.add(establishBelorussianLocaleCommand);
        directorCommands.add(establishEnglishLocaleCommand);
        directorCommands.add("request_filter");
        directorCommands.add("vacancy_registration");
        directorCommands.add("close_request");
        directorCommands.add("send_request_answer");
        directorCommands.add("close_vacancy");
        directorCommands.add("edit_vacancy");
        directorCommands.add(logOutCommand);
        directorCommands.add(editUser);
        adminCommands.add(vacFilterCommand);
        adminCommands.add(contentCommand);
        adminCommands.add(establishRussianLocaleCommand);
        adminCommands.add(establishEnglishLocaleCommand);
        adminCommands.add(establishBelorussianLocaleCommand);
        adminCommands.add(logOutCommand);
        adminCommands.add("confirm_vacancy");
        adminCommands.add("delete_vacancy");
        adminCommands.add("delete_user");
        adminCommands.add("close_old_vacancy");
        adminCommands.add("delete_user");
        adminCommands.add("delete_organization");
        adminCommands.add("rise_to_admin");
        adminCommands.add(editUser);
    }

    public static CommandAffiliation getInstance() {
        return COMMAND_AFFILIATION;
    }

    boolean isCommandCorrect(String role, String command) {
        boolean result;
        String emptyString = "";
        if (emptyString.equals(command) || command == null) {
            result = true;
        } else {
            if (role == null) {
                role = emptyString;
            }
            switch (role) {
                case ROLE_ASPIRANT:
                    result = aspirantCommands.contains(command);
                    break;
                case ROLE_DIRECTOR:
                    result = directorCommands.contains(command);
                    break;
                case ROLE_ADMIN:
                    result = adminCommands.contains(command);
                    break;
                default:
                    result = guestCommands.contains(command);
                    break;
            }
        }
        return result;
    }
}
