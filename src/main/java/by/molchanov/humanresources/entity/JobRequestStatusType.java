package by.molchanov.humanresources.entity;

/**
 * Enum {@link JobRequestStatusType} contains types of job requests .
 *
 * @author Molchanov Vladislav
 */
public enum JobRequestStatusType {
    REJECTED("rejected"), APPROVED("approved") , ADDED("added");

    private String value;

    JobRequestStatusType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
