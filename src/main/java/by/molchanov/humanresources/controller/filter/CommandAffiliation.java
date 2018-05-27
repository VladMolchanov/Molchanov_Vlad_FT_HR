package by.molchanov.humanresources.controller.filter;

import java.util.HashSet;

public class CommandAffiliation {
    private static final CommandAffiliation COMMAND_AFFILIATION = new CommandAffiliation();
    private HashSet<String> adminCommand;
    private HashSet<String> aspirantCommand;
    private HashSet<String> guestCommand;
    private HashSet<String> directorCommand;

    private CommandAffiliation() {
        String vacFilterCommand = "vacancy_filter";
        String contentCommand = "fill_content";
        String establishRussianLocaleCommand = "est_russian_locale";
        String establishBelorussianLocaleCommand = "est_belorussian_locale";
        String establishEnglishLocaleCommand = "est_english_locale";
        String logOutCommand = "log_out";
        guestCommand = new HashSet<>();
        adminCommand = new HashSet<>();
        aspirantCommand = new HashSet<>();
        directorCommand = new HashSet<>();
        guestCommand.add("authentication");
        guestCommand.add("user_registration");
        guestCommand.add(vacFilterCommand);
        guestCommand.add(contentCommand);
        guestCommand.add(establishRussianLocaleCommand);
        guestCommand.add(establishEnglishLocaleCommand);
        guestCommand.add(establishBelorussianLocaleCommand);
        aspirantCommand.add(vacFilterCommand);
        aspirantCommand.add(contentCommand);
        aspirantCommand.add(establishEnglishLocaleCommand);
        aspirantCommand.add(establishBelorussianLocaleCommand);
        aspirantCommand.add("org_registration");
        aspirantCommand.add(logOutCommand);
        aspirantCommand.add("request_registration");
        aspirantCommand.add(establishRussianLocaleCommand);
        directorCommand.add(vacFilterCommand);
        directorCommand.add(contentCommand);
        directorCommand.add(establishRussianLocaleCommand);
        directorCommand.add(establishBelorussianLocaleCommand);
        directorCommand.add(establishEnglishLocaleCommand);
        directorCommand.add("request_filter");
        directorCommand.add("vacancy_registration");
        directorCommand.add("close_request");
        directorCommand.add("send_request_answer");
        directorCommand.add("close_vacancy");
        directorCommand.add(logOutCommand);
        adminCommand.add(vacFilterCommand);
        adminCommand.add(contentCommand);
        adminCommand.add(establishRussianLocaleCommand);
        adminCommand.add(establishEnglishLocaleCommand);
        adminCommand.add(establishBelorussianLocaleCommand);
        adminCommand.add(logOutCommand);
        adminCommand.add("confirm_vacancy");
        adminCommand.add("delete_vacancy");
        adminCommand.add("delete_user");
        adminCommand.add("close_old_vacancy");
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
                case "aspirant":
                    result = aspirantCommand.contains(command);
                    break;
                case "director":
                    result = directorCommand.contains(command);
                    break;
                case "admin":
                    result = adminCommand.contains(command);
                    break;
                default:
                    result = guestCommand.contains(command);
                    break;
            }
        }
        return result;
    }
}
