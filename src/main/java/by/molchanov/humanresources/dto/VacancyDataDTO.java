package by.molchanov.humanresources.dto;

import by.molchanov.humanresources.entity.JobVacancy;
import by.molchanov.humanresources.entity.Organization;

/**
 * Class {@link VacancyDataDTO} is used for transfer data about user between command and service level.
 *
 * @author MolcanovVladislav
 */
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
