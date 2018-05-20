package by.molchanov.humanresources.entity;

/**
 * Enum {@link OrganizationType} contains types of organizations .
 *
 * @author MolcanovVladislav
 */
public enum OrganizationType {
    COMMERCIAL("commercial"), NONCOMMERCIAL("noncommercial");

    private String value;

    OrganizationType(String description) {
        this.value = description;
    }

    public String getValue() {
        return value;
    }
}
