package by.molchanov.humanresources.dto;

import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.Organization;

public class VacancyDataDTO {
    private JobVacancy jobVacancy;
    private String infoMessage;

    public JobVacancy getJobVacancy() {
        return jobVacancy;
    }

    public void setJobVacancy(JobVacancy jobVacancy) {
        this.jobVacancy = jobVacancy;
    }

    public String getInfoMessage() {
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage) {
        this.infoMessage = infoMessage;
    }
}
