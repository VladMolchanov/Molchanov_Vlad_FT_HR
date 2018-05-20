package by.molchanov.humanresources.entity;

/**
 * Enum {@link JobRequestStatusType} contains types of job requests .
 *
 * @author MolcanovVladislav
 */
public enum JobRequestStatusType {
    REJECTED("rejected"), NEW("new"), APPROVED("approved") , ADDED("added");

    private String value;

    JobRequestStatusType(String description) {
        this.value = description;
    }

    public String getValue() {
        return value;
    }
}
