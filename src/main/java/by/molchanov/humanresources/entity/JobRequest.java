package by.molchanov.humanresources.entity;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Class {@link JobRequest} is used to store information about job requests.
 *
 * @author MolchanovVladislav
 */
public class JobRequest implements Serializable {
    private int id;
    private JobVacancy jobVacancy;
    private User user;
    private String resume;
    private JobRequestStatusType status;

    public JobRequest() {
    }

    public JobRequest(JobVacancy jobVacancy, User user, String resume, JobRequestStatusType status) {
        this.jobVacancy = jobVacancy;
        this.user = user;
        this.resume = resume;
        this.status = status;
    }

    public static Comparator<JobRequest> COMPARE_BY_VAC_NAME = (o1, o2) -> {
        int res = String.CASE_INSENSITIVE_ORDER.compare(o1.getJobVacancy().getName(), o2.getJobVacancy().getName());
        if (res == 0) {
            res = o1.getJobVacancy().getName().compareTo(o2.getJobVacancy().getName());
        }
        return res;
    };

    public static Comparator<JobRequest> COMPARE_BY_USER_NAME = (o1, o2) -> {
        int res = String.CASE_INSENSITIVE_ORDER.compare(o1.getUser().getEmail(), o2.getUser().getEmail());
        if (res == 0) {
            res = o1.getUser().getEmail().compareTo(o2.getUser().getEmail());
        }
        return res;
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JobVacancy getJobVacancy() {
        return jobVacancy;
    }

    public void setJobVacancy(JobVacancy jobVacancy) {
        this.jobVacancy = jobVacancy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public JobRequestStatusType getStatus() {
        return status;
    }

    public void setStatus(JobRequestStatusType status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobRequest that = (JobRequest) o;

        if (id != that.id) return false;
        if (jobVacancy != null ? !jobVacancy.equals(that.jobVacancy) : that.jobVacancy != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (resume != null ? !resume.equals(that.resume) : that.resume != null) return false;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (jobVacancy != null ? jobVacancy.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (resume != null ? resume.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JobRequest{" +
                "id=" + id +
                ", jobVacancy=" + jobVacancy +
                ", user=" + user +
                ", resume='" + resume + '\'' +
                ", status=" + status +
                '}';
    }
}
