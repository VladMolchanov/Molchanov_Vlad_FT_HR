package by.molchanov.humanresources.entity;

/**
 * Enum {@link JobVacancyStatusType} contains types of job vacancies.
 *
 * @author Molchanov Vladislav
 */
public enum JobVacancyStatusType {
    OPEN("open"), CLOSE("close"), NEW("new");

    private String value;

    JobVacancyStatusType(String description) {
        this.value = description;
    }

    public String getValue() {
        return value;
    }
}
