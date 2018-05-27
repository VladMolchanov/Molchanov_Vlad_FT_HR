package by.molchanov.humanresources.entity;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Class {@link JobVacancy} is used to store information about job vacancies.
 *
 * @author MolchanovVladislav
 */
public class JobVacancy implements Serializable {
    private int id;
    private Organization organization;
    private String name;
    private String uploadDate;
    private String requirement;
    private JobVacancyStatusType status;

    public JobVacancy() {
    }

    public JobVacancy(Organization organization, String name, String requirement, JobVacancyStatusType status) {
        this.organization = organization;
        this.name = name;
        this.requirement = requirement;
        this.status = status;
    }

    public JobVacancy(int id, Organization organization, String name, String uploadDate, String requirement, JobVacancyStatusType status) {
        this.id = id;
        this.organization = organization;
        this.name = name;
        this.uploadDate = uploadDate;
        this.requirement = requirement;
        this.status = status;
    }

    public static Comparator<JobVacancy> COMPARE_BY_NAME = (o1, o2) -> {
        int res = String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName());
        if (res == 0) {
            res = o1.getName().compareTo(o2.getName());
        }
        return res;
    };

    public static Comparator<JobVacancy> COMPARE_BY_ORG_NAME = (o1, o2) -> {
        int res = String.CASE_INSENSITIVE_ORDER.compare(o1.getOrganization().getName(), o2.getOrganization().getName());
        if (res == 0) {
            res = o1.getOrganization().getName().compareTo(o2.getOrganization().getName());
        }
        return res;
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public JobVacancyStatusType getStatus() {
        return status;
    }

    public void setStatus(JobVacancyStatusType status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobVacancy that = (JobVacancy) o;

        if (id != that.id) return false;
        if (organization != null ? !organization.equals(that.organization) : that.organization != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (uploadDate != null ? !uploadDate.equals(that.uploadDate) : that.uploadDate != null) return false;
        if (requirement != null ? !requirement.equals(that.requirement) : that.requirement != null) return false;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (organization != null ? organization.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (uploadDate != null ? uploadDate.hashCode() : 0);
        result = 31 * result + (requirement != null ? requirement.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JobVacancy{" +
                "id=" + id +
                ", organization=" + organization +
                ", name='" + name + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                ", requirement='" + requirement + '\'' +
                ", status=" + status +
                '}';
    }
}
