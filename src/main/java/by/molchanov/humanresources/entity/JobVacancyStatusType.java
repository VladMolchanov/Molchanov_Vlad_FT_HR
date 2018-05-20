package by.molchanov.humanresources.entity;

public enum JobVacancyStatusType {
    OPEN("close"), CLOSE("open"), NEW("new");

    private String value;

    JobVacancyStatusType(String description) {
        this.value = description;
    }

    public String getValue() {
        return value;
    }
}
