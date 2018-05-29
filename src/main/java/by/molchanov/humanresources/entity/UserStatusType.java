package by.molchanov.humanresources.entity;

/**
 * Enum {@link JobRequestStatusType} contains types of job requests .
 *
 * @author Molchanov Vladislav
 */
public enum UserStatusType {
    ADMIN("admin"), ASPIRANT("aspirant"), WORKER("worker"), DIRECTOR("director");

    private String value;

    UserStatusType(String description) {
        this.value = description;
    }

    public String getValue() {
        return value;
    }
}
