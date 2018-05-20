package by.molchanov.humanresources.entity;

import java.io.Serializable;

/**
 * Class {@link Organization} is used to store information about organizations.
 *
 * @author MolchanovVladislav
 */
public class Organization implements Serializable {
    private int id;
    private String name;
    private String website;
    private String description;
    private OrganizationType type;

    public Organization() {
    }

    public Organization(String name, String website, String description, OrganizationType type) {
        this.name = name;
        this.website = website;
        this.description = description;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (website != null ? !website.equals(that.website) : that.website != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }
}
