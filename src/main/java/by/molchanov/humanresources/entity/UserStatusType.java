package by.molchanov.humanresources.entity;

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
